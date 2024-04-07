package com.wxmf.dao.impl;

import com.wxmf.dao.PackageVersionDao;
import com.wxmf.pojo.PackageVersion;
import com.wxmf.utils.DBUtil;
import com.wxmf.utils.PageParam;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageVersionDaoImpl implements PackageVersionDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertPackageVersion(PackageVersion packageVersion) throws SQLException {
        String sql = "insert into PackageVersion (ID, PackageName, PackagePath, HardwareVersion, SystemProgramVersion, ApplicationVersion) values (?, ?, ?, ?, ?, ?)";
        return queryRunner.update(sql, packageVersion.getID(), packageVersion.getPackageName(), packageVersion.getPackagePath(), packageVersion.getHardwareVersion(), packageVersion.getSystemProgramVersion(), packageVersion.getApplicationVersion());
    }

    @Override
    public int batchInsertPackageVersion(List<PackageVersion> packageVersionList) throws SQLException {
        Object[][] params = new Object[packageVersionList.size()][6];

        for (int i = 0; i < params.length; i++) {
            PackageVersion packageVersion = packageVersionList.get(i);
            params[i][0] = packageVersion.getID();
            params[i][1] = packageVersion.getPackageName();
            params[i][2] = packageVersion.getPackagePath();
            params[i][3] = packageVersion.getHardwareVersion();
            params[i][4] = packageVersion.getSystemProgramVersion();
            params[i][5] = packageVersion.getApplicationVersion();
        }

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < params[0].length; i++) {
            wenHao.append("?,");
        }
        String sql = "insert into PackageVersion values(" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deletePackageVersionByID(Integer ID) throws SQLException {
        String sql = "delete from PackageVersion where ID = ?";
        return queryRunner.update(sql, ID);
    }

    @Override
    public int deletePackageVersionByCondition(PackageVersion packageVersion) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (packageVersion.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(packageVersion.getID());
        }
        if (packageVersion.getPackageName() != null) {
            paramBuf.append(" PackageName= ? and");
            paramValueList.add(packageVersion.getPackageName());
        }
        if (packageVersion.getPackagePath() != null) {
            paramBuf.append(" PackagePath= ? and");
            paramValueList.add(packageVersion.getPackagePath());
        }
        if (packageVersion.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(packageVersion.getHardwareVersion());
        }
        if (packageVersion.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(packageVersion.getSystemProgramVersion());
        }
        if (packageVersion.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(packageVersion.getApplicationVersion());
        }

        String sql = "delete from PackageVersion where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.update(sql, paramValueList.toArray());
    }

    @Override
    public int batchDeletePackageVersionByIDs(String IDs) throws SQLException {
        String[] split = IDs.split(",");
        Object[][] params = new Object[1][];

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            wenHao.append("?,");
        }
        params[0] = split;

        String sql = "delete from PackageVersion where ID in (" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int updatePackageVersion(PackageVersion packageVersion) throws SQLException {
        String sql = "update PackageVersion set PackageName= ? , PackagePath= ? , HardwareVersion= ? , SystemProgramVersion= ? , ApplicationVersion= ?  where ID = ?";
        return queryRunner.update(sql, packageVersion.getPackageName(), packageVersion.getPackagePath(), packageVersion.getHardwareVersion(), packageVersion.getSystemProgramVersion(), packageVersion.getApplicationVersion(), packageVersion.getID());
    }

    @Override
    public long selectCount() throws SQLException {
        String sql = "select count(*) from PackageVersion";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }

    @Override
    public long selectCountByCondition(PackageVersion packageVersion) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (packageVersion.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(packageVersion.getID());
        }
        if (packageVersion.getPackageName() != null) {
            paramBuf.append(" PackageName= ? and");
            paramValueList.add(packageVersion.getPackageName());
        }
        if (packageVersion.getPackagePath() != null) {
            paramBuf.append(" PackagePath= ? and");
            paramValueList.add(packageVersion.getPackagePath());
        }
        if (packageVersion.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(packageVersion.getHardwareVersion());
        }
        if (packageVersion.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(packageVersion.getSystemProgramVersion());
        }
        if (packageVersion.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(packageVersion.getApplicationVersion());
        }

        String sql = "select count(*) from PackageVersion where " + paramBuf.substring(0, paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(), paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public PackageVersion selectPackageVersionByID(Integer ID) throws SQLException {
        String sql = "select ID as ID, PackageName as PackageName, PackagePath as PackagePath, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion from PackageVersion where  ID = ?";
        //String sql = "select * from PackageVersion";
        return queryRunner.query(sql, new BeanHandler<>(PackageVersion.class), ID);
    }

    @Override
    public List<PackageVersion> selectAllPackageVersion() throws SQLException {
        String sql = "select ID as ID, PackageName as PackageName, PackagePath as PackagePath, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion from PackageVersion";
        return queryRunner.query(sql, new BeanListHandler<>(PackageVersion.class));
    }

    @Override
    public List<PackageVersion> selectPackageVersionByCondition(PackageVersion packageVersion) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (packageVersion.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(packageVersion.getID());
        }
        if (packageVersion.getPackageName() != null) {
            paramBuf.append(" PackageName= ? and");
            paramValueList.add(packageVersion.getPackageName());
        }
        if (packageVersion.getPackagePath() != null) {
            paramBuf.append(" PackagePath= ? and");
            paramValueList.add(packageVersion.getPackagePath());
        }
        if (packageVersion.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(packageVersion.getHardwareVersion());
        }
        if (packageVersion.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(packageVersion.getSystemProgramVersion());
        }
        if (packageVersion.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(packageVersion.getApplicationVersion());
        }

        String sql = "select ID as ID, PackageName as PackageName, PackagePath as PackagePath, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion  from PackageVersion where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.query(sql, new BeanListHandler<>(PackageVersion.class), paramValueList.toArray());
    }

    @Override
    public List<PackageVersion> selectPackageVersionWithPagination(PageParam pageParam) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        String sql = "select ID as ID, PackageName as PackageName, PackagePath as PackagePath, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion from PackageVersion limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(PackageVersion.class), (page - 1) * rows, rows);
    }

    @Override
    public List<PackageVersion> selectPackageVersionWithPaginationByCondition(PageParam pageParam, PackageVersion packageVersion) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (packageVersion.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(packageVersion.getID());
        }
        if (packageVersion.getPackageName() != null) {
            paramBuf.append(" PackageName= ? and");
            paramValueList.add(packageVersion.getPackageName());
        }
        if (packageVersion.getPackagePath() != null) {
            paramBuf.append(" PackagePath= ? and");
            paramValueList.add(packageVersion.getPackagePath());
        }
        if (packageVersion.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(packageVersion.getHardwareVersion());
        }
        if (packageVersion.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(packageVersion.getSystemProgramVersion());
        }
        if (packageVersion.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(packageVersion.getApplicationVersion());
        }

        String sql = "select ID as ID, PackageName as PackageName, PackagePath as PackagePath, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion  from PackageVersion where " + paramBuf.substring(0, paramBuf.length() - 3) + " limit ?, ?";

        paramValueList.add((page - 1) * rows);
        paramValueList.add(rows);
        return queryRunner.query(sql, new BeanListHandler<>(PackageVersion.class), paramValueList.toArray());
    }

}