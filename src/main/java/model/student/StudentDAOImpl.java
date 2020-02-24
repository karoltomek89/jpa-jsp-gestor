package model.student;

import model.SessionFactory;
import model.parent.Parent;
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
    public void register(String name, String surname, String email, String password) {

        Student newStudent = new Student();

        newStudent.setName(name);
        newStudent.setSurname(surname);
        newStudent.setEmail(email);
        newStudent.setPassword(password);

        save(newStudent);
    }

    @Override
    public void save(Student s) {
        String query = "INSERT INTO students (name, surname, email, password) VALUES (?,?,?,?)";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            //parameterIndex zaczyna się od 1!
            statement.setString(1, s.getName());
            statement.setString(2, s.getSurname());
            statement.setString(3, s.getEmail());
            statement.setString(4, s.getPassword());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Student not added");
            }
        } catch (SQLException e) {
            logger.error("Student cannot be added", e);
        }
    }

    @Override
    public void update(Student p) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Student find(String id) {
        return null;
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
