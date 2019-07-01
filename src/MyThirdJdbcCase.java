import java.sql.*;

public class MyThirdJdbcCase {
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/memo?user=root&password=a1814203288";
            String sql = "select name,created_time from memo_group";
            try (Connection connection = DriverManager.getConnection(url);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    Timestamp createtime = resultSet.getTimestamp("created_time");
                    System.out.println(name + " " + createtime);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
