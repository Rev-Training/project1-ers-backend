package dao;

import java.util.List;

import pojo.RequestPojo;

public interface RequestDao
{
    void createRequest(RequestPojo request);
    RequestPojo getRequest(int requestKey);
    void updateRequest(int requestKey, boolean approve);
    void deleteRequest(int requestKey);
    List<RequestPojo> getAllRequests();
    List<RequestPojo> getAllRequests(int userKey);
}
