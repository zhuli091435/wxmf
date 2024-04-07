package com.wxmf.pojo;

import java.util.Date;
import java.lang.String;
import java.lang.Integer;

public class DeviceParamValue {
    /**
     * ID
     */
    private Integer ID;
    /**
     * DeviceID
     */
    private String DeviceID;
    /**
     * ParamIndex
     */
    private Integer ParamIndex;
    /**
     * ParamValue
     */
    private String ParamValue;
    /**
     * UpdateTime
     */
    private Date UpdateTime;

    public DeviceParamValue() {
    }

    public DeviceParamValue(Integer ID, String DeviceID, Integer ParamIndex, String ParamValue, Date UpdateTime) {
        this.ID = ID;
        this.DeviceID = DeviceID;
        this.ParamIndex = ParamIndex;
        this.ParamValue = ParamValue;
        this.UpdateTime = UpdateTime;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDeviceID() {
        return this.DeviceID;
    }

    public void setDeviceID(String DeviceID) {
        this.DeviceID = DeviceID;
    }

    public Integer getParamIndex() {
        return this.ParamIndex;
    }

    public void setParamIndex(Integer ParamIndex) {
        this.ParamIndex = ParamIndex;
    }

    public String getParamValue() {
        return this.ParamValue;
    }

    public void setParamValue(String ParamValue) {
        this.ParamValue = ParamValue;
    }

    public Date getUpdateTime() {
        return this.UpdateTime;
    }

    public void setUpdateTime(Date UpdateTime) {
        this.UpdateTime = UpdateTime;
    }


    @Override
    public String toString() {
        return "DeviceParamValue{" +
                "ID=" + ID +
                ", DeviceID=" + DeviceID +
                ", ParamIndex=" + ParamIndex +
                ", ParamValue=" + ParamValue +
                ", UpdateTime=" + UpdateTime +
                '}';
    }

}
