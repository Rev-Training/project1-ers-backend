package service;

import java.util.List;

import pojo.RequestPojo;

public interface RequestService
{
    RequestPojo createRequest(RequestPojo request);
    RequestPojo getRequest(int requestKey);
    RequestPojo updateRequest(int requestKey, boolean approve);
    RequestPojo deleteRequest(int requestKey);
    List<RequestPojo> getAllRequests();
    List<RequestPojo> getAllRequests(int userKey);
}
