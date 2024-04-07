package com.wxmf.service;

import com.wxmf.pojo.DeviceOrder;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;
import java.util.List;

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
}