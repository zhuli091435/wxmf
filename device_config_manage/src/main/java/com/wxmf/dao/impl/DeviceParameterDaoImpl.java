package com.wxmf.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxmf.pojo.DeviceParameter;

import java.util.List;
import java.util.ArrayList;

import com.wxmf.utils.DBUtil;
import com.wxmf.dao.DeviceParameterDao;

import java.sql.SQLException;

import com.wxmf.utils.PageParam;

public class DeviceParameterDaoImpl implements DeviceParameterDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertDeviceParameter(DeviceParameter deviceParameter) throws SQLException {
        String sql = "insert into DeviceParameter (ID, ParamName, ParamLength, DecimalPlaces, Unit, MinValue, MaxValue, RegisterAddress, StartBit, EndBit, Symbol, ParamType, Category, Remark) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return queryRunner.update(sql, deviceParameter.getID(), deviceParameter.getParamName(), deviceParameter.getParamLength(), deviceParameter.getDecimalPlaces(), deviceParameter.getUnit(), deviceParameter.getMinValue(), deviceParameter.getMaxValue(), deviceParameter.getRegisterAddress(), deviceParameter.getStartBit(), deviceParameter.getEndBit(), deviceParameter.getSymbol(), deviceParameter.getParamType(), deviceParameter.getCategory(), deviceParameter.getRemark());
    }

    @Override
    public int batchInsertDeviceParameter(List<DeviceParameter> deviceParameterList) throws SQLException {
        Object[][] params = new Object[deviceParameterList.size()][14];

        for (int i = 0; i < params.length; i++) {
            DeviceParameter deviceParameter = deviceParameterList.get(i);
            params[i][0] = deviceParameter.getID();
            params[i][1] = deviceParameter.getParamName();
            params[i][2] = deviceParameter.getParamLength();
            params[i][3] = deviceParameter.getDecimalPlaces();
            params[i][4] = deviceParameter.getUnit();
            params[i][5] = deviceParameter.getMinValue();
            params[i][6] = deviceParameter.getMaxValue();
            params[i][7] = deviceParameter.getRegisterAddress();
            params[i][8] = deviceParameter.getStartBit();
            params[i][9] = deviceParameter.getEndBit();
            params[i][10] = deviceParameter.getSymbol();
            params[i][11] = deviceParameter.getParamType();
            params[i][12] = deviceParameter.getCategory();
            params[i][13] = deviceParameter.getRemark();
        }

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < params[0].length; i++) {
            wenHao.append("?,");
        }
        String sql = "insert into DeviceParameter values(" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deleteDeviceParameterByID(Integer ID) throws SQLException {
        String sql = "delete from DeviceParameter where ID = ?";
        return queryRunner.update(sql, ID);
    }

    @Override
    public int deleteDeviceParameterByCondition(DeviceParameter deviceParameter) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceParameter.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceParameter.getID());
        }
        if (deviceParameter.getParamName() != null) {
            paramBuf.append(" ParamName= ? and");
            paramValueList.add(deviceParameter.getParamName());
        }
        if (deviceParameter.getParamLength() != null) {
            paramBuf.append(" ParamLength= ? and");
            paramValueList.add(deviceParameter.getParamLength());
        }
        if (deviceParameter.getDecimalPlaces() != null) {
            paramBuf.append(" DecimalPlaces= ? and");
            paramValueList.add(deviceParameter.getDecimalPlaces());
        }
        if (deviceParameter.getUnit() != null) {
            paramBuf.append(" Unit= ? and");
            paramValueList.add(deviceParameter.getUnit());
        }
        if (deviceParameter.getMinValue() != null) {
            paramBuf.append(" MinValue= ? and");
            paramValueList.add(deviceParameter.getMinValue());
        }
        if (deviceParameter.getMaxValue() != null) {
            paramBuf.append(" MaxValue= ? and");
            paramValueList.add(deviceParameter.getMaxValue());
        }
        if (deviceParameter.getRegisterAddress() != null) {
            paramBuf.append(" RegisterAddress= ? and");
            paramValueList.add(deviceParameter.getRegisterAddress());
        }
        if (deviceParameter.getStartBit() != null) {
            paramBuf.append(" StartBit= ? and");
            paramValueList.add(deviceParameter.getStartBit());
        }
        if (deviceParameter.getEndBit() != null) {
            paramBuf.append(" EndBit= ? and");
            paramValueList.add(deviceParameter.getEndBit());
        }
        if (deviceParameter.getSymbol() != null) {
            paramBuf.append(" Symbol= ? and");
            paramValueList.add(deviceParameter.getSymbol());
        }
        if (deviceParameter.getParamType() != null) {
            paramBuf.append(" ParamType= ? and");
            paramValueList.add(deviceParameter.getParamType());
        }
        if (deviceParameter.getCategory() != null) {
            paramBuf.append(" Category= ? and");
            paramValueList.add(deviceParameter.getCategory());
        }
        if (deviceParameter.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceParameter.getRemark());
        }

        String sql = "delete from DeviceParameter where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.update(sql, paramValueList.toArray());
    }

    @Override
    public int batchDeleteDeviceParameterByIDs(String IDs) throws SQLException {
        String[] split = IDs.split(",");
        Object[][] params = new Object[1][];

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            wenHao.append("?,");
        }
        params[0] = split;

        String sql = "delete from DeviceParameter where ID in (" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int updateDeviceParameter(DeviceParameter deviceParameter) throws SQLException {
        String sql = "update DeviceParameter set ParamName= ? , ParamLength= ? , DecimalPlaces= ? , Unit= ? , MinValue= ? , MaxValue= ? , RegisterAddress= ? , StartBit= ? , EndBit= ? , Symbol= ? , ParamType= ? , Category= ? , Remark= ?  where ID = ?";
        return queryRunner.update(sql, deviceParameter.getParamName(), deviceParameter.getParamLength(), deviceParameter.getDecimalPlaces(), deviceParameter.getUnit(), deviceParameter.getMinValue(), deviceParameter.getMaxValue(), deviceParameter.getRegisterAddress(), deviceParameter.getStartBit(), deviceParameter.getEndBit(), deviceParameter.getSymbol(), deviceParameter.getParamType(), deviceParameter.getCategory(), deviceParameter.getRemark(), deviceParameter.getID());
    }

    @Override
    public long selectCount() throws SQLException {
        String sql = "select count(*) from DeviceParameter";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }

    @Override
    public long selectCountByCondition(DeviceParameter deviceParameter) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceParameter.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceParameter.getID());
        }
        if (deviceParameter.getParamName() != null) {
            paramBuf.append(" ParamName= ? and");
            paramValueList.add(deviceParameter.getParamName());
        }
        if (deviceParameter.getParamLength() != null) {
            paramBuf.append(" ParamLength= ? and");
            paramValueList.add(deviceParameter.getParamLength());
        }
        if (deviceParameter.getDecimalPlaces() != null) {
            paramBuf.append(" DecimalPlaces= ? and");
            paramValueList.add(deviceParameter.getDecimalPlaces());
        }
        if (deviceParameter.getUnit() != null) {
            paramBuf.append(" Unit= ? and");
            paramValueList.add(deviceParameter.getUnit());
        }
        if (deviceParameter.getMinValue() != null) {
            paramBuf.append(" MinValue= ? and");
            paramValueList.add(deviceParameter.getMinValue());
        }
        if (deviceParameter.getMaxValue() != null) {
            paramBuf.append(" MaxValue= ? and");
            paramValueList.add(deviceParameter.getMaxValue());
        }
        if (deviceParameter.getRegisterAddress() != null) {
            paramBuf.append(" RegisterAddress= ? and");
            paramValueList.add(deviceParameter.getRegisterAddress());
        }
        if (deviceParameter.getStartBit() != null) {
            paramBuf.append(" StartBit= ? and");
            paramValueList.add(deviceParameter.getStartBit());
        }
        if (deviceParameter.getEndBit() != null) {
            paramBuf.append(" EndBit= ? and");
            paramValueList.add(deviceParameter.getEndBit());
        }
        if (deviceParameter.getSymbol() != null) {
            paramBuf.append(" Symbol= ? and");
            paramValueList.add(deviceParameter.getSymbol());
        }
        if (deviceParameter.getParamType() != null) {
            paramBuf.append(" ParamType= ? and");
            paramValueList.add(deviceParameter.getParamType());
        }
        if (deviceParameter.getCategory() != null) {
            paramBuf.append(" Category= ? and");
            paramValueList.add(deviceParameter.getCategory());
        }
        if (deviceParameter.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceParameter.getRemark());
        }

        String sql = "select count(*) from DeviceParameter where " + paramBuf.substring(0, paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(), paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public DeviceParameter selectDeviceParameterByID(Integer ID) throws SQLException {
        String sql = "select ID as ID, ParamName as ParamName, ParamLength as ParamLength, DecimalPlaces as DecimalPlaces, Unit as Unit, MinValue as MinValue, MaxValue as MaxValue, RegisterAddress as RegisterAddress, StartBit as StartBit, EndBit as EndBit, Symbol as Symbol, ParamType as ParamType, Category as Category, Remark as Remark from DeviceParameter where  ID = ?";
        return queryRunner.query(sql, new BeanHandler<>(DeviceParameter.class), ID);
    }

    @Override
    public List<DeviceParameter> selectAllDeviceParameter() throws SQLException {
        String sql = "select ID as ID, ParamName as ParamName, ParamLength as ParamLength, DecimalPlaces as DecimalPlaces, Unit as Unit, MinValue as MinValue, MaxValue as MaxValue, RegisterAddress as RegisterAddress, StartBit as StartBit, EndBit as EndBit, Symbol as Symbol, ParamType as ParamType, Category as Category, Remark as Remark from DeviceParameter";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParameter.class));
    }

    @Override
    public List<DeviceParameter> selectDeviceParameterByCondition(DeviceParameter deviceParameter) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceParameter.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceParameter.getID());
        }
        if (deviceParameter.getParamName() != null) {
            paramBuf.append(" ParamName= ? and");
            paramValueList.add(deviceParameter.getParamName());
        }
        if (deviceParameter.getParamLength() != null) {
            paramBuf.append(" ParamLength= ? and");
            paramValueList.add(deviceParameter.getParamLength());
        }
        if (deviceParameter.getDecimalPlaces() != null) {
            paramBuf.append(" DecimalPlaces= ? and");
            paramValueList.add(deviceParameter.getDecimalPlaces());
        }
        if (deviceParameter.getUnit() != null) {
            paramBuf.append(" Unit= ? and");
            paramValueList.add(deviceParameter.getUnit());
        }
        if (deviceParameter.getMinValue() != null) {
            paramBuf.append(" MinValue= ? and");
            paramValueList.add(deviceParameter.getMinValue());
        }
        if (deviceParameter.getMaxValue() != null) {
            paramBuf.append(" MaxValue= ? and");
            paramValueList.add(deviceParameter.getMaxValue());
        }
        if (deviceParameter.getRegisterAddress() != null) {
            paramBuf.append(" RegisterAddress= ? and");
            paramValueList.add(deviceParameter.getRegisterAddress());
        }
        if (deviceParameter.getStartBit() != null) {
            paramBuf.append(" StartBit= ? and");
            paramValueList.add(deviceParameter.getStartBit());
        }
        if (deviceParameter.getEndBit() != null) {
            paramBuf.append(" EndBit= ? and");
            paramValueList.add(deviceParameter.getEndBit());
        }
        if (deviceParameter.getSymbol() != null) {
            paramBuf.append(" Symbol= ? and");
            paramValueList.add(deviceParameter.getSymbol());
        }
        if (deviceParameter.getParamType() != null) {
            paramBuf.append(" ParamType= ? and");
            paramValueList.add(deviceParameter.getParamType());
        }
        if (deviceParameter.getCategory() != null) {
            paramBuf.append(" Category= ? and");
            paramValueList.add(deviceParameter.getCategory());
        }
        if (deviceParameter.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceParameter.getRemark());
        }

        String sql = "select ID as ID, ParamName as ParamName, ParamLength as ParamLength, DecimalPlaces as DecimalPlaces, Unit as Unit, MinValue as MinValue, MaxValue as MaxValue, RegisterAddress as RegisterAddress, StartBit as StartBit, EndBit as EndBit, Symbol as Symbol, ParamType as ParamType, Category as Category, Remark as Remark  from DeviceParameter where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParameter.class), paramValueList.toArray());
    }

    @Override
    public List<DeviceParameter> selectDeviceParameterWithPagination(PageParam pageParam) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        String sql = "select ID as ID, ParamName as ParamName, ParamLength as ParamLength, DecimalPlaces as DecimalPlaces, Unit as Unit, MinValue as MinValue, MaxValue as MaxValue, RegisterAddress as RegisterAddress, StartBit as StartBit, EndBit as EndBit, Symbol as Symbol, ParamType as ParamType, Category as Category, Remark as Remark from DeviceParameter limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParameter.class), (page - 1) * rows, rows);
    }

    @Override
    public List<DeviceParameter> selectDeviceParameterWithPaginationByCondition(PageParam pageParam, DeviceParameter deviceParameter) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceParameter.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceParameter.getID());
        }
        if (deviceParameter.getParamName() != null) {
            paramBuf.append(" ParamName= ? and");
            paramValueList.add(deviceParameter.getParamName());
        }
        if (deviceParameter.getParamLength() != null) {
            paramBuf.append(" ParamLength= ? and");
            paramValueList.add(deviceParameter.getParamLength());
        }
        if (deviceParameter.getDecimalPlaces() != null) {
            paramBuf.append(" DecimalPlaces= ? and");
            paramValueList.add(deviceParameter.getDecimalPlaces());
        }
        if (deviceParameter.getUnit() != null) {
            paramBuf.append(" Unit= ? and");
            paramValueList.add(deviceParameter.getUnit());
        }
        if (deviceParameter.getMinValue() != null) {
            paramBuf.append(" MinValue= ? and");
            paramValueList.add(deviceParameter.getMinValue());
        }
        if (deviceParameter.getMaxValue() != null) {
            paramBuf.append(" MaxValue= ? and");
            paramValueList.add(deviceParameter.getMaxValue());
        }
        if (deviceParameter.getRegisterAddress() != null) {
            paramBuf.append(" RegisterAddress= ? and");
            paramValueList.add(deviceParameter.getRegisterAddress());
        }
        if (deviceParameter.getStartBit() != null) {
            paramBuf.append(" StartBit= ? and");
            paramValueList.add(deviceParameter.getStartBit());
        }
        if (deviceParameter.getEndBit() != null) {
            paramBuf.append(" EndBit= ? and");
            paramValueList.add(deviceParameter.getEndBit());
        }
        if (deviceParameter.getSymbol() != null) {
            paramBuf.append(" Symbol= ? and");
            paramValueList.add(deviceParameter.getSymbol());
        }
        if (deviceParameter.getParamType() != null) {
            paramBuf.append(" ParamType= ? and");
            paramValueList.add(deviceParameter.getParamType());
        }
        if (deviceParameter.getCategory() != null) {
            paramBuf.append(" Category= ? and");
            paramValueList.add(deviceParameter.getCategory());
        }
        if (deviceParameter.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceParameter.getRemark());
        }

        String sql = "select ID as ID, ParamName as ParamName, ParamLength as ParamLength, DecimalPlaces as DecimalPlaces, Unit as Unit, MinValue as MinValue, MaxValue as MaxValue, RegisterAddress as RegisterAddress, StartBit as StartBit, EndBit as EndBit, Symbol as Symbol, ParamType as ParamType, Category as Category, Remark as Remark  from DeviceParameter where " + paramBuf.substring(0, paramBuf.length() - 3) + " limit ?, ?";

        paramValueList.add((page - 1) * rows);
        paramValueList.add(rows);
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParameter.class), paramValueList.toArray());
    }

}