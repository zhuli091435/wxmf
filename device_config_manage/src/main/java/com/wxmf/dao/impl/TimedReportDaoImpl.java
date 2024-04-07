package com.wxmf.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxmf.pojo.TimedReport;

import java.util.List;
import java.util.ArrayList;

import com.wxmf.utils.DBUtil;
import com.wxmf.dao.TimedReportDao;

import java.sql.SQLException;

import com.wxmf.utils.PageParam;

public class TimedReportDaoImpl implements TimedReportDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertTimedReport(TimedReport timedReport) throws SQLException {
        String sql = "insert into TimedReport ( DeviceID, ReportTime, ReportData) values ( ?, ?, ?)";
        return queryRunner.update(sql, timedReport.getDeviceID(), timedReport.getReportTime(), timedReport.getReportData());
    }

    @Override
    public int batchInsertTimedReport(List<TimedReport> timedReportList) throws SQLException {
        Object[][] params = new Object[timedReportList.size()][4];

        for (int i = 0; i < params.length; i++) {
            TimedReport timedReport = timedReportList.get(i);
            params[i][0] = timedReport.getID();
            params[i][1] = timedReport.getDeviceID();
            params[i][2] = timedReport.getReportTime();
            params[i][3] = timedReport.getReportData();
        }

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < params[0].length; i++) {
            wenHao.append("?,");
        }
        String sql = "insert into TimedReport values(" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deleteTimedReportByID(Integer ID) throws SQLException {
        String sql = "delete from TimedReport where ID = ?";
        return queryRunner.update(sql, ID);
    }

    @Override
    public int deleteTimedReportByCondition(TimedReport timedReport) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (timedReport.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(timedReport.getID());
        }
        if (timedReport.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(timedReport.getDeviceID());
        }
        if (timedReport.getReportTime() != null) {
            paramBuf.append(" ReportTime= ? and");
            paramValueList.add(timedReport.getReportTime());
        }
        if (timedReport.getReportData() != null) {
            paramBuf.append(" ReportData= ? and");
            paramValueList.add(timedReport.getReportData());
        }

        String sql = "delete from TimedReport where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.update(sql, paramValueList.toArray());
    }

    @Override
    public int batchDeleteTimedReportByIDs(String IDs) throws SQLException {
        String[] split = IDs.split(",");
        Object[][] params = new Object[1][];

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            wenHao.append("?,");
        }
        params[0] = split;

        String sql = "delete from TimedReport where ID in (" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int updateTimedReport(TimedReport timedReport) throws SQLException {
        String sql = "update TimedReport set DeviceID= ? , ReportTime= ? , ReportData= ?  where ID = ?";
        return queryRunner.update(sql, timedReport.getDeviceID(), timedReport.getReportTime(), timedReport.getReportData(), timedReport.getID());
    }

    @Override
    public long selectCount() throws SQLException {
        String sql = "select count(*) from TimedReport";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }

    @Override
    public long selectCountByCondition(TimedReport timedReport) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (timedReport.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(timedReport.getID());
        }
        if (timedReport.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(timedReport.getDeviceID());
        }
        if (timedReport.getReportTime() != null) {
            paramBuf.append(" ReportTime= ? and");
            paramValueList.add(timedReport.getReportTime());
        }
        if (timedReport.getReportData() != null) {
            paramBuf.append(" ReportData= ? and");
            paramValueList.add(timedReport.getReportData());
        }

        String sql = "select count(*) from TimedReport where " + paramBuf.substring(0, paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(), paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public TimedReport selectTimedReportByID(Integer ID) throws SQLException {
        String sql = "select ID as ID, DeviceID as DeviceID, ReportTime as ReportTime, ReportData as ReportData from TimedReport where  ID = ?";
        return queryRunner.query(sql, new BeanHandler<>(TimedReport.class), ID);
    }

    @Override
    public List<TimedReport> selectAllTimedReport() throws SQLException {
        String sql = "select ID as ID, DeviceID as DeviceID, ReportTime as ReportTime, ReportData as ReportData from TimedReport";
        return queryRunner.query(sql, new BeanListHandler<>(TimedReport.class));
    }

    @Override
    public List<TimedReport> selectTimedReportByCondition(TimedReport timedReport) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (timedReport.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(timedReport.getID());
        }
        if (timedReport.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(timedReport.getDeviceID());
        }
        if (timedReport.getReportTime() != null) {
            paramBuf.append(" ReportTime= ? and");
            paramValueList.add(timedReport.getReportTime());
        }
        if (timedReport.getReportData() != null) {
            paramBuf.append(" ReportData= ? and");
            paramValueList.add(timedReport.getReportData());
        }

        String sql = "select ID as ID, DeviceID as DeviceID, ReportTime as ReportTime, ReportData as ReportData  from TimedReport where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.query(sql, new BeanListHandler<>(TimedReport.class), paramValueList.toArray());
    }

    @Override
    public List<TimedReport> selectTimedReportWithPagination(PageParam pageParam) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        String sql = "select ID as ID, DeviceID as DeviceID, ReportTime as ReportTime, ReportData as ReportData from TimedReport limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(TimedReport.class), (page - 1) * rows, rows);
    }

    @Override
    public List<TimedReport> selectTimedReportWithPaginationByCondition(PageParam pageParam, TimedReport timedReport) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (timedReport.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(timedReport.getID());
        }
        if (timedReport.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(timedReport.getDeviceID());
        }
        if (timedReport.getReportTime() != null) {
            paramBuf.append(" ReportTime= ? and");
            paramValueList.add(timedReport.getReportTime());
        }
        if (timedReport.getReportData() != null) {
            paramBuf.append(" ReportData= ? and");
            paramValueList.add(timedReport.getReportData());
        }

        String sql = "select ID as ID, DeviceID as DeviceID, ReportTime as ReportTime, ReportData as ReportData  from TimedReport where " + paramBuf.substring(0, paramBuf.length() - 3) + " limit ?, ?";

        paramValueList.add((page - 1) * rows);
        paramValueList.add(rows);
        return queryRunner.query(sql, new BeanListHandler<>(TimedReport.class), paramValueList.toArray());
    }

}