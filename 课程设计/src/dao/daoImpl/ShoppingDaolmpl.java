package dao.daoImpl;

import dao.ShoppingDao;
import entity.Menus;
import entity.Shopping;
import entity.User;
import until.JMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingDaolmpl implements ShoppingDao {


    @Override
    public List<Shopping> shoppingList(String username) {
        Connection connection = JMysql.getConnection();
        String sql = "SELECT * FROM shopping WHERE username=?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,username);
        ResultSet resultSet = null;
        Shopping shopping = null;
        List<Shopping> list = new ArrayList<>();
        try {
            resultSet = preparedStatement.executeQuery();
            if (resultSet==null){
                System.out.println("未查询到此人");
            }else{
                while(resultSet.next()){
                    shopping  =new Shopping(
                            resultSet.getInt("id"),
                            resultSet.getString("sUsername"),
                            resultSet.getString("menuName"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("num"),
                            resultSet.getDouble("sum")
                    );
                    list.add(shopping);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int deleteById(int id) {
        Connection connection = JMysql.getConnection();
        String sql = "DELETE FROM shopping WHERE id = ?";
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

    @Override
    public int insert(Shopping shopping) {
        Connection connection = JMysql.getConnection();
        String sql = "INSERT INTO shopping(username,sUsername,menuName,price,num,sum) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,shopping.getUsername(),
                shopping.getsUsername(),
                shopping.getMenuName(),
                shopping.getPrice(),
                shopping.getNum(),
                shopping.getSum());
        int flag = 0 ;
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
    public int deleteAll(String username) {
        Connection connection = JMysql.getConnection();
        String sql = "DELETE FROM shopping WHERE username = ?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,username);
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
