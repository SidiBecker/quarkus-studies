package com.sidneibecker.quarkus.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.sidneibecker.quarkus.dto.UserDTO;
import com.sidneibecker.quarkus.model.User;

import io.quarkus.elytron.security.common.BcryptUtil;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

	@GET
	@Transactional
	public List<User> list() {
		return User.listAll();
	}

	@POST
	@Transactional
	public String register(@RequestBody UserDTO userDTO) {

		User user = new User();
		user.setName(userDTO.getName());
		user.setLogin(userDTO.getLogin());
		user.setPassword(BcryptUtil.bcryptHash(userDTO.getPassword()));
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());

		User.persist(user);

		System.out.println("ID: " + user.getId());

		return userDTO.getName() + " is now registred!";
	}

	@PUT
	@Transactional
	public Boolean edit(@RequestBody UserDTO userDTO) throws Exception {

		User user = User.findById(userDTO.getId());

		if (user == null) {
			throw new Exception("O usuário " + userDTO.getId().toString() + "não existe");
		}

		user.setLogin(userDTO.getLogin());
		user.setName(userDTO.getName());
		user.setPassword(BcryptUtil.bcryptHash(userDTO.getPassword()));
		user.setUpdateDate(new Date());

		User.persist(user);

		return Boolean.TRUE;
	}

	@DELETE
	@Transactional
	public Boolean delete(@RequestBody Long id) throws Exception {

		User user = User.findById(id);

		if (user == null) {
			throw new Exception("O usuário " + id.toString() + "não existe");
		}

		User.deleteById(id);

		return Boolean.TRUE;
	}

}