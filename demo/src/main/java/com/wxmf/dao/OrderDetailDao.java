package com.wxmf.dao;

import com.wxmf.pojo.OrderDetail;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao {
    int insertOrderDetail(OrderDetail orderDetail) throws SQLException;

	int batchInsertOrderDetail(List<OrderDetail> orderDetailList) throws SQLException;

    int deleteOrderDetailByID(Integer ID) throws SQLException;

	int deleteOrderDetailByCondition(OrderDetail orderDetail) throws SQLException;

	int batchDeleteOrderDetailByIDs(String IDs) throws SQLException;

    int updateOrderDetail(OrderDetail orderDetail) throws SQLException;

    long selectCount() throws SQLException;
    
    long selectCountByCondition(OrderDetail orderDetail) throws SQLException;

    OrderDetail selectOrderDetailByID(Integer ID) throws SQLException;

    List<OrderDetail> selectAllOrderDetail() throws SQLException;

	List<OrderDetail> selectOrderDetailByCondition(OrderDetail orderDetail) throws SQLException;

    List<OrderDetail> selectOrderDetailWithPagination(PageParam pageParam) throws SQLException;

    List<OrderDetail> selectOrderDetailWithPaginationByCondition(PageParam pageParam, OrderDetail orderDetail) throws SQLException;

    List<OrderDetail> selectOrderDetailByOrderID(Integer orderID) throws SQLException;
}