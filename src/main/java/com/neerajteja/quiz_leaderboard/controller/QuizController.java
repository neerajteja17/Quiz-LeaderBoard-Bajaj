package com.neerajteja.quiz_leaderboard.controller;

import com.neerajteja.quiz_leaderboard.model.*;
import com.neerajteja.quiz_leaderboard.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class QuizController {

    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping("/run")
    public String run() throws InterruptedException {

        System.out.println("STARTING PROCESS...");

        List<ApiResponse> responses = service.collectPolls();

        List<Event> unique = service.removeDuplicates(responses);

        Map<String, Integer> scores = service.aggregate(unique);

        List<LeaderboardEntry> leaderboard = service.leaderboard(scores);

        int total = service.total(scores);

        System.out.println("Leaderboard:");
        for (LeaderboardEntry e : leaderboard) {
            System.out.println(e.getParticipant() + " -> " + e.getTotalScore());
        }

        System.out.println("TOTAL SCORE: " + total);

        return "Done! Check IntelliJ Console";
    }
}