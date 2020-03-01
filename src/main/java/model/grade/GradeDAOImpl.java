package model.grade;

import model.SessionFactory;
import model.grade.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradeDAOImpl implements GradeDAO {

    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    SessionFactory sessionFactory = new SessionFactory();

    @Override
    public void save(int value, int students_studentId, int subjects_subjectId) {
        String query = "INSERT INTO grades (value, students_studentId, subjects_subjectId) VALUES (?, ?, ?)";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setDouble(1, value);
            statement.setInt(2, students_studentId);
            statement.setInt(3, subjects_subjectId);
            int i = statement.executeUpdate();
            logger.info("Grade added");
            if (i == 0) {
                logger.info("Grade not added");
            }
        } catch (SQLException e) {
            logger.error("Grade cannot be added", e);
        }
    }

    @Override
    public void update(Grade g) {
        String query = "UPDATE grades SET value = ? WHERE gradeId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setDouble(1, g.getValue());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Nothing changed");
            } else {
                logger.info(i + " grades changed");
            }

        } catch (SQLException e) {
            logger.error("Grade cannot be changed", e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM grades WHERE gradeId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            int i = statement.executeUpdate();

            if (i == 0) {
                logger.info("Nothing deleted");
            } else {
                logger.info("Grade deleted");
            }

        } catch (SQLException e) {
            logger.error("Grade cannot be deleted",e);
        }
    }

    @Override
    public Grade find(String id) {
        Grade grade = new Grade();

        String query = "SELECT * FROM grades WHERE gradeId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                grade.setValue(result.getDouble("value"));
            } else {
                logger.info("Nothing found");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error searching grades", e);
        }
        return grade;
    }

    @Override
    public List<Grade> findAll() {
        List<Grade> list = new ArrayList<>();

        String query = "SELECT * FROM grades";

        try (Statement statement = sessionFactory.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Grade grade = new Grade();
                grade.setGradeId(result.getInt("gradeId"));
                grade.setValue(result.getDouble("value"));
                list.add(grade);
            }
        } catch (SQLException e) {
            logger.error("Error listing all grades", e);
        }

        return list;
    }

    @Override
    public List<Grade> findAllByStudentId(String studentId) {
        List<Grade> list = new ArrayList<>();

        String query = "SELECT * FROM grades WHERE students_studentId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, studentId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Grade grade = new Grade();
                grade.setGradeId(result.getInt("gradeId"));
                grade.setValue(result.getDouble("value"));
                list.add(grade);
            }
        } catch (SQLException e) {
            logger.error("Error listing all grades", e);
        }

        return list;

    }
}
