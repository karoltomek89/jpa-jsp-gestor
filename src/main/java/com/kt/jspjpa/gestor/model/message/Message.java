package com.kt.jspjpa.gestor.model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Message {

    private ObjectId id;
    private String from;
    private String to;
    private String topic;
    private String text;

    public Message(String from, String to, String topic, String text) {
        this.from = from;
        this.to = to;
        this.topic = topic;
        this.text = text;
    }
}
