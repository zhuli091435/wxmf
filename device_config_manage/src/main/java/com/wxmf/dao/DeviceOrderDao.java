package com.wxmf.dao;

import com.wxmf.pojo.DeviceOrder;

import java.util.List;
import java.sql.SQLException;

import com.wxmf.utils.PageParam;

public interface DeviceOrderDao {
    int insertDeviceOrder(DeviceOrder deviceOrder) throws SQLException;

    int batchInsertDeviceOrder(List<DeviceOrder> deviceOrderList) throws SQLException;

    int deleteDeviceOrderByID(Integer ID) throws SQLException;

    int deleteDeviceOrderByCondition(DeviceOrder deviceOrder) throws SQLException;

    int batchDeleteDeviceOrderByIDs(String IDs) throws SQLException;

    int updateDeviceOrder(DeviceOrder deviceOrder) throws SQLException;

    long selectCount() throws SQLException;

    long selectCountByCondition(DeviceOrder deviceOrder) throws SQLException;

    DeviceOrder selectDeviceOrderByID(Integer ID) throws SQLException;

    List<DeviceOrder> selectAllDeviceOrder() throws SQLException;

    List<DeviceOrder> selectDeviceOrderByCondition(DeviceOrder deviceOrder) throws SQLException;

    List<DeviceOrder> selectDeviceOrderWithPagination(PageParam pageParam) throws SQLException;

    List<DeviceOrder> selectDeviceOrderWithPaginationByCondition(PageParam pageParam, DeviceOrder deviceOrder) throws SQLException;

    List<DeviceOrder> selectAllUnexecutedDeviceOrder() throws SQLException;

    List<DeviceOrder> selectDeviceOrderByStatus(Integer orderState) throws SQLException;

    List<DeviceOrder> selectDeviceOrderByStatusAndDeviceID(Integer orderState, String deviceID) throws SQLException;
}