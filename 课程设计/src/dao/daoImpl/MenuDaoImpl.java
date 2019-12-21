package dao.daoImpl;

import dao.MenuDao;
import entity.Menu;
import until.JMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取到指定商家的菜单
 */
public class MenuDaoImpl implements MenuDao {
    @Override
    public List<Menu> selectMenuByuserName(String userName) {
        Connection connection = JMysql.getConnection();
        String sql = "SELECT * FROM menu WHERE userName=?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,userName);
        ResultSet resultSet = null;
        Menu menu = null;
        List<Menu> list = new ArrayList<>();
        try {
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                menu = new Menu(resultSet.getInt("id"),
                        resultSet.getString("path"),
                        resultSet.getString("menuName"),
                        resultSet.getDouble("price"));
                list.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据传入menu的id修改指定菜的信息
     * @param menu
     * @return
     */
    @Override
    public int updateMenu(Menu menu) {
        Connection connection = JMysql.getConnection();
        String sql = "UPDATE menu SET path = ? , menuName = ? , price = ? WHERE id = ?";
        int flag = 0;
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,menu.getPath(),
                menu.getMenuName(),
                menu.getPrice(),
                menu.getId());
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
     * 增加菜品
     * @param menu
     * @return
     */
    @Override
    public int insertMenu(Menu menu) {
        Connection connection = JMysql.getConnection();
        String sql = " INSERT INTO menu(userName,path,menuName, price) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,menu.getUserName(),
                menu.getPath(),
                menu.getMenuName(),
                menu.getPrice());
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
     * 通过菜品id删除指定菜品
     * @param id
     * @return
     */
    @Override
    public int deleteUserByPrinaryKey(int id) {
        Connection connection = JMysql.getConnection();
        String sql = " DELETE  FROM menu WHERE id=?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,id);
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
}
