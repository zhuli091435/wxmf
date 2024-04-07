package com.wxmf.service;

import com.wxmf.pojo.DeviceParameter;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

public interface DeviceParameterService {
    int addDeviceParameter(DeviceParameter deviceParameter) throws SQLException;
    
    int batchAddDeviceParameter(List<DeviceParameter> deviceParameterList) throws SQLException;

    int deleteDeviceParameterByID(Integer ID) throws SQLException;

	int deleteDeviceParameter(DeviceParameter deviceParameter) throws SQLException;
	
	int batchDeleteDeviceParameterByIDs(String IDs) throws SQLException;
	
    int updateDeviceParameter(DeviceParameter deviceParameter) throws SQLException;

    DeviceParameter getDeviceParameterByID(Integer ID) throws SQLException;

    List<DeviceParameter> getAllDeviceParameter() throws SQLException;

	List<DeviceParameter> getDeviceParameter(DeviceParameter deviceParameter) throws SQLException;
	
    PageBean<DeviceParameter> getDeviceParameterWithPagination(PageParam pageParam) throws SQLException;

	PageBean<DeviceParameter> getDeviceParameterWithPagination(PageParam pageParam, DeviceParameter deviceParameter) throws SQLException;
  
}