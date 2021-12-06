package service;

import java.util.List;

import dao.UserDaoJdbcImpl;
import pojo.UserPojo;

public final class UserServiceImpl implements UserService
{

    @Override
    public UserPojo createUser(UserPojo user)
    {
	return getUser(UserDaoJdbcImpl.instance().createUser(user));
    }

    @Override
    public UserPojo getUser(int userKey)
    {	
	UserPojo userCache = UserDaoJdbcImpl.instance().getUser(userKey);	
	return userCache == null ? new UserPojo() : userCache;
    }

    @Override
    public UserPojo updateUser(UserPojo user)
    {
	UserDaoJdbcImpl.instance().updateUser(user);
	return getUser(user.getUserID());
    }

    @Override
    public UserPojo removeUser(int userKey)
    {
	UserDaoJdbcImpl.instance().removeUser(userKey);
	return getUser(userKey);
    }

    @Override
    public List<UserPojo> getAllUsers()
    {
	return UserDaoJdbcImpl.instance().getAllUsers();
    }

    @Override
    public int getUserID(String username)
    {
	UserPojo user = UserDaoJdbcImpl.instance().getUser(username);
	if (user != null) {
	    return user.getUserID();
	}
	else {
	    return -1;
	}
	
    }
    
    @Override
    public final boolean verifyPassword(UserPojo user) {
	return PasswordEncoder.verifyPassword(user.getUserPassword(), getUser(user.getUserID()).getUserPassword());
    }
}
