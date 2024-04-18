package ContactProject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataUtil {

    public static void createTable(){
        try {
            Connection connection = DataUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE if not exists contact(" +
                    "id serial primary key," +
                    "name varchar(25) not null, " +
                    "surname varchar (25) not null, " +
                    "phone varchar(12) not null unique)";
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "123");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }
}
