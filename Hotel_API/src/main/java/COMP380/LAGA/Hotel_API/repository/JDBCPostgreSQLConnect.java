package COMP380.LAGA.Hotel_API.repository;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCPostgreSQLConnect {
    private static Connection connection;

    public static Connection getConnection() throws URISyntaxException, SQLException {
        if (connection == null || connection.isClosed()) {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];

            connection = DriverManager.getConnection(dbUrl, username, password);
        }
        return connection;
    }
}
