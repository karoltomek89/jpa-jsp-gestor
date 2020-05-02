package model.user;

import model.SQLSessionFactory;
import model.membership.MembershipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);

    SQLSessionFactory SQLSessionFactory = new SQLSessionFactory();

    @Override
    public void register(String name, String surname, String email, String password, MembershipType membershipType) {
        User newUser = new User();

        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setMembershipId(membershipType);

        save(newUser);
        logger.info("User registered");
    }

    @Override
    public void save(User u) {
        String query =
                "INSERT INTO gestorDatabase.users (name, surname, email, password, membershipId) VALUES (?,?,?,?,?)";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPassword());
            statement.setInt(5, u.getMembershipId());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("User not added");
            }
        } catch (SQLException e) {
            logger.error("User cannot be added", e);
        }
    }

    @Override
    public void update(User u) {
        String query = "UPDATE gestorDatabase.users SET name = ?, surname =?, email =?, password= ? WHERE userId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPassword());
            statement.setInt(5, u.getUserId());
            int i = statement.executeUpdate();
            if (i == 0) {
                logger.info("Nothing changed");
            } else {
                logger.info(i + " users changed");
            }

        } catch (SQLException e) {
            logger.error("User cannot be changed", e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM gestorDatabase.users WHERE userId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            int i = statement.executeUpdate();

            if (i == 0) {
                logger.info("Nothing deleted");
            } else {
                logger.info("User deleted");
            }

        } catch (SQLException e) {
            logger.error("User cannot be deleted", e);
        }
    }


    @Override
    public User findById(String id) {
        User user = new User();

        String query = "SELECT * FROM gestorDatabase.users WHERE userId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user.setName(result.getString("name"));
                user.setSurname(result.getString("surname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setUserId(result.getInt("userId"));
                user.setMembershipId(getMembershipTypeById(result.getInt("membershipId")));
                logger.info("User found");
            } else {
                logger.info("Nothing found");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error searching users", e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        String query = "SELECT * FROM gestorDatabase.users";

        try (Statement statement = SQLSessionFactory.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                User user = new User();
                user.setUserId(result.getInt("userId"));
                user.setName(result.getString("name"));
                user.setSurname(result.getString("surname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setMembershipId(getMembershipTypeById(result.getInt("membershipId")));
                list.add(user);
            }
        } catch (SQLException e) {
            logger.error("Error listing all users", e);
        }
        logger.info("Uers found");
        return list;
    }

    @Override
    public List<User> findAllByMembershipType(MembershipType membershipType) {
        List<User> list = new ArrayList<>();
        Integer id = membershipType.getMembershipTypeId();

        String query = "SELECT * FROM gestorDatabase.users WHERE membershipId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, id.toString());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                User user = new User();
                user.setUserId(result.getInt("userId"));
                user.setName(result.getString("name"));
                user.setSurname(result.getString("surname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setMembershipId(getMembershipTypeById(result.getInt("membershipId")));
                list.add(user);
            }
        } catch (SQLException e) {
            logger.error("Error listing users by membershipId", e);
        }
        logger.info("Users found");
        return list;
    }

    @Override
    public int login(String email, String password) {
        User user = new User();

        String query = "SELECT * FROM gestorDatabase.users WHERE email= ? && password= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user.setName(result.getString("name"));
                user.setSurname(result.getString("surname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setUserId(result.getInt("userId"));
                user.setMembershipId(getMembershipTypeById(result.getInt("membershipId")));
                logger.info("User founded in database and logged");
            } else {
                logger.info("Error serching user for login");
                return user.getUserId();
            }
        } catch (SQLException e) {
            logger.error("Exception in database during searching user", e);
        }
        return user.getUserId();
    }

    @Override
    public String getEmail(String id) {
        return findById(id).getEmail();
    }

    @Override
    public int getMembershipTypeId(int userId) {

        int membershipTypeId = 0;

        String query = "SELECT membershipId FROM gestorDatabase.users WHERE userId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, Integer.toString(userId));
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                membershipTypeId = result.getInt("membershipId");
                logger.info("User membership found in database");
            } else {
                logger.info("User membership not found in database");
                return membershipTypeId;
            }
        } catch (SQLException e) {
            logger.error("Error searching user membershipId in database", e);
        }
        return membershipTypeId;
    }

    @Override
    public MembershipType getMembershipTypeById(int membershipTypeId) {
        switch (membershipTypeId) {
            case 1:
                return MembershipType.STUDENT;
            case 2:
                return MembershipType.TEACHER;
            case 3:
                return MembershipType.PARENT;
            case 4:
                return MembershipType.DIRECTOR;
            default:
                return MembershipType.UNSET;
        }
    }

    @Override
    public int getUserId(User user) {
        return user.getUserId();
    }

    @Override
    public int getUserGroupId(String userId) {

        int groupId = 0;

        String query = "SELECT groups_groupId FROM gestorDatabase.groups_has_users WHERE users_userId= ?";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                groupId = result.getInt("groups_groupId");
            } else {
                logger.info("Nothing found");
                return groupId;
            }
        } catch (SQLException e) {
            logger.error("Error searching group", e);
        }
        logger.info("User group found");
        return groupId;
    }

    @Override
    public List<User> findAllByGroup(String groupId) {
        List<User> list = new ArrayList<>();

        String query = "SELECT * FROM gestorDatabase.users " +
                "JOIN gestorDatabase.groups_has_users " +
                "ON users.userId = groups_has_users.users_userId  WHERE groups_groupId= ?;";

        try (PreparedStatement statement = SQLSessionFactory.getConnection().prepareStatement(query)) {
            statement.setString(1, groupId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                User user = new User();
                user.setUserId(result.getInt("userId"));
                user.setName(result.getString("name"));
                user.setSurname(result.getString("surname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setMembershipId(getMembershipTypeById(result.getInt("membershipId")));
                list.add(user);
            }
        } catch (SQLException e) {
            logger.error("Error listing users by groupId", e);
        }
        logger.info("Users found");
        return list;
    }
}
