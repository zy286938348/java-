package dao;


import entity.Shopping;
import entity.User;

import java.util.List;

public interface ShoppingDao {
    /**
     * 根据用户信息获取购物车中的菜
     * @param username
     * @return
     */
    List<Shopping>  shoppingList(String username);

    /**
     * 根据ID删除购物车中的物品
     * @param id
     * @return
     */
    int delet(int id);

    /***
     * 把购物车中的信息返回到数据库
     * @param shopping
     * @return
     */
    int insert(Shopping shopping);


}
