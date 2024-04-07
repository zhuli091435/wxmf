package com.wxmf.pojo;

import java.lang.String;
import java.lang.Integer;

public class OrderDetail {

    public final static Integer UNEXECUTED = 0;
    public final static Integer SUCCEED = 1;
    public final static Integer FAILED = 2;
    /**
     * ID
     */
    private Integer ID;
    /**
     * OrderID
     */
    private Integer OrderID;
    /**
     * CurPackageNumber
     */
    private Integer CurPackageNumber;
    /**
     * TotalPackageNumber
     */
    private Integer TotalPackageNumber;
    /**
     * MsgType
     */
    private String MsgType;
    /**
     * MsgContent
     */
    private String MsgContent;
    /**
     * MsgState
     */
    private Integer MsgState;
    /**
     * ExecuteCount
     */
    private Integer ExecuteCount;
    /**
     * Sort
     */
    private Integer Sort;

    public OrderDetail() {
    }

    public OrderDetail(Integer ID, Integer OrderID, Integer CurPackageNumber, Integer TotalPackageNumber, String MsgType, String MsgContent, Integer MsgState, Integer ExecuteCount, Integer Sort) {
        this.ID = ID;
        this.OrderID = OrderID;
        this.CurPackageNumber = CurPackageNumber;
        this.TotalPackageNumber = TotalPackageNumber;
        this.MsgType = MsgType;
        this.MsgContent = MsgContent;
        this.MsgState = MsgState;
        this.ExecuteCount = ExecuteCount;
        this.Sort = Sort;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getOrderID() {
        return this.OrderID;
    }

    public void setOrderID(Integer OrderID) {
        this.OrderID = OrderID;
    }

    public Integer getCurPackageNumber() {
        return this.CurPackageNumber;
    }

    public void setCurPackageNumber(Integer CurPackageNumber) {
        this.CurPackageNumber = CurPackageNumber;
    }

    public Integer getTotalPackageNumber() {
        return this.TotalPackageNumber;
    }

    public void setTotalPackageNumber(Integer TotalPackageNumber) {
        this.TotalPackageNumber = TotalPackageNumber;
    }

    public String getMsgType() {
        return this.MsgType;
    }

    public void setMsgType(String MsgType) {
        this.MsgType = MsgType;
    }

    public String getMsgContent() {
        return this.MsgContent;
    }

    public void setMsgContent(String MsgContent) {
        this.MsgContent = MsgContent;
    }

    public Integer getMsgState() {
        return this.MsgState;
    }

    public void setMsgState(Integer MsgState) {
        this.MsgState = MsgState;
    }

    public Integer getExecuteCount() {
        return this.ExecuteCount;
    }

    public void setExecuteCount(Integer ExecuteCount) {
        this.ExecuteCount = ExecuteCount;
    }

    public Integer getSort() {
        return this.Sort;
    }

    public void setSort(Integer Sort) {
        this.Sort = Sort;
    }


    @Override
    public String toString() {
        return "OrderDetail{" +
                "ID=" + ID +
                ", OrderID=" + OrderID +
                ", CurPackageNumber=" + CurPackageNumber +
                ", TotalPackageNumber=" + TotalPackageNumber +
                ", MsgType=" + MsgType +
                ", MsgContent=" + MsgContent +
                ", MsgState=" + MsgState +
                ", ExecuteCount=" + ExecuteCount +
                ", Sort=" + Sort +
                '}';
    }

}
