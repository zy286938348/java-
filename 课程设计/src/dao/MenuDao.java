package dao;

import entity.Menus;

import java.util.List;

public interface MenuDao {
    /**
     * 通过商家用户名获取到指定的菜谱
     * @param userName
     * @return
     */
    List<Menus> selectMenuByuserName(String userName);

    /**
     * 用来实时更新菜谱信息
     * @param menus
     * @return
     */
    int updateMenu(Menus menus);

    /**
     * 添加菜
     * @param menus
     * @return
     */
    int insertMenu(Menus menus);

    /**
     * 商家可根据菜品id删除指定菜品
     * @param id
     * @return
     */
    int deleteUserByPrinaryKey(int id);
}
