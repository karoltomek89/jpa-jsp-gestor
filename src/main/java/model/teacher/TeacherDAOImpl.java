package model.teacher;

import model.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {


    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    SessionFactory sessionFactory = new SessionFactory();

    @Override
    public void register(String name, String surname, String email, String password, int acces) {
        Teacher newTeacher = new Teacher();

        newTeacher.setName(name);
        newTeacher.setSurname(surname);
        newTeacher.setEmail(email);
        newTeacher.setPassword(password);
        newTeacher.setAcces(acces);

        save(newTeacher);
        logger.info("Teacher registered");
    }

    @Override
    public void save(Teacher t) {
        String query = "INSERT INTO teachers (name, surname, email, password, acces_accesId) VALUES (?,?,?,?,?)";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            //parameterIndex zaczyna się od 1!
            statement.setString(1, t.getName());
            statement.setString(2, t.getSurname());
            statement.setString(3, t.getEmail());
            statement.setString(4, t.getPassword());
            statement.setInt(5, t.getAcces());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Teacher not added");
            }
        } catch (SQLException e) {
            logger.error("Teacher cannot be added", e);
        }
    }

    @Override
    public void update(Teacher t) {
        String query = "UPDATE teachers SET name = ?, surname =?, mail =?, password= ? WHERE teacherId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, t.getName());
            statement.setString(2, t.getSurname());
            statement.setString(3, t.getEmail());
            statement.setString(4, t.getPassword());
            statement.setInt(5, t.getTeacherId());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Nothing changed");
            } else {
                logger.info(i + " teachers changed");
            }

        } catch (SQLException e) {
            logger.error("Teacher cannot be changed", e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM teachers WHERE teacherId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            int i = statement.executeUpdate();

            if (i == 0) {
                logger.info("Nothing deleted");
            } else {
                logger.info("Teacher deleted");
            }

        } catch (SQLException e) {
            logger.error("Teacher cannot be deleted",e);
        }
    }

    @Override
    public Teacher find(String id) {
        Teacher teacher = new Teacher();

        String query = "SELECT * FROM teachers WHERE teacherId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                teacher.setName(result.getString("teacherId"));
                teacher.setSurname(result.getString("surname"));
                teacher.setEmail(result.getString("email"));
                teacher.setPassword(result.getString("password"));
                teacher.setTeacherId(result.getInt("teacherId"));
            } else {
                logger.info("Nothing found");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error searching teachers", e);
        }
        return teacher;
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

    @Override
    public int login(String email, String password) {
        Teacher teacher = new Teacher();

        String query = "SELECT * FROM teachers WHERE email= ? && password= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                teacher.setName(result.getString("teacherId"));
                teacher.setSurname(result.getString("surname"));
                teacher.setEmail(result.getString("email"));
                teacher.setPassword(result.getString("password"));
                teacher.setTeacherId(result.getInt("teacherId"));
                teacher.setAcces(result.getInt("acces_accesId"));
            } else {
                logger.info("Error login teacher");
                return 0;
            }
        } catch (SQLException e) {
            logger.error("Error login teacher", e);
        }
        return teacher.getTeacherId();
    }
}
