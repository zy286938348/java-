package service.serviceImpl;

import dao.OrderDao;
import dao.daoImpl.OrderDaoImpl;
import entity.Order;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    @Override
    public int insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    @Override
    public int delete(int id) {
        return orderDao.deleteOrder(id);
    }
}
