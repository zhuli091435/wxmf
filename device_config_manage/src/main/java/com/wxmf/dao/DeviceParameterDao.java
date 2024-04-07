package com.wxmf.dao;

import com.wxmf.pojo.DeviceParameter;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageParam;

public interface DeviceParameterDao {
    int insertDeviceParameter(DeviceParameter deviceParameter) throws SQLException;

	int batchInsertDeviceParameter(List<DeviceParameter> deviceParameterList) throws SQLException;

    int deleteDeviceParameterByID(Integer ID) throws SQLException;

	int deleteDeviceParameterByCondition(DeviceParameter deviceParameter) throws SQLException;

	int batchDeleteDeviceParameterByIDs(String IDs) throws SQLException;

    int updateDeviceParameter(DeviceParameter deviceParameter) throws SQLException;

    long selectCount() throws SQLException;
    
    long selectCountByCondition(DeviceParameter deviceParameter) throws SQLException;

    DeviceParameter selectDeviceParameterByID(Integer ID) throws SQLException;

    List<DeviceParameter> selectAllDeviceParameter() throws SQLException;

	List<DeviceParameter> selectDeviceParameterByCondition(DeviceParameter deviceParameter) throws SQLException;

    List<DeviceParameter> selectDeviceParameterWithPagination(PageParam pageParam) throws SQLException;

    List<DeviceParameter> selectDeviceParameterWithPaginationByCondition(PageParam pageParam, DeviceParameter deviceParameter) throws SQLException;
    
}