package com.bbva.tinfoilhat.model;
import java.util.List;

public class Goal {

    private String name;
    private String description;
    private Double percentGoals;
    private List<UserGoal> goalUser;

    public Goal() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentGoals() {
        return percentGoals;
    }

    public void setPercentGoals(Double percentGoals) {
        this.percentGoals = percentGoals;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGoalPoint(){
        return goalPoint;
    }

    public void setGoalPoint(Double taskPoint) {
        this.goalPoint = goalPoint;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<UserGoal> getGoalUser() {
        return this.goalUser;
    }

    public List<UserGoal> setGoalUser(List<UserGoal> goalUser) {
        this.goalUser = goalUser;
    }


}