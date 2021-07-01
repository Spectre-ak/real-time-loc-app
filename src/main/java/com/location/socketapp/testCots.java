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
import org.apache.http.client.methods.HttpPost;
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

	@GetMapping("/req")
	public String Request() {
		String keyVaultName = "keyVaultsForSecureCreds";
		String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";

		SecretClient secretClient = new SecretClientBuilder()
		    .vaultUrl(keyVaultUri)
		    .credential(new DefaultAzureCredentialBuilder().build())
		    .buildClient();
		
		secretClient.setSecret(new KeyVaultSecret("secretName","secretValue"));
		
		System.out.println("As");
		
		return "created";
	}
	
	@GetMapping("/reqGet")
	public String RequestGet() {
		String keyVaultName = "keyVaultsForSecureCreds";
		String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";

		SecretClient secretClient = new SecretClientBuilder()
		    .vaultUrl(keyVaultUri)
		    .credential(new DefaultAzureCredentialBuilder().build())
		    .buildClient();
		
		 KeyVaultSecret retrievedSecret = secretClient.getSecret("secretName");
		 
		return retrievedSecret.getValue();
	}
	
	@GetMapping("/saveCustom")
	public String RequestGet(
			@RequestParam(value = "name",defaultValue = "defValue")String name) {
		
		String keyVaultName = "keyVaultsForSecureCreds";
		
		String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";

		SecretClient secretClient = new SecretClientBuilder()
		    .vaultUrl(keyVaultUri)
		    .credential(new DefaultAzureCredentialBuilder().build())
		    .buildClient();
		
		secretClient.setSecret(new KeyVaultSecret("secretName11",name));
		 
		return "added new value";
	}
	
	
	
	@GetMapping("/make")
	public void name() {

		try {
			HttpClient httpclient = HttpClients.createDefault();
			
			HttpPost httppost = new HttpPost("https://triton.azurewebsites.net/test.php");
			//HttpPost httppost = new HttpPost("https://www.google.com");

			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("getCreds", "12345"));

			
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			//Execute and get the response.
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
			    try (InputStream instream = entity.getContent()) {
			        Scanner scanner=new Scanner(instream);
			        while(scanner.hasNextLine()) {
			        	System.out.println(scanner.nextLine());
			        }
			    }
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}
