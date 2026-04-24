package com.neerajteja.quiz_leaderboard.service;

import com.neerajteja.quiz_leaderboard.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class QuizService {

    private final String BASE_URL = "https://devapigw.vidalhealthtpa.com/srm-quiz-task";
    private final String REG_NO = "RA2311003010319";

    public ApiResponse fetchPoll(int poll) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "/quiz/messages?regNo=" + REG_NO + "&poll=" + poll;

        return restTemplate.getForObject(url, ApiResponse.class);
    }

    public List<ApiResponse> collectPolls() throws InterruptedException {
        List<ApiResponse> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            System.out.println("Calling poll: " + i);

            list.add(fetchPoll(i));

            Thread.sleep(5000); // IMPORTANT
        }

        return list;
    }

    public List<Event> removeDuplicates(List<ApiResponse> responses) {
        Set<String> seen = new HashSet<>();
        List<Event> unique = new ArrayList<>();

        for (ApiResponse res : responses) {
            for (Event e : res.getEvents()) {

                String key = e.getRoundId() + "-" + e.getParticipant();

                if (!seen.contains(key)) {
                    seen.add(key);
                    unique.add(e);
                }
            }
        }
        return unique;
    }

    public Map<String, Integer> aggregate(List<Event> events) {
        Map<String, Integer> map = new HashMap<>();

        for (Event e : events) {
            map.put(e.getParticipant(),
                    map.getOrDefault(e.getParticipant(), 0) + e.getScore());
        }

        return map;
    }

    public List<LeaderboardEntry> leaderboard(Map<String, Integer> map) {
        List<LeaderboardEntry> list = new ArrayList<>();

        for (String p : map.keySet()) {
            list.add(new LeaderboardEntry(p, map.get(p)));
        }

        list.sort((a, b) -> b.getTotalScore() - a.getTotalScore());

        return list;
    }

    public int total(Map<String, Integer> map) {
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }
}