package com.example.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class User  implements Serializable{
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String picture;
	private int isVirtual;
	private String phone;
	private String remember_token;
	private String created_at;
	private String updated_at;
	private int id;
	private boolean isSuccess;
	private List<Group> groupObject;
	
	
	
	public List<Group> getGroupObject() {
		return groupObject;
	}

	public void setGroupObject(List<Group> groupObject) {
		this.groupObject = groupObject;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public int getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(int isVirtual) {
		this.isVirtual = isVirtual;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemember_token() {
		return remember_token;
	}

	public void setRemember_token(String remember_token) {
		this.remember_token = remember_token;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	

	

	
	
	public User(){
		this.groupObject =new ArrayList<Group>() ;
		
	}
	
	public User(  String first_name, String last_name, String email,
			String password, String picture,
			String phone) {
		super();
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = email;
		this.password = password;
		this.picture = picture;
		this.phone = phone;
		this.isSuccess=false;
		this.groupObject =new ArrayList<Group>() ;
		
	}
	
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}
	

	
	
}
