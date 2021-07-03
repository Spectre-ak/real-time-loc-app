package com.location.socketapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.types.ObjectId;
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
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@RestController
public class UserDetailController {

	private String dburl=null;
	private MongoClient mongoClient=null;
	
	private synchronized HashMap<String,String> AuthenticateUser(String type,HashMap<String,String> data) {
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
		
		
		
		FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        
		
        HashMap<String,String> response=new 
        		HashMap<>();
        
		
		if(type.equals("1")) {
			//log in
			try {
	            while(cursor.hasNext()) {               
	                Document document=cursor.next();
	                if(document.get("username").equals(data.get("username"))) {
	                	if(document.get("password")
	                			.equals(data.get("passsword"))) {
	                		
	                		response.put("v","1");
	                		response.put("name",data.get("name"));
	                		response.put("socketId",document.get("socketId").toString());
	                		
	                		return response;
	                	}
	                	break;
	                }
	            }
	        } finally {
	            cursor.close();
	        }
			response.put("v","-1");
			return response;
		}
		else if(type.equals("2")) {
			//sign up
			try {
	            while(cursor.hasNext()) {               
	                Document document=cursor.next();
	                if(document.get("username").equals(data.get("username"))) {
	                	//returning if the username is already present
	                	response.put("v","-2");
	                	return response;
	                }
	            }
	        } finally {
	            cursor.close();
	        }
			
			//username is not present so adding to db
			Document user = createObject(data);
			
			
			//adding user
			collection.insertOne(user);
			
			response.put("v","2");
			response.put("name",data.get("name"));
			response.put("socketId", user.get("socketId").toString());
			
			return response;
		}
		else {
			//google
			try {
	            while(cursor.hasNext()) {               
	                Document document=cursor.next();
	                if(document.get("username").equals(data.get("username"))) {
	                	//returning if the username is present
	                	response.put("v","3");
	                	response.put("socketId",document.get("socketId").toString());
	                	response.put("name",document.get("name").toString());
	                	return response;
	                }
	            }
	        } finally {
	            cursor.close();
	        }
			
			//g user not present
			
			Document user = createObject(data);
			
			//adding user
			collection.insertOne(user);
			
			response.put("v","3");
			response.put("name",data.get("name"));
			response.put("socketId", user.get("socketId").toString());
			
		}
		
		
		return null;
	}
	
	
	
	
	@RequestMapping(value = "/loginDetail", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String,String>> loginSimple(HttpServletResponse response,
			HttpServletRequest request, @RequestBody HashMap<String,String> data) {

		System.out.println(data);
	    
	    HashMap<String,String> userVerdict=this.AuthenticateUser("1", data);

	    System.out.println(userVerdict);
	    
	    if(userVerdict.get("v").equals("1")) {
	    	response.addCookie(new Cookie("p","t"));
	    	response.addCookie(new Cookie("socketId",userVerdict.get("socketId")));
	    }
	    
	    //return new ResponseEntity<String>("msg",HttpStatus.OK); 
	    return new ResponseEntity<HashMap<String,String>>(userVerdict, HttpStatus.OK);
	    
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public Object signup(HttpServletResponse response,
			HttpServletRequest request, @RequestBody HashMap<String,String> data) throws IOException {

		
		System.out.println(data);
	    
	    HashMap<String,String> userVerdict=this.AuthenticateUser("2", data);

	    System.out.println(userVerdict);
	    
	    if(userVerdict.get("v").equals("2")) {
	    	response.addCookie(new Cookie("p","t"));
	    	response.addCookie(new Cookie("socketId",userVerdict.get("socketId")));
	    }
		
	    return new ResponseEntity<HashMap<String,String>>(userVerdict
	    		, HttpStatus.OK);
	    

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
	
	
	private Document createObject(HashMap<String,String> data) {
		Document user = new Document("_id", new ObjectId());
		user.append("username",data.get("username"));
		user.append("name",data.get("name"));
		user.append("password",data.get("password"));
		user.append("imgUrl",data.get("img"));
		
		//socket ID generation
		
		String socketId=UUID.randomUUID().toString();
		socketId=socketId.replaceAll("-","");
		socketId=socketId+(new ObjectId()).toString();
		
		user.append("socketId", socketId);
		
		return user;
	}
}
