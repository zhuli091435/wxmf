package com.wxmf.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxmf.pojo.ParamOption;

import java.util.List;
import java.util.ArrayList;

import com.wxmf.utils.DBUtil;
import com.wxmf.dao.ParamOptionDao;

import java.sql.SQLException;

import com.wxmf.utils.PageParam;

public class ParamOptionDaoImpl implements ParamOptionDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertParamOption(ParamOption paramOption) throws SQLException {
        String sql = "insert into ParamOption (ID, OptionIndex, OptionName, OptionValue, ParamTypeID, Remark) values (?, ?, ?, ?, ?, ?)";
        return queryRunner.update(sql, paramOption.getID(), paramOption.getOptionIndex(), paramOption.getOptionName(), paramOption.getOptionValue(), paramOption.getParamTypeID(), paramOption.getRemark());
    }

    @Override
    public int batchInsertParamOption(List<ParamOption> paramOptionList) throws SQLException {
        Object[][] params = new Object[paramOptionList.size()][6];

        for (int i = 0; i < params.length; i++) {
            ParamOption paramOption = paramOptionList.get(i);
            params[i][0] = paramOption.getID();
            params[i][1] = paramOption.getOptionIndex();
            params[i][2] = paramOption.getOptionName();
            params[i][3] = paramOption.getOptionValue();
            params[i][4] = paramOption.getParamTypeID();
            params[i][5] = paramOption.getRemark();
        }

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < params[0].length; i++) {
            wenHao.append("?,");
        }
        String sql = "insert into ParamOption values(" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deleteParamOptionByID(Integer ID) throws SQLException {
        String sql = "delete from ParamOption where ID = ?";
        return queryRunner.update(sql, ID);
    }

    @Override
    public int deleteParamOptionByCondition(ParamOption paramOption) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (paramOption.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(paramOption.getID());
        }
        if (paramOption.getOptionIndex() != null) {
            paramBuf.append(" OptionIndex= ? and");
            paramValueList.add(paramOption.getOptionIndex());
        }
        if (paramOption.getOptionName() != null) {
            paramBuf.append(" OptionName= ? and");
            paramValueList.add(paramOption.getOptionName());
        }
        if (paramOption.getOptionValue() != null) {
            paramBuf.append(" OptionValue= ? and");
            paramValueList.add(paramOption.getOptionValue());
        }
        if (paramOption.getParamTypeID() != null) {
            paramBuf.append(" ParamTypeID= ? and");
            paramValueList.add(paramOption.getParamTypeID());
        }
        if (paramOption.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(paramOption.getRemark());
        }

        String sql = "delete from ParamOption where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.update(sql, paramValueList.toArray());
    }

    @Override
    public int batchDeleteParamOptionByIDs(String IDs) throws SQLException {
        String[] split = IDs.split(",");
        Object[][] params = new Object[1][];

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            wenHao.append("?,");
        }
        params[0] = split;

        String sql = "delete from ParamOption where ID in (" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int updateParamOption(ParamOption paramOption) throws SQLException {
        String sql = "update ParamOption set OptionIndex= ? , OptionName= ? , OptionValue= ? , ParamTypeID= ? , Remark= ?  where ID = ?";
        return queryRunner.update(sql, paramOption.getOptionIndex(), paramOption.getOptionName(), paramOption.getOptionValue(), paramOption.getParamTypeID(), paramOption.getRemark(), paramOption.getID());
    }

    @Override
    public long selectCount() throws SQLException {
        String sql = "select count(*) from ParamOption";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }

    @Override
    public long selectCountByCondition(ParamOption paramOption) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (paramOption.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(paramOption.getID());
        }
        if (paramOption.getOptionIndex() != null) {
            paramBuf.append(" OptionIndex= ? and");
            paramValueList.add(paramOption.getOptionIndex());
        }
        if (paramOption.getOptionName() != null) {
            paramBuf.append(" OptionName= ? and");
            paramValueList.add(paramOption.getOptionName());
        }
        if (paramOption.getOptionValue() != null) {
            paramBuf.append(" OptionValue= ? and");
            paramValueList.add(paramOption.getOptionValue());
        }
        if (paramOption.getParamTypeID() != null) {
            paramBuf.append(" ParamType= ? and");
            paramValueList.add(paramOption.getParamTypeID());
        }
        if (paramOption.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(paramOption.getRemark());
        }

        String sql = "select count(*) from ParamOption where " + paramBuf.substring(0, paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(), paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public ParamOption selectParamOptionByID(Integer ID) throws SQLException {
        String sql = "select ID as ID, OptionIndex as OptionIndex, OptionName as OptionName, OptionValue as OptionValue, ParamTypeID as ParamTypeID, Remark as Remark from ParamOption where  ID = ?";
        return queryRunner.query(sql, new BeanHandler<>(ParamOption.class), ID);
    }

    @Override
    public List<ParamOption> selectAllParamOption() throws SQLException {
        String sql = "select ID as ID, OptionIndex as OptionIndex, OptionName as OptionName, OptionValue as OptionValue, ParamTypeID as ParamTypeID, Remark as Remark from ParamOption";
        return queryRunner.query(sql, new BeanListHandler<>(ParamOption.class));
    }

    @Override
    public List<ParamOption> selectParamOptionByCondition(ParamOption paramOption) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (paramOption.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(paramOption.getID());
        }
        if (paramOption.getOptionIndex() != null) {
            paramBuf.append(" OptionIndex= ? and");
            paramValueList.add(paramOption.getOptionIndex());
        }
        if (paramOption.getOptionName() != null) {
            paramBuf.append(" OptionName= ? and");
            paramValueList.add(paramOption.getOptionName());
        }
        if (paramOption.getOptionValue() != null) {
            paramBuf.append(" OptionValue= ? and");
            paramValueList.add(paramOption.getOptionValue());
        }
        if (paramOption.getParamTypeID() != null) {
            paramBuf.append(" ParamTypeID= ? and");
            paramValueList.add(paramOption.getParamTypeID());
        }
        if (paramOption.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(paramOption.getRemark());
        }

        String sql = "select ID as ID, OptionIndex as OptionIndex, OptionName as OptionName, OptionValue as OptionValue, ParamTypeID as ParamTypeID, Remark as Remark  from ParamOption where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.query(sql, new BeanListHandler<>(ParamOption.class), paramValueList.toArray());
    }

    @Override
    public List<ParamOption> selectParamOptionWithPagination(PageParam pageParam) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        String sql = "select ID as ID, OptionIndex as OptionIndex, OptionName as OptionName, OptionValue as OptionValue, ParamTypeID as ParamTypeID, Remark as Remark from ParamOption limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(ParamOption.class), (page - 1) * rows, rows);
    }

    @Override
    public List<ParamOption> selectParamOptionWithPaginationByCondition(PageParam pageParam, ParamOption paramOption) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (paramOption.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(paramOption.getID());
        }
        if (paramOption.getOptionIndex() != null) {
            paramBuf.append(" OptionIndex= ? and");
            paramValueList.add(paramOption.getOptionIndex());
        }
        if (paramOption.getOptionName() != null) {
            paramBuf.append(" OptionName= ? and");
            paramValueList.add(paramOption.getOptionName());
        }
        if (paramOption.getOptionValue() != null) {
            paramBuf.append(" OptionValue= ? and");
            paramValueList.add(paramOption.getOptionValue());
        }
        if (paramOption.getParamTypeID() != null) {
            paramBuf.append(" ParamType= ? and");
            paramValueList.add(paramOption.getParamTypeID());
        }
        if (paramOption.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(paramOption.getRemark());
        }

        String sql = "select ID as ID, OptionIndex as OptionIndex, OptionName as OptionName, OptionValue as OptionValue, ParamTypeID as ParamTypeID, Remark as Remark  from ParamOption where " + paramBuf.substring(0, paramBuf.length() - 3) + " limit ?, ?";

        paramValueList.add((page - 1) * rows);
        paramValueList.add(rows);
        return queryRunner.query(sql, new BeanListHandler<>(ParamOption.class), paramValueList.toArray());
    }

}