package dao;

import java.util.List;

import pojo.UserPojo;

public interface UserDao
{
    void createUser(UserPojo user);
    UserPojo getUser(int userKey);
    UserPojo getUser(String username);
    void updateUser(UserPojo user);
    void removeUser(int userKey);
    List<UserPojo> getAllUsers();
}
