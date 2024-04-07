package com.wxmf.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxmf.pojo.DeviceOrder;

import java.util.List;
import java.util.ArrayList;

import com.wxmf.utils.DBUtil;
import com.wxmf.dao.DeviceOrderDao;

import java.sql.SQLException;

import com.wxmf.utils.PageParam;

public class DeviceOrderDaoImpl implements DeviceOrderDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertDeviceOrder(DeviceOrder deviceOrder) throws SQLException {
        String sql = "insert into DeviceOrder ( DeviceID, OrderName, OrderCode, OrderType, DistributeTime, BeginExecuteTime, CompleteTime, OrderState, Parameter, CurMsgIndex, TotalMsgCount, Percentage, Remark, OrderResult) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return queryRunner.update(sql, deviceOrder.getDeviceID(), deviceOrder.getOrderName(), deviceOrder.getOrderCode(), deviceOrder.getOrderType(), deviceOrder.getDistributeTime(), deviceOrder.getBeginExecuteTime(), deviceOrder.getCompleteTime(), deviceOrder.getOrderState(), deviceOrder.getParameter(), deviceOrder.getCurMsgIndex(), deviceOrder.getTotalMsgCount(), deviceOrder.getPercentage(), deviceOrder.getRemark(), deviceOrder.getOrderResult());
    }

    @Override
    public int batchInsertDeviceOrder(List<DeviceOrder> deviceOrderList) throws SQLException {
        Object[][] params = new Object[deviceOrderList.size()][15];

        for (int i = 0; i < params.length; i++) {
            DeviceOrder deviceOrder = deviceOrderList.get(i);
            params[i][0] = deviceOrder.getID();
            params[i][1] = deviceOrder.getDeviceID();
            params[i][2] = deviceOrder.getOrderName();
            params[i][3] = deviceOrder.getOrderCode();
            params[i][4] = deviceOrder.getOrderType();
            params[i][5] = deviceOrder.getDistributeTime();
            params[i][6] = deviceOrder.getBeginExecuteTime();
            params[i][7] = deviceOrder.getCompleteTime();
            params[i][8] = deviceOrder.getOrderState();
            params[i][9] = deviceOrder.getParameter();
            params[i][10] = deviceOrder.getCurMsgIndex();
            params[i][11] = deviceOrder.getTotalMsgCount();
            params[i][12] = deviceOrder.getPercentage();
            params[i][13] = deviceOrder.getRemark();
            params[i][14] = deviceOrder.getOrderResult();
        }

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < params[0].length; i++) {
            wenHao.append("?,");
        }
        String sql = "insert into DeviceOrder values(" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deleteDeviceOrderByID(Integer ID) throws SQLException {
        String sql = "delete from DeviceOrder where ID = ?";
        return queryRunner.update(sql, ID);
    }

    @Override
    public int deleteDeviceOrderByCondition(DeviceOrder deviceOrder) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceOrder.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceOrder.getID());
        }
        if (deviceOrder.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceOrder.getDeviceID());
        }
        if (deviceOrder.getOrderName() != null) {
            paramBuf.append(" OrderName= ? and");
            paramValueList.add(deviceOrder.getOrderName());
        }
        if (deviceOrder.getOrderCode() != null) {
            paramBuf.append(" OrderCode= ? and");
            paramValueList.add(deviceOrder.getOrderCode());
        }
        if (deviceOrder.getOrderType() != null) {
            paramBuf.append(" OrderType= ? and");
            paramValueList.add(deviceOrder.getOrderType());
        }
        if (deviceOrder.getDistributeTime() != null) {
            paramBuf.append(" DistributeTime= ? and");
            paramValueList.add(deviceOrder.getDistributeTime());
        }
        if (deviceOrder.getBeginExecuteTime() != null) {
            paramBuf.append(" BeginExecuteTime= ? and");
            paramValueList.add(deviceOrder.getBeginExecuteTime());
        }
        if (deviceOrder.getCompleteTime() != null) {
            paramBuf.append(" CompleteTime= ? and");
            paramValueList.add(deviceOrder.getCompleteTime());
        }
        if (deviceOrder.getOrderState() != null) {
            paramBuf.append(" OrderState= ? and");
            paramValueList.add(deviceOrder.getOrderState());
        }
        if (deviceOrder.getParameter() != null) {
            paramBuf.append(" Parameter= ? and");
            paramValueList.add(deviceOrder.getParameter());
        }
        if (deviceOrder.getCurMsgIndex() != null) {
            paramBuf.append(" CurMsgIndex= ? and");
            paramValueList.add(deviceOrder.getCurMsgIndex());
        }
        if (deviceOrder.getTotalMsgCount() != null) {
            paramBuf.append(" TotalMsgCount= ? and");
            paramValueList.add(deviceOrder.getTotalMsgCount());
        }
        if (deviceOrder.getPercentage() != null) {
            paramBuf.append(" Percentage= ? and");
            paramValueList.add(deviceOrder.getPercentage());
        }
        if (deviceOrder.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceOrder.getRemark());
        }

        if (deviceOrder.getOrderResult() != null) {
            paramBuf.append(" OrderResult= ? and");
            paramValueList.add(deviceOrder.getOrderResult());
        }

        String sql = "delete from DeviceOrder where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.update(sql, paramValueList.toArray());
    }

    @Override
    public int batchDeleteDeviceOrderByIDs(String IDs) throws SQLException {
        String[] split = IDs.split(",");
        Object[][] params = new Object[1][];

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            wenHao.append("?,");
        }
        params[0] = split;

        String sql = "delete from DeviceOrder where ID in (" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int updateDeviceOrder(DeviceOrder deviceOrder) throws SQLException {
        String sql = "update DeviceOrder set DeviceID= ? , OrderName= ? , OrderCode= ? , OrderType= ? , DistributeTime= ? , BeginExecuteTime= ? , CompleteTime= ? , OrderState= ? , Parameter= ? , CurMsgIndex= ? , TotalMsgCount= ? , Percentage= ? , Remark= ? , OrderResult= ? where ID = ?";
        return queryRunner.update(sql, deviceOrder.getDeviceID(), deviceOrder.getOrderName(), deviceOrder.getOrderCode(), deviceOrder.getOrderType(), deviceOrder.getDistributeTime(), deviceOrder.getBeginExecuteTime(), deviceOrder.getCompleteTime(), deviceOrder.getOrderState(), deviceOrder.getParameter(), deviceOrder.getCurMsgIndex(), deviceOrder.getTotalMsgCount(), deviceOrder.getPercentage(), deviceOrder.getRemark(), deviceOrder.getOrderResult(), deviceOrder.getID());
    }

    @Override
    public long selectCount() throws SQLException {
        String sql = "select count(*) from DeviceOrder";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }

    @Override
    public long selectCountByCondition(DeviceOrder deviceOrder) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceOrder.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceOrder.getID());
        }
        if (deviceOrder.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceOrder.getDeviceID());
        }
        if (deviceOrder.getOrderName() != null) {
            paramBuf.append(" OrderName= ? and");
            paramValueList.add(deviceOrder.getOrderName());
        }
        if (deviceOrder.getOrderCode() != null) {
            paramBuf.append(" OrderCode= ? and");
            paramValueList.add(deviceOrder.getOrderCode());
        }
        if (deviceOrder.getOrderType() != null) {
            paramBuf.append(" OrderType= ? and");
            paramValueList.add(deviceOrder.getOrderType());
        }
        if (deviceOrder.getDistributeTime() != null) {
            paramBuf.append(" DistributeTime= ? and");
            paramValueList.add(deviceOrder.getDistributeTime());
        }
        if (deviceOrder.getBeginExecuteTime() != null) {
            paramBuf.append(" BeginExecuteTime= ? and");
            paramValueList.add(deviceOrder.getBeginExecuteTime());
        }
        if (deviceOrder.getCompleteTime() != null) {
            paramBuf.append(" CompleteTime= ? and");
            paramValueList.add(deviceOrder.getCompleteTime());
        }
        if (deviceOrder.getOrderState() != null) {
            paramBuf.append(" OrderState= ? and");
            paramValueList.add(deviceOrder.getOrderState());
        }
        if (deviceOrder.getParameter() != null) {
            paramBuf.append(" Parameter= ? and");
            paramValueList.add(deviceOrder.getParameter());
        }
        if (deviceOrder.getCurMsgIndex() != null) {
            paramBuf.append(" CurMsgIndex= ? and");
            paramValueList.add(deviceOrder.getCurMsgIndex());
        }
        if (deviceOrder.getTotalMsgCount() != null) {
            paramBuf.append(" TotalMsgCount= ? and");
            paramValueList.add(deviceOrder.getTotalMsgCount());
        }
        if (deviceOrder.getPercentage() != null) {
            paramBuf.append(" Percentage= ? and");
            paramValueList.add(deviceOrder.getPercentage());
        }
        if (deviceOrder.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceOrder.getRemark());
        }
        if (deviceOrder.getOrderResult() != null) {
            paramBuf.append(" OrderResult= ? and");
            paramValueList.add(deviceOrder.getOrderResult());
        }

        String sql = "select count(*) from DeviceOrder where " + paramBuf.substring(0, paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(), paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public DeviceOrder selectDeviceOrderByID(Integer ID) throws SQLException {
        String sql = "select ID as ID, DeviceID as DeviceID, OrderName as OrderName, OrderCode as OrderCode, OrderType as OrderType, DistributeTime as DistributeTime, BeginExecuteTime as BeginExecuteTime, CompleteTime as CompleteTime, OrderState as OrderState, Parameter as Parameter, CurMsgIndex as CurMsgIndex, TotalMsgCount as TotalMsgCount, Percentage as Percentage, Remark as Remark , OrderResult as OrderResult from DeviceOrder where  ID = ?";
        return queryRunner.query(sql, new BeanHandler<>(DeviceOrder.class), ID);
    }

    @Override
    public List<DeviceOrder> selectAllDeviceOrder() throws SQLException {
        String sql = "select ID as ID, DeviceID as DeviceID, OrderName as OrderName, OrderCode as OrderCode, OrderType as OrderType, DistributeTime as DistributeTime, BeginExecuteTime as BeginExecuteTime, CompleteTime as CompleteTime, OrderState as OrderState, Parameter as Parameter, CurMsgIndex as CurMsgIndex, TotalMsgCount as TotalMsgCount, Percentage as Percentage, Remark as Remark, OrderResult as OrderResult from DeviceOrder";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceOrder.class));
    }

    @Override
    public List<DeviceOrder> selectDeviceOrderByCondition(DeviceOrder deviceOrder) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceOrder.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceOrder.getID());
        }
        if (deviceOrder.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceOrder.getDeviceID());
        }
        if (deviceOrder.getOrderName() != null) {
            paramBuf.append(" OrderName= ? and");
            paramValueList.add(deviceOrder.getOrderName());
        }
        if (deviceOrder.getOrderCode() != null) {
            paramBuf.append(" OrderCode= ? and");
            paramValueList.add(deviceOrder.getOrderCode());
        }
        if (deviceOrder.getOrderType() != null) {
            paramBuf.append(" OrderType= ? and");
            paramValueList.add(deviceOrder.getOrderType());
        }
        if (deviceOrder.getDistributeTime() != null) {
            paramBuf.append(" DistributeTime= ? and");
            paramValueList.add(deviceOrder.getDistributeTime());
        }
        if (deviceOrder.getBeginExecuteTime() != null) {
            paramBuf.append(" BeginExecuteTime= ? and");
            paramValueList.add(deviceOrder.getBeginExecuteTime());
        }
        if (deviceOrder.getCompleteTime() != null) {
            paramBuf.append(" CompleteTime= ? and");
            paramValueList.add(deviceOrder.getCompleteTime());
        }
        if (deviceOrder.getOrderState() != null) {
            paramBuf.append(" OrderState= ? and");
            paramValueList.add(deviceOrder.getOrderState());
        }
        if (deviceOrder.getParameter() != null) {
            paramBuf.append(" Parameter= ? and");
            paramValueList.add(deviceOrder.getParameter());
        }
        if (deviceOrder.getCurMsgIndex() != null) {
            paramBuf.append(" CurMsgIndex= ? and");
            paramValueList.add(deviceOrder.getCurMsgIndex());
        }
        if (deviceOrder.getTotalMsgCount() != null) {
            paramBuf.append(" TotalMsgCount= ? and");
            paramValueList.add(deviceOrder.getTotalMsgCount());
        }
        if (deviceOrder.getPercentage() != null) {
            paramBuf.append(" Percentage= ? and");
            paramValueList.add(deviceOrder.getPercentage());
        }
        if (deviceOrder.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceOrder.getRemark());
        }
        if (deviceOrder.getOrderResult() != null) {
            paramBuf.append(" OrderResult= ? and");
            paramValueList.add(deviceOrder.getOrderResult());
        }

        String sql = "select ID as ID, DeviceID as DeviceID, OrderName as OrderName, OrderCode as OrderCode, OrderType as OrderType, DistributeTime as DistributeTime, BeginExecuteTime as BeginExecuteTime, CompleteTime as CompleteTime, OrderState as OrderState, Parameter as Parameter, CurMsgIndex as CurMsgIndex, TotalMsgCount as TotalMsgCount, Percentage as Percentage, Remark as Remark, OrderResult as OrderResult  from DeviceOrder where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.query(sql, new BeanListHandler<>(DeviceOrder.class), paramValueList.toArray());
    }

    @Override
    public List<DeviceOrder> selectDeviceOrderWithPagination(PageParam pageParam) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        String sql = "select ID as ID, DeviceID as DeviceID, OrderName as OrderName, OrderCode as OrderCode, OrderType as OrderType, DistributeTime as DistributeTime, BeginExecuteTime as BeginExecuteTime, CompleteTime as CompleteTime, OrderState as OrderState, Parameter as Parameter, CurMsgIndex as CurMsgIndex, TotalMsgCount as TotalMsgCount, Percentage as Percentage, Remark as Remark, OrderResult as OrderResult from DeviceOrder limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceOrder.class), (page - 1) * rows, rows);
    }

    @Override
    public List<DeviceOrder> selectDeviceOrderWithPaginationByCondition(PageParam pageParam, DeviceOrder deviceOrder) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceOrder.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceOrder.getID());
        }
        if (deviceOrder.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceOrder.getDeviceID());
        }
        if (deviceOrder.getOrderName() != null) {
            paramBuf.append(" OrderName= ? and");
            paramValueList.add(deviceOrder.getOrderName());
        }
        if (deviceOrder.getOrderCode() != null) {
            paramBuf.append(" OrderCode= ? and");
            paramValueList.add(deviceOrder.getOrderCode());
        }
        if (deviceOrder.getOrderType() != null) {
            paramBuf.append(" OrderType= ? and");
            paramValueList.add(deviceOrder.getOrderType());
        }
        if (deviceOrder.getDistributeTime() != null) {
            paramBuf.append(" DistributeTime= ? and");
            paramValueList.add(deviceOrder.getDistributeTime());
        }
        if (deviceOrder.getBeginExecuteTime() != null) {
            paramBuf.append(" BeginExecuteTime= ? and");
            paramValueList.add(deviceOrder.getBeginExecuteTime());
        }
        if (deviceOrder.getCompleteTime() != null) {
            paramBuf.append(" CompleteTime= ? and");
            paramValueList.add(deviceOrder.getCompleteTime());
        }
        if (deviceOrder.getOrderState() != null) {
            paramBuf.append(" OrderState= ? and");
            paramValueList.add(deviceOrder.getOrderState());
        }
        if (deviceOrder.getParameter() != null) {
            paramBuf.append(" Parameter= ? and");
            paramValueList.add(deviceOrder.getParameter());
        }
        if (deviceOrder.getCurMsgIndex() != null) {
            paramBuf.append(" CurMsgIndex= ? and");
            paramValueList.add(deviceOrder.getCurMsgIndex());
        }
        if (deviceOrder.getTotalMsgCount() != null) {
            paramBuf.append(" TotalMsgCount= ? and");
            paramValueList.add(deviceOrder.getTotalMsgCount());
        }
        if (deviceOrder.getPercentage() != null) {
            paramBuf.append(" Percentage= ? and");
            paramValueList.add(deviceOrder.getPercentage());
        }
        if (deviceOrder.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceOrder.getRemark());
        }
        if (deviceOrder.getOrderResult() != null) {
            paramBuf.append(" OrderResult= ? and");
            paramValueList.add(deviceOrder.getOrderResult());
        }

        String sql = "select ID as ID, DeviceID as DeviceID, OrderName as OrderName, OrderCode as OrderCode, OrderType as OrderType, DistributeTime as DistributeTime, BeginExecuteTime as BeginExecuteTime, CompleteTime as CompleteTime, OrderState as OrderState, Parameter as Parameter, CurMsgIndex as CurMsgIndex, TotalMsgCount as TotalMsgCount, Percentage as Percentage, Remark as Remark, OrderResult as OrderResult  from DeviceOrder where " + paramBuf.substring(0, paramBuf.length() - 3) + " limit ?, ?";

        paramValueList.add((page - 1) * rows);
        paramValueList.add(rows);
        return queryRunner.query(sql, new BeanListHandler<>(DeviceOrder.class), paramValueList.toArray());
    }

    @Override
    public List<DeviceOrder> selectAllUnexecutedDeviceOrder() throws SQLException {
        String sql = "select ID as ID, DeviceID as DeviceID, OrderName as OrderName, OrderCode as OrderCode, OrderType as OrderType, DistributeTime as DistributeTime, BeginExecuteTime as BeginExecuteTime, CompleteTime as CompleteTime, OrderState as OrderState, Parameter as Parameter, CurMsgIndex as CurMsgIndex, TotalMsgCount as TotalMsgCount, Percentage as Percentage, Remark as Remark, OrderResult as OrderResult from DeviceOrder where OrderState in (0,1) order by DistributeTime";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceOrder.class));
    }

    @Override
    public List<DeviceOrder> selectDeviceOrderByStatus(Integer orderState) throws SQLException {
        String sql = "select ID as ID, DeviceID as DeviceID, OrderName as OrderName, OrderCode as OrderCode, OrderType as OrderType, DistributeTime as DistributeTime, BeginExecuteTime as BeginExecuteTime, CompleteTime as CompleteTime, OrderState as OrderState, Parameter as Parameter, CurMsgIndex as CurMsgIndex, TotalMsgCount as TotalMsgCount, Percentage as Percentage, Remark as Remark, OrderResult as OrderResult from DeviceOrder where OrderState = ? order by DistributeTime";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceOrder.class), orderState);
    }

    public List<DeviceOrder> selectDeviceOrderByStatusAndDeviceID(Integer orderState, String deviceID) throws SQLException {
        String sql = "select ID as ID, DeviceID as DeviceID, OrderName as OrderName, OrderCode as OrderCode, OrderType as OrderType, DistributeTime as DistributeTime, BeginExecuteTime as BeginExecuteTime, CompleteTime as CompleteTime, OrderState as OrderState, Parameter as Parameter, CurMsgIndex as CurMsgIndex, TotalMsgCount as TotalMsgCount, Percentage as Percentage, Remark as Remark, OrderResult as OrderResult from DeviceOrder where OrderState = ? and DeviceID = ? order by DistributeTime";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceOrder.class), orderState, deviceID);
    }
}
