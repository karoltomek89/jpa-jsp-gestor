package model.parent;

import model.SessionFactory;
import model.grade.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParentDAOImpl implements ParentDAO {

    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    SessionFactory sessionFactory = new SessionFactory();

    @Override
    public void save(Parent p) {

    }

    @Override
    public void update(Parent p) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Parent find(String id) {
        return null;
    }

    @Override
    public List<Parent> findAll() {
        List<Parent> list = new ArrayList<>();

        String query = "SELECT * FROM parents";

        try (Statement statement = sessionFactory.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Parent parent = new Parent();
                parent.setParentId(result.getInt("parentId"));
                parent.setName(result.getString("name"));
                parent.setSurname(result.getString("surname"));
                parent.setEmail(result.getString("email"));
                parent.setPassword(result.getString("password"));
                list.add(parent);
            }
        } catch (SQLException e) {
            logger.error("Error listing all parents", e);
        }

        return list;
    }
}
