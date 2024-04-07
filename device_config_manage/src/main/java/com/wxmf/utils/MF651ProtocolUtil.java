package com.wxmf.utils;

public class MF651ProtocolUtil {
    //回执报文
    public String getReceipt(String msgStr) {
        String receipt = "";
        String head = msgStr.substring(0, 4);//帧起始符
        String centerStationID = msgStr.substring(4, 6);//中心站站号
        String telemetryStationID = msgStr.substring(6, 16);//遥测站地址
        String passWord = msgStr.substring(16, 20);//密码
        String functionCode = msgStr.substring(20, 22);//功能码
        switch (functionCode) {
            case "30":
            case "32":
            case "33":
            case "34":
            case "37":
            case "38":
                //测试报,定时报,加报报,小时报,召测报,召测历史数据报
            {
                String mainData = msgStr.substring(26, 44);//02报文起始符,流水号,发报时间

                receipt = head + telemetryStationID + centerStationID + passWord + functionCode + "8008" + mainData + "1B";
                break;
            }
            case "4C":
                //泵站状态自报报
            {
                String mainData = msgStr.substring(26, 44);//02报文起始符,流水号,发报时间

                receipt = head + telemetryStationID + centerStationID + passWord + "32" + "8008" + mainData + "04";
                break;
            }
            case "36":
                //图片报
            {
                String mainData = msgStr.substring(26, 50);//16报文起始符,流水号,发报时间
                receipt = head + telemetryStationID + centerStationID + passWord + functionCode + "800B" + mainData + "1B";
                break;
            }
        }
        String checkCode = CrcUtil.getCRC(receipt);
        receipt = receipt + checkCode;
        return receipt;
    }
}
