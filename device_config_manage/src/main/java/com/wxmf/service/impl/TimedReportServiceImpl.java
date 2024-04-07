package com.wxmf.service.impl;

import com.wxmf.pojo.TimedReport;
import java.util.List;
import com.wxmf.service.TimedReportService;
import com.wxmf.dao.TimedReportDao;
import com.wxmf.dao.impl.TimedReportDaoImpl;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;
import java.sql.SQLException;

public class TimedReportServiceImpl implements TimedReportService {

    private TimedReportDao timedReportDao = new TimedReportDaoImpl();

    @Override
    public int addTimedReport(TimedReport timedReport) throws SQLException{
        int res = timedReportDao.insertTimedReport(timedReport);
        return res;
    }

	@Override
    public int batchAddTimedReport(List<TimedReport> timedReportList) throws SQLException{
		int res = timedReportDao.batchInsertTimedReport(timedReportList);
		return res;
	}

    @Override
    public int deleteTimedReportByID(Integer ID) throws SQLException{
        int res = timedReportDao.deleteTimedReportByID(ID);
        return res;
    }
    
 	@Override
	public int deleteTimedReport(TimedReport timedReport) throws SQLException{
	 	int res = timedReportDao.deleteTimedReportByCondition(timedReport);
        return res;
	}
	
	@Override
	public int batchDeleteTimedReportByIDs(String IDs) throws SQLException{
		int res = timedReportDao.batchDeleteTimedReportByIDs(IDs);
        return res;
	}
	
    @Override
    public int updateTimedReport(TimedReport timedReport) throws SQLException{
        int res = timedReportDao.updateTimedReport(timedReport);
        return res;
    }

    @Override
    public TimedReport getTimedReportByID(Integer ID) throws SQLException{
        TimedReport res = timedReportDao.selectTimedReportByID(ID);
        return res;
    }

    @Override
    public List<TimedReport> getAllTimedReport() throws SQLException{
        List<TimedReport> res = timedReportDao.selectAllTimedReport();
        return res;
    }

	@Override
	public 	List<TimedReport> getTimedReport(TimedReport timedReport) throws SQLException{
		List<TimedReport> res = timedReportDao.selectTimedReportByCondition(timedReport);
		return res;
	}
	
    @Override
    public PageBean<TimedReport> getTimedReportWithPagination(PageParam pageParam) throws SQLException{
        long count = timedReportDao.selectCount();
        PageBean<TimedReport> res = new PageBean<>(pageParam, (int)count);
        List<TimedReport> data = timedReportDao.selectTimedReportWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

	@Override
	public PageBean<TimedReport> getTimedReportWithPagination(PageParam pageParam, TimedReport timedReport) throws SQLException{
        long count = timedReportDao.selectCountByCondition(timedReport);
        PageBean<TimedReport> res = new PageBean<>(pageParam, (int)count);		
		List<TimedReport> data = timedReportDao.selectTimedReportWithPaginationByCondition(pageParam,timedReport);
		res.setRecords(data);
        return res;
	}
  
}