package com.bbva.tinfoilhat.repository;

import com.bbva.tinfoilhat.model.Goal;
import com.bbva.tinfoilhat.model.Task;
import com.bbva.tinfoilhat.model.UserGoal;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import io.quarkus.mongodb.MongoClientName;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;


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
                goal.setPercentGoals(document.getDouble("percentGoals"));
                goal.setDescription(document.getString("description"));
                goal.setGoalUser(document.getList("goalUser", Document.class).stream()
                .map(p -> new UserGoal(p.getString("key"), p.getDouble("goalPoint")))
                .collect(Collectors.toList()));
                list.add(goal);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public List<Goal> setChildGoal(String key, Integer totalPoint) {
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                String name = document.getString("name");
                BasicDBObject newDocument = new BasicDBObject();
                BasicDBObject searchQuery = new BasicDBObject().append("name", name);

                Double percentage = document.getDouble("percentGoals");
                List<Document> documents = new ArrayList<>();
                List<UserGoal> newUserGoal = document.getList("goalUser", Document.class).stream()
                        .map(p -> new UserGoal(p.getString("key"), p.getDouble("goalPoint"))).collect(Collectors.toList());
                newUserGoal.stream().filter(it -> it.getkey().equals(key)).forEach(x -> x.setGoalPoint(percentage * totalPoint));

                for(UserGoal userGoal : newUserGoal){
                    Document doc = new Document();
                    doc.put("key", userGoal.getkey());
                    doc.put("goalPoint", userGoal.getGoalPoint());
                    documents.add(doc);
                }

                newDocument.put("name", document.getString("name"));
                newDocument.put("description", document.getString("description"));
                newDocument.put("percentGoals", document.getDouble("percentGoals"));
                newDocument.put("goalUser", documents);

                getCollection().replaceOne(searchQuery, newDocument);
            }
        } finally {
            cursor.close();
        }
        return getGoals();
    }
}
