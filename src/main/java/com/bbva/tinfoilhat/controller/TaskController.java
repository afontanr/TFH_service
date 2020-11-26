package com.bbva.tinfoilhat.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.bbva.tinfoilhat.model.Task;
import com.bbva.tinfoilhat.service.TaskService;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {

    @Inject
    TaskService service;

    @GET
    @Path("/users/{key}/tasks")
    public List<Task> findAllByKey(@PathParam("key") String key){
        return service.findAllByKey(key);
    }

    @POST
    @Path("/tasks")
    public Task addTask(Task task) {
        return service.add(task);
    }

}