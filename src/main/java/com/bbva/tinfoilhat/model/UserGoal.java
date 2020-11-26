

package com.bbva.tinfoilhat.model;
import java.util.List;

public class UserGoal {

    private String key;
    private Integer goalPoint;

    public UserGoal() {}

    public UserGoal(String key, Integer goalPoint) {
        this.key= key;
        this.goalPoint = goalPoint;
    }

    public String getkey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGoalPoint() {
        return goalPoint;
    }

    public void setGoalPoint(String goalPoint) {
        this.goalPoint = goalPoint;
    }
}