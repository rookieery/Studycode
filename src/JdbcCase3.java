import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcCase3 {
    public static List<MemoGroup> queryMemoGroup(Integer id, String name) {
        List<MemoGroup> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/memo?user=root&password=a1814203288");
                 PreparedStatement statement = connection.prepareStatement("select id, name, created_time, modify_time from memo_group " +
                         "where name = ? or id = ?")){
                statement.setString(1, name);
                statement.setInt(2, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    while (resultSet.next()) {
                        MemoGroup memoGroup = new MemoGroup();
                        memoGroup.setId(resultSet.getInt("id"));
                        memoGroup.setName(resultSet.getString("name"));
                        memoGroup.setCreatedTime(resultSet.getTimestamp("created_time").toLocalDateTime());
                        memoGroup.setModifyTime(resultSet.getTimestamp("modify_time").toLocalDateTime());
                        list.add(memoGroup);
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
    public static boolean createMemoGroup(MemoGroup memoGroup) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/memo", "root", "a1814203288");
                 PreparedStatement statement = connection.prepareStatement("insert into memo_group (name, created_time) values(?,?)")

            ) {
                statement.setString(1, memoGroup.getName());
                statement.setTimestamp(2, Timestamp.valueOf(memoGroup.getCreatedTime()));

                int effect = statement.executeUpdate();
                return effect == 1;
            }
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        MemoGroup memoGroup = new MemoGroup();
        memoGroup.setName("日常心得");
        memoGroup.setCreatedTime(LocalDateTime.now());
        boolean rs  = createMemoGroup(memoGroup);
        if(rs){
            System.out.println("创建便签组成功");
        }else {
            System.out.println("创建便签组失败");
        }
    }
}
class MemoGroup2{
    private Integer id;
    private String name;
    private LocalDateTime createdTime;
    private LocalDateTime modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "MemoGroup2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdTime=" + createdTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}