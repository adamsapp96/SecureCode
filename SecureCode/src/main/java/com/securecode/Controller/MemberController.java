package com.securecode.Controller;

import java.util.ArrayList;

import com.securecode.DomainObject.MemberDomainObject;
import com.securecode.Model.MemberModel;
import com.securecode.restService.ResponseMessage;
import com.securecode.restService.Response.MemberResponse;

public class MemberController {
	//The PlayerController will convert the request from the REST Request and the Domain Model.
	//Then it will call the Model or Service, and convert the response from the Domain model
	//to the REST Response.
	
	public static ArrayList<MemberResponse> getAllMembers(ResponseMessage message) {
		try{
			ArrayList<MemberDomainObject> domain = MemberModel.GetAllMembers(message);
			ArrayList<MemberResponse> response = MemberResponse.MapList(domain);
			return response;				
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}

	}
	public static MemberResponse getMemberById(ResponseMessage message, int memberId) {
		try {
			MemberDomainObject domain = MemberModel.GetMemberById(message, memberId);
			MemberResponse response = new MemberResponse(domain);
			return response;
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}

	}
/*	
	public static MemberResponse registerMember(ResponseMessage message, RegisterMemberRequest request) {
		try {
			MemberDomainObject requestDomain = new MemberDomainObject(request);

			MemberDomainObject domain = MemberModel.RegisterMember(message, requestDomain);
			MemberResponse response = new MemberResponse(domain);
			return response;
			
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}

	}
*/
}