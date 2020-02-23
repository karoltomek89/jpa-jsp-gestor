package model.subject;

import model.SessionFactory;
import model.parent.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    SessionFactory sessionFactory = new SessionFactory();

    @Override
    public void save(Subject s) {

    }

    @Override
    public void update(Subject p) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Subject find(String id) {
        return null;
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> list = new ArrayList<>();

        String query = "SELECT * FROM subject";

        try (Statement statement = sessionFactory.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(result.getInt("subjectId"));
                subject.setName(result.getString("name"));
                list.add(subject);
            }
        } catch (SQLException e) {
            logger.error("Error listing all subjects", e);
        }

        return list;

    }
}
