package com.wxmf.service.impl;

import com.wxmf.pojo.DeviceParam;
import java.util.List;
import com.wxmf.service.DeviceParamService;
import com.wxmf.dao.DeviceParamDao;
import com.wxmf.dao.impl.DeviceParamDaoImpl;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;
import java.sql.SQLException;

public class DeviceParamServiceImpl implements DeviceParamService {

    private DeviceParamDao deviceParamDao = new DeviceParamDaoImpl();

    @Override
    public int addDeviceParam(DeviceParam deviceParam) throws SQLException{
        int res = deviceParamDao.insertDeviceParam(deviceParam);
        return res;
    }

	@Override
    public int batchAddDeviceParam(List<DeviceParam> deviceParamList) throws SQLException{
		int res = deviceParamDao.batchInsertDeviceParam(deviceParamList);
		return res;
	}

    @Override
    public int deleteDeviceParamByID(Integer ID) throws SQLException{
        int res = deviceParamDao.deleteDeviceParamByID(ID);
        return res;
    }
    
 	@Override
	public int deleteDeviceParam(DeviceParam deviceParam) throws SQLException{
	 	int res = deviceParamDao.deleteDeviceParamByCondition(deviceParam);
        return res;
	}
	
	@Override
	public int batchDeleteDeviceParamByIDs(String IDs) throws SQLException{
		int res = deviceParamDao.batchDeleteDeviceParamByIDs(IDs);
        return res;
	}
	
    @Override
    public int updateDeviceParam(DeviceParam deviceParam) throws SQLException{
        int res = deviceParamDao.updateDeviceParam(deviceParam);
        return res;
    }

    @Override
    public DeviceParam getDeviceParamByID(Integer ID) throws SQLException{
        DeviceParam res = deviceParamDao.selectDeviceParamByID(ID);
        return res;
    }

    @Override
    public List<DeviceParam> getAllDeviceParam() throws SQLException{
        List<DeviceParam> res = deviceParamDao.selectAllDeviceParam();
        return res;
    }

	@Override
	public 	List<DeviceParam> getDeviceParam(DeviceParam deviceParam) throws SQLException{
		List<DeviceParam> res = deviceParamDao.selectDeviceParamByCondition(deviceParam);
		return res;
	}
	
    @Override
    public PageBean<DeviceParam> getDeviceParamWithPagination(PageParam pageParam) throws SQLException{
        long count = deviceParamDao.selectCount();
        PageBean<DeviceParam> res = new PageBean<>(pageParam, (int)count);
        List<DeviceParam> data = deviceParamDao.selectDeviceParamWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

	@Override
	public PageBean<DeviceParam> getDeviceParamWithPagination(PageParam pageParam, DeviceParam deviceParam) throws SQLException{
        long count = deviceParamDao.selectCountByCondition(deviceParam);
        PageBean<DeviceParam> res = new PageBean<>(pageParam, (int)count);		
		List<DeviceParam> data = deviceParamDao.selectDeviceParamWithPaginationByCondition(pageParam,deviceParam);
		res.setRecords(data);
        return res;
	}
  
}