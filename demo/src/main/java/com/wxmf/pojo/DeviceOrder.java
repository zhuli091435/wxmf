package com.wxmf.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DeviceOrder {
    public final static Integer EXECUTING = 1;
    public final static Integer SUCCEED = 2;
    public final static Integer FAILED = 3;
    /**
     * ID
     */
    private Integer ID;
    /**
     * DeviceID
     */
    private String DeviceID;
    /**
     * OrderName
     */
    private String OrderName;
    /**
     * OrderCode
     */
    private String OrderCode;
    /**
     * OrderType
     */
    private String OrderType;
    /**
     * DistributeTime
     */
    private Date DistributeTime;
    /**
     * BeginExecuteTime
     */
    private Date BeginExecuteTime;
    /**
     * CompleteTime
     */
    private Date CompleteTime;
    /**
     * OrderState
     */
    private Integer OrderState;
    /**
     * Parameter
     */
    private String Parameter;
    /**
     * CurMsgIndex
     */
    private Integer CurMsgIndex;
    /**
     * TotalMsgCount
     */
    private Integer TotalMsgCount;
    /**
     * Percentage
     */
    private BigDecimal Percentage;
    /**
     * Remark
     */
    private String Remark;

    private String OrderResult;

    public DeviceOrder() {
    }


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public Date getDistributeTime() {
        return DistributeTime;
    }

    public void setDistributeTime(Date distributeTime) {
        DistributeTime = distributeTime;
    }

    public Date getBeginExecuteTime() {
        return BeginExecuteTime;
    }

    public void setBeginExecuteTime(Date beginExecuteTime) {
        BeginExecuteTime = beginExecuteTime;
    }

    public Date getCompleteTime() {
        return CompleteTime;
    }

    public void setCompleteTime(Date completeTime) {
        CompleteTime = completeTime;
    }

    public Integer getOrderState() {
        return OrderState;
    }

    public void setOrderState(Integer orderState) {
        OrderState = orderState;
    }

    public String getParameter() {
        return Parameter;
    }

    public void setParameter(String parameter) {
        Parameter = parameter;
    }

    public Integer getCurMsgIndex() {
        return CurMsgIndex;
    }

    public void setCurMsgIndex(Integer curMsgIndex) {
        CurMsgIndex = curMsgIndex;
    }

    public Integer getTotalMsgCount() {
        return TotalMsgCount;
    }

    public void setTotalMsgCount(Integer totalMsgCount) {
        TotalMsgCount = totalMsgCount;
    }

    public BigDecimal getPercentage() {
        return Percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        Percentage = percentage;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getOrderResult() {
        return OrderResult;
    }

    public void setOrderResult(String orderResult) {
        OrderResult = orderResult;
    }

    @Override
    public String toString() {
        return "DeviceOrder{" + "ID=" + ID + ", DeviceID=" + DeviceID + ", OrderName=" + OrderName + ", OrderCode=" + OrderCode + ", OrderType=" + OrderType + ", DistributeTime=" + DistributeTime + ", BeginExecuteTime=" + BeginExecuteTime + ", CompleteTime=" + CompleteTime + ", OrderState=" + OrderState + ", Parameter=" + Parameter + ", CurMsgIndex=" + CurMsgIndex + ", TotalMsgCount=" + TotalMsgCount + ", Percentage=" + Percentage + ", Remark=" + Remark + '}';
    }

}
