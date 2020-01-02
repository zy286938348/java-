package service;

import entity.Order;

public interface OrderService {

    int insertOrder(Order order);

    int delete(int id);
}
