package com.location.socketapp;

import java.util.HashMap;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Object greeting(HashMap<String, String> message) throws Exception {
		System.out.println(message.toString());
		
		return "hello akash";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}