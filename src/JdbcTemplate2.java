import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JdbcTemplate2 {
    public static void main(String[] args) {
        GenericityJdbcTemple temple = new GenericityJdbcTemple();
        String rs = temple.execute("select id,name,created_time, modify_time from memo_group",
                new GenericityJdbcTemple.Handler<ResultSet, String>() {
                    @Override
                    public String handler(ResultSet resultSet) {
                        StringBuilder sb = new StringBuilder();
                        try {
                            while (resultSet.next()) {
                                int id = resultSet.getInt("id");
                                String name = resultSet.getString("name");
                                Timestamp createTime = resultSet.getTimestamp("created_time");
                                Timestamp modifyTime = resultSet.getTimestamp("modify_time");
                                sb.append(id).append(" ").append(name).append(" ").append(createTime).append(" ").append(modifyTime);
                                sb.append("\n");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return sb.toString();
                    }
                });
        System.out.println(rs);
    }
}
