package com.wxmf.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

public class HisDeviceInfo {
	/**
    * ID
    */
    private Integer ID;
	/**
    * DeviceName
    */
    private String DeviceName;
	/**
    * UpdateTime
    */
    private Date UpdateTime;
	/**
    * HardwareVersion
    */
    private String HardwareVersion;
	/**
    * SystemProgramVersion
    */
    private String SystemProgramVersion;
	/**
    * ApplicationVersion
    */
    private String ApplicationVersion;
	/**
    * ProvincesCitiesCode
    */
    private String ProvincesCitiesCode;
	/**
    * DeviceAddress
    */
    private String DeviceAddress;
	/**
    * BatteryVoltage
    */
    private BigDecimal BatteryVoltage;
	/**
    * ChargingVoltage
    */
    private BigDecimal ChargingVoltage;
	/**
    * Temperature
    */
    private BigDecimal Temperature;
	/**
    * Humidity
    */
    private BigDecimal Humidity;
	/**
    * Signal
    */
    private BigDecimal Signal;
	/**
    * DeviceID
    */
    private String DeviceID;
	/**
    * CCID
    */
    private String CCID;
	/**
    * IMEI
    */
    private String IMEI;
	/**
    * IMSI
    */
    private String IMSI;
	/**
    * MSISDN
    */
    private String MSISDN;
	/**
    * AddressEncodingFormat
    */
    private String AddressEncodingFormat;
	/**
    * HighAddress
    */
    private String HighAddress;
	/**
    * ManageChannel
    */
    private String ManageChannel;
	/**
    * DomainName
    */
    private String DomainName;

    public HisDeviceInfo(){
    }

    public HisDeviceInfo(Integer ID, String DeviceName, Date UpdateTime, String HardwareVersion, String SystemProgramVersion, String ApplicationVersion, String ProvincesCitiesCode, String DeviceAddress, BigDecimal BatteryVoltage, BigDecimal ChargingVoltage, BigDecimal Temperature, BigDecimal Humidity, BigDecimal Signal, String DeviceID, String CCID, String IMEI, String IMSI, String MSISDN, String AddressEncodingFormat, String HighAddress, String ManageChannel, String DomainName){
        this.ID=ID;
        this.DeviceName=DeviceName;
        this.UpdateTime=UpdateTime;
        this.HardwareVersion=HardwareVersion;
        this.SystemProgramVersion=SystemProgramVersion;
        this.ApplicationVersion=ApplicationVersion;
        this.ProvincesCitiesCode=ProvincesCitiesCode;
        this.DeviceAddress=DeviceAddress;
        this.BatteryVoltage=BatteryVoltage;
        this.ChargingVoltage=ChargingVoltage;
        this.Temperature=Temperature;
        this.Humidity=Humidity;
        this.Signal=Signal;
        this.DeviceID=DeviceID;
        this.CCID=CCID;
        this.IMEI=IMEI;
        this.IMSI=IMSI;
        this.MSISDN=MSISDN;
        this.AddressEncodingFormat=AddressEncodingFormat;
        this.HighAddress=HighAddress;
        this.ManageChannel=ManageChannel;
        this.DomainName=DomainName;
    }

    public Integer getID(){
        return this.ID;
    }
    public void setID(Integer ID){
        this.ID = ID;
    }

    public String getDeviceName(){
        return this.DeviceName;
    }
    public void setDeviceName(String DeviceName){
        this.DeviceName = DeviceName;
    }

    public Date getUpdateTime(){
        return this.UpdateTime;
    }
    public void setUpdateTime(Date UpdateTime){
        this.UpdateTime = UpdateTime;
    }

    public String getHardwareVersion(){
        return this.HardwareVersion;
    }
    public void setHardwareVersion(String HardwareVersion){
        this.HardwareVersion = HardwareVersion;
    }

    public String getSystemProgramVersion(){
        return this.SystemProgramVersion;
    }
    public void setSystemProgramVersion(String SystemProgramVersion){
        this.SystemProgramVersion = SystemProgramVersion;
    }

