package com.location.socketapp;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ReceiveSocketMsg {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	
	@MessageMapping("/coordinatesGlobal")
	@SendTo("/topic/coordinatesGlobalReceive")
	public Object coordinatesGlobal(HashMap<String,String> coordinates) throws Exception{
		System.out.println(coordinates);
		return coordinates;
	}
	
	@MessageMapping("/{path}")
	public void simple(@DestinationVariable String path,HashMap<String,String> hm) {
	    System.out.println(path);
	    System.out.println(hm);
	    hm.put("add","asdad");
	    this.template.convertAndSend("/topic/globalReceiver",hm);
	    
	    
	}
	
	@SendTo("/topic/globalReceiver")
	public String sendMsgToUser() {
		return "testing---testing";
	}
}
