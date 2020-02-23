package model.grade;

import model.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradeDAOImpl implements GradeDAO {

    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    SessionFactory sessionFactory = new SessionFactory();

    @Override
    public void save(Grade g) {

    }

    @Override
    public void update(Grade g) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Grade find(String id) {
        return null;
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
}
