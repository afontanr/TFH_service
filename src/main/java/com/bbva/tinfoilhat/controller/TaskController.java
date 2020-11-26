package com.bbva.tinfoilhat.controller;

import com.bbva.tinfoilhat.model.Task;
import com.bbva.tinfoilhat.service.TaskService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {

    @Inject
    TaskService service;



    @POST
    @Path("/tasks")
    public Task addTask(Task task) {
        return service.add(task);
    }

    @GET
    @Path("/tasks")
    public List<Task> getUnassignedTask(){
        return service.getUnassignedTask();
    }

    @PUT
    @Path("/tasks")
    public Task setUser(Task task) {
        return service.setUser(task);
    }

}