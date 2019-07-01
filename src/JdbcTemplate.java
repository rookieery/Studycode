import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JdbcTemplate extends AbstractJdbcTemplate {
    @Override
    public <T> T handlerResult(ResultSet resultSet) {
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
        return (T) sb.toString();
    }

    @Override
    public <T> T handlerResult(int effect) {
        return (T) Integer.valueOf(effect);
    }

    public static void main(String[] args) {
        AbstractJdbcTemplate template = new JdbcTemplate();
//        String rs = template.execute("select id,name,created_time,modify_time from memo_group");
//        System.out.println(rs);
        Integer rs = template.execute("update memo_group set name='好日子' where id=667");
        System.out.println(rs);
    }
}
