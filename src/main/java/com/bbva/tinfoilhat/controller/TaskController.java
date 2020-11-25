package com.bbva.tinfoilhat.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bbva.tinfoilhat.model.Task;
import com.bbva.tinfoilhat.service.TaskService;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/users/{key}/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {

    @Inject
    TaskService service;

    @GET
    public List<Task> findAllByKey(@PathParam("key") String key){
        return service.findAllByKey(key);
    }
}