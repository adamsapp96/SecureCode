package com.securecode.DomainObject;

import java.util.ArrayList;
import com.securecode.DataObject.MemberDataObject;
import com.securecode.restService.Request.RegisterMemberRequest;

public class MemberDomainObject {

	public int memberid;
	public String username;
	public String fullname;
	public String password;

	public MemberDomainObject(MemberDataObject data) {
		this.memberid = data.memberid;
		this.username = data.username;
		this.fullname = data.fullname;
		this.password = data.password;
	}
	
	public static ArrayList<MemberDomainObject> MapList(ArrayList<MemberDataObject> dataList) {
		ArrayList<MemberDomainObject> domainObjectList = new ArrayList<MemberDomainObject>();
		
		for (int i=0; i<dataList.size(); i++) {
			MemberDomainObject domain = new MemberDomainObject(dataList.get(i));
			domainObjectList.add(domain);
		}

		return domainObjectList;
	}

	public MemberDomainObject(RegisterMemberRequest request) {
		this.username = request.getUsername();
		this.password = request.getPassword();
	}

}