package com.laboratory.controller;

import com.laboratory.model.MemberProfile;
import com.laboratory.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/{memberId}/profile")
    public MemberProfile memberProfile(@PathVariable String memberId) {
        return memberService.getMemberProfile(memberId);
    }
}
