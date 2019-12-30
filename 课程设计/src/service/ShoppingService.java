package service;

import entity.Shopping;

import java.util.List;

public interface ShoppingService {
    List<Shopping> selectShoppingByuserName(String username);
}
