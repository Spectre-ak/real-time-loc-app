package com.location.socketapp;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static java.util.Arrays.asList;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SpringBootApplication
public class SocketAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocketAppApplication.class, args);
		
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
	
	public static void name() {
		ConnectionString connectionString = new 
				ConnectionString("");
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("mongoDatabase0");

		Random rand = new Random();
		Document student = new Document("_id", new ObjectId());
		student.append("student_id", 10000d)
        .append("class_id", 1d)
        .append("scores", asList(new Document("type", "exam").append("score", rand.nextDouble() * 100),
                                 new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                                 new Document("type", "homework").append("score", rand.nextDouble() * 100),
                                 new Document("type", "homework").append("score", rand.nextDouble() * 100)));

		
		database.getCollection("collection0").insertOne(student);
		
		
		MongoCollection<Document> collection = database.getCollection("collection0");
		
		
		Document query = new Document("_id", new ObjectId("60db49c74c9a4324fb978112"));
		Document result = collection.find(query).iterator().next();
		result.append("pos","above horizon");
		System.out.println(result.getString("level"));
		
		ConcurrentHashMap<String,String> concurrentHashMap=new ConcurrentHashMap<String, String>();
		
	}
	
	public static void tasks() {
		Runnable runnable = new Runnable() {
		      public void run() {
		        // task to run goes here
		        System.out.println("Hello !!");
		      }
		    };
		    ScheduledExecutorService service = Executors
		                    .newSingleThreadScheduledExecutor();
		    service.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
	}

}
