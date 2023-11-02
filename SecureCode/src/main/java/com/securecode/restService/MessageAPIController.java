package com.securecode.restService;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.securecode.Controller.MessageController;
import com.securecode.restService.Request.CreateMessageRequest;
import com.securecode.restService.Request.EditMessageRequest;
import com.securecode.restService.Response.MessageResponse;
import com.securecode.restService.Response.Response;


@RestController
public class MessageAPIController {

	
	@CrossOrigin(origins = "*")
	@GetMapping("/api/message")
	public ResponseEntity<Response> getMessages() {
		ResponseMessage responseMessage = new ResponseMessage();
		List<MessageResponse> messages = MessageController.getAllMessages(responseMessage);

		Response response = new Response(responseMessage, messages);
		if (response.getSuccess())
			return ResponseEntity.status(HttpStatus.OK).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		

	}


	@CrossOrigin(origins = "*")
	@PostMapping("/api/message/{id}")
	public ResponseEntity<Response> updateMessage(@RequestBody EditMessageRequest request, @PathVariable int id) {
		ResponseMessage responseMessage = new ResponseMessage();
		
		MessageResponse message = MessageController.updateMessage(responseMessage, request, id);

		Response response = new Response(responseMessage, message);
		if (response.getSuccess())
			return ResponseEntity.status(HttpStatus.OK).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/api/message")
	public ResponseEntity<Response> createMessage(@RequestBody CreateMessageRequest request) {
		ResponseMessage responseMessage = new ResponseMessage();
		MessageResponse message = MessageController.createMessage(responseMessage, request);

		Response response = new Response(responseMessage, message);
		if (response.getSuccess())
			return ResponseEntity.status(HttpStatus.OK).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}



	@CrossOrigin(origins = "*")
	@PostMapping("/api/message/secure")
	public ResponseEntity<Response> createMessageSecure(@RequestBody CreateMessageRequest request, @CookieValue(value="Session") String sessionid) {

		ResponseMessage responseMessage = new ResponseMessage();
		
		//Not actually checking the session id here.
		if (sessionid == null) {
			Response response = new Response(responseMessage, "");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

		}

		
		MessageResponse message = MessageController.createMessage(responseMessage, request);

		Response response = new Response(responseMessage, message);
		if (response.getSuccess())
			return ResponseEntity.status(HttpStatus.OK).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}


}