package dao;

import entity.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 通过客户昵称获取订单信息
     * @param username
     * @return
     */
    List<Order> getMenusByUsername(String username);

    /**
     * 通过订单id删除指定订单信息
     * @param id
     * @return
     */
    int deletOrder (int id);

    /**
     * 根据客户名称添加订单信息
     * @param order
     * @return
     */
    int insertOrder(Order order);

}
