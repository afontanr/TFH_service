package com.bbva.tinfoilhat.repository;

import com.bbva.tinfoilhat.model.Parent;
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
public class ParentRepository {

    @Inject
    @MongoClientName("parent")
    MongoClient mongoClient;

    public List<Parent> findParentByName(String name) {
        MongoCursor<Document> cursor = getCollection().find(eq("name", name)).iterator();
        return toList(cursor);
    }


    private MongoCollection getCollection(){
        return mongoClient.getDatabase("tfhparent").getCollection("tfhparent");
    }

    private List<Parent> toList(MongoCursor<Document> cursor){
        List<Parent> list = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Parent parent = new Parent();
                parent.setName(document.getString("name"));
                parent.setSurname(document.getString("surname"));
                parent.setChildren(document.getList("children", String.class));
                list.add(parent);
            }
        } finally {
            cursor.close();
        }
        return list;
    }
}
