package com.securecode.Model;
import java.util.ArrayList;
import java.util.Set;

import com.securecode.DataAccess.MemberDataAccess;
import com.securecode.DataObject.MemberDataObject;
import com.securecode.DomainObject.MemberDomainObject;
import com.securecode.restService.ResponseMessage;


public class MemberModel {
	
	public static MemberDomainObject GetMemberById(ResponseMessage message, int id) {
		MemberDataObject playerData = MemberDataAccess.getMemberById(id);
		MemberDomainObject playerDomain = new MemberDomainObject(playerData);
		return playerDomain;
	}

	public static ArrayList<MemberDomainObject> GetMembersByIds(ResponseMessage message, Set<Integer> ids) {
		ArrayList<MemberDataObject> memberDataList = MemberDataAccess.getMembersByIds(ids);
		ArrayList<MemberDomainObject> memberDomainList = MemberDomainObject.MapList(memberDataList);
		return memberDomainList;
	}

	public static ArrayList<MemberDomainObject> GetAllMembers(ResponseMessage messasge) {
		ArrayList<MemberDataObject> memberDataList = MemberDataAccess.getAllMembers();
		ArrayList<MemberDomainObject> memberDomainList = MemberDomainObject.MapList(memberDataList);
		return memberDomainList;
	}


	public static MemberDomainObject CheckLogin(String username, String password) {
		MemberDataObject playerData = MemberDataAccess.getMemberByCredentials(username, password);
		MemberDomainObject playerDomain = new MemberDomainObject(playerData);
		return playerDomain;
	}




	/*
	public static MemberDomainObject RegisterMember(ResponseMessage message, MemberDomainObject domainMemberToCreate) {
		//To Be Written
		String userName = domainMemberToCreate.UserName;
		String passWord = domainMemberToCreate.Password;
		int uCount = userName.length();
		int pCount = passWord.length();
		ArrayList<MemberDomainObject> arr = new ArrayList<MemberDomainObject>();
		
		//username validation
		if (userName == null){
			message.addErrorMessage("Username is empty, please enter a valid username");
			return domainMemberToCreate;
		}

		// The service will validate the username is between 8 and 25 characters.
		// The service will validate the username can only contain English letters (A-Z, a-z) and numbers.
		if (uCount < 8 && uCount > 25 && userName.matches("[a-zA-Z0-9]*")){
			return domainMemberToCreate;
		}else{
		//If the username is invalid, the response will include an errormessage “Username must contain 8-
		// 25 characters - including only English letters and numbers.”
			message.addErrorMessage("Username must contain 8-25 characters - including only English letters and numbers.");
		}

		//The service will validate the username is unique
		for(int i =0; i<arr.size(); i++){
			if(userName != domainMemberToCreate.UserName){
				return domainMemberToCreate;
			}else{ //If the username is not unique, the response will include an errormessage “Username is already
					//taken. Please select another.”
					message.addErrorMessage("Username is already taken. Please select another.");
				}
			}
			//password validation
		// The service will validate the password is between 8 and 25 characters.
		// The service will validate the password can only contain English letters (A-Z, a-z) and numbers.


		if (passWord == null){
			message.addErrorMessage("Password is empty, please enter a valid password");
		}

		if (pCount < 8 && pCount > 25 && passWord.matches("[a-zA-Z0-9]*")){
			return domainMemberToCreate;
		}else{
			// If the password is invalid, the response will include an errormessage “Password must contain 8-
			// 25 characters - including only English letters and numbers.”
			message.addErrorMessage("Password must contain 8-25 characters - including only English letters and numbers.");
		}
			// If validation passes, the new player will be created with the next sequential playerId, starting with 1.


		return null;	
	}
*/

}