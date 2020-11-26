package com.bbva.tinfoilhat.controller;

import com.bbva.tinfoilhat.model.Goal;
import com.bbva.tinfoilhat.service.GoalService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GoalController {

    @Inject
    GoalService service;

    @GET
    @Path("/goals")
    public List<Goal> listGoals(){
        return service.listGoals();
    }

}