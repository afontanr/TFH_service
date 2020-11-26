package com.bbva.tinfoilhat.repository;

import com.bbva.tinfoilhat.model.Child;
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
public class ChildrenRepository {

    @Inject
    @MongoClientName("children")
    MongoClient mongoClient;

    public List<Child> findAll(){
        MongoCursor<Document> cursor = getCollection().find().iterator();
        return toList(cursor);
    }

    public Child findById(String id) {
        MongoCursor<Document> cursor = getCollection().find(eq("id", id)).iterator();
        return toList(cursor).get(0);
    }

    public Child add(Child child) {
        Document document = new Document()
                .append("id", child.getId()).append("name", child.getName())
                .append("surname", child.getSurname()).append("age", child.getAge())
                .append("chatbotid", child.getChatBotID()).append("totalPoint", child.getTotalPoint());
        getCollection().insertOne(document);
        return child;
    }

    public void addTotalPoint(String id, Integer points){
        MongoCursor<Document> cursor = getCollection().find(eq("id", id)).iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                document.put("totalPoint", points);
                getCollection().insertOne(document);
            }
        } finally {
            cursor.close();
        }
    }

    public void setBotID(String id, String botID) {
        MongoCursor<Document> cursor = getCollection().find(eq("id", id)).iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                document.put("chatbotid", botID);
                getCollection().insertOne(document);
            }
        } finally {
            cursor.close();
        }
    }

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("tfhchildren").getCollection("tfhchildren");
    }

    private List<Child> toList(MongoCursor<Document> cursor){
        List<Child> list = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Child child = new Child();
                child.setId(document.getString("id"));
                child.setName(document.getString("name"));
                child.setSurname(document.getString("surname"));
                child.setAge(document.getDouble("status").intValue());
                child.setChatBotID(document.getString("chatbotid"));
                child.setTotalPoint(document.getDouble("totalPoint").intValue());
                list.add(child);
            }
        } finally {
            cursor.close();
        }
        return list;
    }
}
