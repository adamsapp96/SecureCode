package com.securecode.Controller;

import java.util.ArrayList;


import com.securecode.DomainObject.MessageDomainObject;
import com.securecode.Model.MessageModel;
import com.securecode.restService.ResponseMessage;
import com.securecode.restService.Request.CreateMessageRequest;
import com.securecode.restService.Request.EditMessageRequest;
import com.securecode.restService.Response.MessageResponse;

public class MessageController {
	//The MessageController will convert the request from the REST Request and the Domain Model.
	//Then it will call the Model or Service, and convert the response from the Domain model
	//to the REST Response.
	public static ArrayList<MessageResponse> getAllMessages(ResponseMessage responseMessage) {
		try {
			ArrayList<MessageDomainObject> domain = MessageModel.GetAllMessages(responseMessage);
			ArrayList<MessageResponse> response = MessageResponse.MapList(domain);
			return response;
		} catch (Exception ex) {
			responseMessage.addErrorMessage(ex.getMessage());
			return null;
		}
	}

	public static MessageResponse updateMessage(ResponseMessage responseMessage, EditMessageRequest request, int id) {
		try {
			MessageDomainObject updateMessage = new MessageDomainObject(request);
			updateMessage.messageId = id;

			MessageDomainObject domain = MessageModel.UpdateMessage(responseMessage, updateMessage);
			MessageResponse response = new MessageResponse(domain);
			return response;
		} catch (Exception ex) {
			responseMessage.addErrorMessage(ex.getMessage());
			return null;
		}

	}


	public static MessageResponse createMessage(ResponseMessage responseMessage, CreateMessageRequest request) {
		try {
			MessageDomainObject newMessage = new MessageDomainObject(request);

			MessageDomainObject domain = MessageModel.CreateMessage(responseMessage, newMessage);
			MessageResponse response = new MessageResponse(domain);
			return response;
		} catch (Exception ex) {
			responseMessage.addErrorMessage(ex.getMessage());
			return null;
		}

	}
}
