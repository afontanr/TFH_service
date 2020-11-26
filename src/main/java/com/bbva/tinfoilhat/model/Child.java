package com.bbva.tinfoilhat.model;

public class Child {

    private String id;
    private String name;
    private String surname;
    private Integer age;
    private String chatBotID;
    private Integer totalPoint;

    public Child() {
    }

    public Child(String id, String name, String surname, Integer age, String chatBotID, Integer totalPoint) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.chatBotID = chatBotID;
        this.totalPoint = totalPoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getChatBotID() {
        return chatBotID;
    }

    public void setChatBotID(String chatBotID) {
        this.chatBotID = chatBotID;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }
}
