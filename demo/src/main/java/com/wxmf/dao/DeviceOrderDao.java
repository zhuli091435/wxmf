package com.wxmf.dao;

import com.wxmf.pojo.DeviceOrder;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;
import java.util.List;

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
}