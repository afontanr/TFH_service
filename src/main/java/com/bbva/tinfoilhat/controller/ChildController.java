package com.bbva.tinfoilhat.controller;

import com.bbva.tinfoilhat.model.Child;
import com.bbva.tinfoilhat.model.Task;
import com.bbva.tinfoilhat.service.ChildService;
import com.bbva.tinfoilhat.service.TaskService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import io.smallrye.mutiny.Uni;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response.Status;

@Path("/children")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChildController {

    @Inject
    ChildService service;

    @Inject
    TaskService taskService;


    @GET
    public List<Child> getChildByChatbotId(@QueryParam("chatbotId") String chatbotId){
        return service.getChildByChatbotID(chatbotId);
    }

    @GET
    @Path("/{id}")
    public List<Child> getChild(@PathParam("id") String key){
        return service.findByID(key);
    }

    @PUT
    @Path("/{id}")
    public void addPoints(@PathParam("id") String key, @QueryParam("points") Integer points){
        service.addTotalPoint(key, points);
    }

    @PUT
    @Path("/{id}/chatbot")
    public void setBotID(@PathParam("id") String key, @QueryParam("chatbotid") String chatBotID){
        service.setBotID(key, chatBotID);
    }

    @GET
    @Path("/{id}/tasks")
    public List<Task> findAllByKey(@PathParam("id") String key){
        return taskService.findAllByKey(key);
    }
}
