package com.bbva.tinfoilhat.service;


import com.bbva.tinfoilhat.model.Child;
import com.bbva.tinfoilhat.model.Parent;
import com.bbva.tinfoilhat.repository.ParentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ParentService {

    @Inject
    ParentRepository repository;

    @Inject
    ChildService childService;


    public Parent getParent(String name) {
        List<Parent> parent = repository.findParentByName(name);
        return (parent.isEmpty()) ? null : parent.get(0);
    }

    public List<Child> getChildren(String name) {
        List<Parent> parent = repository.findParentByName(name);
        List<String> childrenName = new ArrayList<>();
        if(!parent.isEmpty()) {
            childrenName = parent.get(0).getChildren();
        }

        return childrenName.stream().map(it -> childService.findByID(it).get(0)).collect(Collectors.toList());
    }

}
