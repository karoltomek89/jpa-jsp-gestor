package model.teacher;

import model.SessionFactory;
import model.parent.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {


    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    SessionFactory sessionFactory = new SessionFactory();

    @Override
    public void save(Teacher t) {

    }

    @Override
    public void update(Teacher t) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Teacher find(String id) {
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> list = new ArrayList<>();

        String query = "SELECT * FROM teachers";

        try (Statement statement = sessionFactory.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(result.getInt("teacherId"));
                teacher.setName(result.getString("name"));
                teacher.setSurname(result.getString("surname"));
                teacher.setEmail(result.getString("email"));
                teacher.setPassword(result.getString("password"));
                list.add(teacher);
            }
        } catch (SQLException e) {
            logger.error("Error listing all teachers", e);
        }

        return list;

    }
}
