package com.laboratory.service;

import com.laboratory.model.MemberProfile;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final ExternalApiService externalApiService;
    private final StringRedisTemplate redisTemplate;

    public MemberProfile getMemberProfile(String memberId) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String cachedMemberName = ops.get("memberName:" + memberId);

        // Cache-Aside
        String memberName = null;
        if (Objects.nonNull(cachedMemberName)) {
            memberName = cachedMemberName;
        } else {
            memberName = externalApiService.getMemberName(memberId);
            ops.set("memberName:" + memberId, memberName, 5, TimeUnit.SECONDS);
        }

        int memberAge = externalApiService.getMemberAge(memberId);

        return new MemberProfile(memberName, memberAge);
    }
}
