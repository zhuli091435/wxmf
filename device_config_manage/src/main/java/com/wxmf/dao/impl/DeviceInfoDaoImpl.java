package com.wxmf.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxmf.pojo.DeviceInfo;

import java.util.List;
import java.util.ArrayList;

import com.wxmf.utils.DBUtil;
import com.wxmf.dao.DeviceInfoDao;

import java.sql.SQLException;

import com.wxmf.utils.PageParam;

public class DeviceInfoDaoImpl implements DeviceInfoDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertDeviceInfo(DeviceInfo deviceInfo) throws SQLException {
        String sql = "insert into DeviceInfo (DeviceName, UpdateTime, HardwareVersion, SystemProgramVersion, ApplicationVersion, ProvincesCitiesCode, DeviceAddress, BatteryVoltage, ChargingVoltage, Temperature, Humidity, Signal, IPLocation, ProjectID, DeliveryTime, InstallationTime, DeviceID, CCID, IMEI, IMSI, MSISDN, AddressEncodingFormat, HighAddress, ManageChannel, DomainName, CustomizedFeatures, ProgramName, Status, Remark) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return queryRunner.update(sql, deviceInfo.getDeviceName(), deviceInfo.getUpdateTime(), deviceInfo.getHardwareVersion(), deviceInfo.getSystemProgramVersion(), deviceInfo.getApplicationVersion(), deviceInfo.getProvincesCitiesCode(), deviceInfo.getDeviceAddress(), deviceInfo.getBatteryVoltage(), deviceInfo.getChargingVoltage(), deviceInfo.getTemperature(), deviceInfo.getHumidity(), deviceInfo.getSignal(), deviceInfo.getIPLocation(), deviceInfo.getProjectID(), deviceInfo.getDeliveryTime(), deviceInfo.getInstallationTime(), deviceInfo.getDeviceID(), deviceInfo.getCCID(), deviceInfo.getIMEI(), deviceInfo.getIMSI(), deviceInfo.getMSISDN(), deviceInfo.getAddressEncodingFormat(), deviceInfo.getHighAddress(), deviceInfo.getManageChannel(), deviceInfo.getDomainName(), deviceInfo.getCustomizedFeatures(), deviceInfo.getProgramName(), deviceInfo.getStatus(), deviceInfo.getRemark());
    }

    @Override
    public int batchInsertDeviceInfo(List<DeviceInfo> deviceInfoList) throws SQLException {
        Object[][] params = new Object[deviceInfoList.size()][30];

        for (int i = 0; i < params.length; i++) {
            DeviceInfo deviceInfo = deviceInfoList.get(i);
            params[i][0] = deviceInfo.getID();
            params[i][1] = deviceInfo.getDeviceName();
            params[i][2] = deviceInfo.getUpdateTime();
            params[i][3] = deviceInfo.getHardwareVersion();
            params[i][4] = deviceInfo.getSystemProgramVersion();
            params[i][5] = deviceInfo.getApplicationVersion();
            params[i][6] = deviceInfo.getProvincesCitiesCode();
            params[i][7] = deviceInfo.getDeviceAddress();
            params[i][8] = deviceInfo.getBatteryVoltage();
            params[i][9] = deviceInfo.getChargingVoltage();
            params[i][10] = deviceInfo.getTemperature();
            params[i][11] = deviceInfo.getHumidity();
            params[i][12] = deviceInfo.getSignal();
            params[i][13] = deviceInfo.getIPLocation();
            params[i][14] = deviceInfo.getProjectID();
            params[i][15] = deviceInfo.getDeliveryTime();
            params[i][16] = deviceInfo.getInstallationTime();
            params[i][17] = deviceInfo.getDeviceID();
            params[i][18] = deviceInfo.getCCID();
            params[i][19] = deviceInfo.getIMEI();
            params[i][20] = deviceInfo.getIMSI();
            params[i][21] = deviceInfo.getMSISDN();
            params[i][22] = deviceInfo.getAddressEncodingFormat();
            params[i][23] = deviceInfo.getHighAddress();
            params[i][24] = deviceInfo.getManageChannel();
            params[i][25] = deviceInfo.getDomainName();
            params[i][26] = deviceInfo.getCustomizedFeatures();
            params[i][27] = deviceInfo.getProgramName();
            params[i][28] = deviceInfo.getStatus();
            params[i][29] = deviceInfo.getRemark();
        }

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < params[0].length; i++) {
            wenHao.append("?,");
        }
        String sql = "insert into DeviceInfo values(" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deleteDeviceInfoByID(Integer ID) throws SQLException {
        String sql = "delete from DeviceInfo where ID = ?";
        return queryRunner.update(sql, ID);
    }

    @Override
    public int deleteDeviceInfoByCondition(DeviceInfo deviceInfo) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceInfo.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceInfo.getID());
        }
        if (deviceInfo.getDeviceName() != null) {
            paramBuf.append(" DeviceName= ? and");
            paramValueList.add(deviceInfo.getDeviceName());
        }
        if (deviceInfo.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(deviceInfo.getUpdateTime());
        }
        if (deviceInfo.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(deviceInfo.getHardwareVersion());
        }
        if (deviceInfo.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(deviceInfo.getSystemProgramVersion());
        }
        if (deviceInfo.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(deviceInfo.getApplicationVersion());
        }
        if (deviceInfo.getProvincesCitiesCode() != null) {
            paramBuf.append(" ProvincesCitiesCode= ? and");
            paramValueList.add(deviceInfo.getProvincesCitiesCode());
        }
        if (deviceInfo.getDeviceAddress() != null) {
            paramBuf.append(" DeviceAddress= ? and");
            paramValueList.add(deviceInfo.getDeviceAddress());
        }
        if (deviceInfo.getBatteryVoltage() != null) {
            paramBuf.append(" BatteryVoltage= ? and");
            paramValueList.add(deviceInfo.getBatteryVoltage());
        }
        if (deviceInfo.getChargingVoltage() != null) {
            paramBuf.append(" ChargingVoltage= ? and");
            paramValueList.add(deviceInfo.getChargingVoltage());
        }
        if (deviceInfo.getTemperature() != null) {
            paramBuf.append(" Temperature= ? and");
            paramValueList.add(deviceInfo.getTemperature());
        }
        if (deviceInfo.getHumidity() != null) {
            paramBuf.append(" Humidity= ? and");
            paramValueList.add(deviceInfo.getHumidity());
        }
        if (deviceInfo.getSignal() != null) {
            paramBuf.append(" Signal= ? and");
            paramValueList.add(deviceInfo.getSignal());
        }
        if (deviceInfo.getIPLocation() != null) {
            paramBuf.append(" IPLocation= ? and");
            paramValueList.add(deviceInfo.getIPLocation());
        }
        if (deviceInfo.getProjectID() != null) {
            paramBuf.append(" ProjectID= ? and");
            paramValueList.add(deviceInfo.getProjectID());
        }
        if (deviceInfo.getDeliveryTime() != null) {
            paramBuf.append(" DeliveryTime= ? and");
            paramValueList.add(deviceInfo.getDeliveryTime());
        }
        if (deviceInfo.getInstallationTime() != null) {
            paramBuf.append(" InstallationTime= ? and");
            paramValueList.add(deviceInfo.getInstallationTime());
        }
        if (deviceInfo.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceInfo.getDeviceID());
        }
        if (deviceInfo.getCCID() != null) {
            paramBuf.append(" CCID= ? and");
            paramValueList.add(deviceInfo.getCCID());
        }
        if (deviceInfo.getIMEI() != null) {
            paramBuf.append(" IMEI= ? and");
            paramValueList.add(deviceInfo.getIMEI());
        }
        if (deviceInfo.getIMSI() != null) {
            paramBuf.append(" IMSI= ? and");
            paramValueList.add(deviceInfo.getIMSI());
        }
        if (deviceInfo.getMSISDN() != null) {
            paramBuf.append(" MSISDN= ? and");
            paramValueList.add(deviceInfo.getMSISDN());
        }
        if (deviceInfo.getAddressEncodingFormat() != null) {
            paramBuf.append(" AddressEncodingFormat= ? and");
            paramValueList.add(deviceInfo.getAddressEncodingFormat());
        }
        if (deviceInfo.getHighAddress() != null) {
            paramBuf.append(" HighAddress= ? and");
            paramValueList.add(deviceInfo.getHighAddress());
        }
        if (deviceInfo.getManageChannel() != null) {
            paramBuf.append(" ManageChannel= ? and");
            paramValueList.add(deviceInfo.getManageChannel());
        }
        if (deviceInfo.getDomainName() != null) {
            paramBuf.append(" DomainName= ? and");
            paramValueList.add(deviceInfo.getDomainName());
        }
        if (deviceInfo.getCustomizedFeatures() != null) {
            paramBuf.append(" CustomizedFeatures= ? and");
            paramValueList.add(deviceInfo.getCustomizedFeatures());
        }
        if (deviceInfo.getProgramName() != null) {
            paramBuf.append(" ProgramName= ? and");
            paramValueList.add(deviceInfo.getProgramName());
        }
        if (deviceInfo.getStatus() != null) {
            paramBuf.append(" Status= ? and");
            paramValueList.add(deviceInfo.getStatus());
        }
        if (deviceInfo.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceInfo.getRemark());
        }

        String sql = "delete from DeviceInfo where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.update(sql, paramValueList.toArray());
    }

    @Override
    public int batchDeleteDeviceInfoByIDs(String IDs) throws SQLException {
        String[] split = IDs.split(",");
        Object[][] params = new Object[1][];

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            wenHao.append("?,");
        }
        params[0] = split;

        String sql = "delete from DeviceInfo where ID in (" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo) throws SQLException {
        String sql = "update DeviceInfo set DeviceName= ? , UpdateTime= ? , HardwareVersion= ? , SystemProgramVersion= ? , ApplicationVersion= ? , ProvincesCitiesCode= ? , DeviceAddress= ? , BatteryVoltage= ? , ChargingVoltage= ? , Temperature= ? , Humidity= ? , Signal= ? , IPLocation= ? , ProjectID= ? , DeliveryTime= ? , InstallationTime= ? , DeviceID= ? , CCID= ? , IMEI= ? , IMSI= ? , MSISDN= ? , AddressEncodingFormat= ? , HighAddress= ? , ManageChannel= ? , DomainName= ? , CustomizedFeatures= ? , ProgramName= ? , Status= ? , Remark= ?  where ID = ?";
        return queryRunner.update(sql, deviceInfo.getDeviceName(), deviceInfo.getUpdateTime(), deviceInfo.getHardwareVersion(), deviceInfo.getSystemProgramVersion(), deviceInfo.getApplicationVersion(), deviceInfo.getProvincesCitiesCode(), deviceInfo.getDeviceAddress(), deviceInfo.getBatteryVoltage(), deviceInfo.getChargingVoltage(), deviceInfo.getTemperature(), deviceInfo.getHumidity(), deviceInfo.getSignal(), deviceInfo.getIPLocation(), deviceInfo.getProjectID(), deviceInfo.getDeliveryTime(), deviceInfo.getInstallationTime(), deviceInfo.getDeviceID(), deviceInfo.getCCID(), deviceInfo.getIMEI(), deviceInfo.getIMSI(), deviceInfo.getMSISDN(), deviceInfo.getAddressEncodingFormat(), deviceInfo.getHighAddress(), deviceInfo.getManageChannel(), deviceInfo.getDomainName(), deviceInfo.getCustomizedFeatures(), deviceInfo.getProgramName(), deviceInfo.getStatus(), deviceInfo.getRemark(), deviceInfo.getID());
    }

    @Override
    public long selectCount() throws SQLException {
        String sql = "select count(*) from DeviceInfo";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }

    @Override
    public long selectCountByCondition(DeviceInfo deviceInfo) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceInfo.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceInfo.getID());
        }
        if (deviceInfo.getDeviceName() != null) {
            paramBuf.append(" DeviceName= ? and");
            paramValueList.add(deviceInfo.getDeviceName());
        }
        if (deviceInfo.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(deviceInfo.getUpdateTime());
        }
        if (deviceInfo.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(deviceInfo.getHardwareVersion());
        }
        if (deviceInfo.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(deviceInfo.getSystemProgramVersion());
        }
        if (deviceInfo.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(deviceInfo.getApplicationVersion());
        }
        if (deviceInfo.getProvincesCitiesCode() != null) {
            paramBuf.append(" ProvincesCitiesCode= ? and");
            paramValueList.add(deviceInfo.getProvincesCitiesCode());
        }
        if (deviceInfo.getDeviceAddress() != null) {
            paramBuf.append(" DeviceAddress= ? and");
            paramValueList.add(deviceInfo.getDeviceAddress());
        }
        if (deviceInfo.getBatteryVoltage() != null) {
            paramBuf.append(" BatteryVoltage= ? and");
            paramValueList.add(deviceInfo.getBatteryVoltage());
        }
        if (deviceInfo.getChargingVoltage() != null) {
            paramBuf.append(" ChargingVoltage= ? and");
            paramValueList.add(deviceInfo.getChargingVoltage());
        }
        if (deviceInfo.getTemperature() != null) {
            paramBuf.append(" Temperature= ? and");
            paramValueList.add(deviceInfo.getTemperature());
        }
        if (deviceInfo.getHumidity() != null) {
            paramBuf.append(" Humidity= ? and");
            paramValueList.add(deviceInfo.getHumidity());
        }
        if (deviceInfo.getSignal() != null) {
            paramBuf.append(" Signal= ? and");
            paramValueList.add(deviceInfo.getSignal());
        }
        if (deviceInfo.getIPLocation() != null) {
            paramBuf.append(" IPLocation= ? and");
            paramValueList.add(deviceInfo.getIPLocation());
        }
        if (deviceInfo.getProjectID() != null) {
            paramBuf.append(" ProjectID= ? and");
            paramValueList.add(deviceInfo.getProjectID());
        }
        if (deviceInfo.getDeliveryTime() != null) {
            paramBuf.append(" DeliveryTime= ? and");
            paramValueList.add(deviceInfo.getDeliveryTime());
        }
        if (deviceInfo.getInstallationTime() != null) {
            paramBuf.append(" InstallationTime= ? and");
            paramValueList.add(deviceInfo.getInstallationTime());
        }
        if (deviceInfo.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceInfo.getDeviceID());
        }
        if (deviceInfo.getCCID() != null) {
            paramBuf.append(" CCID= ? and");
            paramValueList.add(deviceInfo.getCCID());
        }
        if (deviceInfo.getIMEI() != null) {
            paramBuf.append(" IMEI= ? and");
            paramValueList.add(deviceInfo.getIMEI());
        }
        if (deviceInfo.getIMSI() != null) {
            paramBuf.append(" IMSI= ? and");
            paramValueList.add(deviceInfo.getIMSI());
        }
        if (deviceInfo.getMSISDN() != null) {
            paramBuf.append(" MSISDN= ? and");
            paramValueList.add(deviceInfo.getMSISDN());
        }
        if (deviceInfo.getAddressEncodingFormat() != null) {
            paramBuf.append(" AddressEncodingFormat= ? and");
            paramValueList.add(deviceInfo.getAddressEncodingFormat());
        }
        if (deviceInfo.getHighAddress() != null) {
            paramBuf.append(" HighAddress= ? and");
            paramValueList.add(deviceInfo.getHighAddress());
        }
        if (deviceInfo.getManageChannel() != null) {
            paramBuf.append(" ManageChannel= ? and");
            paramValueList.add(deviceInfo.getManageChannel());
        }
        if (deviceInfo.getDomainName() != null) {
            paramBuf.append(" DomainName= ? and");
            paramValueList.add(deviceInfo.getDomainName());
        }
        if (deviceInfo.getCustomizedFeatures() != null) {
            paramBuf.append(" CustomizedFeatures= ? and");
            paramValueList.add(deviceInfo.getCustomizedFeatures());
        }
        if (deviceInfo.getProgramName() != null) {
            paramBuf.append(" ProgramName= ? and");
            paramValueList.add(deviceInfo.getProgramName());
        }
        if (deviceInfo.getStatus() != null) {
            paramBuf.append(" Status= ? and");
            paramValueList.add(deviceInfo.getStatus());
        }
        if (deviceInfo.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceInfo.getRemark());
        }

        String sql = "select count(*) from DeviceInfo where " + paramBuf.substring(0, paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(), paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public DeviceInfo selectDeviceInfoByID(Integer ID) throws SQLException {
        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, IPLocation as IPLocation, ProjectID as ProjectID, DeliveryTime as DeliveryTime, InstallationTime as InstallationTime, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName, CustomizedFeatures as CustomizedFeatures, ProgramName as ProgramName, Status as Status, Remark as Remark from DeviceInfo where  ID = ?";
        return queryRunner.query(sql, new BeanHandler<>(DeviceInfo.class), ID);
    }

    @Override
    public List<DeviceInfo> selectAllDeviceInfo() throws SQLException {
        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, IPLocation as IPLocation, ProjectID as ProjectID, DeliveryTime as DeliveryTime, InstallationTime as InstallationTime, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName, CustomizedFeatures as CustomizedFeatures, ProgramName as ProgramName, Status as Status, Remark as Remark from DeviceInfo";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceInfo.class));
    }

    @Override
    public List<DeviceInfo> selectDeviceInfoByCondition(DeviceInfo deviceInfo) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceInfo.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceInfo.getID());
        }
        if (deviceInfo.getDeviceName() != null) {
            paramBuf.append(" DeviceName= ? and");
            paramValueList.add(deviceInfo.getDeviceName());
        }
        if (deviceInfo.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(deviceInfo.getUpdateTime());
        }
        if (deviceInfo.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(deviceInfo.getHardwareVersion());
        }
        if (deviceInfo.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(deviceInfo.getSystemProgramVersion());
        }
        if (deviceInfo.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(deviceInfo.getApplicationVersion());
        }
        if (deviceInfo.getProvincesCitiesCode() != null) {
            paramBuf.append(" ProvincesCitiesCode= ? and");
            paramValueList.add(deviceInfo.getProvincesCitiesCode());
        }
        if (deviceInfo.getDeviceAddress() != null) {
            paramBuf.append(" DeviceAddress= ? and");
            paramValueList.add(deviceInfo.getDeviceAddress());
        }
        if (deviceInfo.getBatteryVoltage() != null) {
            paramBuf.append(" BatteryVoltage= ? and");
            paramValueList.add(deviceInfo.getBatteryVoltage());
        }
        if (deviceInfo.getChargingVoltage() != null) {
            paramBuf.append(" ChargingVoltage= ? and");
            paramValueList.add(deviceInfo.getChargingVoltage());
        }
        if (deviceInfo.getTemperature() != null) {
            paramBuf.append(" Temperature= ? and");
            paramValueList.add(deviceInfo.getTemperature());
        }
        if (deviceInfo.getHumidity() != null) {
            paramBuf.append(" Humidity= ? and");
            paramValueList.add(deviceInfo.getHumidity());
        }
        if (deviceInfo.getSignal() != null) {
            paramBuf.append(" Signal= ? and");
            paramValueList.add(deviceInfo.getSignal());
        }
        if (deviceInfo.getIPLocation() != null) {
            paramBuf.append(" IPLocation= ? and");
            paramValueList.add(deviceInfo.getIPLocation());
        }
        if (deviceInfo.getProjectID() != null) {
            paramBuf.append(" ProjectID= ? and");
            paramValueList.add(deviceInfo.getProjectID());
        }
        if (deviceInfo.getDeliveryTime() != null) {
            paramBuf.append(" DeliveryTime= ? and");
            paramValueList.add(deviceInfo.getDeliveryTime());
        }
        if (deviceInfo.getInstallationTime() != null) {
            paramBuf.append(" InstallationTime= ? and");
            paramValueList.add(deviceInfo.getInstallationTime());
        }
        if (deviceInfo.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceInfo.getDeviceID());
        }
        if (deviceInfo.getCCID() != null) {
            paramBuf.append(" CCID= ? and");
            paramValueList.add(deviceInfo.getCCID());
        }
        if (deviceInfo.getIMEI() != null) {
            paramBuf.append(" IMEI= ? and");
            paramValueList.add(deviceInfo.getIMEI());
        }
        if (deviceInfo.getIMSI() != null) {
            paramBuf.append(" IMSI= ? and");
            paramValueList.add(deviceInfo.getIMSI());
        }
        if (deviceInfo.getMSISDN() != null) {
            paramBuf.append(" MSISDN= ? and");
            paramValueList.add(deviceInfo.getMSISDN());
        }
        if (deviceInfo.getAddressEncodingFormat() != null) {
            paramBuf.append(" AddressEncodingFormat= ? and");
            paramValueList.add(deviceInfo.getAddressEncodingFormat());
        }
        if (deviceInfo.getHighAddress() != null) {
            paramBuf.append(" HighAddress= ? and");
            paramValueList.add(deviceInfo.getHighAddress());
        }
        if (deviceInfo.getManageChannel() != null) {
            paramBuf.append(" ManageChannel= ? and");
            paramValueList.add(deviceInfo.getManageChannel());
        }
        if (deviceInfo.getDomainName() != null) {
            paramBuf.append(" DomainName= ? and");
            paramValueList.add(deviceInfo.getDomainName());
        }
        if (deviceInfo.getCustomizedFeatures() != null) {
            paramBuf.append(" CustomizedFeatures= ? and");
            paramValueList.add(deviceInfo.getCustomizedFeatures());
        }
        if (deviceInfo.getProgramName() != null) {
            paramBuf.append(" ProgramName= ? and");
            paramValueList.add(deviceInfo.getProgramName());
        }
        if (deviceInfo.getStatus() != null) {
            paramBuf.append(" Status= ? and");
            paramValueList.add(deviceInfo.getStatus());
        }
        if (deviceInfo.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceInfo.getRemark());
        }

        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, IPLocation as IPLocation, ProjectID as ProjectID, DeliveryTime as DeliveryTime, InstallationTime as InstallationTime, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName, CustomizedFeatures as CustomizedFeatures, ProgramName as ProgramName, Status as Status, Remark as Remark  from DeviceInfo where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.query(sql, new BeanListHandler<>(DeviceInfo.class), paramValueList.toArray());
    }

    @Override
    public List<DeviceInfo> selectDeviceInfoWithPagination(PageParam pageParam) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, IPLocation as IPLocation, ProjectID as ProjectID, DeliveryTime as DeliveryTime, InstallationTime as InstallationTime, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName, CustomizedFeatures as CustomizedFeatures, ProgramName as ProgramName, Status as Status, Remark as Remark from DeviceInfo limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(DeviceInfo.class), (page - 1) * rows, rows);
    }

    @Override
    public List<DeviceInfo> selectDeviceInfoWithPaginationByCondition(PageParam pageParam, DeviceInfo deviceInfo) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (deviceInfo.getID() != null) {
            paramBuf.append(" ID= ? and");
            paramValueList.add(deviceInfo.getID());
        }
        if (deviceInfo.getDeviceName() != null) {
            paramBuf.append(" DeviceName= ? and");
            paramValueList.add(deviceInfo.getDeviceName());
        }
        if (deviceInfo.getUpdateTime() != null) {
            paramBuf.append(" UpdateTime= ? and");
            paramValueList.add(deviceInfo.getUpdateTime());
        }
        if (deviceInfo.getHardwareVersion() != null) {
            paramBuf.append(" HardwareVersion= ? and");
            paramValueList.add(deviceInfo.getHardwareVersion());
        }
        if (deviceInfo.getSystemProgramVersion() != null) {
            paramBuf.append(" SystemProgramVersion= ? and");
            paramValueList.add(deviceInfo.getSystemProgramVersion());
        }
        if (deviceInfo.getApplicationVersion() != null) {
            paramBuf.append(" ApplicationVersion= ? and");
            paramValueList.add(deviceInfo.getApplicationVersion());
        }
        if (deviceInfo.getProvincesCitiesCode() != null) {
            paramBuf.append(" ProvincesCitiesCode= ? and");
            paramValueList.add(deviceInfo.getProvincesCitiesCode());
        }
        if (deviceInfo.getDeviceAddress() != null) {
            paramBuf.append(" DeviceAddress= ? and");
            paramValueList.add(deviceInfo.getDeviceAddress());
        }
        if (deviceInfo.getBatteryVoltage() != null) {
            paramBuf.append(" BatteryVoltage= ? and");
            paramValueList.add(deviceInfo.getBatteryVoltage());
        }
        if (deviceInfo.getChargingVoltage() != null) {
            paramBuf.append(" ChargingVoltage= ? and");
            paramValueList.add(deviceInfo.getChargingVoltage());
        }
        if (deviceInfo.getTemperature() != null) {
            paramBuf.append(" Temperature= ? and");
            paramValueList.add(deviceInfo.getTemperature());
        }
        if (deviceInfo.getHumidity() != null) {
            paramBuf.append(" Humidity= ? and");
            paramValueList.add(deviceInfo.getHumidity());
        }
        if (deviceInfo.getSignal() != null) {
            paramBuf.append(" Signal= ? and");
            paramValueList.add(deviceInfo.getSignal());
        }
        if (deviceInfo.getIPLocation() != null) {
            paramBuf.append(" IPLocation= ? and");
            paramValueList.add(deviceInfo.getIPLocation());
        }
        if (deviceInfo.getProjectID() != null) {
            paramBuf.append(" ProjectID= ? and");
            paramValueList.add(deviceInfo.getProjectID());
        }
        if (deviceInfo.getDeliveryTime() != null) {
            paramBuf.append(" DeliveryTime= ? and");
            paramValueList.add(deviceInfo.getDeliveryTime());
        }
        if (deviceInfo.getInstallationTime() != null) {
            paramBuf.append(" InstallationTime= ? and");
            paramValueList.add(deviceInfo.getInstallationTime());
        }
        if (deviceInfo.getDeviceID() != null) {
            paramBuf.append(" DeviceID= ? and");
            paramValueList.add(deviceInfo.getDeviceID());
        }
        if (deviceInfo.getCCID() != null) {
            paramBuf.append(" CCID= ? and");
            paramValueList.add(deviceInfo.getCCID());
        }
        if (deviceInfo.getIMEI() != null) {
            paramBuf.append(" IMEI= ? and");
            paramValueList.add(deviceInfo.getIMEI());
        }
        if (deviceInfo.getIMSI() != null) {
            paramBuf.append(" IMSI= ? and");
            paramValueList.add(deviceInfo.getIMSI());
        }
        if (deviceInfo.getMSISDN() != null) {
            paramBuf.append(" MSISDN= ? and");
            paramValueList.add(deviceInfo.getMSISDN());
        }
        if (deviceInfo.getAddressEncodingFormat() != null) {
            paramBuf.append(" AddressEncodingFormat= ? and");
            paramValueList.add(deviceInfo.getAddressEncodingFormat());
        }
        if (deviceInfo.getHighAddress() != null) {
            paramBuf.append(" HighAddress= ? and");
            paramValueList.add(deviceInfo.getHighAddress());
        }
        if (deviceInfo.getManageChannel() != null) {
            paramBuf.append(" ManageChannel= ? and");
            paramValueList.add(deviceInfo.getManageChannel());
        }
        if (deviceInfo.getDomainName() != null) {
            paramBuf.append(" DomainName= ? and");
            paramValueList.add(deviceInfo.getDomainName());
        }
        if (deviceInfo.getCustomizedFeatures() != null) {
            paramBuf.append(" CustomizedFeatures= ? and");
            paramValueList.add(deviceInfo.getCustomizedFeatures());
        }
        if (deviceInfo.getProgramName() != null) {
            paramBuf.append(" ProgramName= ? and");
            paramValueList.add(deviceInfo.getProgramName());
        }
        if (deviceInfo.getStatus() != null) {
            paramBuf.append(" Status= ? and");
            paramValueList.add(deviceInfo.getStatus());
        }
        if (deviceInfo.getRemark() != null) {
            paramBuf.append(" Remark= ? and");
            paramValueList.add(deviceInfo.getRemark());
        }

        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, IPLocation as IPLocation, ProjectID as ProjectID, DeliveryTime as DeliveryTime, InstallationTime as InstallationTime, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName, CustomizedFeatures as CustomizedFeatures, ProgramName as ProgramName, Status as Status, Remark as Remark  from DeviceInfo where " + paramBuf.substring(0, paramBuf.length() - 3) + " limit ?, ?";

        paramValueList.add((page - 1) * rows);
        paramValueList.add(rows);
        return queryRunner.query(sql, new BeanListHandler<>(DeviceInfo.class), paramValueList.toArray());
    }

    @Override
    public DeviceInfo selectDeviceInfoByDeviceID(String deviceID) throws SQLException {
        String sql = "select ID as ID, DeviceName as DeviceName, UpdateTime as UpdateTime, HardwareVersion as HardwareVersion, SystemProgramVersion as SystemProgramVersion, ApplicationVersion as ApplicationVersion, ProvincesCitiesCode as ProvincesCitiesCode, DeviceAddress as DeviceAddress, BatteryVoltage as BatteryVoltage, ChargingVoltage as ChargingVoltage, Temperature as Temperature, Humidity as Humidity, Signal as Signal, IPLocation as IPLocation, ProjectID as ProjectID, DeliveryTime as DeliveryTime, InstallationTime as InstallationTime, DeviceID as DeviceID, CCID as CCID, IMEI as IMEI, IMSI as IMSI, MSISDN as MSISDN, AddressEncodingFormat as AddressEncodingFormat, HighAddress as HighAddress, ManageChannel as ManageChannel, DomainName as DomainName, CustomizedFeatures as CustomizedFeatures, ProgramName as ProgramName, Status as Status, Remark as Remark from DeviceInfo where  DeviceID = ?";
        return queryRunner.query(sql, new BeanHandler<>(DeviceInfo.class), deviceID);
    }

}