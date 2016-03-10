package com.niejinkun.springboot.helloworld.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niejinkun.springboot.helloworld.model.Greeting;

@EnableAutoConfiguration
@RestController
public class GreetihngController {
	private static final String template = "hello,%s!";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name",defaultValue="world")String name)
	{
		return new Greeting(counter.incrementAndGet(),String.format(template, name));
	}
	
/*	public static void main(String[] args){
		SpringApplication.run(GreetihngController.class,args);
	}*/
}
