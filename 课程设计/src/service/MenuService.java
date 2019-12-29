package service;

import entity.Menus;

import java.util.List;

public interface MenuService {
    /**
     * 根据商家用户名获取菜单信息
     * @param username
     * @return
     */
    List<Menus> selectMenuByuserName(String username);

    int insertMenu(Menus menus);

    int deleteUserByPrinaryKey(int id);

    int updateMenu(Menus menus);
 }
