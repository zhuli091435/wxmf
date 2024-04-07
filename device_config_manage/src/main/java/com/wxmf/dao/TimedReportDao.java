package com.wxmf.dao;

import com.wxmf.pojo.TimedReport;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageParam;

public interface TimedReportDao {
    int insertTimedReport(TimedReport timedReport) throws SQLException;

	int batchInsertTimedReport(List<TimedReport> timedReportList) throws SQLException;

    int deleteTimedReportByID(Integer ID) throws SQLException;

	int deleteTimedReportByCondition(TimedReport timedReport) throws SQLException;

	int batchDeleteTimedReportByIDs(String IDs) throws SQLException;

    int updateTimedReport(TimedReport timedReport) throws SQLException;

    long selectCount() throws SQLException;
    
    long selectCountByCondition(TimedReport timedReport) throws SQLException;

    TimedReport selectTimedReportByID(Integer ID) throws SQLException;

    List<TimedReport> selectAllTimedReport() throws SQLException;

	List<TimedReport> selectTimedReportByCondition(TimedReport timedReport) throws SQLException;

    List<TimedReport> selectTimedReportWithPagination(PageParam pageParam) throws SQLException;

    List<TimedReport> selectTimedReportWithPaginationByCondition(PageParam pageParam, TimedReport timedReport) throws SQLException;
    
}