package com.example.bean;

import java.util.ArrayList;

public class Group {

	
	public  int groupID ;
	public  String name ;
	public  String currency ;
	public int userID ;
	private boolean isSuccess;
	private int [] billsID =new int[40] ;
	private ArrayList<Integer> friendID =new ArrayList<Integer>() ;
	private String message;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public int getGroupId() {
		return groupID;
	}
	public ArrayList<Integer> getFriendID() {
		return friendID;
	}
	public void setFriendID(ArrayList<Integer> friendID) {
		this.friendID = friendID;
	}
	public void setGroupId(int groupId) {
		this.groupID = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getUserId() {
		return userID;
	}
	public void setUserId(int userId) {
		this.userID = userId;
	}
	public int[] getBillsID() {
		return billsID;
	}
	public void setBillsID(int[] billsID) {
		this.billsID = billsID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Group(int groupId, String name, String currency,int userId,
			int[] billsID,ArrayList<Integer> friendID ) {
		super();
		this.groupID = groupId;
		this.name = name;
		this.currency = currency;
		this.userID = userId;
		this.billsID = billsID;
		this.friendID = friendID;
		
	}
	
	
	
}
