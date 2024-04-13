package com.wxmf.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

@Data
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
    private String ICCID2;
    private String IMSI2;

}
