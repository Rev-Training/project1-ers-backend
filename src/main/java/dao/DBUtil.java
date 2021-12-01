package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBUtil
{
    static Connection conn = null;
    
    static
    {
	try
	{
	    Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e)
	{
	    e.printStackTrace();
	}
    }
    
    public static Connection makeConnection()
    {
	if (conn == null)
	{
	    try
	    {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ers_p1", "postgres", "admin");
	    } catch (SQLException e)
	    {
		e.printStackTrace();
	    }
	}
	return conn;
    }
    
    public static void closeConnection()
    {
	try
	{
	    conn.close();
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
//    public static int fetchID(String table, String keyColumn, String name)
//    {
//	int ID = -1;
//	try
//	{
//	    Statement stmt = conn.createStatement();
//
//	    String query = "SELECT " + keyColumn + " FROM " + table + " WHERE " + keyColumn + "=" + name;
//	    System.out.println(query);
//	    ResultSet rs = stmt.executeQuery(query);
//
//	    if (rs.next())
//	    {
//		ID = rs.getInt(1);
//	    }
//	} catch (SQLException e)
//	{
//	    e.printStackTrace();
//	}
//	return ID;
//    }
}