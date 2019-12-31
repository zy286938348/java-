package service.serviceImpl;

import dao.ShoppingDao;
import dao.daoImpl.ShoppingDaolmpl;
import entity.Shopping;
import service.ShoppingService;

import java.util.List;

public class ShoppingServiceImpl implements ShoppingService {
    ShoppingDao shoppingDao = new ShoppingDaolmpl();
    @Override
    public List<Shopping> selectShoppingByuserName(String username) {
        return shoppingDao.shoppingList(username);
    }
}
