package service;

import entity.User;

public interface UserService {
    /**
     * 检测登录
     * @param username
     * @param password
     * @param type
     * @return
     */
    User checkLogin(String username,String  password,String type);

    /**
     * 检测注册
     * @param username
     * @return
     */
    Boolean checkRegister(String username);

    void insertUser(User user);
}
