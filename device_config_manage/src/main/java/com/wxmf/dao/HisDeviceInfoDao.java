package com.wxmf.dao;

import com.wxmf.pojo.HisDeviceInfo;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageParam;

public interface HisDeviceInfoDao {
    int insertHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException;

	int batchInsertHisDeviceInfo(List<HisDeviceInfo> hisDeviceInfoList) throws SQLException;

    int deleteHisDeviceInfoByID(Integer ID) throws SQLException;

	int deleteHisDeviceInfoByCondition(HisDeviceInfo hisDeviceInfo) throws SQLException;

	int batchDeleteHisDeviceInfoByIDs(String IDs) throws SQLException;

    int updateHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException;

    long selectCount() throws SQLException;
    
    long selectCountByCondition(HisDeviceInfo hisDeviceInfo) throws SQLException;

    HisDeviceInfo selectHisDeviceInfoByID(Integer ID) throws SQLException;

    List<HisDeviceInfo> selectAllHisDeviceInfo() throws SQLException;

	List<HisDeviceInfo> selectHisDeviceInfoByCondition(HisDeviceInfo hisDeviceInfo) throws SQLException;

    List<HisDeviceInfo> selectHisDeviceInfoWithPagination(PageParam pageParam) throws SQLException;

    List<HisDeviceInfo> selectHisDeviceInfoWithPaginationByCondition(PageParam pageParam, HisDeviceInfo hisDeviceInfo) throws SQLException;
    
}