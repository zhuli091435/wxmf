package com.wxmf.service;

import com.wxmf.pojo.PackageVersion;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;
import java.util.List;

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