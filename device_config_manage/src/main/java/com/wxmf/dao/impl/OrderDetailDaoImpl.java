package com.wxmf.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxmf.pojo.OrderDetail;

import java.util.List;
import java.util.ArrayList;

import com.wxmf.utils.DBUtil;
import com.wxmf.dao.OrderDetailDao;

import java.sql.SQLException;

import com.wxmf.utils.PageParam;

public class OrderDetailDaoImpl implements OrderDetailDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertOrderDetail(OrderDetail orderDetail) throws SQLException {
        String sql = "insert into OrderDetail (OrderID, CurPackageNumber, TotalPackageNumber, MsgType, MsgContent, MsgState, ExecuteCount, Sort) values (?, ?, ?, ?, ?, ?, ?, ?)";
        return queryRunner.update(sql, orderDetail.getOrderID(), orderDetail.getCurPackageNumber(), orderDetail.getTotalPackageNumber(), orderDetail.getMsgType(), orderDetail.getMsgContent(), orderDetail.getMsgState(), orderDetail.getExecuteCount(), orderDetail.getSort());
    }

    @Override
    public int batchInsertOrderDetail(List<OrderDetail> orderDetailList) throws SQLException {
        Object[][] params = new Object[orderDetailList.size()][9];

        for (int i = 0; i < params.length; i++) {
            OrderDetail orderDetail = orderDetailList.get(i);
            params[i][0] = orderDetail.getID();
            params[i][1] = orderDetail.getOrderID();
            params[i][2] = orderDetail.getCurPackageNumber();
            params[i][3] = orderDetail.getTotalPackageNumber();
            params[i][4] = orderDetail.getMsgType();
            params[i][5] = orderDetail.getMsgContent();
            params[i][6] = orderDetail.getMsgState();
            params[i][7] = orderDetail.getExecuteCount();
            params[i][8] = orderDetail.getSort();
        }

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < params[0].length; i++) {
            wenHao.append("?,");
        }
        String sql = "insert into OrderDetail values(" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deleteOrderDetailByID(Integer ID) throws SQLException {
        String sql = "delete from OrderDetail where ID = ?";
        return queryRunner.update(sql, ID);
    }

    @Override
    public int deleteOrderDetailByCondition(OrderDetail orderDetail) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (orderDetail.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(orderDetail.getID());
        }
        if (orderDetail.getOrderID() != null) {
            paramBuf.append(" OrderID= ? and");
            paramValueList.add(orderDetail.getOrderID());
        }
        if (orderDetail.getCurPackageNumber() != null) {
            paramBuf.append(" CurPackageNumber= ? and");
            paramValueList.add(orderDetail.getCurPackageNumber());
        }
        if (orderDetail.getTotalPackageNumber() != null) {
            paramBuf.append(" TotalPackageNumber= ? and");
            paramValueList.add(orderDetail.getTotalPackageNumber());
        }
        if (orderDetail.getMsgType() != null) {
            paramBuf.append(" MsgType= ? and");
            paramValueList.add(orderDetail.getMsgType());
        }
        if (orderDetail.getMsgContent() != null) {
            paramBuf.append(" MsgContent= ? and");
            paramValueList.add(orderDetail.getMsgContent());
        }
        if (orderDetail.getMsgState() != null) {
            paramBuf.append(" MsgState= ? and");
            paramValueList.add(orderDetail.getMsgState());
        }
        if (orderDetail.getExecuteCount() != null) {
            paramBuf.append(" ExecuteCount= ? and");
            paramValueList.add(orderDetail.getExecuteCount());
        }
        if (orderDetail.getSort() != null) {
            paramBuf.append(" Sort= ? and");
            paramValueList.add(orderDetail.getSort());
        }

        String sql = "delete from OrderDetail where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.update(sql, paramValueList.toArray());
    }

    @Override
    public int batchDeleteOrderDetailByIDs(String IDs) throws SQLException {
        String[] split = IDs.split(",");
        Object[][] params = new Object[1][];

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            wenHao.append("?,");
        }
        params[0] = split;

        String sql = "delete from OrderDetail where ID in (" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int updateOrderDetail(OrderDetail orderDetail) throws SQLException {
        String sql = "update OrderDetail set OrderID= ? , CurPackageNumber= ? , TotalPackageNumber= ? , MsgType= ? , MsgContent= ? , MsgState= ? , ExecuteCount= ? , Sort= ?  where ID = ?";
        return queryRunner.update(sql, orderDetail.getOrderID(), orderDetail.getCurPackageNumber(), orderDetail.getTotalPackageNumber(), orderDetail.getMsgType(), orderDetail.getMsgContent(), orderDetail.getMsgState(), orderDetail.getExecuteCount(), orderDetail.getSort(), orderDetail.getID());
    }

    @Override
    public long selectCount() throws SQLException {
        String sql = "select count(*) from OrderDetail";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }

    @Override
    public long selectCountByCondition(OrderDetail orderDetail) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (orderDetail.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(orderDetail.getID());
        }
        if (orderDetail.getOrderID() != null) {
            paramBuf.append(" OrderID= ? and");
            paramValueList.add(orderDetail.getOrderID());
        }
        if (orderDetail.getCurPackageNumber() != null) {
            paramBuf.append(" CurPackageNumber= ? and");
            paramValueList.add(orderDetail.getCurPackageNumber());
        }
        if (orderDetail.getTotalPackageNumber() != null) {
            paramBuf.append(" TotalPackageNumber= ? and");
            paramValueList.add(orderDetail.getTotalPackageNumber());
        }
        if (orderDetail.getMsgType() != null) {
            paramBuf.append(" MsgType= ? and");
            paramValueList.add(orderDetail.getMsgType());
        }
        if (orderDetail.getMsgContent() != null) {
            paramBuf.append(" MsgContent= ? and");
            paramValueList.add(orderDetail.getMsgContent());
        }
        if (orderDetail.getMsgState() != null) {
            paramBuf.append(" MsgState= ? and");
            paramValueList.add(orderDetail.getMsgState());
        }
        if (orderDetail.getExecuteCount() != null) {
            paramBuf.append(" ExecuteCount= ? and");
            paramValueList.add(orderDetail.getExecuteCount());
        }
        if (orderDetail.getSort() != null) {
            paramBuf.append(" Sort= ? and");
            paramValueList.add(orderDetail.getSort());
        }

        String sql = "select count(*) from OrderDetail where " + paramBuf.substring(0, paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(), paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public OrderDetail selectOrderDetailByID(Integer ID) throws SQLException {
        String sql = "select ID as ID, OrderID as OrderID, CurPackageNumber as CurPackageNumber, TotalPackageNumber as TotalPackageNumber, MsgType as MsgType, MsgContent as MsgContent, MsgState as MsgState, ExecuteCount as ExecuteCount, Sort as Sort from OrderDetail where  ID = ?";
        return queryRunner.query(sql, new BeanHandler<>(OrderDetail.class), ID);
    }

    @Override
    public List<OrderDetail> selectAllOrderDetail() throws SQLException {
        String sql = "select ID as ID, OrderID as OrderID, CurPackageNumber as CurPackageNumber, TotalPackageNumber as TotalPackageNumber, MsgType as MsgType, MsgContent as MsgContent, MsgState as MsgState, ExecuteCount as ExecuteCount, Sort as Sort from OrderDetail";
        return queryRunner.query(sql, new BeanListHandler<>(OrderDetail.class));
    }

    @Override
    public List<OrderDetail> selectOrderDetailByCondition(OrderDetail orderDetail) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (orderDetail.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(orderDetail.getID());
        }
        if (orderDetail.getOrderID() != null) {
            paramBuf.append(" OrderID= ? and");
            paramValueList.add(orderDetail.getOrderID());
        }
        if (orderDetail.getCurPackageNumber() != null) {
            paramBuf.append(" CurPackageNumber= ? and");
            paramValueList.add(orderDetail.getCurPackageNumber());
        }
        if (orderDetail.getTotalPackageNumber() != null) {
            paramBuf.append(" TotalPackageNumber= ? and");
            paramValueList.add(orderDetail.getTotalPackageNumber());
        }
        if (orderDetail.getMsgType() != null) {
            paramBuf.append(" MsgType= ? and");
            paramValueList.add(orderDetail.getMsgType());
        }
        if (orderDetail.getMsgContent() != null) {
            paramBuf.append(" MsgContent= ? and");
            paramValueList.add(orderDetail.getMsgContent());
        }
        if (orderDetail.getMsgState() != null) {
            paramBuf.append(" MsgState= ? and");
            paramValueList.add(orderDetail.getMsgState());
        }
        if (orderDetail.getExecuteCount() != null) {
            paramBuf.append(" ExecuteCount= ? and");
            paramValueList.add(orderDetail.getExecuteCount());
        }
        if (orderDetail.getSort() != null) {
            paramBuf.append(" Sort= ? and");
            paramValueList.add(orderDetail.getSort());
        }

        String sql = "select ID as ID, OrderID as OrderID, CurPackageNumber as CurPackageNumber, TotalPackageNumber as TotalPackageNumber, MsgType as MsgType, MsgContent as MsgContent, MsgState as MsgState, ExecuteCount as ExecuteCount, Sort as Sort  from OrderDetail where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.query(sql, new BeanListHandler<>(OrderDetail.class), paramValueList.toArray());
    }

    @Override
    public List<OrderDetail> selectOrderDetailWithPagination(PageParam pageParam) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        String sql = "select ID as ID, OrderID as OrderID, CurPackageNumber as CurPackageNumber, TotalPackageNumber as TotalPackageNumber, MsgType as MsgType, MsgContent as MsgContent, MsgState as MsgState, ExecuteCount as ExecuteCount, Sort as Sort from OrderDetail limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(OrderDetail.class), (page - 1) * rows, rows);
    }

    @Override
    public List<OrderDetail> selectOrderDetailWithPaginationByCondition(PageParam pageParam, OrderDetail orderDetail) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (orderDetail.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(orderDetail.getID());
        }
        if (orderDetail.getOrderID() != null) {
            paramBuf.append(" OrderID= ? and");
            paramValueList.add(orderDetail.getOrderID());
        }
        if (orderDetail.getCurPackageNumber() != null) {
            paramBuf.append(" CurPackageNumber= ? and");
            paramValueList.add(orderDetail.getCurPackageNumber());
        }
        if (orderDetail.getTotalPackageNumber() != null) {
            paramBuf.append(" TotalPackageNumber= ? and");
            paramValueList.add(orderDetail.getTotalPackageNumber());
        }
        if (orderDetail.getMsgType() != null) {
            paramBuf.append(" MsgType= ? and");
            paramValueList.add(orderDetail.getMsgType());
        }
        if (orderDetail.getMsgContent() != null) {
            paramBuf.append(" MsgContent= ? and");
            paramValueList.add(orderDetail.getMsgContent());
        }
        if (orderDetail.getMsgState() != null) {
            paramBuf.append(" MsgState= ? and");
            paramValueList.add(orderDetail.getMsgState());
        }
        if (orderDetail.getExecuteCount() != null) {
            paramBuf.append(" ExecuteCount= ? and");
            paramValueList.add(orderDetail.getExecuteCount());
        }
        if (orderDetail.getSort() != null) {
            paramBuf.append(" Sort= ? and");
            paramValueList.add(orderDetail.getSort());
        }

        String sql = "select ID as ID, OrderID as OrderID, CurPackageNumber as CurPackageNumber, TotalPackageNumber as TotalPackageNumber, MsgType as MsgType, MsgContent as MsgContent, MsgState as MsgState, ExecuteCount as ExecuteCount, Sort as Sort  from OrderDetail where " + paramBuf.substring(0, paramBuf.length() - 3) + " limit ?, ?";

        paramValueList.add((page - 1) * rows);
        paramValueList.add(rows);
        return queryRunner.query(sql, new BeanListHandler<>(OrderDetail.class), paramValueList.toArray());
    }

    @Override
    public List<OrderDetail> selectOrderDetailByOrderID(Integer orderID) throws SQLException {
        String sql = "select ID as ID, OrderID as OrderID, CurPackageNumber as CurPackageNumber, TotalPackageNumber as TotalPackageNumber, MsgType as MsgType, MsgContent as MsgContent, MsgState as MsgState, ExecuteCount as ExecuteCount, Sort as Sort  from OrderDetail  where  OrderID = ? order by Sort";
        return queryRunner.query(sql, new BeanListHandler<>(OrderDetail.class), orderID);
    }

    @Override
    public List<OrderDetail> selectOrderDetailByOrderID(Integer orderID, Integer status) throws SQLException {
        String sql = "select ID as ID, OrderID as OrderID, CurPackageNumber as CurPackageNumber, TotalPackageNumber as TotalPackageNumber, MsgType as MsgType, MsgContent as MsgContent, MsgState as MsgState, ExecuteCount as ExecuteCount, Sort as Sort  from OrderDetail  where  OrderID = ? and MsgState = ? order by Sort";
        return queryRunner.query(sql, new BeanListHandler<>(OrderDetail.class), orderID, status);
    }

}