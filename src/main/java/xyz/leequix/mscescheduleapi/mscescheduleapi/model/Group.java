package xyz.leequix.mscescheduleapi.mscescheduleapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Document
public class Group implements Serializable {
    @Id
    private String id;

    private String number;

    public Group(String number) {
        this.id = id;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
