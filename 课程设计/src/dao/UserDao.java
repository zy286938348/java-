package dao;

import entity.User;

public interface UserDao {

    User selectUserByUsernameAndPassword(String username , String password, String type);

    Boolean selectUserByUsername(String username);

    int updateUser(User user);

    int insertUser(User user);

    int deleteUserByPrinaryKey(String username);
}
