package com.wxmf.service.impl;

import com.wxmf.pojo.HisDeviceInfo;
import java.util.List;
import com.wxmf.service.HisDeviceInfoService;
import com.wxmf.dao.HisDeviceInfoDao;
import com.wxmf.dao.impl.HisDeviceInfoDaoImpl;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;
import java.sql.SQLException;

public class HisDeviceInfoServiceImpl implements HisDeviceInfoService {

    private HisDeviceInfoDao hisDeviceInfoDao = new HisDeviceInfoDaoImpl();

    @Override
    public int addHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException{
        int res = hisDeviceInfoDao.insertHisDeviceInfo(hisDeviceInfo);
        return res;
    }

	@Override
    public int batchAddHisDeviceInfo(List<HisDeviceInfo> hisDeviceInfoList) throws SQLException{
		int res = hisDeviceInfoDao.batchInsertHisDeviceInfo(hisDeviceInfoList);
		return res;
	}

    @Override
    public int deleteHisDeviceInfoByID(Integer ID) throws SQLException{
        int res = hisDeviceInfoDao.deleteHisDeviceInfoByID(ID);
        return res;
    }
    
 	@Override
	public int deleteHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException{
	 	int res = hisDeviceInfoDao.deleteHisDeviceInfoByCondition(hisDeviceInfo);
        return res;
	}
	
	@Override
	public int batchDeleteHisDeviceInfoByIDs(String IDs) throws SQLException{
		int res = hisDeviceInfoDao.batchDeleteHisDeviceInfoByIDs(IDs);
        return res;
	}
	
    @Override
    public int updateHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException{
        int res = hisDeviceInfoDao.updateHisDeviceInfo(hisDeviceInfo);
        return res;
    }

    @Override
    public HisDeviceInfo getHisDeviceInfoByID(Integer ID) throws SQLException{
        HisDeviceInfo res = hisDeviceInfoDao.selectHisDeviceInfoByID(ID);
        return res;
    }

    @Override
    public List<HisDeviceInfo> getAllHisDeviceInfo() throws SQLException{
        List<HisDeviceInfo> res = hisDeviceInfoDao.selectAllHisDeviceInfo();
        return res;
    }

	@Override
	public 	List<HisDeviceInfo> getHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException{
		List<HisDeviceInfo> res = hisDeviceInfoDao.selectHisDeviceInfoByCondition(hisDeviceInfo);
		return res;
	}
	
    @Override
    public PageBean<HisDeviceInfo> getHisDeviceInfoWithPagination(PageParam pageParam) throws SQLException{
        long count = hisDeviceInfoDao.selectCount();
        PageBean<HisDeviceInfo> res = new PageBean<>(pageParam, (int)count);
        List<HisDeviceInfo> data = hisDeviceInfoDao.selectHisDeviceInfoWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

	@Override
	public PageBean<HisDeviceInfo> getHisDeviceInfoWithPagination(PageParam pageParam, HisDeviceInfo hisDeviceInfo) throws SQLException{
        long count = hisDeviceInfoDao.selectCountByCondition(hisDeviceInfo);
        PageBean<HisDeviceInfo> res = new PageBean<>(pageParam, (int)count);		
		List<HisDeviceInfo> data = hisDeviceInfoDao.selectHisDeviceInfoWithPaginationByCondition(pageParam,hisDeviceInfo);
		res.setRecords(data);
        return res;
	}
  
}