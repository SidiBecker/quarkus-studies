package com.sidneibecker.quarkus.controller;

import java.util.Date;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.sidneibecker.quarkus.dto.UserDTO;
import com.sidneibecker.quarkus.model.User;

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
	@Transactional
	public String register(@RequestBody UserDTO userDTO) {

		User user = new User();
		user.setName(userDTO.getName());
		user.setLogin(userDTO.getLogin());
		user.setCreationDate(new Date());

		User.persist(user);

		System.out.println("ID: " + user.getId());

		return userDTO.getName() + " is now registred!";
	}
}