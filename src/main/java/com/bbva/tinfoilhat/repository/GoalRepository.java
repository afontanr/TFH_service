package com.bbva.tinfoilhat.repository;

import com.bbva.tinfoilhat.model.Goal;
import com.bbva.tinfoilhat.model.UserGoal;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import io.quarkus.mongodb.MongoClientName;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class GoalRepository {

    @Inject
    @MongoClientName("goal")
    MongoClient mongoClient;

    public List<Goal> getGoals(){
        MongoCursor<Document> cursor = getCollection().find().iterator();
        return toList(cursor);
    }

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("tfhgoal").getCollection("tfhgoal");
    }

    private List<Goal> toList(MongoCursor<Document> cursor) {
        List<Goal> list = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Goal goal = new Goal();
                goal.setName(document.getString("name"));
                goal.setDescription(document.getString("description"));
                goal.setGoalUser(document.getList("goalUser", UserGoal.class));
                list.add(goal);
            }
        } finally {
            cursor.close();
        }
        return list;
    }
}
