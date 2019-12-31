package service;

import entity.Shopping;

import java.util.List;

public interface ShoppingService {
    List<Shopping> selectShoppingByuserName(String username);

    int deleteById(int id);

    int deleteAll(String username);
}
