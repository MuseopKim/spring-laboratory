package com.laboratory.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

    public String getMemberName(String memberId) {
        System.out.println("get member name from external api");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        if (memberId.equals("A")) {
            return "AAA";
        }

        if (memberId.equals("B")) {
            return "BBB";
        }

        return "";
    }

    @Cacheable(cacheNames = "memberAgeCache", key = "#memberId")
    public int getMemberAge(String memberId) {
        System.out.println("get member age from external api");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        if (memberId.equals("A")) {
            return 32;
        }

        if (memberId.equals("B")) {
            return 33;
        }

        return -1;
    }
}
