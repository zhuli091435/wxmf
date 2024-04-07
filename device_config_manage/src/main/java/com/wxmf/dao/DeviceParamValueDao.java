package com.wxmf.dao;

import com.wxmf.pojo.DeviceParamValue;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageParam;

public interface DeviceParamValueDao {
    int insertDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException;

	int batchInsertDeviceParamValue(List<DeviceParamValue> deviceParamValueList) throws SQLException;

    int deleteDeviceParamValueByID(Integer ID) throws SQLException;

	int deleteDeviceParamValueByCondition(DeviceParamValue deviceParamValue) throws SQLException;

	int batchDeleteDeviceParamValueByIDs(String IDs) throws SQLException;

    int updateDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException;

    long selectCount() throws SQLException;
    
    long selectCountByCondition(DeviceParamValue deviceParamValue) throws SQLException;

    DeviceParamValue selectDeviceParamValueByID(Integer ID) throws SQLException;

    List<DeviceParamValue> selectAllDeviceParamValue() throws SQLException;

	List<DeviceParamValue> selectDeviceParamValueByCondition(DeviceParamValue deviceParamValue) throws SQLException;

    List<DeviceParamValue> selectDeviceParamValueWithPagination(PageParam pageParam) throws SQLException;

    List<DeviceParamValue> selectDeviceParamValueWithPaginationByCondition(PageParam pageParam, DeviceParamValue deviceParamValue) throws SQLException;

    int deleteDeviceParamValueByDeviceID(String deviceID) throws SQLException;

    List<DeviceParamValue> getDeviceParamValueByDeviceID(String deviceID) throws SQLException;
}