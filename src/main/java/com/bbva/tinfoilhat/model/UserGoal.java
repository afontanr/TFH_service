package com.bbva.tinfoilhat.model;

public class UserGoal {

    private String key;
    private Double goalPoint;

    public UserGoal() {}

    public UserGoal(String key, Double goalPoint) {
        this.key= key;
        this.goalPoint = goalPoint;
    }

    public String getkey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getGoalPoint() {
        return goalPoint;
    }

    public void setGoalPoint(Double goalPoint) {
        this.goalPoint = goalPoint;
    }
}