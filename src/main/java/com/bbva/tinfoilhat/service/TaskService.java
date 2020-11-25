package com.bbva.tinfoilhat.service;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.bbva.tinfoilhat.model.Task;
import com.bbva.tinfoilhat.repository.TaskRepository;

@ApplicationScoped
public class TaskService {


    @Inject
    TaskRepository repository;


    public List<Task> findAllByKey(String key) {
        return repository.findAllByKey(key);
    }

}
