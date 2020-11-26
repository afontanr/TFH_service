package com.bbva.tinfoilhat.service;

import com.bbva.tinfoilhat.model.Child;
import com.bbva.tinfoilhat.repository.ChildrenRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ChildService {

    @Inject
    ChildrenRepository repository;

    public List<Child> findAll() {
        return repository.findAll();
    }

    public Child findByID(String id) {
        return repository.findById(id);
    }

    public void addTotalPoint(String id, Integer points) {
        repository.addTotalPoint(id, points);
    }

    public void setBotID(String id, String botID) {
        repository.setBotID(id, botID);
    }

}
