package model.grade;

import model.SQLSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradeDAOImpl implements GradeDAO {

    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);

    SQLSessionFactory SQLSessionFactory = new SQLSessionFactory();

    @Override
    public void save(int value, int userId, int subjectsSubjectId) {
        String query = "INSERT INTO gestordatabase.grades (value, users_userId, subjects_subjectId) VALUES (?, ?, ?)";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setDouble(1, value);
            statement.setInt(2, userId);
            statement.setInt(3, subjectsSubjectId);
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
        String query = "UPDATE gestordatabase.grades SET value = ? WHERE gradeId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
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
        String query = "DELETE FROM gestordatabase.grades WHERE gradeId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            int i = statement.executeUpdate();

            if (i == 0) {
                logger.info("Nothing deleted");
            } else {
                logger.info("Grade deleted");
            }

        } catch (SQLException e) {
            logger.error("Grade cannot be deleted", e);
        }
    }

    @Override
    public Grade find(String id) {
        Grade grade = new Grade();

        String query = "SELECT * FROM gestordatabase.grades WHERE gradeId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                grade.setValue(result.getDouble("value"));
                logger.info("Grade found");
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

        String query = "SELECT * FROM gestordatabase.grades";

        try (Statement statement = SQLSessionFactory.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Grade grade = new Grade();
                grade.setGradeId(result.getInt("gradeId"));
                grade.setValue(result.getDouble("value"));
                list.add(grade);
                logger.info("Grade listed");
            }
        } catch (SQLException e) {
            logger.error("Error listing all grades", e);
        }
        return list;
    }

    @Override
    public List<Grade> findAllByStudentId(int studentId) {
        List<Grade> list = new ArrayList<>();

        String query = "SELECT * FROM gestordatabase.grades WHERE users_userId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, Integer.toString(studentId));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Grade grade = new Grade();
                grade.setGradeId(result.getInt("gradeId"));
                grade.setValue(result.getDouble("value"));
                list.add(grade);
                logger.info("Grade found");
            }
        } catch (SQLException e) {
            logger.error("Error listing all grades", e);
        }
        return list;
    }

    @Override
    public List<GradeWithSubjectName> findAllByStudentIdWithName(int studentId) {
        List<GradeWithSubjectName> list = new ArrayList<>();

        String query = "SELECT * FROM gestordatabase.grades JOIN gestordatabase.subjects ON subjects.subjectId = grades.subjects_subjectId WHERE users_userId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, Integer.toString(studentId));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                GradeWithSubjectName grade = new GradeWithSubjectName();
                grade.setGradeId(result.getInt("gradeId"));
                grade.setValue(result.getDouble("value"));
                grade.setName(result.getString("name"));
                list.add(grade);
                logger.info("Grade found");
            }
        } catch (SQLException e) {
            logger.error("Error listing all grades", e);
        }
        return list;
    }
}
