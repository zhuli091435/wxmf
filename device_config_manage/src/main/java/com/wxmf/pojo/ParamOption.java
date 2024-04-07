package com.wxmf.pojo;

import java.lang.String;
import java.lang.Integer;

public class ParamOption {
    /**
     * ID
     */
    private Integer ID;
    /**
     * OptionIndex
     */
    private Integer OptionIndex;
    /**
     * OptionName
     */
    private String OptionName;
    /**
     * OptionValue
     */
    private String OptionValue;
    /**
     * ParamType
     */
    private Integer ParamTypeID;
    /**
     * Remark
     */
    private String Remark;

    public ParamOption() {
    }

    public ParamOption(Integer ID, Integer OptionIndex, String OptionName, String OptionValue, Integer ParamTypeID, String Remark) {
        this.ID = ID;
        this.OptionIndex = OptionIndex;
        this.OptionName = OptionName;
        this.OptionValue = OptionValue;
        this.ParamTypeID = ParamTypeID;
        this.Remark = Remark;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getOptionIndex() {
        return this.OptionIndex;
    }

    public void setOptionIndex(Integer OptionIndex) {
        this.OptionIndex = OptionIndex;
    }

    public String getOptionName() {
        return this.OptionName;
    }

    public void setOptionName(String OptionName) {
        this.OptionName = OptionName;
    }

    public String getOptionValue() {
        return this.OptionValue;
    }

    public void setOptionValue(String OptionValue) {
        this.OptionValue = OptionValue;
    }

    public Integer getParamTypeID() {
        return this.ParamTypeID;
    }

    public void setParamTypeID(Integer ParamType) {
        this.ParamTypeID = ParamType;
    }

    public String getRemark() {
        return this.Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }


    @Override
    public String toString() {
        return OptionName;
        //return "ParamOption{" + "ID=" + ID + ", OptionIndex=" + OptionIndex + ", OptionName=" + OptionName + ", OptionValue=" + OptionValue + ", ParamType=" + ParamType + ", Remark=" + Remark + '}';
    }

}
