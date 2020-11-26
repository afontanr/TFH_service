package com.bbva.tinfoilhat.service;


import com.bbva.tinfoilhat.model.Goal;
import com.bbva.tinfoilhat.repository.GoalRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GoalService {


    @Inject
    GoalRepository repository;

    public List<Goal> listGoals(){
        return repository.getGoals();
    }
}
