package com.bbva.tinfoilhat.controller;

import com.bbva.tinfoilhat.model.Task;
import com.bbva.tinfoilhat.service.TaskService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {

    @Inject
    TaskService service;
    private final List<String> statuses = Arrays.asList("NUEVO", "EN CURSO", "FINALIZADO");


    @POST
    public Task addTask(Task task) {
        return service.add(task);
    }

    @PUT
    @Path("/tasks/status")
    public void setStatus(Task task) {
        if(task.getStatus().equals("FINALIZADO")){
            service.statusFinal(task);
        } else {
            service.setStatus(task);
        }

    }

    @GET
    public List<Task> getUnassignedTask(){
        return service.getUnassignedTask();
    }

    @PUT
    public Task setUser(Task task) {
        return service.setUser(task);
    }

}