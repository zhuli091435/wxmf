package com.wxmf.utils;

import java.math.BigDecimal;

public class WXMFProtocolData {

    private String identification;
    private String response;
    private String equipDate;
    private String deviceID;

    private String hardwareVersion;

    private String systemProgramVersion;

    private String applicationVersion;

    private String provincesCitiesCode;

    private String deviceAddress;

    private BigDecimal batteryVoltage;

    private BigDecimal chargingVoltage;

    private BigDecimal temperature;

    private BigDecimal humidity;

    private BigDecimal signal;

    private Integer subpackageNumber;

    private byte[] bytes;

    public WXMFProtocolData() {
    }

    public WXMFProtocolData(String identification, String response, String equipDate, String deviceID, String hardwareVersion, String systemProgramVersion, String applicationVersion, String provincesCitiesCode, String deviceAddress, BigDecimal batteryVoltage, BigDecimal chargingVoltage, BigDecimal temperature, BigDecimal humidity, BigDecimal signal, Integer subpackageNumber) {
        this.identification = identification;
        this.response = response;
        this.equipDate = equipDate;
        this.deviceID = deviceID;
        this.hardwareVersion = hardwareVersion;
        this.systemProgramVersion = systemProgramVersion;
        this.applicationVersion = applicationVersion;
        this.provincesCitiesCode = provincesCitiesCode;
        this.deviceAddress = deviceAddress;
        this.batteryVoltage = batteryVoltage;
        this.chargingVoltage = chargingVoltage;
        this.temperature = temperature;
        this.humidity = humidity;
        this.signal = signal;
        this.subpackageNumber = subpackageNumber;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getEquipDate() {
        return equipDate;
    }

    public void setEquipDate(String equipDate) {
        this.equipDate = equipDate;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getSystemProgramVersion() {
        return systemProgramVersion;
    }

    public void setSystemProgramVersion(String systemProgramVersion) {
        this.systemProgramVersion = systemProgramVersion;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getProvincesCitiesCode() {
        return provincesCitiesCode;
    }

    public void setProvincesCitiesCode(String provincesCitiesCode) {
        this.provincesCitiesCode = provincesCitiesCode;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public BigDecimal getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(BigDecimal batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public BigDecimal getChargingVoltage() {
        return chargingVoltage;
    }

    public void setChargingVoltage(BigDecimal chargingVoltage) {
        this.chargingVoltage = chargingVoltage;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getSignal() {
        return signal;
    }

    public void setSignal(BigDecimal signal) {
        this.signal = signal;
    }

    public Integer getSubpackageNumber() {
        return subpackageNumber;
    }

    public void setSubpackageNumber(Integer subpackageNumber) {
        this.subpackageNumber = subpackageNumber;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
