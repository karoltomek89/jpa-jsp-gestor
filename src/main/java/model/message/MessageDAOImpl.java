package model.message;

import com.mongodb.client.FindIterable;
import model.MongoDBSessionFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MessageDAOImpl implements MessageDAO {

    MongoDBSessionFactory mongoDBSessionFactory = new MongoDBSessionFactory();


    public static void main(String[] args) {

        Message newMessage1 = new Message("adamadamski@xyz.pl", "ziutek@yyy.pl", "topic1", "text1");
        Message newMessage2 = new Message("bartoszbartowski@xxx.pl", "ziutek@yyy.pl", "topic1", "text2");
        Message newMessage3 = new Message("cecyliancecylowski@zz.pl", "ziutek@yyy.pl", "topic1", "text3");

        Message newMessage4 = new Message("adamadamski@xyz.pl", "ziutka@xxx.pl", "topic1", "text4");
        Message newMessage5 = new Message("bartoszbartowski@xxx.pl", "ziutka@xxx.pl", "topic1", "text5");
        Message newMessage6 = new Message("cecyliancecylowski@zz.pl", "ziutka@xxx.pl", "topic1", "text6");

        MongoDBSessionFactory mongoDBSessionFactory = new MongoDBSessionFactory();
        mongoDBSessionFactory.getCollection().insertOne(newMessage1);
        mongoDBSessionFactory.getCollection().insertOne(newMessage2);
        mongoDBSessionFactory.getCollection().insertOne(newMessage3);
        mongoDBSessionFactory.getCollection().insertOne(newMessage4);
        mongoDBSessionFactory.getCollection().insertOne(newMessage5);
        mongoDBSessionFactory.getCollection().insertOne(newMessage6);

    }

    @Override
    public void insert(String from, String to, String topic, String text) {


        Message newMessage = new Message(from, to, topic, text);

        mongoDBSessionFactory.getCollection().insertOne(newMessage);

    }

    @Override
    public List<Message> findAllOfUser(String userEmail) {


        List<Message> messageList = new ArrayList<>();

        FindIterable<Message> messagesTo = mongoDBSessionFactory.getCollection().find(eq("to", userEmail));
        for (Message m : messagesTo) {
            messageList.add(m);
        }
        FindIterable<Message> messagesFrom = mongoDBSessionFactory.getCollection().find(eq("from", userEmail));
        for (Message m : messagesFrom) {
            messageList.add(m);
        }
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
    public void remove(String id) {

    }

    @Override
    public List<Message> findAll() {
        return null;
    }


}
