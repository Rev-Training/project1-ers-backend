package pojo;

import java.sql.Timestamp;

public final class RequestPojo
{
    private int requestID = -1;
    private int userRef = -1;
    private float amount = 0;
    private Timestamp dateCreated = null;
    private RequestStatus status = RequestStatus.PENDING;    
    
    public RequestPojo()
    {
	super();
	// TODO Auto-generated constructor stub
    }

    public RequestPojo(int requestID, int userRef, float amount, Timestamp creationDate, RequestStatus status)
    {
	super();
	this.requestID = requestID;
	this.userRef = userRef;
	this.amount = amount;
	this.dateCreated = creationDate;
	this.status = status;
    }
    
    public RequestStatus getStatus()
    {
        return status;
    }

    public void setStatus(RequestStatus status)
    {
        this.status = status;
    }

    public int getRequestID()
    {
        return requestID;
    }

    public int getUserRef()
    {
        return userRef;
    }

    public float getAmount()
    {
        return amount;
    }

    public Timestamp getDateCreated()
    {
	return dateCreated;
    }

}
