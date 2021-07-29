package com.websocket.resouces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.websocket.model.MessageModel;
import com.websocket.storage.UserStorage;

@RestController
public class MessageResource {
	
	@Autowired
	private SimpMessagingTemplate simp;

	@MessageMapping("/chat/{to}")
	public void sendMessage(@DestinationVariable String to, @Payload MessageModel message) {
		System.out.println("handling send message: "+message+ " to: "+to);
		boolean isExists = UserStorage.getInstance().getUsers().contains(to);
		if(isExists) {
			simp.convertAndSend("/queue/messages/"+to, message);
		}
	}
}
