package dao;

import entity.User;

import java.util.List;

public interface UserDao {

    User selectUserByUsernameAndPassword(String username , String password, String type);

    Boolean selectUserByUsername(String username);

    int updateUser(String username,String text);

    int update(User user);

    int insertUser(User user);

    int deleteUserByUserName(String username);

    List<User> selectMenuByType(String type);

    String getPassword(String username);

    String getTextByUsername(String username);
}
