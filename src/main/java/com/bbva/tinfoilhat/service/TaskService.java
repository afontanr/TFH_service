package com.bbva.tinfoilhat.service;


import com.bbva.tinfoilhat.model.Task;
import com.bbva.tinfoilhat.repository.TaskRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TaskService {


    @Inject
    TaskRepository repository;


    public List<Task> findAllByKey(String key) {
        return repository.findAllByKey(key);
    }

    public Task add(Task task) {
        return repository.add(task);
    }

    public List<Task> getUnassignedTask(){
        return repository.getTaskUnassigned();
    }

    public Task setUser(Task task) {
        return repository.setUser(task);
    }
}
