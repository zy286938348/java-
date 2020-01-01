package service.serviceImpl;

import dao.UserDao;
import dao.daoImpl.UserDaoImpl;
import entity.User;

import java.util.List;

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

    @Override
    public List<User> selectUserByType(String type) {
        return userdao.selectMenuByType(type);
    }

    @Override
    public int deleteUserByUserName(String username) {
        return userdao.deleteUserByUserName(username);
    }

    @Override
    public String getPasswordByUsername(String username) {
        return userdao.getPassword(username);
    }

    @Override
    public List<User> selectMenuByType(String type) {
        return userdao.selectMenuByType(type);
    }

    @Override
    public String getTextByUsername(String username) {
        return userdao.getTextByUsername(username);
    }

    @Override
    public int setTextUsername(String username, String text) {
        return userdao.updateUser(username,text);
    }
}
