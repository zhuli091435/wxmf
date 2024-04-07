package com.wxmf.pojo;

import java.lang.Long;
import java.lang.String;
import java.lang.Integer;

public class DeviceParameter {
	/**
    * ID
    */
    private Integer ID;
	/**
    * ParamName
    */
    private String ParamName;
	/**
    * ParamLength
    */
    private Integer ParamLength;
	/**
    * DecimalPlaces
    */
    private Integer DecimalPlaces;
	/**
    * Unit
    */
    private String Unit;
	/**
    * MinValue
    */
    private Long MinValue;
	/**
    * MaxValue
    */
    private Long MaxValue;
	/**
    * RegisterAddress
    */
    private Integer RegisterAddress;
	/**
    * StartBit
    */
    private Integer StartBit;
	/**
    * EndBit
    */
    private Integer EndBit;
	/**
    * Symbol
    */
    private String Symbol;
	/**
    * ParamType
    */
    private Integer ParamType;
	/**
    * Category
    */
    private String Category;
	/**
    * Remark
    */
    private String Remark;

    public DeviceParameter(){
    }

    public DeviceParameter(Integer ID, String ParamName, Integer ParamLength, Integer DecimalPlaces, String Unit, Long MinValue, Long MaxValue, Integer RegisterAddress, Integer StartBit, Integer EndBit, String Symbol, Integer ParamType, String Category, String Remark){
        this.ID=ID;
        this.ParamName=ParamName;
        this.ParamLength=ParamLength;
        this.DecimalPlaces=DecimalPlaces;
        this.Unit=Unit;
        this.MinValue=MinValue;
        this.MaxValue=MaxValue;
        this.RegisterAddress=RegisterAddress;
        this.StartBit=StartBit;
        this.EndBit=EndBit;
        this.Symbol=Symbol;
        this.ParamType=ParamType;
        this.Category=Category;
        this.Remark=Remark;
    }

    public Integer getID(){
        return this.ID;
    }
    public void setID(Integer ID){
        this.ID = ID;
    }

    public String getParamName(){
        return this.ParamName;
    }
    public void setParamName(String ParamName){
        this.ParamName = ParamName;
    }

    public Integer getParamLength(){
        return this.ParamLength;
    }
    public void setParamLength(Integer ParamLength){
        this.ParamLength = ParamLength;
    }

    public Integer getDecimalPlaces(){
        return this.DecimalPlaces;
    }
    public void setDecimalPlaces(Integer DecimalPlaces){
        this.DecimalPlaces = DecimalPlaces;
    }

    public String getUnit(){
        return this.Unit;
    }
    public void setUnit(String Unit){
        this.Unit = Unit;
    }

    public Long getMinValue(){
        return this.MinValue;
    }
    public void setMinValue(Long MinValue){
        this.MinValue = MinValue;
    }

    public Long getMaxValue(){
        return this.MaxValue;
    }
    public void setMaxValue(Long MaxValue){
        this.MaxValue = MaxValue;
    }

    public Integer getRegisterAddress(){
        return this.RegisterAddress;
    }
    public void setRegisterAddress(Integer RegisterAddress){
        this.RegisterAddress = RegisterAddress;
    }

    public Integer getStartBit(){
        return this.StartBit;
    }
    public void setStartBit(Integer StartBit){
        this.StartBit = StartBit;
    }

    public Integer getEndBit(){
        return this.EndBit;
    }
    public void setEndBit(Integer EndBit){
        this.EndBit = EndBit;
    }

    public String getSymbol(){
        return this.Symbol;
    }
    public void setSymbol(String Symbol){
        this.Symbol = Symbol;
    }

    public Integer getParamType(){
        return this.ParamType;
    }
    public void setParamType(Integer ParamType){
        this.ParamType = ParamType;
    }

    public String getCategory(){
        return this.Category;
    }
    public void setCategory(String Category){
        this.Category = Category;
    }

    public String getRemark(){
        return this.Remark;
    }
    public void setRemark(String Remark){
        this.Remark = Remark;
    }


    @Override
    public String toString() {
        return "DeviceParameter{" +
                "ID=" + ID +
                ", ParamName=" + ParamName +
                ", ParamLength=" + ParamLength +
                ", DecimalPlaces=" + DecimalPlaces +
                ", Unit=" + Unit +
                ", MinValue=" + MinValue +
                ", MaxValue=" + MaxValue +
                ", RegisterAddress=" + RegisterAddress +
                ", StartBit=" + StartBit +
                ", EndBit=" + EndBit +
                ", Symbol=" + Symbol +
                ", ParamType=" + ParamType +
                ", Category=" + Category +
                ", Remark=" + Remark +
                '}';
    }

}
