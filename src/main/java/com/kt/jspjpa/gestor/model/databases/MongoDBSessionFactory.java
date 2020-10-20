package com.kt.jspjpa.gestor.model.databases;

import com.kt.jspjpa.gestor.model.message.Message;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBSessionFactory {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBSessionFactory.class);

    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    MongoClient mongoClient = new MongoClient(System.getenv("MONGODB_HOSTNAME"));
    MongoDatabase database = mongoClient.getDatabase("gestorMessages")
            .withCodecRegistry(pojoCodecRegistry);

    MongoCollection<Message> collection = database.getCollection("messages", Message.class);

    public MongoCollection<Message> getCollection() {
        return collection;
    }
}
