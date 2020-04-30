package model.subject;

import model.SQLSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);

    SQLSessionFactory SQLSessionFactory = new SQLSessionFactory();

    @Override
    public void register(String name) {
        Subject newSubject = new Subject();
        newSubject.setName(name);
        save(newSubject);
        logger.info("Subject registered");
    }

    @Override
    public void save(Subject s) {
        String query = "INSERT INTO gestorDatabase.subjects (name) VALUES (?)";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, s.getName());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Subject not added");
            }
        } catch (SQLException e) {
            logger.error("Subject cannot be added", e);
        }
    }

    @Override
    public void update(Subject s) {
        String query = "UPDATE gestorDatabase.subjects SET name = ? WHERE subjectId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, s.getName());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Nothing changed");
            } else {
                logger.info(i + " subjects changed");
            }

        } catch (SQLException e) {
            logger.error("Subject cannot be changed", e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM gestorDatabase.subjects WHERE subjectId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            int i = statement.executeUpdate();

            if (i == 0) {
                logger.info("Nothing deleted");
            } else {
                logger.info("Subject deleted");
            }

        } catch (SQLException e) {
            logger.error("Subject cannot be deleted", e);
        }
    }

    @Override
    public Subject find(String id) {
        Subject subject = new Subject();

        String query = "SELECT * FROM gestorDatabase.subjects WHERE subjectId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                subject.setName(result.getString("subjectId"));
                logger.info("Subject found");
            } else {
                logger.info("Nothing found");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error searching subjects", e);
        }
        return subject;
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> list = new ArrayList<>();

        String query = "SELECT * FROM gestorDatabase.subjects";

        try (Statement statement = SQLSessionFactory.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(result.getInt("subjectId"));
                subject.setName(result.getString("name"));
                list.add(subject);
                logger.info("Subject added");
            }
        } catch (SQLException e) {
            logger.error("Error listing all subjects", e);
        }
        return list;
    }

    @Override
    public List<Subject> findAllByUserId(String userId) {
        List<Subject> list = new ArrayList<>();

        String query = "SELECT * FROM gestorDatabase.subjects " +
                "JOIN gestorDatabase.users_has_subjects " +
                "ON subjects.subjectId = users_has_subjects.subjects_subjectId " +
                "WHERE users_userId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(result.getInt("subjectId"));
                subject.setName(result.getString("name"));
                list.add(subject);
                logger.info("Subject added");
            }
        } catch (SQLException e) {
            logger.error("Error listing subjects", e);
        }
        return list;
    }
}
