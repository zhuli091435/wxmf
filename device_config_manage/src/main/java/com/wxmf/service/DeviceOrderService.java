package com.wxmf.service;

import com.wxmf.pojo.DeviceOrder;

import java.util.List;
import java.sql.SQLException;

import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

public interface DeviceOrderService {
    int addDeviceOrder(DeviceOrder deviceOrder) throws SQLException;

    int batchAddDeviceOrder(List<DeviceOrder> deviceOrderList) throws SQLException;

    int deleteDeviceOrderByID(Integer ID) throws SQLException;

    int deleteDeviceOrder(DeviceOrder deviceOrder) throws SQLException;

    int batchDeleteDeviceOrderByIDs(String IDs) throws SQLException;

    int updateDeviceOrder(DeviceOrder deviceOrder) throws SQLException;

    DeviceOrder getDeviceOrderByID(Integer ID) throws SQLException;

    List<DeviceOrder> getAllDeviceOrder() throws SQLException;

    List<DeviceOrder> getDeviceOrder(DeviceOrder deviceOrder) throws SQLException;

    PageBean<DeviceOrder> getDeviceOrderWithPagination(PageParam pageParam) throws SQLException;

    PageBean<DeviceOrder> getDeviceOrderWithPagination(PageParam pageParam, DeviceOrder deviceOrder) throws SQLException;

    List<DeviceOrder> getAllUnexecutedDeviceOrder() throws SQLException;

    List<DeviceOrder> getDeviceOrderByStatus(Integer orderState) throws SQLException;

    List<DeviceOrder> getDeviceOrderByStatusAndDeviceID(Integer orderState, String deviceID) throws SQLException;
}