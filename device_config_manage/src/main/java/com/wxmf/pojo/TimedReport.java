package com.wxmf.pojo;

import java.util.Date;
import java.lang.String;
import java.lang.Integer;

public class TimedReport {
	/**
    * ID
    */
    private Integer ID;
	/**
    * DeviceID
    */
    private String DeviceID;
	/**
    * ReportTime
    */
    private Date ReportTime;
	/**
    * ReportData
    */
    private String ReportData;

    public TimedReport(){
    }

    public TimedReport(Integer ID, String DeviceID, Date ReportTime, String ReportData){
        this.ID=ID;
        this.DeviceID=DeviceID;
        this.ReportTime=ReportTime;
        this.ReportData=ReportData;
    }

    public Integer getID(){
        return this.ID;
    }
    public void setID(Integer ID){
        this.ID = ID;
    }

    public String getDeviceID(){
        return this.DeviceID;
    }
    public void setDeviceID(String DeviceID){
        this.DeviceID = DeviceID;
    }

    public Date getReportTime(){
        return this.ReportTime;
    }
    public void setReportTime(Date ReportTime){
        this.ReportTime = ReportTime;
    }

    public String getReportData(){
        return this.ReportData;
    }
    public void setReportData(String ReportData){
        this.ReportData = ReportData;
    }


    @Override
    public String toString() {
        return "TimedReport{" +
                "ID=" + ID +
                ", DeviceID=" + DeviceID +
                ", ReportTime=" + ReportTime +
                ", ReportData=" + ReportData +
                '}';
    }

}
