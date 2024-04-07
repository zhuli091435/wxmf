package com.wxmf.dao;

import com.wxmf.pojo.ParamOption;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageParam;

public interface ParamOptionDao {
    int insertParamOption(ParamOption paramOption) throws SQLException;

	int batchInsertParamOption(List<ParamOption> paramOptionList) throws SQLException;

    int deleteParamOptionByID(Integer ID) throws SQLException;

	int deleteParamOptionByCondition(ParamOption paramOption) throws SQLException;

	int batchDeleteParamOptionByIDs(String IDs) throws SQLException;

    int updateParamOption(ParamOption paramOption) throws SQLException;

    long selectCount() throws SQLException;
    
    long selectCountByCondition(ParamOption paramOption) throws SQLException;

    ParamOption selectParamOptionByID(Integer ID) throws SQLException;

    List<ParamOption> selectAllParamOption() throws SQLException;

	List<ParamOption> selectParamOptionByCondition(ParamOption paramOption) throws SQLException;

    List<ParamOption> selectParamOptionWithPagination(PageParam pageParam) throws SQLException;

    List<ParamOption> selectParamOptionWithPaginationByCondition(PageParam pageParam, ParamOption paramOption) throws SQLException;
    
}