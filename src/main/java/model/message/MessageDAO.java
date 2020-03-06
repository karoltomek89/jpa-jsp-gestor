package model.message;

import java.util.List;

public interface MessageDAO {

    void insert(String from, String to, String topic, String text);

    Message find (String id);

    void update (Message m);

    void remove (String id);

    List<Message> findAll();

    List<Message> findAllOfUser(String userEmail);

}
