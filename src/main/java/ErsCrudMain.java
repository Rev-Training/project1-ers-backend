import dao.DBUtil;
import io.javalin.Javalin;
import pojo.*;
import service.*;

public final class ErsCrudMain
{
    
    public static void main(String[] args)
    {
	DBUtil.makeConnection();
	UserService userService = new UserServiceImpl();
	RequestService requestService = new RequestServiceImpl();
	Javalin server = Javalin.create((config) -> config.enableCorsForAllOrigins()).start(8888);
	
	//for Users
	server.get("api/users/{uid}", (ctx) ->
	{	    
	    System.out.println(userService.getUser(Integer.parseInt(ctx.pathParam("uid"))));
	    ctx.json(userService.getUser(Integer.parseInt(ctx.pathParam("uid"))));
	    System.out.println(ctx.path());
	});
	        
	server.get("api/users/id/{uname}", (ctx) ->
	{	   
	    System.out.println(userService.getUserID(ctx.pathParam("uname")));
	    ctx.json(userService.getUserID(ctx.pathParam("uname")));
	    System.out.println(ctx.path());
	});
	
	server.get("api/users", (ctx) -> {
	    ctx.json(userService.getAllUsers().toArray());
	    System.out.println(ctx.path());
	});
	
	server.post("api/users", (ctx) ->
	{
	    UserPojo newUser = userService.createUser(ctx.bodyAsClass(UserPojo.class));
	    ctx.json(newUser);
	    System.out.println(ctx.path());
	    System.out.println(newUser.toString());
	});
	
	server.put("api/users/{uid}", (ctx) ->
	{
	    ctx.json(userService.updateUser(ctx.bodyAsClass(UserPojo.class)));
	    System.out.println(ctx.path());
	});
	
	server.delete("api/users/r/{uid}", (ctx) ->
	{
	    userService.removeUser(Integer.parseInt(ctx.pathParam("uid")));
	    ctx.json(ctx.bodyAsClass(UserPojo.class));
	});
	
	server.get("api/users/verify/{uid}", (ctx) -> {
	    ctx.json(userService.verifyPassword(userService.getUser(Integer.parseInt(ctx.pathParam("uid")))));
	    System.out.println(ctx.path());
	});
	
	
	//for Requests
	server.get("api/requests/id/{rid}", (ctx) ->
	{
	    ctx.json(requestService.getRequest(Integer.parseInt(ctx.pathParam("rid"))));
	});
	
	server.get("api/requests/", (ctx) ->
	{
	    ctx.json(requestService.getAllRequests().toArray());
	});
	
	server.get("api/requests/user/{uid}", (ctx) ->
	{
	    ctx.json(requestService.getAllRequests(Integer.parseInt(ctx.pathParam("uid"))).toArray());
	});
	
	server.post("api/requests", (ctx) ->
	{
	    ctx.json(requestService.createRequest(ctx.bodyAsClass(RequestPojo.class)));
	});
	
	server.put("api/requests/a/{rid}", (ctx) ->
	{
	    RequestPojo returnedRequest = requestService.updateRequest(ctx.bodyAsClass(RequestPojo.class).getRequestID(), true);
	    ctx.json(returnedRequest);
	});
	
	server.put("api/requests/d/{rid}", (ctx) ->
	{
	    RequestPojo returnedRequest = requestService.updateRequest(ctx.bodyAsClass(RequestPojo.class).getRequestID(), false);
	    ctx.json(returnedRequest);
	});
	
	server.delete("api/requests/{rid}", (ctx) ->
	{
	    requestService.deleteRequest(Integer.parseInt(ctx.pathParam("rid")));
	});
	
    }
    
}
