package com.laboratory.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RankingServiceTest {

    @Autowired
    private RankingService rankingService;

    @Disabled
    @Test
    void insertScore() {
        for (int i = 0; i < 1_000_000; i++) {
            int score = (int)(Math.random() * 1_000_000);
            String memberId = "member_" + score;
            rankingService.setMemberScore(memberId, score);
        }
    }

    @Test
    void rankingsTest() {
        rankingService.getTopRank(1);

        long before = System.currentTimeMillis();
        Long memberRank = rankingService.getMemberRanking("member_100");
        long elapsed = System.currentTimeMillis() - before;

        System.out.println(elapsed + "ms");

        before = System.currentTimeMillis();
        List<String> topRankders = rankingService.getTopRank(10);
        elapsed = System.currentTimeMillis() - before;

        System.out.println(elapsed + "ms");
    }

    @Test
    void inMemorySortPerformanceTest() {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            int score = (int)(Math.random() * 1_000_000);
            integers.add(score);
        }

        long before = System.currentTimeMillis();
        Collections.sort(integers);     // NlongN
        long elapsed = System.currentTimeMillis() - before;

        System.out.println(elapsed + "ms");
    }
}