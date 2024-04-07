package com.wxmf.dao;

import com.wxmf.pojo.DeviceParam;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageParam;

public interface DeviceParamDao {
    int insertDeviceParam(DeviceParam deviceParam) throws SQLException;

	int batchInsertDeviceParam(List<DeviceParam> deviceParamList) throws SQLException;

    int deleteDeviceParamByID(Integer ID) throws SQLException;

	int deleteDeviceParamByCondition(DeviceParam deviceParam) throws SQLException;

	int batchDeleteDeviceParamByIDs(String IDs) throws SQLException;

    int updateDeviceParam(DeviceParam deviceParam) throws SQLException;

    long selectCount() throws SQLException;
    
    long selectCountByCondition(DeviceParam deviceParam) throws SQLException;

    DeviceParam selectDeviceParamByID(Integer ID) throws SQLException;

    List<DeviceParam> selectAllDeviceParam() throws SQLException;

	List<DeviceParam> selectDeviceParamByCondition(DeviceParam deviceParam) throws SQLException;

    List<DeviceParam> selectDeviceParamWithPagination(PageParam pageParam) throws SQLException;

    List<DeviceParam> selectDeviceParamWithPaginationByCondition(PageParam pageParam, DeviceParam deviceParam) throws SQLException;
    
}