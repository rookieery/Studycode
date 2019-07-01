import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;

public class JdbcCase4 {
    public static boolean createMemoInfo(MemoGroup memoGroup, MemoInfo memoInfo) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/memo";
            String user = "root";
            String password = "a1814203288";
            connection = DriverManager.getConnection(url, user, password);
            //事务控制
            connection.setAutoCommit(false);

            //一组DML要么成功，要么失败
            String memoGroupInsertSql = "insert into memo_group(id,name,created_time) values (?,?,?)";
            String memoInfoInsertSql = "insert into memo_info(id,group_id,title,content,created_time) values (?,?,?,?,?)";
            int effectA = -1;
            int effectB = -1;
            try (PreparedStatement statement = connection.prepareStatement(memoGroupInsertSql)) {
                statement.setInt(1, memoGroup.getId());
                statement.setString(2, memoGroup.getName());
                statement.setTimestamp(3, Timestamp.valueOf(memoGroup.getCreatedTime()));
                effectA = statement.executeUpdate();
            }

            try (PreparedStatement statement = connection.prepareStatement(memoInfoInsertSql)) {
                statement.setInt(1, memoInfo.getId());
                statement.setInt(2, memoInfo.getGroupId());
                statement.setString(3, memoInfo.getTitle());
                statement.setString(4, memoInfo.getContent());
                statement.setTimestamp(5, Timestamp.valueOf(memoInfo.getCreatedTime()));
                effectB = statement.executeUpdate();
            }

            //正常执行完成之后，根据结果确定是否提交或者回滚
            if (effectA == 1 && effectB == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //执行失败
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MemoGroup memoGroup = new MemoGroup();
        memoGroup.setId(1314);
        memoGroup.setName("LoveYou");
        memoGroup.setCreatedTime(LocalDateTime.now());

        MemoInfo memoInfo = new MemoInfo();
        memoInfo.setId(520);
        memoInfo.setGroupId(memoGroup.getId());
        memoInfo.setTitle("肾宝");
        memoInfo.setContent("~~~~");
        memoInfo.setCreatedTime(LocalDateTime.now());

        boolean rs = createMemoInfo(memoGroup, memoInfo);
        if (rs) {
            System.out.println("创建便签成功");
        } else {
            System.out.println("创建便签失败");
        }
    }
}


class MemoInfo {
    private Integer id;
    private Integer groupId;
    private String title;
    private String content;
    private String isProtected;
    private Color backGround;
    private String isRemind;
    private LocalDateTime remindTime;
    private LocalDateTime createdTime;
    private LocalDateTime modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(String isProtected) {
        this.isProtected = isProtected;
    }

    public Color getBackGround() {
        return backGround;
    }

    public void setBackGround(Color backGround) {
        this.backGround = backGround;
    }

    public String getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(String isRemind) {
        this.isRemind = isRemind;
    }

    public LocalDateTime getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(LocalDateTime remindTime) {
        this.remindTime = remindTime;
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
        return "MemoInfo{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isProtected='" + isProtected + '\'' +
                ", backGround=" + backGround +
                ", isRemind='" + isRemind + '\'' +
                ", remindTime=" + remindTime +
                ", createdTime=" + createdTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}