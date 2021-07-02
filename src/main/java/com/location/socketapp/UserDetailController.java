package com.location.socketapp;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailController {

	@RequestMapping(value = "/loginDetail", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<HashMap<String,String>> loginSimple(HttpServletResponse response,
			HttpServletRequest request, @RequestBody HashMap<String,String> data) {

		System.out.println(data);
	    response.addCookie(new Cookie("heroku-nav-data", "adad"));
	    HashMap<String,String> responseHashMap=new HashMap<>();
	    responseHashMap.put("res","valid");
	    //return new ResponseEntity<String>("msg",HttpStatus.OK); 
	    return new ResponseEntity<HashMap<String,String>>(responseHashMap, HttpStatus.OK);
	    

	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public Object signup(HttpServletResponse response,
			HttpServletRequest request, @RequestBody HashMap<String,String> data) throws IOException {

		if(true) {
			response.sendRedirect("/");
		}
		System.out.println(data);
	    response.addCookie(new Cookie("heroku-nav-data", "adad"));
	    HashMap<String,String> responseHashMap=new HashMap<>();
	    responseHashMap.put("res","valid");
	    //return new ResponseEntity<String>("msg",HttpStatus.OK); 
	    return new ResponseEntity<HashMap<String,String>>(responseHashMap, HttpStatus.OK);
	    

	}
	
}
