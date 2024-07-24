package org.scoula.todo.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.scoula.todo.Database;
import org.scoula.todo.domain.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class TodoDAO {
    private static TodoDAO instance;
    MongoCollection<Document> collection;

    private TodoDAO() {
        collection = Database.getTodoCollection();
    }

    public static TodoDAO getInstance() {
        if (instance == null) {
            instance = new TodoDAO();
        }
        return instance;
    }

    public void add(Todo todo) {
        InsertOneResult result = collection.insertOne(todo.toDocument());
    }

    public List<Todo> getList() {
        FindIterable<Document> docs = collection.find();
        List<Todo> list = StreamSupport.stream(docs.spliterator(), false)
                .map(Todo::new)
                .toList();
        return list;
    }

    public Todo get(String id) {
        Bson query = eq("_id", new ObjectId(id));
        Document doc = collection.find(query).first();
        return doc != null ? new Todo(doc) : null;
    }

    public void update(Todo todo) {
        Bson query = eq("_id", new ObjectId(todo.getId()));
        Bson updates = Updates.combine(
                Updates.set("title", todo.getTitle()),
                Updates.set("desc", todo.getDesc()),
                Updates.set("done", todo.isDone())
        );
        collection.updateOne(query, updates);
    }

    public void delete(String id) {
        Bson query = eq("_id", new ObjectId(id));
        collection.deleteOne(query);
    }
}
