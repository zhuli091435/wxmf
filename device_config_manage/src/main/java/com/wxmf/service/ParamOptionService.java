package com.wxmf.service;

import com.wxmf.pojo.ParamOption;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

public interface ParamOptionService {
    int addParamOption(ParamOption paramOption) throws SQLException;
    
    int batchAddParamOption(List<ParamOption> paramOptionList) throws SQLException;

    int deleteParamOptionByID(Integer ID) throws SQLException;

	int deleteParamOption(ParamOption paramOption) throws SQLException;
	
	int batchDeleteParamOptionByIDs(String IDs) throws SQLException;
	
    int updateParamOption(ParamOption paramOption) throws SQLException;

    ParamOption getParamOptionByID(Integer ID) throws SQLException;

    List<ParamOption> getAllParamOption() throws SQLException;

	List<ParamOption> getParamOption(ParamOption paramOption) throws SQLException;
	
    PageBean<ParamOption> getParamOptionWithPagination(PageParam pageParam) throws SQLException;

	PageBean<ParamOption> getParamOptionWithPagination(PageParam pageParam, ParamOption paramOption) throws SQLException;
  
}