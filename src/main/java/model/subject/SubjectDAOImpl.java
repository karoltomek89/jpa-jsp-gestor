package model.subject;

import model.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    SessionFactory sessionFactory = new SessionFactory();

    @Override
    public void register(String name) {
        Subject newSubject = new Subject();

        newSubject.setName(name);

        save(newSubject);
    }

    @Override
    public void save(Subject s) {
        String query = "INSERT INTO subjects (name) VALUES (?)";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
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
        String query = "UPDATE subjects SET name = ? WHERE subjectId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
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
        String query = "DELETE FROM subjects WHERE subjectId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            int i = statement.executeUpdate();

            if (i == 0) {
                logger.info("Nothing deleted");
            } else {
                logger.info("Subject deleted");
            }

        } catch (SQLException e) {
            logger.error("Subject cannot be deleted",e);
        }
    }

    @Override
    public Subject find(String id) {
        Subject subject = new Subject();

        String query = "SELECT * FROM subjects WHERE subjectId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                subject.setName(result.getString("subjectId"));
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


    @Override
    public List<Subject> findAllByTeacherId(String teacherId) {
        List<Subject> list = new ArrayList<>();

        String query = "SELECT * FROM subjects JOIN teachers_has_subjects ON subjects.subjectId = teachers_has_subjects.subjects_subjectId WHERE teachers_teacherId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, teacherId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(result.getInt("subjectId"));
                subject.setName(result.getString("name"));
                list.add(subject);
            }
        } catch (SQLException e) {
            logger.error("Error listing subjects", e);
        }

        return list;

    }
}
