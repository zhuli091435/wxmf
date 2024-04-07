package com.wxmf.dao;

import com.wxmf.pojo.DeviceInfo;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;
import java.util.List;

public interface DeviceInfoDao {
    int insertDeviceInfo(DeviceInfo deviceInfo) throws SQLException;

	int batchInsertDeviceInfo(List<DeviceInfo> deviceInfoList) throws SQLException;

    int deleteDeviceInfoByID(Integer ID) throws SQLException;

	int deleteDeviceInfoByCondition(DeviceInfo deviceInfo) throws SQLException;

	int batchDeleteDeviceInfoByIDs(String IDs) throws SQLException;

    int updateDeviceInfo(DeviceInfo deviceInfo) throws SQLException;

    long selectCount() throws SQLException;
    
    long selectCountByCondition(DeviceInfo deviceInfo) throws SQLException;

    DeviceInfo selectDeviceInfoByID(Integer ID) throws SQLException;

    List<DeviceInfo> selectAllDeviceInfo() throws SQLException;

	List<DeviceInfo> selectDeviceInfoByCondition(DeviceInfo deviceInfo) throws SQLException;

    List<DeviceInfo> selectDeviceInfoWithPagination(PageParam pageParam) throws SQLException;

    List<DeviceInfo> selectDeviceInfoWithPaginationByCondition(PageParam pageParam, DeviceInfo deviceInfo) throws SQLException;

    DeviceInfo selectDeviceInfoByDeviceID(String deviceID) throws SQLException;
}