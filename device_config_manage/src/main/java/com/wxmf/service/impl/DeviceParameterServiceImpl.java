package com.wxmf.service.impl;

import com.wxmf.pojo.DeviceParameter;
import java.util.List;
import com.wxmf.service.DeviceParameterService;
import com.wxmf.dao.DeviceParameterDao;
import com.wxmf.dao.impl.DeviceParameterDaoImpl;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;
import java.sql.SQLException;

public class DeviceParameterServiceImpl implements DeviceParameterService {

    private DeviceParameterDao deviceParameterDao = new DeviceParameterDaoImpl();

    @Override
    public int addDeviceParameter(DeviceParameter deviceParameter) throws SQLException{
        int res = deviceParameterDao.insertDeviceParameter(deviceParameter);
        return res;
    }

	@Override
    public int batchAddDeviceParameter(List<DeviceParameter> deviceParameterList) throws SQLException{
		int res = deviceParameterDao.batchInsertDeviceParameter(deviceParameterList);
		return res;
	}

    @Override
    public int deleteDeviceParameterByID(Integer ID) throws SQLException{
        int res = deviceParameterDao.deleteDeviceParameterByID(ID);
        return res;
    }
    
 	@Override
	public int deleteDeviceParameter(DeviceParameter deviceParameter) throws SQLException{
	 	int res = deviceParameterDao.deleteDeviceParameterByCondition(deviceParameter);
        return res;
	}
	
	@Override
	public int batchDeleteDeviceParameterByIDs(String IDs) throws SQLException{
		int res = deviceParameterDao.batchDeleteDeviceParameterByIDs(IDs);
        return res;
	}
	
    @Override
    public int updateDeviceParameter(DeviceParameter deviceParameter) throws SQLException{
        int res = deviceParameterDao.updateDeviceParameter(deviceParameter);
        return res;
    }

    @Override
    public DeviceParameter getDeviceParameterByID(Integer ID) throws SQLException{
        DeviceParameter res = deviceParameterDao.selectDeviceParameterByID(ID);
        return res;
    }

    @Override
    public List<DeviceParameter> getAllDeviceParameter() throws SQLException{
        List<DeviceParameter> res = deviceParameterDao.selectAllDeviceParameter();
        return res;
    }

	@Override
	public 	List<DeviceParameter> getDeviceParameter(DeviceParameter deviceParameter) throws SQLException{
		List<DeviceParameter> res = deviceParameterDao.selectDeviceParameterByCondition(deviceParameter);
		return res;
	}
	
    @Override
    public PageBean<DeviceParameter> getDeviceParameterWithPagination(PageParam pageParam) throws SQLException{
        long count = deviceParameterDao.selectCount();
        PageBean<DeviceParameter> res = new PageBean<>(pageParam, (int)count);
        List<DeviceParameter> data = deviceParameterDao.selectDeviceParameterWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

	@Override
	public PageBean<DeviceParameter> getDeviceParameterWithPagination(PageParam pageParam, DeviceParameter deviceParameter) throws SQLException{
        long count = deviceParameterDao.selectCountByCondition(deviceParameter);
        PageBean<DeviceParameter> res = new PageBean<>(pageParam, (int)count);		
		List<DeviceParameter> data = deviceParameterDao.selectDeviceParameterWithPaginationByCondition(pageParam,deviceParameter);
		res.setRecords(data);
        return res;
	}
  
}