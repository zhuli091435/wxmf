package com.wxmf.service;

import com.wxmf.pojo.DeviceInfo;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

public interface DeviceInfoService {
    int addDeviceInfo(DeviceInfo deviceInfo) throws SQLException;
    
    int batchAddDeviceInfo(List<DeviceInfo> deviceInfoList) throws SQLException;

    int deleteDeviceInfoByID(Integer ID) throws SQLException;

	int deleteDeviceInfo(DeviceInfo deviceInfo) throws SQLException;
	
	int batchDeleteDeviceInfoByIDs(String IDs) throws SQLException;
	
    int updateDeviceInfo(DeviceInfo deviceInfo) throws SQLException;

    DeviceInfo getDeviceInfoByID(Integer ID) throws SQLException;

    List<DeviceInfo> getAllDeviceInfo() throws SQLException;

	List<DeviceInfo> getDeviceInfo(DeviceInfo deviceInfo) throws SQLException;
	
    PageBean<DeviceInfo> getDeviceInfoWithPagination(PageParam pageParam) throws SQLException;

	PageBean<DeviceInfo> getDeviceInfoWithPagination(PageParam pageParam, DeviceInfo deviceInfo) throws SQLException;

    DeviceInfo getDeviceInfoByDeviceID(String deviceID) throws SQLException;
}