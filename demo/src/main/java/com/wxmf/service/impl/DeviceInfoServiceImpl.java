package com.wxmf.service.impl;

import com.wxmf.dao.DeviceInfoDao;
import com.wxmf.dao.impl.DeviceInfoDaoImpl;
import com.wxmf.pojo.DeviceInfo;
import com.wxmf.service.DeviceInfoService;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;
import java.util.List;

public class DeviceInfoServiceImpl implements DeviceInfoService {

    private DeviceInfoDao deviceInfoDao = new DeviceInfoDaoImpl();

    @Override
    public int addDeviceInfo(DeviceInfo deviceInfo) throws SQLException{
        int res = deviceInfoDao.insertDeviceInfo(deviceInfo);
        return res;
    }

	@Override
    public int batchAddDeviceInfo(List<DeviceInfo> deviceInfoList) throws SQLException{
		int res = deviceInfoDao.batchInsertDeviceInfo(deviceInfoList);
		return res;
	}

    @Override
    public int deleteDeviceInfoByID(Integer ID) throws SQLException{
        int res = deviceInfoDao.deleteDeviceInfoByID(ID);
        return res;
    }
    
 	@Override
	public int deleteDeviceInfo(DeviceInfo deviceInfo) throws SQLException{
	 	int res = deviceInfoDao.deleteDeviceInfoByCondition(deviceInfo);
        return res;
	}
	
	@Override
	public int batchDeleteDeviceInfoByIDs(String IDs) throws SQLException{
		int res = deviceInfoDao.batchDeleteDeviceInfoByIDs(IDs);
        return res;
	}
	
    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo) throws SQLException{
        int res = deviceInfoDao.updateDeviceInfo(deviceInfo);
        return res;
    }

    @Override
    public DeviceInfo getDeviceInfoByID(Integer ID) throws SQLException{
        DeviceInfo res = deviceInfoDao.selectDeviceInfoByID(ID);
        return res;
    }

    @Override
    public List<DeviceInfo> getAllDeviceInfo() throws SQLException{
        List<DeviceInfo> res = deviceInfoDao.selectAllDeviceInfo();
        return res;
    }

	@Override
	public 	List<DeviceInfo> getDeviceInfo(DeviceInfo deviceInfo) throws SQLException{
		List<DeviceInfo> res = deviceInfoDao.selectDeviceInfoByCondition(deviceInfo);
		return res;
	}
	
    @Override
    public PageBean<DeviceInfo> getDeviceInfoWithPagination(PageParam pageParam) throws SQLException{
        long count = deviceInfoDao.selectCount();
        PageBean<DeviceInfo> res = new PageBean<>(pageParam, (int)count);
        List<DeviceInfo> data = deviceInfoDao.selectDeviceInfoWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

	@Override
	public PageBean<DeviceInfo> getDeviceInfoWithPagination(PageParam pageParam, DeviceInfo deviceInfo) throws SQLException{
        long count = deviceInfoDao.selectCountByCondition(deviceInfo);
        PageBean<DeviceInfo> res = new PageBean<>(pageParam, (int)count);		
		List<DeviceInfo> data = deviceInfoDao.selectDeviceInfoWithPaginationByCondition(pageParam,deviceInfo);
		res.setRecords(data);
        return res;
	}

    @Override
    public DeviceInfo getDeviceInfoByDeviceID(String deviceID) throws SQLException {
        DeviceInfo res = deviceInfoDao.selectDeviceInfoByDeviceID(deviceID);
        return res;
    }

}