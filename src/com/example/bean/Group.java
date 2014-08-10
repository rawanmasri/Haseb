package com.example.bean;

import java.util.ArrayList;

public class Group {

	
	private  int groupID ;
	private  String name ;
	private  String currency ;
	private int userID ;
	private boolean isSuccess;
	private ArrayList<Bill> billsID =new ArrayList<Bill>() ;
	private ArrayList<User> friendID =new ArrayList<User>() ;
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
	public ArrayList<User> getFriendID() {
		return friendID;
	}
	public void setFriendID(ArrayList<User> friendID) {
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
	public  ArrayList<Bill> getBillsID() {
		return billsID;
	}
	public void setBillsID( ArrayList<Bill>billsID) {
		this.billsID = billsID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Group(String name, String currency,int userId,
			 ArrayList<User> friendID ) {
		super();
		this.name = name;
		this.currency = currency;
		this.userID = userId;
		this.friendID = friendID;
		
	}
	
	
	
}
