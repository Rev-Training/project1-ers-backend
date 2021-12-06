package pojo;

public final class UserPojo
{
    private int userID = -1;
    private String userName = "";
    private String userPassword = null;
    private boolean userIsManager = false;
    private boolean userIsRemoved = false;
    
    public UserPojo(int userID, String username, String passwordHash, boolean isManager, boolean removed)
    {
	super();
	this.userID = userID;
	this.userName = username;
	this.userPassword = passwordHash;
	this.userIsManager = isManager;
	this.userIsRemoved = removed;
    }
    
    public UserPojo(String userName, String userPassword, boolean userIsManager, boolean userIsRemoved)
    {
	super();
	this.userName = userName;
	this.userPassword = userPassword;
	this.userIsManager = userIsManager;
	this.userIsRemoved = userIsRemoved;
    }
    
    public UserPojo()
    {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getUserName()
    {
	return userName;
    }
    
    public void setUserName(String username)
    {
	this.userName = username;
    }
    
    public String getUserPassword()
    {
	return userPassword;
    }
    
    public void setUserPassword(String userPassword)
    {
	this.userPassword = userPassword;
    }
        
    public int getUserID()
    {
	return userID;
    }
    
    public boolean getUserIsManager()
    {
	return userIsManager;
    }
    
    public boolean getUserIsRemoved()
    {
	return userIsRemoved;
    }
    
    public void setUserIsRemoved(boolean isRemoved)
    {
	this.userIsRemoved = isRemoved;
    }

    @Override
    public String toString()
    {
	return "UserPojo [userID=" + userID
	       + ", userName="
	       + userName
	       + ", userPassword="
	       + userPassword
	       + ", userIsManager="
	       + userIsManager
	       + ", userIsRemoved="
	       + userIsRemoved
	       + "]";
    }
    
    
}
