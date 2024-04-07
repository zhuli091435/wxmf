package com.wxmf.service;

import com.wxmf.pojo.TimedReport;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

public interface TimedReportService {
    int addTimedReport(TimedReport timedReport) throws SQLException;
    
    int batchAddTimedReport(List<TimedReport> timedReportList) throws SQLException;

    int deleteTimedReportByID(Integer ID) throws SQLException;

	int deleteTimedReport(TimedReport timedReport) throws SQLException;
	
	int batchDeleteTimedReportByIDs(String IDs) throws SQLException;
	
    int updateTimedReport(TimedReport timedReport) throws SQLException;

    TimedReport getTimedReportByID(Integer ID) throws SQLException;

    List<TimedReport> getAllTimedReport() throws SQLException;

	List<TimedReport> getTimedReport(TimedReport timedReport) throws SQLException;
	
    PageBean<TimedReport> getTimedReportWithPagination(PageParam pageParam) throws SQLException;

	PageBean<TimedReport> getTimedReportWithPagination(PageParam pageParam, TimedReport timedReport) throws SQLException;
  
}