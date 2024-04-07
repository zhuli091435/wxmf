package com.wxmf.service.impl;

import com.wxmf.pojo.ParamOption;
import java.util.List;
import com.wxmf.service.ParamOptionService;
import com.wxmf.dao.ParamOptionDao;
import com.wxmf.dao.impl.ParamOptionDaoImpl;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;
import java.sql.SQLException;

public class ParamOptionServiceImpl implements ParamOptionService {

    private ParamOptionDao paramOptionDao = new ParamOptionDaoImpl();

    @Override
    public int addParamOption(ParamOption paramOption) throws SQLException{
        int res = paramOptionDao.insertParamOption(paramOption);
        return res;
    }

	@Override
    public int batchAddParamOption(List<ParamOption> paramOptionList) throws SQLException{
		int res = paramOptionDao.batchInsertParamOption(paramOptionList);
		return res;
	}

    @Override
    public int deleteParamOptionByID(Integer ID) throws SQLException{
        int res = paramOptionDao.deleteParamOptionByID(ID);
        return res;
    }
    
 	@Override
	public int deleteParamOption(ParamOption paramOption) throws SQLException{
	 	int res = paramOptionDao.deleteParamOptionByCondition(paramOption);
        return res;
	}
	
	@Override
	public int batchDeleteParamOptionByIDs(String IDs) throws SQLException{
		int res = paramOptionDao.batchDeleteParamOptionByIDs(IDs);
        return res;
	}
	
    @Override
    public int updateParamOption(ParamOption paramOption) throws SQLException{
        int res = paramOptionDao.updateParamOption(paramOption);
        return res;
    }

    @Override
    public ParamOption getParamOptionByID(Integer ID) throws SQLException{
        ParamOption res = paramOptionDao.selectParamOptionByID(ID);
        return res;
    }

    @Override
    public List<ParamOption> getAllParamOption() throws SQLException{
        List<ParamOption> res = paramOptionDao.selectAllParamOption();
        return res;
    }

	@Override
	public 	List<ParamOption> getParamOption(ParamOption paramOption) throws SQLException{
		List<ParamOption> res = paramOptionDao.selectParamOptionByCondition(paramOption);
		return res;
	}
	
    @Override
    public PageBean<ParamOption> getParamOptionWithPagination(PageParam pageParam) throws SQLException{
        long count = paramOptionDao.selectCount();
        PageBean<ParamOption> res = new PageBean<>(pageParam, (int)count);
        List<ParamOption> data = paramOptionDao.selectParamOptionWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

	@Override
	public PageBean<ParamOption> getParamOptionWithPagination(PageParam pageParam, ParamOption paramOption) throws SQLException{
        long count = paramOptionDao.selectCountByCondition(paramOption);
        PageBean<ParamOption> res = new PageBean<>(pageParam, (int)count);		
		List<ParamOption> data = paramOptionDao.selectParamOptionWithPaginationByCondition(pageParam,paramOption);
		res.setRecords(data);
        return res;
	}
  
}