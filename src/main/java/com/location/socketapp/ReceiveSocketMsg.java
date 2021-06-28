package com.location.socketapp;

import java.util.HashMap;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

public class ReceiveSocketMsg {
	@MessageMapping("/coordinatesGlobal")
	@SendTo("/coordinatesGlobalReceive")
	public Object coordinatesGlobal(HashMap<String,String> coordinates) {
		System.out.println();
		return coordinates;
	}
}
