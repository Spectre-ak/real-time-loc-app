package com.location.socketapp;

import java.io.IOException;
import java.util.HashMap;import java.util.function.Consumer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@RestController
public class UserDetailController {

	private String dburl=null;
	private MongoClient mongoClient=null;
	
	private synchronized String AuthenticatedUser(String type,HashMap<String,String> data) {
		if(dburl==null) {
			getDBUrl();
			//error retrieving the db url
			if(dburl==null)return null;
		}
		if(mongoClient==null) {
			connectAndIntialize();
			//error connecting to db
			if(mongoClient==null)return null;
		}
		
		MongoDatabase database = mongoClient.getDatabase("mongoDatabase0");
		MongoCollection<Document> collection = database.getCollection("user");
		
		if(type.equals("1")) {
			//log in
		}
		else if(type.equals("2")) {
			//sign up
		}
		else {
			//google
			
		}
		
		
		return null;
	}
	
	
	
	
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
	
	@RequestMapping(value = "/signingoogle",method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Object signingoogle(HttpServletResponse response,
			HttpServletRequest request, @RequestBody HashMap<String,String> data) throws IOException {

		System.out.println(data);
	    response.addCookie(new Cookie("heroku-nav-data", "adad"));
	    HashMap<String,String> responseHashMap=new HashMap<>();
	    responseHashMap.put("res","valid");
	    //return new ResponseEntity<String>("msg",HttpStatus.OK); 
	    return new ResponseEntity<HashMap<String,String>>(responseHashMap, HttpStatus.OK);
	    

	}
	
	private synchronized void getDBUrl() {
		
		if(true) {
			//locally testing
			dburl=dbcon.dburl;return;
		}
		
		try {
			String keyVaultName = "keyVaultsForSecureCreds";
			String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";

			SecretClient secretClient = new SecretClientBuilder()
			    .vaultUrl(keyVaultUri)
			    .credential(new DefaultAzureCredentialBuilder().build())
			    .buildClient();
			
			 KeyVaultSecret retrievedSecret = secretClient.getSecret("databaseCredentials");
			 this.dburl=retrievedSecret.getValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	private synchronized void connectAndIntialize() {
		ConnectionString connectionString = new ConnectionString(dburl);
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .build();
		MongoClient mongoClient = MongoClients.create(settings);
		this.mongoClient=mongoClient;
	}
	
}
