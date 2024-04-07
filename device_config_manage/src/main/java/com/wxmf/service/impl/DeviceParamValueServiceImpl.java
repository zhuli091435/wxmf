package com.wxmf.service.impl;

import com.wxmf.pojo.DeviceParamValue;

import java.util.List;

import com.wxmf.service.DeviceParamValueService;
import com.wxmf.dao.DeviceParamValueDao;
import com.wxmf.dao.impl.DeviceParamValueDaoImpl;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;

public class DeviceParamValueServiceImpl implements DeviceParamValueService {

    private DeviceParamValueDao deviceParamValueDao = new DeviceParamValueDaoImpl();

    @Override
    public int addDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException {
        int res = deviceParamValueDao.insertDeviceParamValue(deviceParamValue);
        return res;
    }

    @Override
    public int batchAddDeviceParamValue(List<DeviceParamValue> deviceParamValueList) throws SQLException {
        int res = deviceParamValueDao.batchInsertDeviceParamValue(deviceParamValueList);
        return res;
    }

    @Override
    public int deleteDeviceParamValueByID(Integer ID) throws SQLException {
        int res = deviceParamValueDao.deleteDeviceParamValueByID(ID);
        return res;
    }

    @Override
    public int deleteDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException {
        int res = deviceParamValueDao.deleteDeviceParamValueByCondition(deviceParamValue);
        return res;
    }

    @Override
    public int batchDeleteDeviceParamValueByIDs(String IDs) throws SQLException {
        int res = deviceParamValueDao.batchDeleteDeviceParamValueByIDs(IDs);
        return res;
    }

    @Override
    public int updateDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException {
        int res = deviceParamValueDao.updateDeviceParamValue(deviceParamValue);
        return res;
    }

    @Override
    public DeviceParamValue getDeviceParamValueByID(Integer ID) throws SQLException {
        DeviceParamValue res = deviceParamValueDao.selectDeviceParamValueByID(ID);
        return res;
    }

    @Override
    public List<DeviceParamValue> getAllDeviceParamValue() throws SQLException {
        List<DeviceParamValue> res = deviceParamValueDao.selectAllDeviceParamValue();
        return res;
    }

    @Override
    public List<DeviceParamValue> getDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException {
        List<DeviceParamValue> res = deviceParamValueDao.selectDeviceParamValueByCondition(deviceParamValue);
        return res;
    }

    @Override
    public PageBean<DeviceParamValue> getDeviceParamValueWithPagination(PageParam pageParam) throws SQLException {
        long count = deviceParamValueDao.selectCount();
        PageBean<DeviceParamValue> res = new PageBean<>(pageParam, (int) count);
        List<DeviceParamValue> data = deviceParamValueDao.selectDeviceParamValueWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

    @Override
    public PageBean<DeviceParamValue> getDeviceParamValueWithPagination(PageParam pageParam, DeviceParamValue deviceParamValue) throws SQLException {
        long count = deviceParamValueDao.selectCountByCondition(deviceParamValue);
        PageBean<DeviceParamValue> res = new PageBean<>(pageParam, (int) count);
        List<DeviceParamValue> data = deviceParamValueDao.selectDeviceParamValueWithPaginationByCondition(pageParam, deviceParamValue);
        res.setRecords(data);
        return res;
    }

    @Override
    public int deleteDeviceParamValueByDeviceID(String deviceID) throws SQLException {
        int res = deviceParamValueDao.deleteDeviceParamValueByDeviceID(deviceID);
        return res;
    }

    @Override
    public List<DeviceParamValue> getDeviceParamValueByDeviceID(String deviceID) throws SQLException {
        List<DeviceParamValue> res = deviceParamValueDao.getDeviceParamValueByDeviceID(deviceID);
        return res;
    }
}