package service.serviceImpl;

import dao.UserDao;
import dao.daoImpl.UserDaoImpl;
import entity.User;

public class UserServiceImpl implements service.UserService {
    UserDao userdao= new UserDaoImpl();
    @Override
    public User checkLogin(String username, String password, String type) {
        return userdao.selectUserByUsernameAndPassword(username, password,type);
    }

    @Override
    public Boolean checkRegister(String username) {
        return userdao.selectUserByUsername(username);
    }

    @Override
    public void insertUser(User user) {
        userdao.insertUser(user);
    }
}
