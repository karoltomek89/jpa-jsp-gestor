package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.message.Message;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBSessionFactory {

    private static Logger logger = LoggerFactory.getLogger(MongoDBSessionFactory.class);

    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    MongoClient mongoClient = new MongoClient();
    MongoDatabase database = mongoClient.getDatabase("gestorMessages")
            .withCodecRegistry(pojoCodecRegistry);

    MongoCollection<Message> collection = database.getCollection("messages", Message.class);

    public MongoCollection<Message> getCollection() {
        return collection;
    }
}
