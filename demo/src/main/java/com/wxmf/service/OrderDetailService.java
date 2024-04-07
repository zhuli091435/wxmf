package com.wxmf.service;

import com.wxmf.pojo.OrderDetail;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailService {
    int addOrderDetail(OrderDetail orderDetail) throws SQLException;

    int batchAddOrderDetail(List<OrderDetail> orderDetailList) throws SQLException;

    int deleteOrderDetailByID(Integer ID) throws SQLException;

    int deleteOrderDetail(OrderDetail orderDetail) throws SQLException;

    int batchDeleteOrderDetailByIDs(String IDs) throws SQLException;

    int updateOrderDetail(OrderDetail orderDetail) throws SQLException;

    OrderDetail getOrderDetailByID(Integer ID) throws SQLException;

    List<OrderDetail> getAllOrderDetail() throws SQLException;

    List<OrderDetail> getOrderDetail(OrderDetail orderDetail) throws SQLException;

    PageBean<OrderDetail> getOrderDetailWithPagination(PageParam pageParam) throws SQLException;

    PageBean<OrderDetail> getOrderDetailWithPagination(PageParam pageParam, OrderDetail orderDetail) throws SQLException;

    List<OrderDetail> getOrderDetailByOrderID(Integer orderID) throws SQLException;
}