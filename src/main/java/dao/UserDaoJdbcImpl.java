package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pojo.*;

public final class UserDaoJdbcImpl implements UserDao
{
    private static UserDaoJdbcImpl userDaoJdbcImpl;
    
    public static UserDaoJdbcImpl instance() {
	if (userDaoJdbcImpl == null) {
	    userDaoJdbcImpl = new UserDaoJdbcImpl();
	}
	return userDaoJdbcImpl;
    }
    
    @Override
    public void createUser(UserPojo user)
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();
	    
	    stmt.execute("INSERT INTO users (user_name, password_hash, user_manager) VALUES ("
			 + user.getUsername()
			 + ", "
			 + user.getPasswordHash()
			 + ", "
			 + user.isManager()
			 + ", "
			 + user.isRemoved()
			 + ", "
			 + ") RETURNING user_id;");
	    
	    System.out.println("User #" + stmt.getResultSet().getInt(1) + " has been created.");
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
    }
    
    @Override
    public UserPojo getUser(int userKey)
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user_id = " + userKey + ";");
	    
	    if (rs.next())
	    {
		return new UserPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5));
	    }
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public UserPojo getUser(String username)
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user_name = '" + username + "';");
	    
	    if (rs.next())
	    {
		return new UserPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5));
	    }
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	return null;
    }
    
    @Override
    public void updateUser(UserPojo user)
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();
	    
	    if (getUser(user.getUserID()) != null)
	    {
		stmt.executeUpdate("UPDATE user SET user_name = '" + user.getUsername()
				   				   + "', password_hash = '"
				   				   + user.getPasswordHash()
				   				   + " WHERE user_id = "
				   				   + user.getUserID() + ";");
	    }
	    else
	    {
		System.out.println("ERROR: User not found.");
	    }
	    
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
    }
    
    @Override
    public void removeUser(int userKey)
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();
	    
	    stmt.executeUpdate("UPDATE users SET user_removed = true WHERE user_id = " + userKey + ";");
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    @Override
    public List<UserPojo> getAllUsers()
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user_removed = false;");
	    List<UserPojo> userList = new ArrayList<UserPojo>();
	    
	    while (rs.next())
	    {
		userList.add(new UserPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5)));
	    }
	    return userList;
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	return null;
    }

}