    public String getApplicationVersion(){
        return this.ApplicationVersion;
    }
    public void setApplicationVersion(String ApplicationVersion){
        this.ApplicationVersion = ApplicationVersion;
    }

    public String getProvincesCitiesCode(){
        return this.ProvincesCitiesCode;
    }
    public void setProvincesCitiesCode(String ProvincesCitiesCode){
        this.ProvincesCitiesCode = ProvincesCitiesCode;
    }

    public String getDeviceAddress(){
        return this.DeviceAddress;
    }
    public void setDeviceAddress(String DeviceAddress){
        this.DeviceAddress = DeviceAddress;
    }

    public BigDecimal getBatteryVoltage(){
        return this.BatteryVoltage;
    }
    public void setBatteryVoltage(BigDecimal BatteryVoltage){
        this.BatteryVoltage = BatteryVoltage;
    }

    public BigDecimal getChargingVoltage(){
        return this.ChargingVoltage;
    }
    public void setChargingVoltage(BigDecimal ChargingVoltage){
        this.ChargingVoltage = ChargingVoltage;
    }

    public BigDecimal getTemperature(){
        return this.Temperature;
    }
    public void setTemperature(BigDecimal Temperature){
        this.Temperature = Temperature;
    }

    public BigDecimal getHumidity(){
        return this.Humidity;
    }
    public void setHumidity(BigDecimal Humidity){
        this.Humidity = Humidity;
    }

    public BigDecimal getSignal(){
        return this.Signal;
    }
    public void setSignal(BigDecimal Signal){
        this.Signal = Signal;
    }

    public String getDeviceID(){
        return this.DeviceID;
    }
    public void setDeviceID(String DeviceID){
        this.DeviceID = DeviceID;
    }

    public String getCCID(){
        return this.CCID;
    }
    public void setCCID(String CCID){
        this.CCID = CCID;
    }

    public String getIMEI(){
        return this.IMEI;
    }
    public void setIMEI(String IMEI){
        this.IMEI = IMEI;
    }

    public String getIMSI(){
        return this.IMSI;
    }
    public void setIMSI(String IMSI){
        this.IMSI = IMSI;
    }

    public String getMSISDN(){
        return this.MSISDN;
    }
    public void setMSISDN(String MSISDN){
        this.MSISDN = MSISDN;
    }

    public String getAddressEncodingFormat(){
        return this.AddressEncodingFormat;
    }
    public void setAddressEncodingFormat(String AddressEncodingFormat){
        this.AddressEncodingFormat = AddressEncodingFormat;
    }

    public String getHighAddress(){
        return this.HighAddress;
    }
    public void setHighAddress(String HighAddress){
        this.HighAddress = HighAddress;
    }

    public String getManageChannel(){
        return this.ManageChannel;
    }
    public void setManageChannel(String ManageChannel){
        this.ManageChannel = ManageChannel;
    }

    public String getDomainName(){
        return this.DomainName;
    }
    public void setDomainName(String DomainName){
        this.DomainName = DomainName;
    }


    @Override
    public String toString() {
        return "HisDeviceInfo{" +
                "ID=" + ID +
                ", DeviceName=" + DeviceName +
                ", UpdateTime=" + UpdateTime +
                ", HardwareVersion=" + HardwareVersion +
                ", SystemProgramVersion=" + SystemProgramVersion +
                ", ApplicationVersion=" + ApplicationVersion +
                ", ProvincesCitiesCode=" + ProvincesCitiesCode +
                ", DeviceAddress=" + DeviceAddress +
                ", BatteryVoltage=" + BatteryVoltage +
                ", ChargingVoltage=" + ChargingVoltage +
                ", Temperature=" + Temperature +
                ", Humidity=" + Humidity +
                ", Signal=" + Signal +
                ", DeviceID=" + DeviceID +
                ", CCID=" + CCID +
                ", IMEI=" + IMEI +
                ", IMSI=" + IMSI +
                ", MSISDN=" + MSISDN +
                ", AddressEncodingFormat=" + AddressEncodingFormat +
                ", HighAddress=" + HighAddress +
                ", ManageChannel=" + ManageChannel +
                ", DomainName=" + DomainName +
                '}';
    }

}
