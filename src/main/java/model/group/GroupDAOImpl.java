package model.group;

import model.SQLSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImpl implements GroupDAO {

    private static Logger logger = LoggerFactory.getLogger(model.SQLSessionFactory.class);

    SQLSessionFactory SQLSessionFactory = new SQLSessionFactory();

    @Override
    public void register(String name) {
        Group newGroup = new Group();

        newGroup.setName(name);

        save(newGroup);

        logger.info("Group registered");
    }

    @Override
    public void save(Group g) {
        String query = "INSERT INTO gestorDatabase.groups (name) VALUES (?)";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, g.getName());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Group not added");
            }
        } catch (SQLException e) {
            logger.error("Group cannot be added", e);
        }
    }

    @Override
    public void update(Group g) {
        String query = "UPDATE gestorDatabase.groups SET name = ? WHERE groupId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, g.getName());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Nothing changed");
            } else {
                logger.info(i + " groups changed");
            }

        } catch (SQLException e) {
            logger.error("Group cannot be changed", e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM gestorDatabase.groups WHERE groupId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            int i = statement.executeUpdate();

            if (i == 0) {
                logger.info("Nothing deleted");
            } else {
                logger.info("Group deleted");
            }

        } catch (SQLException e) {
            logger.error("Group cannot be deleted", e);
        }
    }

    @Override
    public Group find(String id) {
        Group groups = new Group();

        String query = "SELECT * FROM gestorDatabase.groups WHERE groupId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                groups.setName(result.getString("groupId"));
            } else {
                logger.info("Nothing found");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error searching groups", e);
        }
        return groups;
    }

    @Override
    public List<Group> findAll() {
        List<Group> groupList = new ArrayList<>();

        String query = "SELECT * FROM gestorDatabase.groups";

        try (Statement statement = SQLSessionFactory.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Group group = new Group();
                group.setGroupId(result.getInt("groupId"));
                group.setName(result.getString("name"));
                groupList.add(group);
                logger.info("Group found");
            }
        } catch (SQLException e) {
            logger.error("Error listing all groups", e);
        }
        return groupList;
    }
}
