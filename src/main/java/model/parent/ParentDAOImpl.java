package model.parent;

import model.SessionFactory;
import model.grade.Grade;
import model.parent.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParentDAOImpl implements ParentDAO {

    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    SessionFactory sessionFactory = new SessionFactory();

    
    @Override
    public void register(String name, String surname, String email, String password, int acces) {

        Parent newParent = new Parent();

        newParent.setName(name);
        newParent.setSurname(surname);
        newParent.setEmail(email);
        newParent.setPassword(password);
        newParent.setAcces(acces);

        save(newParent);
        
    }

    @Override
    public void save(Parent p) {

        String query = "INSERT INTO parents (name, surname, email, password) VALUES (?,?,?,?)";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            //parameterIndex zaczyna się od 1!
            statement.setString(1, p.getName());
            statement.setString(2, p.getSurname());
            statement.setString(3, p.getEmail());
            statement.setString(4, p.getPassword());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Parent not added");
            }
        } catch (SQLException e) {
            logger.error("Parent cannot be added", e);
        }

    }

    @Override
    public void update(Parent p) {

        String query = "UPDATE parents SET name = ?, surname =?, email =?, password= ? WHERE parentId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, p.getName());
            statement.setString(2, p.getSurname());
            statement.setString(3, p.getEmail());
            statement.setString(4, p.getPassword());
            statement.setInt(5, p.getParentId());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Nothing changed");
            } else {
                logger.info(i + " parents changed");
            }

        } catch (SQLException e) {
            logger.error("Parent cannot be changed", e);
        }

    }

    @Override
    public void delete(String id) {

        String query = "DELETE FROM parents WHERE parentId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            int i = statement.executeUpdate();

            if (i == 0) {
                logger.info("Nothing deleted");
            } else {
                logger.info("Parent deleted");
            }

        } catch (SQLException e) {
            logger.error("Parent cannot be deleted",e);
        }

    }

    @Override
    public Parent find(String id) {
        Parent parent = new Parent();

        String query = "SELECT * FROM parents WHERE parentId= ?";

        try (PreparedStatement statement = sessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                parent.setName(result.getString("parentId"));
                parent.setSurname(result.getString("surname"));
                parent.setEmail(result.getString("email"));
                parent.setPassword(result.getString("password"));
                parent.setParentId(result.getInt("parentId"));
            } else {
                logger.info("Nothing found");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error searching parents", e);
        }
        return parent;
    }

    @Override
    public List<Parent> findAll() {
        List<Parent> list = new ArrayList<>();

        String query = "SELECT * FROM parents";

        try (Statement statement = sessionFactory.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Parent parent = new Parent();
                parent.setParentId(result.getInt("parentId"));
                parent.setName(result.getString("name"));
                parent.setSurname(result.getString("surname"));
                parent.setEmail(result.getString("email"));
                parent.setPassword(result.getString("password"));
                list.add(parent);
            }
        } catch (SQLException e) {
            logger.error("Error listing all parents", e);
        }

        return list;
    }
}
