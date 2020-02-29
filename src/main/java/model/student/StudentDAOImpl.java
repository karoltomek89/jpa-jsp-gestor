package model.student;

import model.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    SessionFactory sessionFactory = new SessionFactory();


    @Override
    public void register(String name, String surname, String email, String password, int acces_accesId) {

        Student newStudent = new Student();

        newStudent.setName(name);
        newStudent.setSurname(surname);
        newStudent.setEmail(email);
        newStudent.setPassword(password);
        newStudent.setAcces(acces_accesId);

        save(newStudent);
    }

    @Override
    public void save(Student s) {
        String query = "INSERT INTO students (name, surname, email, password, acces_accesId) VALUES (?,?,?,?,?)";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            //parameterIndex zaczyna się od 1!
            statement.setString(1, s.getName());
            statement.setString(2, s.getSurname());
            statement.setString(3, s.getEmail());
            statement.setString(4, s.getPassword());
            statement.setInt(5, s.getAcces());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Student not added");
            }
        } catch (SQLException e) {
            logger.error("Student cannot be added", e);
        }
    }

    @Override
    public void update(Student s) {

        String query = "UPDATE students SET name = ?, surname =?, " +
                "email =?, password= ?" +
                " WHERE studentId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, s.getName());
            statement.setString(2, s.getSurname());
            statement.setString(3, s.getEmail());
            statement.setString(4, s.getPassword());
            statement.setInt(5, s.getStudentId());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Nothing changed");
            } else {
                logger.info(i + " students changed");
            }

        } catch (SQLException e) {
            logger.error("Student cannot be changed", e);
        }
    }

    @Override
    public void delete(String id) {

        String query = "DELETE FROM students WHERE studentId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            int i = statement.executeUpdate();

            if (i == 0) {
                logger.info("Nothing deleted");
            } else {
                logger.info("Student deleted");
            }

        } catch (SQLException e) {
            logger.error("Student cannot be deleted",e);
        }
    }

    @Override
    public Student find(String id) {

        Student student = new Student();

        String query = "SELECT * FROM students WHERE studentId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                student.setName(result.getString("studentId"));
                student.setSurname(result.getString("surname"));
                student.setEmail(result.getString("email"));
                student.setPassword(result.getString("password"));
                student.setStudentId(result.getInt("studentId"));
            } else {
                logger.info("Nothing found");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error searching students", e);
        }
        return student;
    }

    @Override
    public int login(String email, String password) {
        Student student = new Student();

        String query = "SELECT * FROM students WHERE email= ? && password= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                student.setName(result.getString("studentId"));
                student.setSurname(result.getString("surname"));
                student.setEmail(result.getString("email"));
                student.setPassword(result.getString("password"));
                student.setStudentId(result.getInt("studentId"));
            } else {
                logger.info("Error login student");
                return 0;
            }
        } catch (SQLException e) {
            logger.error("Error login student", e);
        }
        return student.getStudentId();
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();

        String query = "SELECT * FROM students";

        try (Statement statement = sessionFactory.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Student student = new Student();
                student.setStudentId(result.getInt("studentId"));
                student.setName(result.getString("name"));
                student.setSurname(result.getString("surname"));
                student.setEmail(result.getString("email"));
                student.setPassword(result.getString("password"));
                list.add(student);
            }
        } catch (SQLException e) {
            logger.error("Error listing all students", e);
        }

        return list;

    }
}
