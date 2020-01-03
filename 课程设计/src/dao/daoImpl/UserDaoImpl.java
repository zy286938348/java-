package dao.daoImpl;

import dao.UserDao;
import entity.User;
import until.JMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    /**
     * 使用username、password和type检索用户信息
     * 用于检验能否登录
     * @param username
     * @param password
     * @param type
     * @return
     */
    @Override
    public User selectUserByUsernameAndPassword(String username, String password, String type) {
        Connection connection = JMysql.getConnection();
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND type = ?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,username,password,type);
        ResultSet resultSet = null;
        User user = null;
        try {
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getInt("age"),
                        resultSet.getString("sex"),
                        resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JMysql.closeConnection(connection);
        }
        return user;
    }

    /**
     * 使用username检索用户信息
     * 用于判断是否可以注册
     * @param username
     * @return
     */
    @Override
    public Boolean selectUserByUsername(String username) {
        Connection connection = JMysql.getConnection();
        String sql = "SELECT * FROM user WHERE  username=?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,username);
        ResultSet resultSet = null;
        Boolean flag = false;
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JMysql.closeConnection(connection);
        }
        return flag;
    }

    /**
     * 根据商家用户名更新报表信息
     * @param username
     * @param text
     * @return
     */
    @Override
    public int updateUser(String username,String text) {
        Connection connection = JMysql.getConnection();
        String sql = "UPDATE user SET text = ? WHERE username = ?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,text,username);
        int flag = 0;
        try {
            flag = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JMysql.closeConnection(connection);
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public int update(User user) {
        Connection connection = JMysql.getConnection();
        String sql = "UPDATE user SET name = ?, age = ?, sex = ?, password = ? WHERE username = ?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,user.getName(),user.getAge(),user.getSex(),user.getPassword(),user.getUsername());
        int flag = 0;
        try {
            flag = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JMysql.closeConnection(connection);
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 增加用户
     * 实现注册功能
     * @param user
     * @return
     */
    @Override
    public int insertUser(User user) {
        Connection connection = JMysql.getConnection();
        String sql = "INSERT INTO user (username, password, type) VALUES (?,?,?)";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,user.getUsername(),
                user.getPassword(),
                user.getType());
        int flag = 0;
        try {
            flag = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JMysql.closeConnection(connection);
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 删除用户
     * 实现管理员的管理功能
     * @param username
     * @return
     */
    @Override
    public int deleteUserByUserName(String username) {
        Connection connection = JMysql.getConnection();
        String sql = "DELETE FROM user WHERE username = ?";
        PreparedStatement pstmt = JMysql.getPreparedStatement(connection, sql, username);
        int flag = 0;
        try {
            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JMysql.closeConnection(connection);
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public List<User> selectMenuByType(String type) {

        Connection connection = JMysql.getConnection();
        String sql = "SELECT * FROM user WHERE type = ?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,type);
        ResultSet resultSet = null;
        User user = null;
        List<User> userList = new ArrayList<>();

        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("path"),
                        resultSet.getString("username"),
                        resultSet.getString("name"),
                        resultSet.getString("sex"),
                        resultSet.getInt("age"),
                        resultSet.getTimestamp("data").toString());
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public String getPassword(String username) {
        Connection connection = JMysql.getConnection();
        String sql = "SELECT * FROM user WHERE username = ?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,username);
        ResultSet resultSet = null;
        String password = null;
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    @Override
    public String getTextByUsername(String username) {
        Connection connection = JMysql.getConnection();
        String sql = "SELECT * FROM user WHERE username = ?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,username);
        ResultSet resultSet = null;
        String text = null;
        try {
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                text = resultSet.getString("text");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JMysql.closeConnection(connection);
        }
        return text;
    }
}
