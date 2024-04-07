package com.wxmf.service;

import com.wxmf.pojo.HisDeviceInfo;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

public interface HisDeviceInfoService {
    int addHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException;
    
    int batchAddHisDeviceInfo(List<HisDeviceInfo> hisDeviceInfoList) throws SQLException;

    int deleteHisDeviceInfoByID(Integer ID) throws SQLException;

	int deleteHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException;
	
	int batchDeleteHisDeviceInfoByIDs(String IDs) throws SQLException;
	
    int updateHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException;

    HisDeviceInfo getHisDeviceInfoByID(Integer ID) throws SQLException;

    List<HisDeviceInfo> getAllHisDeviceInfo() throws SQLException;

	List<HisDeviceInfo> getHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException;
	
    PageBean<HisDeviceInfo> getHisDeviceInfoWithPagination(PageParam pageParam) throws SQLException;

	PageBean<HisDeviceInfo> getHisDeviceInfoWithPagination(PageParam pageParam, HisDeviceInfo hisDeviceInfo) throws SQLException;
  
}