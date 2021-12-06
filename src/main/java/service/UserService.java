package service;

import java.util.List;

import pojo.UserPojo;

public interface UserService
{
    UserPojo createUser(UserPojo user);
    int getUserID(String username);
    UserPojo getUser(int userKey);
    UserPojo updateUser(UserPojo user);
    UserPojo removeUser(int userKey);
    List<UserPojo> getAllUsers();
    boolean verifyPassword(UserPojo user);
}
