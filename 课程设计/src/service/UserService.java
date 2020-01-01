package service;

import entity.User;

import java.util.List;

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

    /**
     * 增加用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 获取该类型的User
     * @param type
     * @return
     */
    List<User> selectUserByType(String type);

    /**
     * 根据id删除指定user
     * @param username
     * @return
     */
    int deleteUserByUserName(String username);

    /**
     * 根据用户名查找user
     */
    String getPasswordByUsername(String username);

    List<User> selectMenuByType(String type);

    String getTextByUsername(String username);

    int setTextUsername(String username,String text);




}
