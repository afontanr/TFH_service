package com.bbva.tinfoilhat.model;

public class Task {

    private String name;
    private String description;
    private Double taskPoint;
    private String status;
    private String key;

    public Task() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTaskPoint(){
        return taskPoint;
    }

    public void setTaskPoint(Double taskPoint) {
        this.taskPoint = taskPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}