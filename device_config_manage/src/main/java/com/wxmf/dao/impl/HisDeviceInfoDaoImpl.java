package com.wxmf.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxmf.pojo.HisDeviceInfo;

import java.util.List;
import java.util.ArrayList;

import com.wxmf.utils.DBUtil;
import com.wxmf.dao.HisDeviceInfoDao;

import java.sql.SQLException;

import com.wxmf.utils.PageParam;

public class HisDeviceInfoDaoImpl implements HisDeviceInfoDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException {
        String sql = "insert into HisDeviceInfo (DeviceName, UpdateTime, HardwareVersion, SystemProgramVersion, ApplicationVersion, ProvincesCitiesCode, DeviceAddress, BatteryVoltage, ChargingVoltage, Temperature, Humidity, Signal, DeviceID, CCID, IMEI, IMSI, MSISDN, AddressEncodingFormat, HighAddress, ManageChannel, DomainName) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return queryRunner.update(sql, hisDeviceInfo.getDeviceName(), hisDeviceInfo.getUpdateTime(), hisDeviceInfo.getHardwareVersion(), hisDeviceInfo.getSystemProgramVersion(), hisDeviceInfo.getApplicationVersion(), hisDeviceInfo.getProvincesCitiesCode(), hisDeviceInfo.getDeviceAddress(), hisDeviceInfo.getBatteryVoltage(), hisDeviceInfo.getChargingVoltage(), hisDeviceInfo.getTemperature(), hisDeviceInfo.getHumidity(), hisDeviceInfo.getSignal(), hisDeviceInfo.getDeviceID(), hisDeviceInfo.getCCID(), hisDeviceInfo.getIMEI(), hisDeviceInfo.getIMSI(), hisDeviceInfo.getMSISDN(), hisDeviceInfo.getAddressEncodingFormat(), hisDeviceInfo.getHighAddress(), hisDeviceInfo.getManageChannel(), hisDeviceInfo.getDomainName());
    }

    @Override
    public int batchInsertHisDeviceInfo(List<HisDeviceInfo> hisDeviceInfoList) throws SQLException {
        Object[][] params = new Object[hisDeviceInfoList.size()][22];

        for (int i = 0; i < params.length; i++) {
            HisDeviceInfo hisDeviceInfo = hisDeviceInfoList.get(i);
            params[i][0] = hisDeviceInfo.getID();
            params[i][1] = hisDeviceInfo.getDeviceName();
            params[i][2] = hisDeviceInfo.getUpdateTime();
            params[i][3] = hisDeviceInfo.getHardwareVersion();
            params[i][4] = hisDeviceInfo.getSystemProgramVersion();
            params[i][5] = hisDeviceInfo.getApplicationVersion();
            params[i][6] = hisDeviceInfo.getProvincesCitiesCode();
            params[i][7] = hisDeviceInfo.getDeviceAddress();
            params[i][8] = hisDeviceInfo.getBatteryVoltage();
            params[i][9] = hisDeviceInfo.getChargingVoltage();
            params[i][10] = hisDeviceInfo.getTemperature();
            params[i][11] = hisDeviceInfo.getHumidity();
            params[i][12] = hisDeviceInfo.getSignal();
            params[i][13] = hisDeviceInfo.getDeviceID();
            params[i][14] = hisDeviceInfo.getCCID();
            params[i][15] = hisDeviceInfo.getIMEI();
            params[i][16] = hisDeviceInfo.getIMSI();
            params[i][17] = hisDeviceInfo.getMSISDN();
            params[i][18] = hisDeviceInfo.getAddressEncodingFormat();
            params[i][19] = hisDeviceInfo.getHighAddress();
            params[i][20] = hisDeviceInfo.getManageChannel();
            params[i][21] = hisDeviceInfo.getDomainName();
        }

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < params[0].length; i++) {
            wenHao.append("?,");
        }
        String sql = "insert into HisDeviceInfo values(" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deleteHisDeviceInfoByID(Integer ID) throws SQLException {
        String sql = "delete from HisDeviceInfo where ID = ?";
        return queryRunner.update(sql, ID);
    }

    @Override
    public int deleteHisDeviceInfoByCondition(HisDeviceInfo hisDeviceInfo) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (hisDeviceInfo.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(hisDeviceInfo.getID());
        }
        if (hisDeviceInfo.getDeviceName() != null) {
            paramBuf.append(" DeviceName= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceName());
        }
        if (hisDeviceInfo.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(hisDeviceInfo.getUpdateTime());
        }
        if (hisDeviceInfo.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(hisDeviceInfo.getHardwareVersion());
        }
        if (hisDeviceInfo.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(hisDeviceInfo.getSystemProgramVersion());
        }
        if (hisDeviceInfo.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(hisDeviceInfo.getApplicationVersion());
        }
        if (hisDeviceInfo.getProvincesCitiesCode() != null) {
            paramBuf.append(" ProvincesCitiesCode= ? and");
            paramValueList.add(hisDeviceInfo.getProvincesCitiesCode());
        }
        if (hisDeviceInfo.getDeviceAddress() != null) {
            paramBuf.append(" DeviceAddress= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceAddress());
        }
        if (hisDeviceInfo.getBatteryVoltage() != null) {
            paramBuf.append(" BatteryVoltage= ? and");
            paramValueList.add(hisDeviceInfo.getBatteryVoltage());
        }
        if (hisDeviceInfo.getChargingVoltage() != null) {
            paramBuf.append(" ChargingVoltage= ? and");
            paramValueList.add(hisDeviceInfo.getChargingVoltage());
        }
        if (hisDeviceInfo.getTemperature() != null) {
            paramBuf.append(" Temperature= ? and");
            paramValueList.add(hisDeviceInfo.getTemperature());
        }
        if (hisDeviceInfo.getHumidity() != null) {
            paramBuf.append(" Humidity= ? and");
            paramValueList.add(hisDeviceInfo.getHumidity());
        }
        if (hisDeviceInfo.getSignal() != null) {
            paramBuf.append(" Signal= ? and");
            paramValueList.add(hisDeviceInfo.getSignal());
        }
        if (hisDeviceInfo.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceID());
        }
        if (hisDeviceInfo.getCCID() != null) {
            paramBuf.append(" CCID= ? and");
            paramValueList.add(hisDeviceInfo.getCCID());
        }
        if (hisDeviceInfo.getIMEI() != null) {
            paramBuf.append(" IMEI= ? and");
            paramValueList.add(hisDeviceInfo.getIMEI());
        }
        if (hisDeviceInfo.getIMSI() != null) {
            paramBuf.append(" IMSI= ? and");
            paramValueList.add(hisDeviceInfo.getIMSI());
        }
        if (hisDeviceInfo.getMSISDN() != null) {
            paramBuf.append(" MSISDN= ? and");
            paramValueList.add(hisDeviceInfo.getMSISDN());
        }
        if (hisDeviceInfo.getAddressEncodingFormat() != null) {
            paramBuf.append(" AddressEncodingFormat= ? and");
            paramValueList.add(hisDeviceInfo.getAddressEncodingFormat());
        }
        if (hisDeviceInfo.getHighAddress() != null) {
            paramBuf.append(" HighAddress= ? and");
            paramValueList.add(hisDeviceInfo.getHighAddress());
        }
        if (hisDeviceInfo.getManageChannel() != null) {
            paramBuf.append(" ManageChannel= ? and");
            paramValueList.add(hisDeviceInfo.getManageChannel());
        }
        if (hisDeviceInfo.getDomainName() != null) {
            paramBuf.append(" DomainName= ? and");
            paramValueList.add(hisDeviceInfo.getDomainName());
        }

        String sql = "delete from HisDeviceInfo where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.update(sql, paramValueList.toArray());
    }

    @Override
    public int batchDeleteHisDeviceInfoByIDs(String IDs) throws SQLException {
        String[] split = IDs.split(",");
        Object[][] params = new Object[1][];

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            wenHao.append("?,");
        }
        params[0] = split;

        String sql = "delete from HisDeviceInfo where ID in (" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int updateHisDeviceInfo(HisDeviceInfo hisDeviceInfo) throws SQLException {
        String sql = "update HisDeviceInfo set DeviceName= ? , UpdateTime= ? , HardwareVersion= ? , SystemProgramVersion= ? , ApplicationVersion= ? , ProvincesCitiesCode= ? , DeviceAddress= ? , BatteryVoltage= ? , ChargingVoltage= ? , Temperature= ? , Humidity= ? , Signal= ? , DeviceID= ? , CCID= ? , IMEI= ? , IMSI= ? , MSISDN= ? , AddressEncodingFormat= ? , HighAddress= ? , ManageChannel= ? , DomainName= ?  where ID = ?";
        return queryRunner.update(sql, hisDeviceInfo.getDeviceName(), hisDeviceInfo.getUpdateTime(), hisDeviceInfo.getHardwareVersion(), hisDeviceInfo.getSystemProgramVersion(), hisDeviceInfo.getApplicationVersion(), hisDeviceInfo.getProvincesCitiesCode(), hisDeviceInfo.getDeviceAddress(), hisDeviceInfo.getBatteryVoltage(), hisDeviceInfo.getChargingVoltage(), hisDeviceInfo.getTemperature(), hisDeviceInfo.getHumidity(), hisDeviceInfo.getSignal(), hisDeviceInfo.getDeviceID(), hisDeviceInfo.getCCID(), hisDeviceInfo.getIMEI(), hisDeviceInfo.getIMSI(), hisDeviceInfo.getMSISDN(), hisDeviceInfo.getAddressEncodingFormat(), hisDeviceInfo.getHighAddress(), hisDeviceInfo.getManageChannel(), hisDeviceInfo.getDomainName(), hisDeviceInfo.getID());
    }

    @Override
    public long selectCount() throws SQLException {
        String sql = "select count(*) from HisDeviceInfo";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }

    @Override
    public long selectCountByCondition(HisDeviceInfo hisDeviceInfo) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (hisDeviceInfo.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(hisDeviceInfo.getID());
        }
        if (hisDeviceInfo.getDeviceName() != null) {
            paramBuf.append(" DeviceName= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceName());
        }
        if (hisDeviceInfo.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(hisDeviceInfo.getUpdateTime());
        }
        if (hisDeviceInfo.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(hisDeviceInfo.getHardwareVersion());
        }
        if (hisDeviceInfo.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(hisDeviceInfo.getSystemProgramVersion());
        }
        if (hisDeviceInfo.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(hisDeviceInfo.getApplicationVersion());
        }
        if (hisDeviceInfo.getProvincesCitiesCode() != null) {
            paramBuf.append(" ProvincesCitiesCode= ? and");
            paramValueList.add(hisDeviceInfo.getProvincesCitiesCode());
        }
        if (hisDeviceInfo.getDeviceAddress() != null) {
            paramBuf.append(" DeviceAddress= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceAddress());
        }
        if (hisDeviceInfo.getBatteryVoltage() != null) {
            paramBuf.append(" BatteryVoltage= ? and");
            paramValueList.add(hisDeviceInfo.getBatteryVoltage());
        }
        if (hisDeviceInfo.getChargingVoltage() != null) {
            paramBuf.append(" ChargingVoltage= ? and");
            paramValueList.add(hisDeviceInfo.getChargingVoltage());
        }
        if (hisDeviceInfo.getTemperature() != null) {
            paramBuf.append(" Temperature= ? and");
            paramValueList.add(hisDeviceInfo.getTemperature());
        }
        if (hisDeviceInfo.getHumidity() != null) {
            paramBuf.append(" Humidity= ? and");
            paramValueList.add(hisDeviceInfo.getHumidity());
        }
        if (hisDeviceInfo.getSignal() != null) {
            paramBuf.append(" Signal= ? and");
            paramValueList.add(hisDeviceInfo.getSignal());
        }
        if (hisDeviceInfo.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceID());
        }
        if (hisDeviceInfo.getCCID() != null) {
            paramBuf.append(" CCID= ? and");
            paramValueList.add(hisDeviceInfo.getCCID());
        }
        if (hisDeviceInfo.getIMEI() != null) {
            paramBuf.append(" IMEI= ? and");
            paramValueList.add(hisDeviceInfo.getIMEI());
        }
        if (hisDeviceInfo.getIMSI() != null) {
            paramBuf.append(" IMSI= ? and");
            paramValueList.add(hisDeviceInfo.getIMSI());
        }
        if (hisDeviceInfo.getMSISDN() != null) {
            paramBuf.append(" MSISDN= ? and");
            paramValueList.add(hisDeviceInfo.getMSISDN());
        }
        if (hisDeviceInfo.getAddressEncodingFormat() != null) {
            paramBuf.append(" AddressEncodingFormat= ? and");
            paramValueList.add(hisDeviceInfo.getAddressEncodingFormat());
        }
        if (hisDeviceInfo.getHighAddress() != null) {
            paramBuf.append(" HighAddress= ? and");
            paramValueList.add(hisDeviceInfo.getHighAddress());
        }
        if (hisDeviceInfo.getManageChannel() != null) {
            paramBuf.append(" ManageChannel= ? and");
            paramValueList.add(hisDeviceInfo.getManageChannel());
        }
        if (hisDeviceInfo.getDomainName() != null) {
            paramBuf.append(" DomainName= ? and");
            paramValueList.add(hisDeviceInfo.getDomainName());
        }

        String sql = "select count(*) from HisDeviceInfo where " + paramBuf.substring(0, paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(), paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public HisDeviceInfo selectHisDeviceInfoByID(Integer ID) throws SQLException {
        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName from HisDeviceInfo where  ID = ?";
        return queryRunner.query(sql, new BeanHandler<>(HisDeviceInfo.class), ID);
    }

    @Override
    public List<HisDeviceInfo> selectAllHisDeviceInfo() throws SQLException {
        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName from HisDeviceInfo";
        return queryRunner.query(sql, new BeanListHandler<>(HisDeviceInfo.class));
    }

    @Override
    public List<HisDeviceInfo> selectHisDeviceInfoByCondition(HisDeviceInfo hisDeviceInfo) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (hisDeviceInfo.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(hisDeviceInfo.getID());
        }
        if (hisDeviceInfo.getDeviceName() != null) {
            paramBuf.append(" DeviceName= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceName());
        }
        if (hisDeviceInfo.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(hisDeviceInfo.getUpdateTime());
        }
        if (hisDeviceInfo.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(hisDeviceInfo.getHardwareVersion());
        }
        if (hisDeviceInfo.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(hisDeviceInfo.getSystemProgramVersion());
        }
        if (hisDeviceInfo.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(hisDeviceInfo.getApplicationVersion());
        }
        if (hisDeviceInfo.getProvincesCitiesCode() != null) {
            paramBuf.append(" ProvincesCitiesCode= ? and");
            paramValueList.add(hisDeviceInfo.getProvincesCitiesCode());
        }
        if (hisDeviceInfo.getDeviceAddress() != null) {
            paramBuf.append(" DeviceAddress= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceAddress());
        }
        if (hisDeviceInfo.getBatteryVoltage() != null) {
            paramBuf.append(" BatteryVoltage= ? and");
            paramValueList.add(hisDeviceInfo.getBatteryVoltage());
        }
        if (hisDeviceInfo.getChargingVoltage() != null) {
            paramBuf.append(" ChargingVoltage= ? and");
            paramValueList.add(hisDeviceInfo.getChargingVoltage());
        }
        if (hisDeviceInfo.getTemperature() != null) {
            paramBuf.append(" Temperature= ? and");
            paramValueList.add(hisDeviceInfo.getTemperature());
        }
        if (hisDeviceInfo.getHumidity() != null) {
            paramBuf.append(" Humidity= ? and");
            paramValueList.add(hisDeviceInfo.getHumidity());
        }
        if (hisDeviceInfo.getSignal() != null) {
            paramBuf.append(" Signal= ? and");
            paramValueList.add(hisDeviceInfo.getSignal());
        }
        if (hisDeviceInfo.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceID());
        }
        if (hisDeviceInfo.getCCID() != null) {
            paramBuf.append(" CCID= ? and");
            paramValueList.add(hisDeviceInfo.getCCID());
        }
        if (hisDeviceInfo.getIMEI() != null) {
            paramBuf.append(" IMEI= ? and");
            paramValueList.add(hisDeviceInfo.getIMEI());
        }
        if (hisDeviceInfo.getIMSI() != null) {
            paramBuf.append(" IMSI= ? and");
            paramValueList.add(hisDeviceInfo.getIMSI());
        }
        if (hisDeviceInfo.getMSISDN() != null) {
            paramBuf.append(" MSISDN= ? and");
            paramValueList.add(hisDeviceInfo.getMSISDN());
        }
        if (hisDeviceInfo.getAddressEncodingFormat() != null) {
            paramBuf.append(" AddressEncodingFormat= ? and");
            paramValueList.add(hisDeviceInfo.getAddressEncodingFormat());
        }
        if (hisDeviceInfo.getHighAddress() != null) {
            paramBuf.append(" HighAddress= ? and");
            paramValueList.add(hisDeviceInfo.getHighAddress());
        }
        if (hisDeviceInfo.getManageChannel() != null) {
            paramBuf.append(" ManageChannel= ? and");
            paramValueList.add(hisDeviceInfo.getManageChannel());
        }
        if (hisDeviceInfo.getDomainName() != null) {
            paramBuf.append(" DomainName= ? and");
            paramValueList.add(hisDeviceInfo.getDomainName());
        }

        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName  from HisDeviceInfo where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.query(sql, new BeanListHandler<>(HisDeviceInfo.class), paramValueList.toArray());
    }

    @Override
    public List<HisDeviceInfo> selectHisDeviceInfoWithPagination(PageParam pageParam) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName from HisDeviceInfo limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(HisDeviceInfo.class), (page - 1) * rows, rows);
    }

    @Override
    public List<HisDeviceInfo> selectHisDeviceInfoWithPaginationByCondition(PageParam pageParam, HisDeviceInfo hisDeviceInfo) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (hisDeviceInfo.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(hisDeviceInfo.getID());
        }
        if (hisDeviceInfo.getDeviceName() != null) {
            paramBuf.append(" DeviceName= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceName());
        }
        if (hisDeviceInfo.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(hisDeviceInfo.getUpdateTime());
        }
        if (hisDeviceInfo.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(hisDeviceInfo.getHardwareVersion());
        }
        if (hisDeviceInfo.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(hisDeviceInfo.getSystemProgramVersion());
        }
        if (hisDeviceInfo.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(hisDeviceInfo.getApplicationVersion());
        }
        if (hisDeviceInfo.getProvincesCitiesCode() != null) {
            paramBuf.append(" ProvincesCitiesCode= ? and");
            paramValueList.add(hisDeviceInfo.getProvincesCitiesCode());
        }
        if (hisDeviceInfo.getDeviceAddress() != null) {
            paramBuf.append(" DeviceAddress= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceAddress());
        }
        if (hisDeviceInfo.getBatteryVoltage() != null) {
            paramBuf.append(" BatteryVoltage= ? and");
            paramValueList.add(hisDeviceInfo.getBatteryVoltage());
        }
        if (hisDeviceInfo.getChargingVoltage() != null) {
            paramBuf.append(" ChargingVoltage= ? and");
            paramValueList.add(hisDeviceInfo.getChargingVoltage());
        }
        if (hisDeviceInfo.getTemperature() != null) {
            paramBuf.append(" Temperature= ? and");
            paramValueList.add(hisDeviceInfo.getTemperature());
        }
        if (hisDeviceInfo.getHumidity() != null) {
            paramBuf.append(" Humidity= ? and");
            paramValueList.add(hisDeviceInfo.getHumidity());
        }
        if (hisDeviceInfo.getSignal() != null) {
            paramBuf.append(" Signal= ? and");
            paramValueList.add(hisDeviceInfo.getSignal());
        }
        if (hisDeviceInfo.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(hisDeviceInfo.getDeviceID());
        }
        if (hisDeviceInfo.getCCID() != null) {
            paramBuf.append(" CCID= ? and");
            paramValueList.add(hisDeviceInfo.getCCID());
        }
        if (hisDeviceInfo.getIMEI() != null) {
            paramBuf.append(" IMEI= ? and");
            paramValueList.add(hisDeviceInfo.getIMEI());
        }
        if (hisDeviceInfo.getIMSI() != null) {
            paramBuf.append(" IMSI= ? and");
            paramValueList.add(hisDeviceInfo.getIMSI());
        }
        if (hisDeviceInfo.getMSISDN() != null) {
            paramBuf.append(" MSISDN= ? and");
            paramValueList.add(hisDeviceInfo.getMSISDN());
        }
        if (hisDeviceInfo.getAddressEncodingFormat() != null) {
            paramBuf.append(" AddressEncodingFormat= ? and");
            paramValueList.add(hisDeviceInfo.getAddressEncodingFormat());
        }
        if (hisDeviceInfo.getHighAddress() != null) {
            paramBuf.append(" HighAddress= ? and");
            paramValueList.add(hisDeviceInfo.getHighAddress());
        }
        if (hisDeviceInfo.getManageChannel() != null) {
            paramBuf.append(" ManageChannel= ? and");
            paramValueList.add(hisDeviceInfo.getManageChannel());
        }
        if (hisDeviceInfo.getDomainName() != null) {
            paramBuf.append(" DomainName= ? and");
            paramValueList.add(hisDeviceInfo.getDomainName());
        }

        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName  from HisDeviceInfo where " + paramBuf.substring(0, paramBuf.length() - 3) + " limit ?, ?";

        paramValueList.add((page - 1) * rows);
        paramValueList.add(rows);
        return queryRunner.query(sql, new BeanListHandler<>(HisDeviceInfo.class), paramValueList.toArray());
    }

}