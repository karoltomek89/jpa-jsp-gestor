package model.message;

import com.mongodb.client.FindIterable;
import model.MongoDBSessionFactory;
import model.SQLSessionFactory;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MessageDAOImpl implements MessageDAO {
    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);

    MongoDBSessionFactory mongoDBSessionFactory = new MongoDBSessionFactory();

    @Override
    public void insert(String from, String to, String topic, String text) {

        Message newMessage = new Message(from, to, topic, text);

        mongoDBSessionFactory.getCollection().insertOne(newMessage);

        logger.info("Message sended");
    }

    @Override
    public List<Message> findAllOfUser(String userEmail) {

        List<Message> messageList = new ArrayList<>();

        FindIterable<Message> messagesTo = mongoDBSessionFactory.getCollection()
                .find(eq("to", userEmail));
        for (Message m : messagesTo) {
            messageList.add(m);
        }
        FindIterable<Message> messagesFrom = mongoDBSessionFactory.getCollection()
                .find(eq("from", userEmail));
        for (Message m : messagesFrom) {
            messageList.add(m);
        }
        logger.info("Messages found");
        return messageList;
    }


    @Override
    public Message find(String id) {
        return null;
    }

    @Override
    public void update(Message m) {
    }

    @Override
    public void delete(String id) {
        mongoDBSessionFactory.getCollection().findOneAndDelete(eq("_id", new ObjectId(id)));
        logger.info("Messages deleted");
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

}
