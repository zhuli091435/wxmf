package com.wxmf.dao;

import com.wxmf.pojo.PackageVersion;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageParam;

public interface PackageVersionDao {
    int insertPackageVersion(PackageVersion packageVersion) throws SQLException;

	int batchInsertPackageVersion(List<PackageVersion> packageVersionList) throws SQLException;

    int deletePackageVersionByID(Integer ID) throws SQLException;

	int deletePackageVersionByCondition(PackageVersion packageVersion) throws SQLException;

	int batchDeletePackageVersionByIDs(String IDs) throws SQLException;

    int updatePackageVersion(PackageVersion packageVersion) throws SQLException;

    long selectCount() throws SQLException;
    
    long selectCountByCondition(PackageVersion packageVersion) throws SQLException;

    PackageVersion selectPackageVersionByID(Integer ID) throws SQLException;

    List<PackageVersion> selectAllPackageVersion() throws SQLException;

	List<PackageVersion> selectPackageVersionByCondition(PackageVersion packageVersion) throws SQLException;

    List<PackageVersion> selectPackageVersionWithPagination(PageParam pageParam) throws SQLException;

    List<PackageVersion> selectPackageVersionWithPaginationByCondition(PageParam pageParam, PackageVersion packageVersion) throws SQLException;
    
}