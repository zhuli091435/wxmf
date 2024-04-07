package com.wxmf.utils;

import java.math.BigDecimal;
import java.util.Date;

public class WXMFProtocol {
    public static final String HEARTBEAT_FUNCTION_CODE = "F2";
    public static final String TIMED_REPORT_FUNCTION_CODE = "88";
    public static final String TEST_REPORT_FUNCTION_CODE = "89";

    public static final String ADD_REPORT_FUNCTION_CODE = "8A";
    private String startCharacter;
    private String signatureCode;
    private Integer serialNumber;
    private String telemetryStationAddress;
    private Integer length;
    private String functionCode;
    private String checkCode;
    private String endCharacter;
    private WXMFProtocolData protocolData;

    public WXMFProtocol() {
    }

    public WXMFProtocol(String startCharacter, String signatureCode, Integer serialNumber, String telemetryStationAddress, Integer length, String functionCode, String checkCode, String endCharacter, WXMFProtocolData protocolData) {
        this.startCharacter = startCharacter;
        this.signatureCode = signatureCode;
        this.serialNumber = serialNumber;
        this.telemetryStationAddress = telemetryStationAddress;
        this.length = length;
        this.functionCode = functionCode;
        this.checkCode = checkCode;
        this.endCharacter = endCharacter;
        this.protocolData = protocolData;
    }

    public String getStartCharacter() {
        return startCharacter;
    }

    public void setStartCharacter(String startCharacter) {
        this.startCharacter = startCharacter;
    }

    public String getSignatureCode() {
        return signatureCode;
    }

    public void setSignatureCode(String signatureCode) {
        this.signatureCode = signatureCode;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTelemetryStationAddress() {
        return telemetryStationAddress;
    }

    public void setTelemetryStationAddress(String telemetryStationAddress) {
        this.telemetryStationAddress = telemetryStationAddress;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getEndCharacter() {
        return endCharacter;
    }

    public void setEndCharacter(String endCharacter) {
        this.endCharacter = endCharacter;
    }

    public WXMFProtocolData getProtocolData() {
        return protocolData;
    }

    public void setProtocolData(WXMFProtocolData protocolData) {
        this.protocolData = protocolData;
    }
}
