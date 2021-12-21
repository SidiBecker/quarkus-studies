package com.sidneibecker;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.sidneibecker.quarkus.dto.UserDTO;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

	@GET
	@Path("hello")
	public String hello(@QueryParam("name") String name) {
		return "Hello " + name + "!!";
	}

	@POST
	@Path("register")
	public String register(@RequestBody UserDTO userDTO) {
		return userDTO.getName() + " is now registred!";
	}
}