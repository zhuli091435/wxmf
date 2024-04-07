package com.wxmf.service;

import com.wxmf.pojo.DeviceParamValue;

import java.util.List;
import java.sql.SQLException;

import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

public interface DeviceParamValueService {
    int addDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException;

    int batchAddDeviceParamValue(List<DeviceParamValue> deviceParamValueList) throws SQLException;

    int deleteDeviceParamValueByID(Integer ID) throws SQLException;

    int deleteDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException;

    int batchDeleteDeviceParamValueByIDs(String IDs) throws SQLException;

    int updateDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException;

    DeviceParamValue getDeviceParamValueByID(Integer ID) throws SQLException;

    List<DeviceParamValue> getAllDeviceParamValue() throws SQLException;

    List<DeviceParamValue> getDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException;

    PageBean<DeviceParamValue> getDeviceParamValueWithPagination(PageParam pageParam) throws SQLException;

    PageBean<DeviceParamValue> getDeviceParamValueWithPagination(PageParam pageParam, DeviceParamValue deviceParamValue) throws SQLException;

    int deleteDeviceParamValueByDeviceID(String deviceID) throws SQLException;

    List<DeviceParamValue> getDeviceParamValueByDeviceID(String deviceID) throws SQLException;
}