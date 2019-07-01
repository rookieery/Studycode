import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySecondJdbcCase {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/memo?user=root&password=a1814203288";
            try {
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                String sql = "insert into memo_group (name,created_time) values ('King','2019-06-30 17:34')";
                int effect = statement.executeUpdate(sql);
                System.out.println(effect);
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
