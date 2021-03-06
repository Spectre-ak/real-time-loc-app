package com.location.socketapp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.nimbusds.oauth2.sdk.Request;

@RestController
public class testCots {


	public String RequestGet() {
		String keyVaultName = "keyVaultsForSecureCreds";
		String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";

		SecretClient secretClient = new SecretClientBuilder()
		    .vaultUrl(keyVaultUri)
		    .credential(new DefaultAzureCredentialBuilder().build())
		    .buildClient();
		
		 KeyVaultSecret retrievedSecret = secretClient.getSecret("databaseCredentials");
		 
		return retrievedSecret.getValue();
	}


	
	@GetMapping("/make")
	public String name(@RequestParam(value = "q",defaultValue = "")String q) {

		try {
			 CloseableHttpClient httpclient = HttpClients.createDefault();

		      //Creating a HttpGet object
		      HttpGet httpget = new HttpGet("https://www.google.com/search?q="+q);

		      //Printing the method used
		      System.out.println("Request Type: "+httpget.getMethod());

		      //Executing the Get request
		      HttpResponse httpresponse = httpclient.execute(httpget);

		      Scanner sc = new Scanner(httpresponse.getEntity().getContent());

		      //Printing the status line
		      System.out.println(httpresponse.getStatusLine());
		      String totalString="";
		      while(sc.hasNextLine()) {
		         totalString+=sc.nextLine();
		      }
			
			return totalString;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return e.getMessage();
		}
		
		
	}
	
	
}
