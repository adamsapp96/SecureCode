package com.securecode.DataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;



import com.securecode.DataObject.MemberDataObject;


public class MemberDataAccess {

	//private static ArrayList<MemberDataObject> members;

	private static String tableName = "member";
	private static String fields = "memberid, username, fullname";
	private static String idField = "memberid";

	
	public MemberDataAccess() {
	}


	public static ArrayList<MemberDataObject> getAllMembers() {
		String sql = String.format("select %s from %s", fields, tableName);
		List<MemberDataObject> list = DBAccesser.jdbcTemplate.query(sql, new MemberMapper());

		return (ArrayList<MemberDataObject>)list;
	}

	public static ArrayList<MemberDataObject> getMembersByIds(Set<Integer> ids) {

		String idsString = "";
		for (Integer id: ids) {
			if (!idsString.equals("")) idsString += ",";
			idsString += "'" + id + "'";
		}

		String sql = String.format("select %s from %s where %s in (%s)", fields, tableName, idField, idsString);
		List<MemberDataObject> list = DBAccesser.jdbcTemplate.query(sql, new MemberMapper());
		
		return (ArrayList<MemberDataObject>)list;

	}



	public static MemberDataObject getMemberById(int id) {

		String sql = String.format("select %s from %s where %s = %s", fields, tableName, idField, id);
		List<MemberDataObject> list = DBAccesser.jdbcTemplate.query(sql, new MemberMapper());
		
		if (list.size() < 1)
			return null;

		return list.get(0);

	}

    

	public static MemberDataObject getMemberByCredentials(String username, String password) {
		
		String sql = String.format("select %s from %s where username = '%s' AND password = '%s'", fields, tableName, username, password);
		List<MemberDataObject> list = DBAccesser.jdbcTemplate.query(sql, new MemberMapper());
		
		if (list.size() < 1)
			return null;

		return list.get(0);
	}



}