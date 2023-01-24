package com.laboratory.controller;

import com.laboratory.service.RankingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RankingBoardController {

    private final RankingService rankingService;

    /**
     * Browser에서 test 하기 위해 Get Method / query string 사용
     */
    @GetMapping("/set-score")
    public boolean setScore(
            @RequestParam String memberId,
            @RequestParam int score
    ) {
        return rankingService.setMemberScore(memberId, score);
    }

    @GetMapping("/rankings")
    public Long getMemberRank(@RequestParam String memberId) {
        return rankingService.getMemberRanking(memberId);
    }

    @GetMapping("top-rankings")
    public List<String> getTopRank() {
        return rankingService.getTopRank(3);
    }
}
