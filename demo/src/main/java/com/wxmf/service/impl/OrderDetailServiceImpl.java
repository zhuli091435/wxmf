package com.wxmf.service.impl;

import com.wxmf.dao.OrderDetailDao;
import com.wxmf.dao.impl.OrderDetailDaoImpl;
import com.wxmf.pojo.OrderDetail;
import com.wxmf.service.OrderDetailService;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();

    @Override
    public int addOrderDetail(OrderDetail orderDetail) throws SQLException {
        int res = orderDetailDao.insertOrderDetail(orderDetail);
        return res;
    }

    @Override
    public int batchAddOrderDetail(List<OrderDetail> orderDetailList) throws SQLException {
        int res = orderDetailDao.batchInsertOrderDetail(orderDetailList);
        return res;
    }

    @Override
    public int deleteOrderDetailByID(Integer ID) throws SQLException {
        int res = orderDetailDao.deleteOrderDetailByID(ID);
        return res;
    }

    @Override
    public int deleteOrderDetail(OrderDetail orderDetail) throws SQLException {
        int res = orderDetailDao.deleteOrderDetailByCondition(orderDetail);
        return res;
    }

    @Override
    public int batchDeleteOrderDetailByIDs(String IDs) throws SQLException {
        int res = orderDetailDao.batchDeleteOrderDetailByIDs(IDs);
        return res;
    }

    @Override
    public int updateOrderDetail(OrderDetail orderDetail) throws SQLException {
        int res = orderDetailDao.updateOrderDetail(orderDetail);
        return res;
    }

    @Override
    public OrderDetail getOrderDetailByID(Integer ID) throws SQLException {
        OrderDetail res = orderDetailDao.selectOrderDetailByID(ID);
        return res;
    }

    @Override
    public List<OrderDetail> getAllOrderDetail() throws SQLException {
        List<OrderDetail> res = orderDetailDao.selectAllOrderDetail();
        return res;
    }

    @Override
    public List<OrderDetail> getOrderDetail(OrderDetail orderDetail) throws SQLException {
        List<OrderDetail> res = orderDetailDao.selectOrderDetailByCondition(orderDetail);
        return res;
    }

    @Override
    public PageBean<OrderDetail> getOrderDetailWithPagination(PageParam pageParam) throws SQLException {
        long count = orderDetailDao.selectCount();
        PageBean<OrderDetail> res = new PageBean<>(pageParam, (int) count);
        List<OrderDetail> data = orderDetailDao.selectOrderDetailWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

    @Override
    public PageBean<OrderDetail> getOrderDetailWithPagination(PageParam pageParam, OrderDetail orderDetail) throws SQLException {
        long count = orderDetailDao.selectCountByCondition(orderDetail);
        PageBean<OrderDetail> res = new PageBean<>(pageParam, (int) count);
        List<OrderDetail> data = orderDetailDao.selectOrderDetailWithPaginationByCondition(pageParam, orderDetail);
        res.setRecords(data);
        return res;
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderID(Integer orderID) throws SQLException {
        List<OrderDetail> res = orderDetailDao.selectOrderDetailByOrderID(orderID);
        return res;
    }
}