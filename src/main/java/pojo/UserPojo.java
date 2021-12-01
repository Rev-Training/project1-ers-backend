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
    
    public UserPojo(String username, String passwordHash, boolean isManager, boolean removed)
    {
	super();
	this.userName = username;
	this.userPassword = passwordHash;
	this.userIsManager = isManager;
	this.userIsRemoved = removed;
    }
    
    public UserPojo()
    {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getUsername()
    {
	return userName;
    }
    
    public void setUsername(String username)
    {
	this.userName = username;
    }
    
    public String getPasswordHash()
    {
	return userPassword;
    }
    
    public void setPasswordHash(String passwordHash)
    {
	this.userPassword = passwordHash;
    }
        
    public int getUserID()
    {
	return userID;
    }
    
    public boolean isManager()
    {
	return userIsManager;
    }
    
    public boolean isRemoved()
    {
	return userIsRemoved;
    }
    
    public void setRemoved(boolean isRemoved)
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
