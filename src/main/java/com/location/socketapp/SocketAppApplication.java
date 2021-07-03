package com.location.socketapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import ch.qos.logback.classic.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
@SpringBootApplication
public class SocketAppApplication {

//	static void test1() throws Exception{
//		//Creating a HttpClient object
//	      CloseableHttpClient httpclient = HttpClients.createDefault();
//
//	      //Creating a HttpGet object
//	      HttpGet httpget = new HttpGet("https://www.google.com/search?q=akash");
//
//	      //Printing the method used
//	      System.out.println("Request Type: "+httpget.getMethod());
//
//	      //Executing the Get request
//	      HttpResponse httpresponse = httpclient.execute(httpget);
//
//	      Scanner sc = new Scanner(httpresponse.getEntity().getContent());
//
//	      //Printing the status line
//	      System.out.println(httpresponse.getStatusLine());
//	      String totalString="";
//	      while(sc.hasNextLine()) {
//	         totalString+=sc.nextLine();
//	      }
//	      System.out.println();
//	      System.out.println(totalString.length());
//	      try {
//			FileWriter fwFileWriter=new FileWriter(new File("te.html"));
//			fwFileWriter.write(totalString);
//			fwFileWriter.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	
	public static void main(String[] args) throws Exception{
		//name();if(true)return;
		SpringApplication.run(SocketAppApplication.class, args);
		
//		try {
//			URL url=new URL("https://triton.azurewebsites.net/test.php?getCreds=2");
//			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
//			connection.setRequestMethod("GET");
//			Scanner scanner=new Scanner(connection.getInputStream());
//			while(scanner.hasNextLine()) {
//				System.out.println(scanner.next());
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		
		//tasks();
		//if(true)return;
		
		
//		ConnectionString connectionString = new ConnectionString("");
//		MongoClientSettings settings = MongoClientSettings.builder()
//		        .applyConnectionString(connectionString)
//		        .build();
//		MongoClient mongoClient = MongoClients.create(settings);
//		MongoDatabase database = mongoClient.getDatabase("test");
//		
//		database.createCollection("testCollection");
//		
//		Random rand = new Random();
//		Document student = new Document("_id", new ObjectId());
//		student.append("student_id", 10000d)
//        .append("class_id", 1d)
//        .append("scores", asList(new Document("type", "exam").append("score", rand.nextDouble() * 100),
//                                 new Document("type", "quiz").append("score", rand.nextDouble() * 100),
//                                 new Document("type", "homework").append("score", rand.nextDouble() * 100),
//                                 new Document("type", "homework").append("score", rand.nextDouble() * 100)));
//
//		
//
//		System.out.println(database.getName());
		
	}
	
//	public static void name() {
//		ConnectionString connectionString = new 
//				ConnectionString(dbcon.dburl);
//		MongoClientSettings settings = MongoClientSettings.builder()
//		        .applyConnectionString(connectionString)
//		        .build();
//		MongoClient mongoClient = MongoClients.create(settings);
//		MongoDatabase database = mongoClient.getDatabase("mongoDatabase0");
//
//		Random rand = new Random();
//		Document student = new Document("_id", new ObjectId());
//		
//		student.append("student_id", 10000d)
//        .append("class_id", 1d)
//        .append("scores", asList(new Document("type", "exam").append("score", rand.nextDouble() * 100),
//                                 new Document("type", "quiz").append("score", rand.nextDouble() * 100),
//                                 new Document("type", "homework").append("score", rand.nextDouble() * 100),
//                                 new Document("type", "homework").append("score", rand.nextDouble() * 100)));
//
//		
//		database.getCollection("collection0").insertOne(student);
//		
//	
//		MongoCollection<Document> collection = database.getCollection("collection0");
//		
//		FindIterable<Document> fi = collection.find();
//        MongoCursor<Document> cursor = fi.iterator();
//        try {
//            while(cursor.hasNext()) {               
//                Document document=cursor.next();
//                System.out.println(document.get("_id"));
//            }
//        } finally {
//            cursor.close();
//        }
//		
//		
//		Document query = new Document("_id", new ObjectId("60db49c74c9a4324fb978112"));
//		Document result = collection.find(query).iterator().next();
//		result.append("pos","above horizon");
//		System.out.println(result.getString("level"));
//		
//		ConcurrentHashMap<String,String> concurrentHashMap=new ConcurrentHashMap<String, String>();
//		
//	}
//	
//	public static void tasks() {
//		Runnable runnable = new Runnable() {
//		      public void run() {
//		        // task to run goes here
//		        System.out.println("Hello !!");
//		      }
//		    };
//		    ScheduledExecutorService service = Executors
//		                    .newSingleThreadScheduledExecutor();
//		    service.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
//	}

}
