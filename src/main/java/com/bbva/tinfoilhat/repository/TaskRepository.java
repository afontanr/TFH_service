package com.bbva.tinfoilhat.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.bson.Document;

import com.bbva.tinfoilhat.model.Task;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class TaskRepository {

    @Inject MongoClient mongoClient;

    public List<Task> findAllByKey(String key){
        List<Task> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find(eq("key", key)).iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Task task = new Task();
                task.setName(document.getString("name"));
                task.setDescription(document.getString("description"));
                task.setTaskPoint(document.getDouble("taskPoint"));
                task.setStatus(document.getString("status"));
                task.setKey(document.getString("key"));
                list.add(task);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public Task add(Task task) {
        Document document = new Document().append("name", task.getName()).append("description", task.getDescription())
        .append("taskPoint", task.getTaskPoint()).append("status", task.getStatus()).append("key", task.getKey());
        getCollection().insertOne(document);
        return task;
    }

     private MongoCollection getCollection(){
        return mongoClient.getDatabase("tfhtask").getCollection("tfhtask");
    }

}