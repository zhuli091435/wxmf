package com.wxmf.service.impl;

import com.wxmf.pojo.DeviceOrder;

import java.util.List;

import com.wxmf.service.DeviceOrderService;
import com.wxmf.dao.DeviceOrderDao;
import com.wxmf.dao.impl.DeviceOrderDaoImpl;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;

public class DeviceOrderServiceImpl implements DeviceOrderService {

    private DeviceOrderDao deviceOrderDao = new DeviceOrderDaoImpl();

    @Override
    public int addDeviceOrder(DeviceOrder deviceOrder) throws SQLException {
        int res = deviceOrderDao.insertDeviceOrder(deviceOrder);
        return res;
    }

    @Override
    public int batchAddDeviceOrder(List<DeviceOrder> deviceOrderList) throws SQLException {
        int res = deviceOrderDao.batchInsertDeviceOrder(deviceOrderList);
        return res;
    }

    @Override
    public int deleteDeviceOrderByID(Integer ID) throws SQLException {
        int res = deviceOrderDao.deleteDeviceOrderByID(ID);
        return res;
    }

    @Override
    public int deleteDeviceOrder(DeviceOrder deviceOrder) throws SQLException {
        int res = deviceOrderDao.deleteDeviceOrderByCondition(deviceOrder);
        return res;
    }

    @Override
    public int batchDeleteDeviceOrderByIDs(String IDs) throws SQLException {
        int res = deviceOrderDao.batchDeleteDeviceOrderByIDs(IDs);
        return res;
    }

    @Override
    public int updateDeviceOrder(DeviceOrder deviceOrder) throws SQLException {
        int res = deviceOrderDao.updateDeviceOrder(deviceOrder);
        return res;
    }

    @Override
    public DeviceOrder getDeviceOrderByID(Integer ID) throws SQLException {
        DeviceOrder res = deviceOrderDao.selectDeviceOrderByID(ID);
        return res;
    }

    @Override
    public List<DeviceOrder> getAllDeviceOrder() throws SQLException {
        List<DeviceOrder> res = deviceOrderDao.selectAllDeviceOrder();
        return res;
    }

    @Override
    public List<DeviceOrder> getDeviceOrder(DeviceOrder deviceOrder) throws SQLException {
        List<DeviceOrder> res = deviceOrderDao.selectDeviceOrderByCondition(deviceOrder);
        return res;
    }

    @Override
    public PageBean<DeviceOrder> getDeviceOrderWithPagination(PageParam pageParam) throws SQLException {
        long count = deviceOrderDao.selectCount();
        PageBean<DeviceOrder> res = new PageBean<>(pageParam, (int) count);
        List<DeviceOrder> data = deviceOrderDao.selectDeviceOrderWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

    @Override
    public PageBean<DeviceOrder> getDeviceOrderWithPagination(PageParam pageParam, DeviceOrder deviceOrder) throws SQLException {
        long count = deviceOrderDao.selectCountByCondition(deviceOrder);
        PageBean<DeviceOrder> res = new PageBean<>(pageParam, (int) count);
        List<DeviceOrder> data = deviceOrderDao.selectDeviceOrderWithPaginationByCondition(pageParam, deviceOrder);
        res.setRecords(data);
        return res;
    }

    @Override
    public List<DeviceOrder> getAllUnexecutedDeviceOrder() throws SQLException {
        List<DeviceOrder> res = deviceOrderDao.selectAllUnexecutedDeviceOrder();
        return res;
    }

    @Override
    public List<DeviceOrder> getDeviceOrderByStatusAndDeviceID(Integer orderState, String deviceID) throws SQLException {
        List<DeviceOrder> res = deviceOrderDao.selectDeviceOrderByStatusAndDeviceID(orderState, deviceID);
        return res;
    }

    @Override
    public List<DeviceOrder> getDeviceOrderByStatus(Integer orderState) throws SQLException {

        List<DeviceOrder> res = deviceOrderDao.selectDeviceOrderByStatus(orderState);
        return res;
    }

}