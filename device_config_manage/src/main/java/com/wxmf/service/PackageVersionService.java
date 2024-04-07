package com.wxmf.service;

import com.wxmf.pojo.PackageVersion;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

public interface PackageVersionService {
    int addPackageVersion(PackageVersion packageVersion) throws SQLException;
    
    int batchAddPackageVersion(List<PackageVersion> packageVersionList) throws SQLException;

    int deletePackageVersionByID(Integer ID) throws SQLException;

	int deletePackageVersion(PackageVersion packageVersion) throws SQLException;
	
	int batchDeletePackageVersionByIDs(String IDs) throws SQLException;
	
    int updatePackageVersion(PackageVersion packageVersion) throws SQLException;

    PackageVersion getPackageVersionByID(Integer ID) throws SQLException;

    List<PackageVersion> getAllPackageVersion() throws SQLException;

	List<PackageVersion> getPackageVersion(PackageVersion packageVersion) throws SQLException;
	
    PageBean<PackageVersion> getPackageVersionWithPagination(PageParam pageParam) throws SQLException;

	PageBean<PackageVersion> getPackageVersionWithPagination(PageParam pageParam, PackageVersion packageVersion) throws SQLException;
  
}