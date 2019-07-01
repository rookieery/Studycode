import java.sql.*;

public class MyFirstJdbcCase {
    public static void main(String[] args) {
        /*
        1. 加载数据库的JDBC驱动（JDBC API）
        2. 创建连接
        3. 创建命令
        4. 准备SQL语句
        5. 执行SQL
        6. 处理结果
        7. 关闭结果
        8. 关闭命令
        9. 关闭连接
     */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/memo?user=root&password=a1814203288";
            try {
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                String sql = "select id,name,created_time,modify_time from memo_group";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Timestamp createTime = resultSet.getTimestamp("created_time");
                    Timestamp modifyTime = resultSet.getTimestamp("modify_time");
                    System.out.println(id + " " + name + " " + createTime + " " + modifyTime);
                }
                resultSet.close();
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
