package com.wxmf.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;

public class WXMFProtocolUtil {
    public static WXMFProtocol createWXMFProtocol(byte[] bytes) {


        WXMFProtocol wxmfProtocol = new WXMFProtocol();


        //起始符
        wxmfProtocol.setStartCharacter(CommonUil.byteToHexString(bytes[0]));
        //特征码
        wxmfProtocol.setSignatureCode(CommonUil.byteToHexString(bytes[1]));
        //流水号
        wxmfProtocol.setSerialNumber(bytes[2] & 0xFF);
        //遥测站地址
        wxmfProtocol.setTelemetryStationAddress(CommonUil.byteArrayToHexString(bytes, 3, 7));
        //长度

        wxmfProtocol.setLength(Integer.parseInt(CommonUil.byteArrayToHexString(bytes, 7, 9), 16));
        //功能码
        wxmfProtocol.setFunctionCode(CommonUil.byteToHexString(bytes[9]));

        //数据域
        WXMFProtocolData protocolData = new WXMFProtocolData();
        //1字节 系统/应用 标识（00=系统，01=应用）
        protocolData.setIdentification(CommonUil.byteToHexString(bytes[10]));
        //+ 1字节应答信息（00=执行失败，01=执行成功）
        protocolData.setResponse(CommonUil.byteToHexString(bytes[11]));
        //+ 6字节 时间（年月日时分秒，十进制BCD码，全为0为老设备，时间为新设备）
        protocolData.setEquipDate(CommonUil.byteArrayToHexString(bytes, 12, 18));
        //+ 12字节唯一ID
        protocolData.setDeviceID(CommonUil.byteArrayToHexString(bytes, 18, 30));
        //+ 4字节当前硬件版本号
        protocolData.setHardwareVersion(CommonUil.byteArrayToHexString(bytes, 30, 34));
        //+ 4字节当前系统程序版本号
        protocolData.setSystemProgramVersion(CommonUil.byteArrayToHexString(bytes, 34, 38));
        //+ 4字节当前应用程序版本号
        protocolData.setApplicationVersion(CommonUil.byteArrayToHexString(bytes, 38, 42));
        //+ 4字节省市地址
        protocolData.setProvincesCitiesCode(CommonUil.byteArrayToHexString(bytes, 42, 46));
        //+ 4字节本机地址
        protocolData.setDeviceAddress(CommonUil.byteArrayToHexString(bytes, 46, 50));
        //+ 2字节电池电压（单位0.1V）

        double batteryVoltage = Integer.parseInt(CommonUil.byteArrayToHexString(bytes, 50, 52), 16) / 10.0;
        protocolData.setBatteryVoltage(new BigDecimal(batteryVoltage));
        //+ 2字节充电电压（单位0.1V）
        double chargingVoltage = Integer.parseInt(CommonUil.byteArrayToHexString(bytes, 52, 54), 16) / 10.0;
        protocolData.setChargingVoltage(new BigDecimal(chargingVoltage));
        //+ 2字节温度（单位0.1度，有符号）
        double temperature = Integer.parseInt(CommonUil.byteArrayToHexString(bytes, 54, 56), 16) / 10.0;
        protocolData.setTemperature(new BigDecimal(temperature));
        //+ 1字节湿度（单位%，00~64）
        protocolData.setHumidity(new BigDecimal(bytes[56] & 0xFF));
        //+ 1字节4G信号强度（00~1F）
        protocolData.setSignal(new BigDecimal(bytes[57] & 0xFF));
        //+ 2字节分包号
        int subpackageNumber = Integer.parseInt(CommonUil.byteArrayToHexString(bytes, 58, 60), 16);
        protocolData.setSubpackageNumber(subpackageNumber);

        protocolData.setBytes(Arrays.copyOfRange(bytes, 60, bytes.length - 2));

        wxmfProtocol.setProtocolData(protocolData);

        //校验码
        wxmfProtocol.setCheckCode(CommonUil.byteToHexString(bytes[bytes.length - 2]));
        //结束符
        wxmfProtocol.setEndCharacter(CommonUil.byteToHexString(bytes[bytes.length - 1]));

        return wxmfProtocol;
    }

    public static String getSumCheckValue(String hexStr) {
        int sum = 0;
        for (int i = 0; i < hexStr.length() / 2; i++) {
            sum += Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16);
        }
        String sumHex = Integer.toHexString(sum);

        return sumHex.substring(sumHex.length() - 2).toUpperCase();
    }

    public static String getApplicationSumCheckValue(byte[] bytes) {

        ArrayUtils.reverse(bytes);//反转数组
        long sum = 0;
        for (int i = 0; i < bytes.length / 4; i++) {
            sum += Long.parseLong(CommonUil.byteArrayToHexString(bytes, i * 4, (i + 1) * 4), 16);
        }
        String sumHex = Long.toHexString(sum);

        if (sumHex.length() > 8) {
            return sumHex.substring(sumHex.length() - 8).toUpperCase();
        }
        sumHex = StringUtils.leftPad(sumHex, 8, '0');
        return sumHex.toUpperCase();
    }
}

