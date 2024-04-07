package com.wxmf.service;

import com.wxmf.pojo.DeviceParam;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

public interface DeviceParamService {
    int addDeviceParam(DeviceParam deviceParam) throws SQLException;
    
    int batchAddDeviceParam(List<DeviceParam> deviceParamList) throws SQLException;

    int deleteDeviceParamByID(Integer ID) throws SQLException;

	int deleteDeviceParam(DeviceParam deviceParam) throws SQLException;
	
	int batchDeleteDeviceParamByIDs(String IDs) throws SQLException;
	
    int updateDeviceParam(DeviceParam deviceParam) throws SQLException;

    DeviceParam getDeviceParamByID(Integer ID) throws SQLException;

    List<DeviceParam> getAllDeviceParam() throws SQLException;

	List<DeviceParam> getDeviceParam(DeviceParam deviceParam) throws SQLException;
	
    PageBean<DeviceParam> getDeviceParamWithPagination(PageParam pageParam) throws SQLException;

	PageBean<DeviceParam> getDeviceParamWithPagination(PageParam pageParam, DeviceParam deviceParam) throws SQLException;
  
}