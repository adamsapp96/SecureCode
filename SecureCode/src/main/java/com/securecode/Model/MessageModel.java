package com.securecode.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.securecode.DataObject.MessageDataObject;
import com.securecode.DataAccess.MessageDataAccess;
import com.securecode.DomainObject.MemberDomainObject;
import com.securecode.DomainObject.MessageDomainObject;
import com.securecode.restService.ResponseMessage;


public class MessageModel {
	

	public static ArrayList<MessageDomainObject> GetAllMessages(ResponseMessage message) {
		ArrayList<MessageDataObject> messageDataList = MessageDataAccess.getAllMessages();
		ArrayList<MessageDomainObject> messageDomainList = MessageDomainObject.MapList(messageDataList);

		HashSet<Integer> memberIdSet = new HashSet<Integer>();

		for (MessageDomainObject messageDO : messageDomainList) {
			memberIdSet.add(messageDO.authorId);
		}

		ArrayList<MemberDomainObject> members = MemberModel.GetMembersByIds(message, memberIdSet);

		HashMap<Integer, MemberDomainObject> memberMap = new HashMap<Integer, MemberDomainObject>();
		for (MemberDomainObject member : members) {
			memberMap.put(member.memberid, member);
		}



		for (MessageDomainObject messageDO : messageDomainList) {
			messageDO.member = memberMap.get(messageDO.authorId);
		}


		return messageDomainList;
	}


	public static MessageDomainObject GetMessageById(ResponseMessage message, int id) {
		MessageDataObject messageData = MessageDataAccess.getMessageById(id);
		MessageDomainObject messageDomain = new MessageDomainObject(messageData);
		return messageDomain;
	}


	public static MessageDomainObject UpdateMessage(ResponseMessage responseMessage, MessageDomainObject domainMessageToUpdate) {
		
		MessageDataObject newMessage = new MessageDataObject(domainMessageToUpdate.messageId, domainMessageToUpdate.authorId, domainMessageToUpdate.message);
		MessageDataObject updatedMessage = MessageDataAccess.updateMessage(newMessage);

        return new MessageDomainObject(updatedMessage);
	}

	public static MessageDomainObject CreateMessage(ResponseMessage responseMessage, MessageDomainObject domainMessageToCreate) {
		if(domainMessageToCreate.authorId == 0){
			responseMessage.addErrorMessage("Must include all parameters.");
			return null;
		}

		//check if author is registered


		MessageDataObject newMessage = new MessageDataObject(0, domainMessageToCreate.authorId, domainMessageToCreate.message);
		MessageDataObject createdMessage = MessageDataAccess.createMessage(newMessage);

        return new MessageDomainObject(createdMessage);
	}

}