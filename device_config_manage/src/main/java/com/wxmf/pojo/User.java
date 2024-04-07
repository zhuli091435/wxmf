package com.wxmf.pojo;

import java.lang.String;
import java.lang.Integer;

public class User {
	/**
    * UserID
    */
    private Integer UserID;
	/**
    * UserName
    */
    private String UserName;
	/**
    * Password
    */
    private String Password;

    public User(){
    }

    public User(Integer UserID, String UserName, String Password){
        this.UserID=UserID;
        this.UserName=UserName;
        this.Password=Password;
    }

    public Integer getUserID(){
        return this.UserID;
    }
    public void setUserID(Integer UserID){
        this.UserID = UserID;
    }

    public String getUserName(){
        return this.UserName;
    }
    public void setUserName(String UserName){
        this.UserName = UserName;
    }

    public String getPassword(){
        return this.Password;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }


    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", UserName=" + UserName +
                ", Password=" + Password +
                '}';
    }

}
