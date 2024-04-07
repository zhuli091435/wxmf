package com.wxmf.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxmf.pojo.DeviceParamValue;

import java.util.List;
import java.util.ArrayList;

import com.wxmf.utils.DBUtil;
import com.wxmf.dao.DeviceParamValueDao;

import java.sql.SQLException;

import com.wxmf.utils.PageParam;

public class DeviceParamValueDaoImpl implements DeviceParamValueDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException {
        String sql = "insert into DeviceParamValue (DeviceID, ParamIndex, ParamValue, UpdateTime) values ( ?, ?, ?, ?)";
        return queryRunner.update(sql, deviceParamValue.getDeviceID(), deviceParamValue.getParamIndex(), deviceParamValue.getParamValue(), deviceParamValue.getUpdateTime());
    }

    @Override
    public int batchInsertDeviceParamValue(List<DeviceParamValue> deviceParamValueList) throws SQLException {
        Object[][] params = new Object[deviceParamValueList.size()][4];

        for (int i = 0; i < params.length; i++) {
            DeviceParamValue deviceParamValue = deviceParamValueList.get(i);
            params[i][0] = deviceParamValue.getDeviceID();
            params[i][1] = deviceParamValue.getParamIndex();
            params[i][2] = deviceParamValue.getParamValue();
            params[i][3] = deviceParamValue.getUpdateTime();
        }

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < params[0].length; i++) {
            wenHao.append("?,");
        }
        String sql = "insert into DeviceParamValue (DeviceID, ParamIndex, ParamValue, UpdateTime) values(" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deleteDeviceParamValueByID(Integer ID) throws SQLException {
        String sql = "delete from DeviceParamValue where ID = ?";
        return queryRunner.update(sql, ID);
    }

    @Override
    public int deleteDeviceParamValueByCondition(DeviceParamValue deviceParamValue) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceParamValue.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceParamValue.getID());
        }
        if (deviceParamValue.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceParamValue.getDeviceID());
        }
        if (deviceParamValue.getParamIndex() != null) {
            paramBuf.append(" ParamIndex= ? and");
            paramValueList.add(deviceParamValue.getParamIndex());
        }
        if (deviceParamValue.getParamValue() != null) {
            paramBuf.append(" ParamValue= ? and");
            paramValueList.add(deviceParamValue.getParamValue());
        }
        if (deviceParamValue.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(deviceParamValue.getUpdateTime());
        }

        String sql = "delete from DeviceParamValue where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.update(sql, paramValueList.toArray());
    }

    @Override
    public int batchDeleteDeviceParamValueByIDs(String IDs) throws SQLException {
        String[] split = IDs.split(",");
        Object[][] params = new Object[1][];

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            wenHao.append("?,");
        }
        params[0] = split;

        String sql = "delete from DeviceParamValue where ID in (" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int updateDeviceParamValue(DeviceParamValue deviceParamValue) throws SQLException {
        String sql = "update DeviceParamValue set DeviceID= ? , ParamIndex= ? , ParamValue= ? , UpdateTime= ?  where ID = ?";
        return queryRunner.update(sql, deviceParamValue.getDeviceID(), deviceParamValue.getParamIndex(), deviceParamValue.getParamValue(), deviceParamValue.getUpdateTime(), deviceParamValue.getID());
    }

    @Override
    public long selectCount() throws SQLException {
        String sql = "select count(*) from DeviceParamValue";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }

    @Override
    public long selectCountByCondition(DeviceParamValue deviceParamValue) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceParamValue.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceParamValue.getID());
        }
        if (deviceParamValue.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceParamValue.getDeviceID());
        }
        if (deviceParamValue.getParamIndex() != null) {
            paramBuf.append(" ParamIndex= ? and");
            paramValueList.add(deviceParamValue.getParamIndex());
        }
        if (deviceParamValue.getParamValue() != null) {
            paramBuf.append(" ParamValue= ? and");
            paramValueList.add(deviceParamValue.getParamValue());
        }
        if (deviceParamValue.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(deviceParamValue.getUpdateTime());
        }

        String sql = "select count(*) from DeviceParamValue where " + paramBuf.substring(0, paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(), paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public DeviceParamValue selectDeviceParamValueByID(Integer ID) throws SQLException {
        String sql = "select ID as ID, DeviceID as DeviceID, ParamIndex as ParamIndex, ParamValue as ParamValue, UpdateTime as UpdateTime from DeviceParamValue where  ID = ?";
        return queryRunner.query(sql, new BeanHandler<>(DeviceParamValue.class), ID);
    }

    @Override
    public List<DeviceParamValue> selectAllDeviceParamValue() throws SQLException {
        String sql = "select ID as ID, DeviceID as DeviceID, ParamIndex as ParamIndex, ParamValue as ParamValue, UpdateTime as UpdateTime from DeviceParamValue";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParamValue.class));
    }

    @Override
    public List<DeviceParamValue> selectDeviceParamValueByCondition(DeviceParamValue deviceParamValue) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceParamValue.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceParamValue.getID());
        }
        if (deviceParamValue.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceParamValue.getDeviceID());
        }
        if (deviceParamValue.getParamIndex() != null) {
            paramBuf.append(" ParamIndex= ? and");
            paramValueList.add(deviceParamValue.getParamIndex());
        }
        if (deviceParamValue.getParamValue() != null) {
            paramBuf.append(" ParamValue= ? and");
            paramValueList.add(deviceParamValue.getParamValue());
        }
        if (deviceParamValue.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(deviceParamValue.getUpdateTime());
        }

        String sql = "select ID as ID, DeviceID as DeviceID, ParamIndex as ParamIndex, ParamValue as ParamValue, UpdateTime as UpdateTime  from DeviceParamValue where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParamValue.class), paramValueList.toArray());
    }

    @Override
    public List<DeviceParamValue> selectDeviceParamValueWithPagination(PageParam pageParam) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        String sql = "select ID as ID, DeviceID as DeviceID, ParamIndex as ParamIndex, ParamValue as ParamValue, UpdateTime as UpdateTime from DeviceParamValue limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParamValue.class), (page - 1) * rows, rows);
    }

    @Override
    public List<DeviceParamValue> selectDeviceParamValueWithPaginationByCondition(PageParam pageParam, DeviceParamValue deviceParamValue) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceParamValue.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceParamValue.getID());
        }
        if (deviceParamValue.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceParamValue.getDeviceID());
        }
        if (deviceParamValue.getParamIndex() != null) {
            paramBuf.append(" ParamIndex= ? and");
            paramValueList.add(deviceParamValue.getParamIndex());
        }
        if (deviceParamValue.getParamValue() != null) {
            paramBuf.append(" ParamValue= ? and");
            paramValueList.add(deviceParamValue.getParamValue());
        }
        if (deviceParamValue.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(deviceParamValue.getUpdateTime());
        }

        String sql = "select ID as ID, DeviceID as DeviceID, ParamIndex as ParamIndex, ParamValue as ParamValue, UpdateTime as UpdateTime  from DeviceParamValue where " + paramBuf.substring(0, paramBuf.length() - 3) + " limit ?, ?";

        paramValueList.add((page - 1) * rows);
        paramValueList.add(rows);
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParamValue.class), paramValueList.toArray());
    }

    @Override
    public int deleteDeviceParamValueByDeviceID(String deviceID) throws SQLException {
        String sql = "delete from DeviceParamValue where DeviceID = ?";
        return queryRunner.update(sql, deviceID);
    }

    @Override
    public List<DeviceParamValue> getDeviceParamValueByDeviceID(String deviceID) throws SQLException {
        String sql = "select ID as ID, DeviceID as DeviceID, ParamIndex as ParamIndex, ParamValue as ParamValue, UpdateTime as UpdateTime from DeviceParamValue where  DeviceID = ?";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParamValue.class), deviceID);
    }

}