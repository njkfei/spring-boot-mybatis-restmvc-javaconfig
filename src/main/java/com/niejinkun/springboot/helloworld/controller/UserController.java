package com.niejinkun.springboot.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niejinkun.springboot.helloworld.dao.UserDAO;
import com.niejinkun.springboot.helloworld.model.User;

@RestController
@EnableAutoConfiguration
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping("/user")
	@ResponseBody
	User getUser(){
		return userDAO.getUser(971, 2);
	}

/*	public static void main(String[] args){
		SpringApplication.run(SampleController.class,args);
	}*/
}
