package com.wxmf.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

public class DeviceInfo {
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
     * IPLocation
     */
    private String IPLocation;
    /**
     * ProjectID
     */
    private Integer ProjectID;
    /**
     * DeliveryTime
     */
    private Date DeliveryTime;
    /**
     * InstallationTime
     */
    private Date InstallationTime;
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

    private String ManageChannel;
    private String DomainName;
    private String CustomizedFeatures;
    private String ProgramName;
    private String Status;
    private String Remark;

    public DeviceInfo() {
    }


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }

    public String getHardwareVersion() {
        return HardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        HardwareVersion = hardwareVersion;
    }

    public String getSystemProgramVersion() {
        return SystemProgramVersion;
    }

    public void setSystemProgramVersion(String systemProgramVersion) {
        SystemProgramVersion = systemProgramVersion;
    }

    public String getApplicationVersion() {
        return ApplicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        ApplicationVersion = applicationVersion;
    }

    public String getProvincesCitiesCode() {
        return ProvincesCitiesCode;
    }

    public void setProvincesCitiesCode(String provincesCitiesCode) {
        ProvincesCitiesCode = provincesCitiesCode;
    }

    public String getDeviceAddress() {
        return DeviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        DeviceAddress = deviceAddress;
    }

    public BigDecimal getBatteryVoltage() {
        return BatteryVoltage;
    }

    public void setBatteryVoltage(BigDecimal batteryVoltage) {
        BatteryVoltage = batteryVoltage;
    }

    public BigDecimal getChargingVoltage() {
        return ChargingVoltage;
    }

    public void setChargingVoltage(BigDecimal chargingVoltage) {
        ChargingVoltage = chargingVoltage;
    }

    public BigDecimal getTemperature() {
        return Temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        Temperature = temperature;
    }

    public BigDecimal getHumidity() {
        return Humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        Humidity = humidity;
    }

    public BigDecimal getSignal() {
        return Signal;
    }

    public void setSignal(BigDecimal signal) {
        Signal = signal;
    }

    public String getIPLocation() {
        return IPLocation;
    }

    public void setIPLocation(String IPLocation) {
        this.IPLocation = IPLocation;
    }

    public Integer getProjectID() {
        return ProjectID;
    }

    public void setProjectID(Integer projectID) {
        ProjectID = projectID;
    }

    public Date getDeliveryTime() {
        return DeliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        DeliveryTime = deliveryTime;
    }

    public Date getInstallationTime() {
        return InstallationTime;
    }

    public void setInstallationTime(Date installationTime) {
        InstallationTime = installationTime;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getCCID() {
        return CCID;
    }

    public void setCCID(String CCID) {
        this.CCID = CCID;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public String getAddressEncodingFormat() {
        return AddressEncodingFormat;
    }

    public void setAddressEncodingFormat(String addressEncodingFormat) {
        AddressEncodingFormat = addressEncodingFormat;
    }

    public String getHighAddress() {
        return HighAddress;
    }

    public void setHighAddress(String highAddress) {
        HighAddress = highAddress;
    }

    public String getManageChannel() {
        return ManageChannel;
    }

    public void setManageChannel(String manageChannel) {
        ManageChannel = manageChannel;
    }

    public String getDomainName() {
        return DomainName;
    }

    public void setDomainName(String domainName) {
        DomainName = domainName;
    }

    public String getCustomizedFeatures() {
        return CustomizedFeatures;
    }

    public void setCustomizedFeatures(String customizedFeatures) {
        CustomizedFeatures = customizedFeatures;
    }

    public String getProgramName() {
        return ProgramName;
    }

    public void setProgramName(String programName) {
        ProgramName = programName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "ID=" + ID +
                ", DeviceName='" + DeviceName + '\'' +
                ", UpdateTime=" + UpdateTime +
                ", HardwareVersion='" + HardwareVersion + '\'' +
                ", SystemProgramVersion='" + SystemProgramVersion + '\'' +
                ", ApplicationVersion='" + ApplicationVersion + '\'' +
                ", ProvincesCitiesCode='" + ProvincesCitiesCode + '\'' +
                ", DeviceAddress='" + DeviceAddress + '\'' +
                ", BatteryVoltage=" + BatteryVoltage +
                ", ChargingVoltage=" + ChargingVoltage +
                ", Temperature=" + Temperature +
                ", Humidity=" + Humidity +
                ", Signal=" + Signal +
                ", IPLocation='" + IPLocation + '\'' +
                ", ProjectID=" + ProjectID +
                ", DeliveryTime=" + DeliveryTime +
                ", InstallationTime=" + InstallationTime +
                ", DeviceID='" + DeviceID + '\'' +
                ", CCID='" + CCID + '\'' +
                ", IMEI='" + IMEI + '\'' +
                ", IMSI='" + IMSI + '\'' +
                ", MSISDN='" + MSISDN + '\'' +
                ", AddressEncodingFormat='" + AddressEncodingFormat + '\'' +
                ", HighAddress='" + HighAddress + '\'' +
                ", ManageChannel='" + ManageChannel + '\'' +
                ", DomainName='" + DomainName + '\'' +
                ", CustomizedFeatures='" + CustomizedFeatures + '\'' +
                ", ProgramName='" + ProgramName + '\'' +
                ", Status='" + Status + '\'' +
                ", Remark='" + Remark + '\'' +
                '}';
    }
}
