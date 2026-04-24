package com.neerajteja.quiz_leaderboard.model;

import java.util.List;

public class ApiResponse {
    private String regNo;
    private String setId;
    private int pollIndex;
    private List<Event> events;

    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
}