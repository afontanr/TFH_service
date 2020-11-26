package com.bbva.tinfoilhat.controller;


import com.bbva.tinfoilhat.model.Child;
import com.bbva.tinfoilhat.model.Parent;
import com.bbva.tinfoilhat.service.ParentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/parent/{name}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParentController {

    @Inject
    ParentService service;

    @GET
    @Path("/children")
    public List<Child> getChildren(@PathParam("name") String name) {
        return service.getChildren(name);
    }

    @GET
    public Parent getParent(@PathParam("name") String name) {
        return service.getParent(name);
    }
}
