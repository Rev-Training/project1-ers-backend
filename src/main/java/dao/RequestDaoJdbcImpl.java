package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pojo.RequestPojo;
import pojo.RequestStatus;

public final class RequestDaoJdbcImpl implements RequestDao
{
    private static RequestDaoJdbcImpl requestDaoJdbcImpl;
    
    public static RequestDaoJdbcImpl instance() 
    {
	if (requestDaoJdbcImpl == null) 
	{
	    requestDaoJdbcImpl = new RequestDaoJdbcImpl();
	}
	return requestDaoJdbcImpl;
    }
    
    @Override
    public void createRequest(RequestPojo request)
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();
	    Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
	    
	    stmt.execute("INSERT INTO requests (user_ref, amount, date_created, pending, approved) VALUES ("
			 + request.getUserRef()
			 + ", "
			 + request.getAmount()
			 + ", "
			 + currentTime
			 + ", "
			 + (request.getStatus() == RequestStatus.PENDING)
			 + ", "
			 + (request.getStatus() == RequestStatus.ACCEPTED)
			 + ", "
			 + ") RETURNING request_id;");
	    
	    System.out.println("Request #" + stmt.getResultSet().getInt(1) + " has been created.");
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
    }
    
    @Override
    public RequestPojo getRequest(int requestKey)
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();
	    //System.out.println("SELECT * FROM requests WHERE request_id = " + requestKey + ";");
	    ResultSet rs = stmt.executeQuery("SELECT * FROM requests WHERE request_id = " + requestKey + ";");
	    
	    if (rs.next())
	    {
		return new RequestPojo(rs.getInt(1), 
				       rs.getInt(2), 
				       rs.getFloat(3), 
				       rs.getTimestamp(4),
		      newRequestStatus(rs.getBoolean(5), rs.getBoolean(6)));
	    }
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	return null;
    }
    
    @Override
    public void updateRequest(int requestKey, boolean approve)
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();
	    
	    if (requestKey > 0)
	    {
		//System.out.println("UPDATE requests SET pending = false, approved = " + approve + " WHERE request_id = " + requestKey + ";");
		stmt.executeUpdate("UPDATE requests SET pending = false, approved = " + approve + " WHERE request_id = " + requestKey + ";");
	    }
	    else
	    {
		System.out.println("ERROR: Request not found.");
	    }
	    System.out.println("Request #" + requestKey + " has been " + (approve ? "approved." : "rejected."));
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
    }
    
    @Override
    public void deleteRequest(int requestKey)
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();
	    stmt.executeUpdate("DELETE FROM requests WHERE request_id = " + requestKey + ";");
	    System.out.println("Request #" + requestKey + " has been deleted permanently.");
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
    }
    
    @Override
    public List<RequestPojo> getAllRequests()
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();	    	    
	    ResultSet rs = stmt.executeQuery("SELECT * FROM requests;");
	    List<RequestPojo> requests = new ArrayList<RequestPojo>();
	    
	    while (rs.next()) {
		requests.add(new RequestPojo(rs.getInt(1), 
				       	     rs.getInt(2), 
				             rs.getFloat(3), 
				             rs.getTimestamp(4),
			    newRequestStatus(rs.getBoolean(5), rs.getBoolean(6))));
	    }
	    return requests;
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	return null;
    }
    
    @Override
    public List<RequestPojo> getAllRequests(int userKey)
    {
	Connection conn = DBUtil.makeConnection();
	try
	{
	    Statement stmt = conn.createStatement();	    
	    ResultSet rs = stmt.executeQuery("SELECT * FROM requests WHERE user_ref = " + userKey + ";");
	    List<RequestPojo> requests = new ArrayList<RequestPojo>();
	    
	    while (rs.next()) {
		requests.add(new RequestPojo(rs.getInt(1), 
				       	     rs.getInt(2), 
				             rs.getFloat(3), 
				             rs.getTimestamp(4),
			    newRequestStatus(rs.getBoolean(5), rs.getBoolean(6))));
	    }
	    return requests;
	} catch (SQLException e)
	{
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	return null;
    }
    
    private static final RequestStatus newRequestStatus(boolean pending, boolean approved)
    {
	if (!pending)
	{
	    if (!approved) { return RequestStatus.REJECTED; }
	    else { return RequestStatus.ACCEPTED; }
	}
	else { return RequestStatus.PENDING; }
    }
}
