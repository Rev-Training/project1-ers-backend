package service;

import java.util.List;

import dao.RequestDaoJdbcImpl;
import pojo.RequestPojo;

public final class RequestServiceImpl implements RequestService
{

    @Override
    public RequestPojo createRequest(RequestPojo request)
    {
	return getRequest(RequestDaoJdbcImpl.instance().createRequest(request));
    }

    @Override
    public RequestPojo getRequest(int requestKey)
    {
	return RequestDaoJdbcImpl.instance().getRequest(requestKey);
    }

    @Override
    public RequestPojo updateRequest(int requestKey, boolean approve)
    {
	RequestDaoJdbcImpl.instance().updateRequest(requestKey, approve);
	return getRequest(requestKey);
    }

    @Override
    public RequestPojo deleteRequest(int requestKey)
    {
	RequestDaoJdbcImpl.instance().deleteRequest(requestKey);
	return getRequest(requestKey);
    }

    @Override
    public List<RequestPojo> getAllRequests()
    {
	return RequestDaoJdbcImpl.instance().getAllRequests();
    }

    @Override
    public List<RequestPojo> getAllRequests(int userKey)
    {
	return RequestDaoJdbcImpl.instance().getAllRequests(userKey);
    }
    
}
