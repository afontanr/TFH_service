package com.bbva.tinfoilhat.repository;

import com.bbva.tinfoilhat.model.Task;
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

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class TaskRepository {

    @Inject
    @MongoClientName("task")
    MongoClient mongoClient;

    public List<Task> findAllByKey(String key){
        MongoCursor<Document> cursor = getCollection().find(eq("key", key)).iterator();
        return toList(cursor);
    }

    public List<Task> getTaskByName(String name) {
        MongoCursor<Document> cursor = getCollection().find(eq("name", name)).iterator();
        return toList(cursor);
    }

    public Task add(Task task) {
        Document document = new Document().append("name", task.getName()).append("description", task.getDescription())
        .append("taskPoint", task.getTaskPoint()).append("status", task.getStatus()).append("key", task.getKey());
        getCollection().insertOne(document);
        return task;
    }

    public List<Task> getTaskUnassigned(){
        BasicDBObject searchQuery = new BasicDBObject().append("taskPoint", null);
        getCollection().deleteMany(searchQuery);
        MongoCursor<Document> cursor = getCollection().find(eq("key", "")).iterator();
        return toList(cursor);
    }

    public Task setStatus(Task task) {
        MongoCursor<Document> cursor = getCollection().find(eq("name", task.getName())).iterator();
        try {
            while (cursor.hasNext()) {
                BasicDBObject newDocument = new BasicDBObject();
                BasicDBObject searchQuery = new BasicDBObject().append("name", task.getName());
                Document document = cursor.next();
                newDocument.put("name", task.getName());
                newDocument.put("key", document.getString("key"));
                newDocument.put("description", document.getString("description"));
                newDocument.put("taskPoint", document.getInteger("taskPoint"));
                newDocument.put("status", task.getStatus());

                getCollection().replaceOne(searchQuery, newDocument);
            }
        } finally {
            cursor.close();
        }
        return task;
    }

    public Task setUser(Task task) {
        MongoCursor<Document> cursor = getCollection().find(eq("name", task.getName())).iterator();
        try {
            while (cursor.hasNext()) {
                BasicDBObject newDocument = new BasicDBObject();
                BasicDBObject searchQuery = new BasicDBObject().append("name", task.getName());
                Document document = cursor.next();
                newDocument.put("name", task.getName());
                newDocument.put("key", task.getKey());
                newDocument.put("description", document.getString("description"));
                newDocument.put("taskPoint", document.getInteger("taskPoint"));
                newDocument.put("status", document.getString("status"));

                getCollection().replaceOne(searchQuery, newDocument);
            }
        } finally {
            cursor.close();
        }
        return task;
    }

     private MongoCollection getCollection(){
        return mongoClient.getDatabase("tfhtask").getCollection("tfhtask");
    }

    private List<Task> toList(MongoCursor<Document> cursor) {
        List<Task> list = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Task task = new Task();
                task.setName(document.getString("name"));
                task.setDescription(document.getString("description"));
                task.setTaskPoint(document.getInteger("taskPoint"));
                task.setStatus(document.getString("status"));
                task.setKey(document.getString("key"));
                list.add(task);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

}