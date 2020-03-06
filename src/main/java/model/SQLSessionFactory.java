package model;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SQLSessionFactory {

    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);

    private MysqlDataSource dataSource;

    public SQLSessionFactory(String filename) {

        try {
            dataSource = new MysqlDataSource();
            dataSource.setServerName(getDataBaseProperties(filename).getProperty("gestor.jdbc.db.server"));
            dataSource.setDatabaseName(getDataBaseProperties(filename).getProperty("gestor.jdbc.db.name"));
            dataSource.setUser(getDataBaseProperties(filename).getProperty("gestor.jdbc.db.user"));
            dataSource.setPassword(getDataBaseProperties(filename).getProperty("gestor.jdbc.db.password"));
            dataSource.setPort(Integer.parseInt(getDataBaseProperties(filename).getProperty("gestor.jdbc.db.port")));
            dataSource.setServerTimezone("Europe/Warsaw");
            dataSource.setUseSSL(false);
            dataSource.setCharacterEncoding("UTF-8");
            logger.info("DataSource created!");
        } catch (SQLException e) {
            logger.error("Error during creating MysqlDataSource", e);
            return;
        }

    }

    public SQLSessionFactory() {

        this("/database.properties");

    }

    public static void main(String[] args) throws SQLException {

        SQLSessionFactory SQLSessionFactory = new SQLSessionFactory();
        SQLSessionFactory.getConnection();

    }

    public Connection getConnection() throws SQLException {

        Connection connection = dataSource.getConnection();
        logger.info("Connected database successfully!");
        logger.info("Connection = " + connection);
        logger.info("Database name = " + connection.getCatalog());
        return connection;


//          obsługa wyjątku w ciele metody, bez wyrzucania go na zewnątrz
//        try (Connection connection = dataSource.getConnection()) {
//            logger.info("Connected database successfully!");
//
//            /**
//             *Pobieramy informacje o bazie danych i połączeniu
//             */
//            logger.info("Connection = " + connection);
//            logger.info("Database name = " + connection.getCatalog());
//            return connection;
//
//        } catch (SQLException e) {
//            /**
//             *Obsługa wyjątków które mogą pojawić się w trakcie pracy z bazą danych
//             */
//            logger.error("Connection error", e);
//            return null;
//        }


    }

    private Properties getDataBaseProperties(String filename) {
        Properties properties = new Properties();
        try {
            InputStream propertiesStream = SQLSessionFactory.class.getResourceAsStream(filename);
            if (propertiesStream == null) {
                throw new IllegalArgumentException("Can't find file: " + filename);
            }
            properties.load(propertiesStream);
        } catch (IOException e) {
            logger.error("Error during fetching properties for database", e);
            return null;
        }

        return properties;
    }


}
