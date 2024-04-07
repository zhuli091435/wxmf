package com.wxmf.service.impl;

import com.wxmf.dao.PackageVersionDao;
import com.wxmf.dao.impl.PackageVersionDaoImpl;
import com.wxmf.pojo.PackageVersion;
import com.wxmf.service.PackageVersionService;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;
import java.util.List;

public class PackageVersionServiceImpl implements PackageVersionService {

    private PackageVersionDao packageVersionDao = new PackageVersionDaoImpl();

    @Override
    public int addPackageVersion(PackageVersion packageVersion) throws SQLException{
        int res = packageVersionDao.insertPackageVersion(packageVersion);
        return res;
    }

	@Override
    public int batchAddPackageVersion(List<PackageVersion> packageVersionList) throws SQLException{
		int res = packageVersionDao.batchInsertPackageVersion(packageVersionList);
		return res;
	}

    @Override
    public int deletePackageVersionByID(Integer ID) throws SQLException{
        int res = packageVersionDao.deletePackageVersionByID(ID);
        return res;
    }
    
 	@Override
	public int deletePackageVersion(PackageVersion packageVersion) throws SQLException{
	 	int res = packageVersionDao.deletePackageVersionByCondition(packageVersion);
        return res;
	}
	
	@Override
	public int batchDeletePackageVersionByIDs(String IDs) throws SQLException{
		int res = packageVersionDao.batchDeletePackageVersionByIDs(IDs);
        return res;
	}
	
    @Override
    public int updatePackageVersion(PackageVersion packageVersion) throws SQLException{
        int res = packageVersionDao.updatePackageVersion(packageVersion);
        return res;
    }

    @Override
    public PackageVersion getPackageVersionByID(Integer ID) throws SQLException{
        PackageVersion res = packageVersionDao.selectPackageVersionByID(ID);
        return res;
    }

    @Override
    public List<PackageVersion> getAllPackageVersion() throws SQLException{
        List<PackageVersion> res = packageVersionDao.selectAllPackageVersion();
        return res;
    }

	@Override
	public 	List<PackageVersion> getPackageVersion(PackageVersion packageVersion) throws SQLException{
		List<PackageVersion> res = packageVersionDao.selectPackageVersionByCondition(packageVersion);
		return res;
	}
	
    @Override
    public PageBean<PackageVersion> getPackageVersionWithPagination(PageParam pageParam) throws SQLException{
        long count = packageVersionDao.selectCount();
        PageBean<PackageVersion> res = new PageBean<>(pageParam, (int)count);
        List<PackageVersion> data = packageVersionDao.selectPackageVersionWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

	@Override
	public PageBean<PackageVersion> getPackageVersionWithPagination(PageParam pageParam, PackageVersion packageVersion) throws SQLException{
        long count = packageVersionDao.selectCountByCondition(packageVersion);
        PageBean<PackageVersion> res = new PageBean<>(pageParam, (int)count);		
		List<PackageVersion> data = packageVersionDao.selectPackageVersionWithPaginationByCondition(pageParam,packageVersion);
		res.setRecords(data);
        return res;
	}
  
}