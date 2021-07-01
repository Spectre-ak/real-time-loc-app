package com.location.socketapp;
import com.azure.core.util.polling.SyncPoller;
import com.azure.identity.DefaultAzureCredentialBuilder;

import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.DeletedSecret;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

public class testAzureKeyValult {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String keyVaultName = "keyVaultsForSecureCreds";
		String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";

		SecretClient secretClient = new SecretClientBuilder()
		    .vaultUrl(keyVaultUri)
		    .credential(new DefaultAzureCredentialBuilder().build())
		    .buildClient();
		
		secretClient.setSecret(new KeyVaultSecret("secretName","secretValue"));
		
		System.out.println("As");
	}

}
