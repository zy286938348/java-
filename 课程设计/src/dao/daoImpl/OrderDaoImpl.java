package dao.daoImpl;

import dao.OrderDao;
import entity.Order;
import until.JMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> getMenusByUsername(String username) {
        Order order = null;
        List<Order> orderList = new ArrayList<>();

        Connection connection = JMysql.getConnection();
        String sql = "SELECT * FROM orders WHERE merchant = ?";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql,username);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                order = new Order(resultSet.getInt("id"),
                        resultSet.getString("userName"),
                        resultSet.getString("menuName"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("num"),
                        resultSet.getDouble("sum"),
                        resultSet.getString("time"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public int deleteOrder(int id) {
        Connection connection = JMysql.getConnection();
        String sql = "DELETE FROM orders WHERE id = ?";
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
    public int insertOrder(Order order) {
        Connection connection = JMysql.getConnection();
        String sql = " INSERT INTO orders(userName,merchant,menuName,price,num,sum) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = JMysql.getPreparedStatement(connection,sql, order.getUsername(),
                order.getMerchant(),
                order.getMenuName(),
                order.getPrice(),
                order.getNum(),
                order.getNum()*order.getPrice());
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
