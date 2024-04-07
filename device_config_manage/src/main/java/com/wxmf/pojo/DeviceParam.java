package com.wxmf.pojo;

import java.lang.String;
import java.lang.Integer;

public class DeviceParam {
	/**
    * ID
    */
    private Integer ID;
	/**
    * ParamIndex
    */
    private Integer ParamIndex;
	/**
    * RegisterAddress
    */
    private Integer RegisterAddress;
	/**
    * ParamName
    */
    private String ParamName;
	/**
    * ParamType
    */
    private String ParamType;
	/**
    * ParamIdentifier
    */
    private String ParamIdentifier;
	/**
    * Remark
    */
    private String Remark;

    public DeviceParam(){
    }

    public DeviceParam(Integer ID, Integer ParamIndex, Integer RegisterAddress, String ParamName, String ParamType, String ParamIdentifier, String Remark){
        this.ID=ID;
        this.ParamIndex=ParamIndex;
        this.RegisterAddress=RegisterAddress;
        this.ParamName=ParamName;
        this.ParamType=ParamType;
        this.ParamIdentifier=ParamIdentifier;
        this.Remark=Remark;
    }

    public Integer getID(){
        return this.ID;
    }
    public void setID(Integer ID){
        this.ID = ID;
    }

    public Integer getParamIndex(){
        return this.ParamIndex;
    }
    public void setParamIndex(Integer ParamIndex){
        this.ParamIndex = ParamIndex;
    }

    public Integer getRegisterAddress(){
        return this.RegisterAddress;
    }
    public void setRegisterAddress(Integer RegisterAddress){
        this.RegisterAddress = RegisterAddress;
    }

    public String getParamName(){
        return this.ParamName;
    }
    public void setParamName(String ParamName){
        this.ParamName = ParamName;
    }

    public String getParamType(){
        return this.ParamType;
    }
    public void setParamType(String ParamType){
        this.ParamType = ParamType;
    }

    public String getParamIdentifier(){
        return this.ParamIdentifier;
    }
    public void setParamIdentifier(String ParamIdentifier){
        this.ParamIdentifier = ParamIdentifier;
    }

    public String getRemark(){
        return this.Remark;
    }
    public void setRemark(String Remark){
        this.Remark = Remark;
    }


    @Override
    public String toString() {
        return "DeviceParam{" +
                "ID=" + ID +
                ", ParamIndex=" + ParamIndex +
                ", RegisterAddress=" + RegisterAddress +
                ", ParamName=" + ParamName +
                ", ParamType=" + ParamType +
                ", ParamIdentifier=" + ParamIdentifier +
                ", Remark=" + Remark +
                '}';
    }

}
