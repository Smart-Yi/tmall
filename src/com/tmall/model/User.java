package com.tmall.model;

public class User {
	private String username ;
	private String password ;
	private int    id ;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnonymousName(){
		if(null==username){
			return null ;
		}
		if(username.length() <= 1){
			return "*" ;
		}
		if(username.length() == 2){
			return username.substring(0, 1) + "*" ;
		}
		String first = username.substring(0,1);
		String end  = username.substring(username.length()-1,username.length());
		for (int i = 0; i < username.length()-2; i++) {
			first = first + "*";
		}
		return first + end ;
	}
}
