package com.bbva.tinfoilhat.model;

import java.util.List;

public class Parent {

    private String name;
    private String surname;
    private List<String> children;

    public Parent() {
    }

    public Parent(String name, String surname, List<String> children) {
        this.name = name;
        this.surname = surname;
        this.children = children;
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

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }
}
