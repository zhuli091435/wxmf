package com.wxmf.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DeviceInfo {
    /**
     * ID
     */
    private Integer ID;
    /**
     * DeviceID
     */
    private String DeviceID;
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

    public DeviceInfo() {
    }

    public DeviceInfo(Integer ID, String DeviceID, Date UpdateTime, String HardwareVersion, String SystemProgramVersion, String ApplicationVersion, String ProvincesCitiesCode, String DeviceAddress, BigDecimal BatteryVoltage, BigDecimal ChargingVoltage, BigDecimal Temperature, BigDecimal Humidity, BigDecimal Signal) {
        this.ID = ID;
        this.DeviceID = DeviceID;
        this.UpdateTime = UpdateTime;
        this.HardwareVersion = HardwareVersion;
        this.SystemProgramVersion = SystemProgramVersion;
        this.ApplicationVersion = ApplicationVersion;
        this.ProvincesCitiesCode = ProvincesCitiesCode;
        this.DeviceAddress = DeviceAddress;
        this.BatteryVoltage = BatteryVoltage;
        this.ChargingVoltage = ChargingVoltage;
        this.Temperature = Temperature;
        this.Humidity = Humidity;
        this.Signal = Signal;
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

    public Date getUpdateTime() {
        return this.UpdateTime;
    }

    public void setUpdateTime(Date UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public String getHardwareVersion() {
        return this.HardwareVersion;
    }

    public void setHardwareVersion(String HardwareVersion) {
        this.HardwareVersion = HardwareVersion;
    }

    public String getSystemProgramVersion() {
        return this.SystemProgramVersion;
    }

    public void setSystemProgramVersion(String SystemProgramVersion) {
        this.SystemProgramVersion = SystemProgramVersion;
    }

    public String getApplicationVersion() {
        return this.ApplicationVersion;
    }

    public void setApplicationVersion(String ApplicationVersion) {
        this.ApplicationVersion = ApplicationVersion;
    }

    public String getProvincesCitiesCode() {
        return this.ProvincesCitiesCode;
    }

    public void setProvincesCitiesCode(String ProvincesCitiesCode) {
        this.ProvincesCitiesCode = ProvincesCitiesCode;
    }

    public String getDeviceAddress() {
        return this.DeviceAddress;
    }

    public void setDeviceAddress(String DeviceAddress) {
        this.DeviceAddress = DeviceAddress;
    }

    public BigDecimal getBatteryVoltage() {
        return this.BatteryVoltage;
    }

    public void setBatteryVoltage(BigDecimal BatteryVoltage) {
        this.BatteryVoltage = BatteryVoltage;
    }

    public BigDecimal getChargingVoltage() {
        return this.ChargingVoltage;
    }

    public void setChargingVoltage(BigDecimal ChargingVoltage) {
        this.ChargingVoltage = ChargingVoltage;
    }

    public BigDecimal getTemperature() {
        return this.Temperature;
    }

    public void setTemperature(BigDecimal Temperature) {
        this.Temperature = Temperature;
    }

    public BigDecimal getHumidity() {
        return this.Humidity;
    }

    public void setHumidity(BigDecimal Humidity) {
        this.Humidity = Humidity;
    }

    public BigDecimal getSignal() {
        return this.Signal;
    }

    public void setSignal(BigDecimal Signal) {
        this.Signal = Signal;
    }


    @Override
    public String toString() {
        return "DeviceInfo{" +
                "ID=" + ID +
                ", DeviceID=" + DeviceID +
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
                '}';
    }

}
