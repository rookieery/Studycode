import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
///??????
public class JdbcCase2 {
    public static void main(String[] args) {
        List<Map<String, Object>> data = queryMemoGroupByName("'Java','C++'");
        for (Map<String, Object> item : data) {
            System.out.println(item);
        }
    }

    public static List<Map<String, Object>> queryMemoGroupByName(String groupName) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/memo?user=root&password=a1814203288";
            String sql = "select id,name,created_time,modify_time from memo_group where name in (?) ";
            try (Connection connection = DriverManager.getConnection(url);
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                //参数赋值，下标从1开始（切记）
                statement.setString(1, groupName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        Timestamp createdTime = resultSet.getTimestamp("created_time");
                        Timestamp modifyTime = resultSet.getTimestamp("modify_time");
                        Map<String, Object> row = new HashMap<>();
                        row.put("id", id);
                        row.put("name", name);
                        row.put("created_time", createdTime);
                        row.put("modify_time", modifyTime);
                        list.add(row);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
