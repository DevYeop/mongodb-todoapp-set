package org.scoula.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    String id;
    String title;
    String desc;
    boolean done;

    public Todo(Document doc) {
        id = doc.getObjectId("_id").toString();
        title = doc.getString("title");
        desc = doc.getString("desc");
        done = doc.getBoolean("done");
    }

    public Document toDocument() {
        Document doc = new Document();
        if (id != null) {
            doc.append("_id", new ObjectId(id));
        }
        doc.append("title", title);
        doc.append("desc", desc);
        doc.append("done", done);
        return doc;
    }

}
