package dao;

import entity.Menu;

import java.util.List;

public interface MenuDao {
    /**
     * 通过商家用户名获取到指定的菜谱
     * @param userName
     * @return
     */
    List<Menu> selectMenuByuserName(String userName);

    /**
     * 用来实时更新菜谱信息
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);

    /**
     * 添加菜
     * @param menu
     * @return
     */
    int insertMenu(Menu menu);

    /**
     * 商家可根据菜品id删除指定菜品
     * @param id
     * @return
     */
    int deleteUserByPrinaryKey(int id);
}
