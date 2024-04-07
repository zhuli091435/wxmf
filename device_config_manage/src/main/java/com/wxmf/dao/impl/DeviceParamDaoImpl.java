package com.wxmf.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxmf.pojo.DeviceParam;
import java.util.List;
import java.util.ArrayList;
import com.wxmf.utils.DBUtil;
import com.wxmf.dao.DeviceParamDao;
import java.sql.SQLException;
import com.wxmf.utils.PageParam;

public class DeviceParamDaoImpl implements DeviceParamDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertDeviceParam(DeviceParam deviceParam) throws SQLException{
        String sql ="insert into DeviceParam (ID, ParamIndex, RegisterAddress, ParamName, ParamType, ParamIdentifier, Remark) values (?, ?, ?, ?, ?, ?, ?)";
        return queryRunner.update(sql ,deviceParam.getID(), deviceParam.getParamIndex(), deviceParam.getRegisterAddress(), deviceParam.getParamName(), deviceParam.getParamType(), deviceParam.getParamIdentifier(), deviceParam.getRemark());
    }
    
    @Override
    public int batchInsertDeviceParam(List<DeviceParam> deviceParamList) throws SQLException{
    	Object[][] params = new Object[deviceParamList.size()][7];

		for (int i = 0; i < params.length; i++) {
			DeviceParam deviceParam = deviceParamList.get(i);
		    params[i][0] = deviceParam.getID();
		    params[i][1] = deviceParam.getParamIndex();
		    params[i][2] = deviceParam.getRegisterAddress();
		    params[i][3] = deviceParam.getParamName();
		    params[i][4] = deviceParam.getParamType();
		    params[i][5] = deviceParam.getParamIdentifier();
		    params[i][6] = deviceParam.getRemark();
		}
		
    	StringBuilder wenHao = new StringBuilder();
		for(int i =0;i<params[0].length;i++) {
			wenHao.append("?,");
		}
		String sql = "insert into DeviceParam values("+wenHao.deleteCharAt(wenHao.length()-1)+")";
		
		queryRunner.batch(sql, params);
		return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deleteDeviceParamByID(Integer ID) throws SQLException{
        String sql ="delete from DeviceParam where ID = ?";
        return queryRunner.update(sql ,ID);
    }
    
    @Override
	public int deleteDeviceParamByCondition(DeviceParam deviceParam) throws SQLException{
		List<Object> paramValueList = new ArrayList<>();
		StringBuffer paramBuf = new StringBuffer("1=1 and");
		
				if(deviceParam.getID() != null){
			paramBuf.append(" ID= ? and");
			paramValueList.add(deviceParam.getID());
		}
		if(deviceParam.getParamIndex() != null){
			paramBuf.append(" ParamIndex= ? and");
			paramValueList.add(deviceParam.getParamIndex());
		}
		if(deviceParam.getRegisterAddress() != null){
			paramBuf.append(" RegisterAddress= ? and");
			paramValueList.add(deviceParam.getRegisterAddress());
		}
		if(deviceParam.getParamName() != null){
			paramBuf.append(" ParamName= ? and");
			paramValueList.add(deviceParam.getParamName());
		}
		if(deviceParam.getParamType() != null){
			paramBuf.append(" ParamType= ? and");
			paramValueList.add(deviceParam.getParamType());
		}
		if(deviceParam.getParamIdentifier() != null){
			paramBuf.append(" ParamIdentifier= ? and");
			paramValueList.add(deviceParam.getParamIdentifier());
		}
		if(deviceParam.getRemark() != null){
			paramBuf.append(" Remark= ? and");
			paramValueList.add(deviceParam.getRemark());
		}

		String sql = "delete from DeviceParam where "+paramBuf.substring(0,paramBuf.length() - 3);
		return queryRunner.update(sql, paramValueList.toArray());
	}
	
	@Override
	public int batchDeleteDeviceParamByIDs(String IDs) throws SQLException {
		String[] split = IDs.split(",");
		Object[][] params = new Object[1][];
		
		StringBuilder wenHao = new StringBuilder();
		for(int i =0;i<split.length;i++) {
			wenHao.append("?,");
		}
		params[0] = split;
		
		String sql ="delete from DeviceParam where ID in ("+wenHao.deleteCharAt(wenHao.length()-1)+")";
		
		queryRunner.batch(sql, params);
		return 1;  // 如果不抛出异常，就返回1，表示删除成功
	}
	
    @Override
    public int updateDeviceParam(DeviceParam deviceParam) throws SQLException{
        String sql ="update DeviceParam set ParamIndex= ? , RegisterAddress= ? , ParamName= ? , ParamType= ? , ParamIdentifier= ? , Remark= ?  where ID = ?";
        return queryRunner.update(sql ,deviceParam.getParamIndex(), deviceParam.getRegisterAddress(), deviceParam.getParamName(), deviceParam.getParamType(), deviceParam.getParamIdentifier(), deviceParam.getRemark(), deviceParam.getID());
    }

    @Override
    public long selectCount() throws SQLException{
        String sql ="select count(*) from DeviceParam";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }
    
    @Override
    public long selectCountByCondition(DeviceParam deviceParam) throws SQLException{
    	List<Object> paramValueList = new ArrayList<>();
		StringBuffer paramBuf = new StringBuffer("1=1 and");
		
				if(deviceParam.getID() != null){
			paramBuf.append(" ID= ? and");
			paramValueList.add(deviceParam.getID());
		}
		if(deviceParam.getParamIndex() != null){
			paramBuf.append(" ParamIndex= ? and");
			paramValueList.add(deviceParam.getParamIndex());
		}
		if(deviceParam.getRegisterAddress() != null){
			paramBuf.append(" RegisterAddress= ? and");
			paramValueList.add(deviceParam.getRegisterAddress());
		}
		if(deviceParam.getParamName() != null){
			paramBuf.append(" ParamName= ? and");
			paramValueList.add(deviceParam.getParamName());
		}
		if(deviceParam.getParamType() != null){
			paramBuf.append(" ParamType= ? and");
			paramValueList.add(deviceParam.getParamType());
		}
		if(deviceParam.getParamIdentifier() != null){
			paramBuf.append(" ParamIdentifier= ? and");
			paramValueList.add(deviceParam.getParamIdentifier());
		}
		if(deviceParam.getRemark() != null){
			paramBuf.append(" Remark= ? and");
			paramValueList.add(deviceParam.getRemark());
		}

        String sql ="select count(*) from DeviceParam where "+paramBuf.substring(0,paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(),paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public DeviceParam selectDeviceParamByID(Integer ID) throws SQLException{
        String sql ="select ID as ID, ParamIndex as ParamIndex, RegisterAddress as RegisterAddress, ParamName as ParamName, ParamType as ParamType, ParamIdentifier as ParamIdentifier, Remark as Remark from DeviceParam where  ID = ?";
        return queryRunner.query(sql, new BeanHandler<>(DeviceParam.class), ID);
    }

    @Override
    public List<DeviceParam> selectAllDeviceParam() throws SQLException{
        String sql ="select ID as ID, ParamIndex as ParamIndex, RegisterAddress as RegisterAddress, ParamName as ParamName, ParamType as ParamType, ParamIdentifier as ParamIdentifier, Remark as Remark from DeviceParam";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParam.class));
    }

    @Override
	public List<DeviceParam> selectDeviceParamByCondition(DeviceParam deviceParam) throws SQLException{
		List<Object> paramValueList = new ArrayList<>();
		StringBuffer paramBuf = new StringBuffer("1=1 and");
		
				if(deviceParam.getID() != null){
			paramBuf.append(" ID= ? and");
			paramValueList.add(deviceParam.getID());
		}
		if(deviceParam.getParamIndex() != null){
			paramBuf.append(" ParamIndex= ? and");
			paramValueList.add(deviceParam.getParamIndex());
		}
		if(deviceParam.getRegisterAddress() != null){
			paramBuf.append(" RegisterAddress= ? and");
			paramValueList.add(deviceParam.getRegisterAddress());
		}
		if(deviceParam.getParamName() != null){
			paramBuf.append(" ParamName= ? and");
			paramValueList.add(deviceParam.getParamName());
		}
		if(deviceParam.getParamType() != null){
			paramBuf.append(" ParamType= ? and");
			paramValueList.add(deviceParam.getParamType());
		}
		if(deviceParam.getParamIdentifier() != null){
			paramBuf.append(" ParamIdentifier= ? and");
			paramValueList.add(deviceParam.getParamIdentifier());
		}
		if(deviceParam.getRemark() != null){
			paramBuf.append(" Remark= ? and");
			paramValueList.add(deviceParam.getRemark());
		}

		String sql = "select ID as ID, ParamIndex as ParamIndex, RegisterAddress as RegisterAddress, ParamName as ParamName, ParamType as ParamType, ParamIdentifier as ParamIdentifier, Remark as Remark  from DeviceParam where "+paramBuf.substring(0,paramBuf.length() - 3);
		return queryRunner.query(sql, new BeanListHandler<>(DeviceParam.class), paramValueList.toArray());
	}
	
    @Override
    public List<DeviceParam> selectDeviceParamWithPagination(PageParam pageParam) throws SQLException {
    	int page = pageParam.getPage();
    	int rows = pageParam.getRows();
    	
        String sql ="select ID as ID, ParamIndex as ParamIndex, RegisterAddress as RegisterAddress, ParamName as ParamName, ParamType as ParamType, ParamIdentifier as ParamIdentifier, Remark as Remark from DeviceParam limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceParam.class), (page - 1) * rows, rows);
    }

    @Override
  	public List<DeviceParam> selectDeviceParamWithPaginationByCondition(PageParam pageParam, DeviceParam deviceParam) throws SQLException{
  		int page = pageParam.getPage();
    	int rows = pageParam.getRows();
  		
  		List<Object> paramValueList = new ArrayList<>();
		StringBuffer paramBuf = new StringBuffer("1=1 and");
		
				if(deviceParam.getID() != null){
			paramBuf.append(" ID= ? and");
			paramValueList.add(deviceParam.getID());
		}
		if(deviceParam.getParamIndex() != null){
			paramBuf.append(" ParamIndex= ? and");
			paramValueList.add(deviceParam.getParamIndex());
		}
		if(deviceParam.getRegisterAddress() != null){
			paramBuf.append(" RegisterAddress= ? and");
			paramValueList.add(deviceParam.getRegisterAddress());
		}
		if(deviceParam.getParamName() != null){
			paramBuf.append(" ParamName= ? and");
			paramValueList.add(deviceParam.getParamName());
		}
		if(deviceParam.getParamType() != null){
			paramBuf.append(" ParamType= ? and");
			paramValueList.add(deviceParam.getParamType());
		}
		if(deviceParam.getParamIdentifier() != null){
			paramBuf.append(" ParamIdentifier= ? and");
			paramValueList.add(deviceParam.getParamIdentifier());
		}
		if(deviceParam.getRemark() != null){
			paramBuf.append(" Remark= ? and");
			paramValueList.add(deviceParam.getRemark());
		}

		String sql = "select ID as ID, ParamIndex as ParamIndex, RegisterAddress as RegisterAddress, ParamName as ParamName, ParamType as ParamType, ParamIdentifier as ParamIdentifier, Remark as Remark  from DeviceParam where "+paramBuf.substring(0,paramBuf.length() - 3)+" limit ?, ?";
		
		paramValueList.add((page - 1) * rows);
		paramValueList.add(rows);
		return queryRunner.query(sql, new BeanListHandler<>(DeviceParam.class), paramValueList.toArray());
  	}
 
}