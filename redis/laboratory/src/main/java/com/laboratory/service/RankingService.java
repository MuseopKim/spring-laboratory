package com.laboratory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RankingService {

    private final StringRedisTemplate redisTemplate;

    private static final String RANKING_BOARD = "rankingBoard";

    public boolean setMemberScore(String memberId, int score) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add(RANKING_BOARD, memberId, score);

        return true;
    }

    public Long getMemberRanking(String memberId) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Long rank = zSetOperations.reverseRank(RANKING_BOARD, memberId);

        return rank;
    }

    public List<String> getTopRank(int limit) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<String> rangeSet = zSetOperations.reverseRange(RANKING_BOARD, 0, limit - 1);

        return new ArrayList<>(rangeSet);
    }
}
