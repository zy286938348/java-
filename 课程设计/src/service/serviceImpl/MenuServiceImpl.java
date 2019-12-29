package service.serviceImpl;

import dao.MenuDao;
import dao.daoImpl.MenuDaoImpl;
import entity.Menus;
import service.MenuService;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    MenuDao menuDao = new MenuDaoImpl();

    @Override
    public List<Menus> selectMenuByuserName(String username) {
        return menuDao.selectMenuByuserName(username);
    }

    @Override
    public int insertMenu(Menus menus) {
        return menuDao.insertMenu(menus);
    }

    @Override
    public int deleteUserByPrinaryKey(int id) {
        return menuDao.deleteUserByPrinaryKey(id);
    }

    @Override
    public int updateMenu(Menus menus) {
        return menuDao.updateMenu(menus);
    }
}
