//package COMP380.LAGA.Hotel_API.repository;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class JDBCPostgreSQLConnect {
//    private static Connection connection;
//
//    public static Connection getConnection() throws URISyntaxException, SQLException {
//        if (connection == null || connection.isClosed()) {
//            URI dbUri = new URI(System.getenv("DATABASE_URL"));
//            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();
//            String username = dbUri.getUserInfo().split(":")[0];
//            String password = dbUri.getUserInfo().split(":")[1];
//
//            connection = DriverManager.getConnection(dbUrl, username, password);
//        }
//        return connection;
//    }
//}

package COMP380.LAGA.Hotel_API.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class JDBCPostgreSQLConnect {
    private static Connection connection;

    @Autowired
    private static Environment env;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String dbUrl = env.getProperty("spring.datasource.url");
            String username = env.getProperty("spring.datasource.username");
            String password = env.getProperty("spring.datasource.password");

            connection = DriverManager.getConnection(dbUrl, username, password);
        }
        return connection;
    }
}
