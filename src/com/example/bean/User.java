package com.example.bean;

import java.io.Serializable;

public class User  implements Serializable{
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String picture;
	private int isVirtual;
	private String phone;
	private String apiKey;
	private int userID;
	private boolean isSuccess;
	private int [] groupsID =new int[40] ;
	private String message;
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public int[] getGroupsID() {
		return groupsID;
	}
	public void setGroupsID(int[] groupsID) {
		this.groupsID = groupsID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getFirst_name() {
		return firstName;
	}
	public void setFirst_name(String first_name) {
		this.firstName = first_name;
	}
	public String getLast_name() {
		return lastName;
	}
	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getSecondery_pool() {
		return isVirtual;
	}
	public void setSecondery_pool(int secondery_pool) {
		this.isVirtual = secondery_pool;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getId() {
		return userID;
	}
	public void setId(int id) {
		this.userID = id;
	}
	public User(){}
	public User(  String first_name, String last_name, String email,
			String password, String picture, int secondery_pool,
			String phone) {
		super();
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = email;
		this.password = password;
		this.picture = picture;
		this.isVirtual = secondery_pool;
		this.phone = phone;
		isSuccess=false;
		
		
		
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
	
}
