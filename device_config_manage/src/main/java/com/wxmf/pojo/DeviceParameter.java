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

    /**
     * Remark
     */
    private String ChannelAmount;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getParamName() {
        return ParamName;
    }

    public void setParamName(String paramName) {
        ParamName = paramName;
    }

    public Integer getParamLength() {
        return ParamLength;
    }

    public void setParamLength(Integer paramLength) {
        ParamLength = paramLength;
    }

    public Integer getDecimalPlaces() {
        return DecimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        DecimalPlaces = decimalPlaces;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public Long getMinValue() {
        return MinValue;
    }

    public void setMinValue(Long minValue) {
        MinValue = minValue;
    }

    public Long getMaxValue() {
        return MaxValue;
    }

    public void setMaxValue(Long maxValue) {
        MaxValue = maxValue;
    }

    public Integer getRegisterAddress() {
        return RegisterAddress;
    }

    public void setRegisterAddress(Integer registerAddress) {
        RegisterAddress = registerAddress;
    }

    public Integer getStartBit() {
        return StartBit;
    }

    public void setStartBit(Integer startBit) {
        StartBit = startBit;
    }

    public Integer getEndBit() {
        return EndBit;
    }

    public void setEndBit(Integer endBit) {
        EndBit = endBit;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public Integer getParamType() {
        return ParamType;
    }

    public void setParamType(Integer paramType) {
        ParamType = paramType;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getChannelAmount() {
        return ChannelAmount;
    }

    public void setChannelAmount(String channelAmount) {
        ChannelAmount = channelAmount;
    }
}
