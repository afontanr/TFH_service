package com.bbva.tinfoilhat.controller;

import com.bbva.tinfoilhat.model.Goal;
import com.bbva.tinfoilhat.service.GoalService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

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

    @GET
    @Path("/goals/")
    public List<Goal> listGoals(@PathParam("key") String key){
        return service.listGoals().stream().filter(
                g -> g.getGoalUser().stream().anyMatch(u -> u.getkey().equals(key))
        ).collect(Collectors.toList());
    }
}