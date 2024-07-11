/*
 * Created by JFormDesigner on Tue Dec 19 10:23:08 CST 2023
 */

package com.wxmf.ui;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wxmf.pojo.*;
import com.wxmf.service.*;
import com.wxmf.service.impl.*;
import com.wxmf.utils.*;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static com.wxmf.utils.WXMFProtocol.*;
import static java.lang.Integer.*;

/**
 * @author Brainrain
 */
public class MainFrame extends JFrame {

    Logger logger = LoggerFactory.getLogger(MainFrame.class);

    //private final static int PORT = 9527;
    private final static int PORT = 8063;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DeviceInfoService deviceInfoService = new DeviceInfoServiceImpl();

    HisDeviceInfoService hisDeviceInfoService = new HisDeviceInfoServiceImpl();

    DeviceOrderService deviceOrderService = new DeviceOrderServiceImpl();

    PackageVersionService packageVersionService = new PackageVersionServiceImpl();

    OrderDetailService orderDetailService = new OrderDetailServiceImpl();

    DeviceParamService deviceParamService = new DeviceParamServiceImpl();

    DeviceParamValueService deviceParamValueService = new DeviceParamValueServiceImpl();

    DeviceParameterService deviceParameterService = new DeviceParameterServiceImpl();

    ParamOptionService paramOptionService = new ParamOptionServiceImpl();

    TimedReportService timedReportService = new TimedReportServiceImpl();

    public MainFrame() {

        //初始化组件
        initComponents();

        bindEquipTable();
        //
        //bindOrderTable();

        //bindPackageVersionTable();

        //初始化指令
        startInitDeviceOrder();

        startListen();

    }

    private void bindPackageVersionTable() {

        try {
            List<PackageVersion> allPackageVersion = packageVersionService.getAllPackageVersion();
            DefaultTableModel model = (DefaultTableModel) this.packageTable.getModel();
            for (PackageVersion packageVersion : allPackageVersion) {
                Vector<Object> objects = new Vector<>();
                objects.add(packageVersion.getID());
                objects.add(packageVersion.getPackageName());
                objects.add(packageVersion.getHardwareVersion());
                objects.add(packageVersion.getSystemProgramVersion());
                objects.add(packageVersion.getApplicationVersion());
                model.addRow(objects);
            }

        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
    }

    /**
     * 初始化指令
     */
    private void startInitDeviceOrder() {
        //初始化指令
        Thread startInitDeviceOrderThread = new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(10000);
                    //获取未初始化的指令UNINITIALIZED
                    List<DeviceOrder> deviceOrders = deviceOrderService.getDeviceOrderByStatus(DeviceOrder.UNINITIALIZED);
                    if (deviceOrders != null) {
                        for (DeviceOrder deviceOrder : deviceOrders) {
                            try {
                                switch (deviceOrder.getOrderCode()) {
                                    case "65":
                                        //程序升级指令
                                        initUpgradeDeviceOrder(deviceOrder);
                                        break;
                                    case "71":
                                        initQueryAllParamOrder(deviceOrder);
                                        break;
                                    case "72":
                                        initSettingAllParamOrder(deviceOrder);
                                        break;
                                    case "73":
                                        initQueryFewParamOrder(deviceOrder);
                                        break;
                                    case "70":
                                        initQueryFewParamOrderWith70(deviceOrder);
                                        break;
                                    case "74":
                                        initSettingFewParamOrder(deviceOrder);
                                        break;
                                    case "F3":
                                        initSettingOnlineTimeOrder(deviceOrder);
                                        break;
                                    case "75":
                                        initSettingTimeOrder(deviceOrder);
                                        break;
                                    case "F6":
                                        initSetManageParamOrder(deviceOrder);
                                        break;
                                    case "78":
                                        //查询实时数据
                                        initQueryRealDataOrder(deviceOrder);
                                        break;
                                    case "79":
                                        //查询历史数据
                                        initQueryHistoryDataOrder(deviceOrder);
                                        break;
                                    case "90":
                                        //设置RTU断面、流量、库容等关系表
                                        initSetFlowCapacityRelationshipOrder(deviceOrder);
                                        break;
                                    case "91":
                                        //查询RTU断面、流量、库容等关系表
                                        initQueryFlowCapacityRelationshipOrder(deviceOrder);
                                        break;
                                }
                                //refreshOrderTable(deviceOrder);
                            } catch (Exception e) {
                                logger.error(deviceOrder.getDeviceID() + "指令" + deviceOrder.getID() + "初始化失败" + e.getMessage());
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error(ExceptionUtil.getStackTrace(e));
                }
            }
        });
        startInitDeviceOrderThread.setDaemon(true);
        startInitDeviceOrderThread.start();
    }

    private void initQueryFlowCapacityRelationshipOrder(DeviceOrder deviceOrder) {
        StringBuilder sb = new StringBuilder();
        sb.append("F1030000000000");
        //报文长度
        sb.append(StringUtils.leftPad(Integer.toHexString(12), 4, '0').toUpperCase());
        //功能码
        sb.append("91");

        //和校验
        sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
        //结束符
        sb.append("F2");

        try {
            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(1);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("91");
            orderDetail.setMsgContent(sb.toString());
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);
        } catch (SQLException ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加查询实时数据指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
    }

    private void initSetFlowCapacityRelationshipOrder(DeviceOrder deviceOrder) throws SQLException {
        DeviceInfo deviceInfo = deviceInfoService.getDeviceInfoByDeviceID(deviceOrder.getDeviceID());

        if (deviceInfo == null) {
            return;
        }

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {

            fileReader = new FileReader(deviceOrder.getParameter());
            bufferedReader = new BufferedReader(fileReader);
            String line;
            StringBuilder sbData = new StringBuilder();

            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.equals("")) {
                    String[] split = line.split("\t");
                    if (split.length == 3) {
                        count++;
                        sbData.append(StringUtils.leftPad(Integer.toHexString(Integer.parseInt(split[1])), 8, '0').toUpperCase());
                        sbData.append(StringUtils.leftPad(Integer.toHexString(Integer.parseInt(split[2])), 8, '0').toUpperCase());
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            //起始符 特征码  流水号 遥测站地址    7字节
            sb.append("F1030000000000");//
            //报文长度 2字节
            sb.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + 8 * count), 4, '0').toUpperCase());
            //功能码 1字节
            sb.append("90");

            //数据域
            sb.append(StringUtils.leftPad(Integer.toHexString(count), 2, '0').toUpperCase());
            sb.append(sbData);

            //和校验
            sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
            //结束符
            sb.append("F2");


            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("90");
            orderDetail.setMsgContent(sb.toString());
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(0);
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(1);
            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);

            bufferedReader.close();
            fileReader.close();

        } catch (IOException ex) {
            //throw new RuntimeException(ex);

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException exc) {
                    //throw new RuntimeException(exc);
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException exc) {
                    //throw new RuntimeException(exc);
                }
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
    }

    private void initQueryHistoryDataOrder(DeviceOrder deviceOrder) {
        String params = deviceOrder.getParameter();


        StringBuilder sb = new StringBuilder();
        sb.append("F1030000000000");
        //报文长度
        sb.append(StringUtils.leftPad(Integer.toHexString(12 + 10), 4, '0').toUpperCase());
        //功能码
        sb.append("78");

        //和校验
        sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
        //结束符
        sb.append("F2");

        try {
            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(1);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("78");
            orderDetail.setMsgContent(sb.toString());
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);
        } catch (SQLException ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加查询实时数据指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
    }

    private void initQueryRealDataOrder(DeviceOrder deviceOrder) {
        StringBuilder sb = new StringBuilder();
        sb.append("F1030000000000");
        //报文长度
        sb.append(StringUtils.leftPad(Integer.toHexString(12), 4, '0').toUpperCase());
        //功能码
        sb.append("78");

        //和校验
        sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
        //结束符
        sb.append("F2");

        try {
            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(1);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("78");
            orderDetail.setMsgContent(sb.toString());
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);
        } catch (SQLException ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加查询实时数据指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
    }

    private void initSetManageParamOrder(DeviceOrder deviceOrder) {
        String params = deviceOrder.getParameter();


        StringBuilder sb = new StringBuilder();
        sb.append("F1030000000000");
        //报文长度
        sb.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + 1 + 1 + params.length()), 4, '0').toUpperCase());
        //功能码
        sb.append("F6");

        //
        sb.append("01");

        sb.append("80");
        sb.append(StringUtils.leftPad(Integer.toHexString(params.length()), 2, '0').toUpperCase());

        byte[] bytes = params.getBytes();

        sb.append(CommonUil.byteArrayToHexString(bytes));

        //和校验
        sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
        //结束符
        sb.append("F2");

        try {
            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(1);
            //0x74 设置单个参数（用于少量参数）
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("F6");
            orderDetail.setMsgContent(sb.toString());
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);
        } catch (SQLException ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加设置运维平台参数指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
    }

    private void initSettingTimeOrder(DeviceOrder deviceOrder) {

        try {
            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(1);
            //0x74 设置单个参数（用于少量参数）
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("75");
            orderDetail.setMsgContent("");
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);
        } catch (SQLException ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加设置单个参数指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
    }

    private void initSettingOnlineTimeOrder(DeviceOrder deviceOrder) {

        String params = deviceOrder.getParameter();


        StringBuilder sb = new StringBuilder();
        sb.append("F1030000000000");
        //报文长度
        sb.append(StringUtils.leftPad(Integer.toHexString(12 + 2), 4, '0').toUpperCase());
        //功能码
        sb.append("F3");

        sb.append(StringUtils.leftPad(Integer.toHexString(Integer.parseInt(params)), 4, '0').toUpperCase());

        //和校验
        sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
        //结束符
        sb.append("F2");

        try {
            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(1);
            //0x74 设置单个参数（用于少量参数）
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("F3");
            orderDetail.setMsgContent(sb.toString());
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);
        } catch (SQLException ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加设置单个参数指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
    }

    private void initQueryFewParamOrderWith70(DeviceOrder deviceOrder) throws SQLException {
        String[] params = deviceOrder.getParameter().split(";");

        List<DeviceParameter> allDeviceParameter = deviceParameterService.getAllDeviceParameter();

        int paramsLength = 0;

        StringBuilder sbParam = new StringBuilder();
        for (String param : params) {

            int paramId = Integer.parseInt(param.substring(0, param.indexOf('=')));

            List<DeviceParameter> list = allDeviceParameter.stream().filter(o -> o.getID().equals(paramId)).collect(Collectors.toList());
            if (list.size() > 0) {
                //参数数量加1
                paramsLength++;

                //2字节参数地址
                sbParam.append(StringUtils.leftPad(Integer.toHexString(list.get(0).getRegisterAddress()), 4, '0').toUpperCase());
                //4个字节有效参数位

                StringBuilder sbParamBit = new StringBuilder();
                for (int i = 31; i >= 0; i--) {
                    //
                    if (i >= list.get(0).getStartBit() && i <= list.get(0).getEndBit()) {
                        sbParamBit.append("1");
                    } else {
                        sbParamBit.append("0");
                    }
                }
                long decimalNum = Long.parseLong(sbParamBit.toString(), 2); // 先将二进制字符串转换成十进制数值
                String hexStr = StringUtils.leftPad(Long.toHexString(decimalNum), 8, '0').toUpperCase();
                sbParam.append(hexStr);

                //通道1IP地址，通道2IP地址,通道3IP地址,通道5IP地址
                if (list.get(0).getRegisterAddress() == 298 || list.get(0).getRegisterAddress() == 329 || list.get(0).getRegisterAddress() == 332 || list.get(0).getRegisterAddress() == 335) {
                    paramsLength++;

                    //2字节参数地址
                    sbParam.append(StringUtils.leftPad(Integer.toHexString(list.get(0).getRegisterAddress() + 1), 4, '0').toUpperCase());
                    //4个字节有效参数位
                    sbParam.append(hexStr);
                } else if (list.get(0).getRegisterAddress() == 350 || list.get(0).getRegisterAddress() == 351 || list.get(0).getRegisterAddress() == 352 || list.get(0).getRegisterAddress() == 353) {
                    paramsLength++;

                    //2字节参数地址
                    sbParam.append(StringUtils.leftPad(Integer.toHexString(list.get(0).getRegisterAddress() + 4), 4, '0').toUpperCase());
                    //4个字节有效参数位
                    sbParam.append(hexStr);
                } else if (list.get(0).getRegisterAddress() == 364) { //本机号码
                    paramsLength++;

                    //2字节参数地址
                    sbParam.append(StringUtils.leftPad(Integer.toHexString(list.get(0).getRegisterAddress() + 1), 4, '0').toUpperCase());
                    //4个字节有效参数位
                    sbParam.append(hexStr);
                } else if (list.get(0).getRegisterAddress() == 360) { //APN接入点
                    paramsLength++;
                    //2字节参数地址
                    sbParam.append(StringUtils.leftPad(Integer.toHexString(list.get(0).getRegisterAddress() + 1), 4, '0').toUpperCase());
                    //4个字节有效参数位
                    sbParam.append(hexStr);

                    paramsLength++;
                    //2字节参数地址
                    sbParam.append(StringUtils.leftPad(Integer.toHexString(list.get(0).getRegisterAddress() + 2), 4, '0').toUpperCase());
                    //4个字节有效参数位
                    sbParam.append(hexStr);

                    paramsLength++;
                    //2字节参数地址
                    sbParam.append(StringUtils.leftPad(Integer.toHexString(list.get(0).getRegisterAddress() + 3), 4, '0').toUpperCase());
                    //4个字节有效参数位
                    sbParam.append(hexStr);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("F1030000000000");
        //报文长度
        sb.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + paramsLength * 6), 4, '0').toUpperCase());
        //功能码
        sb.append("70");
        //参数个数


        sb.append(StringUtils.leftPad(Integer.toHexString(paramsLength), 2, '0').toUpperCase());

        sb.append(sbParam);

        //和校验
        sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
        //结束符
        sb.append("F2");

        try {
            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(1);
            //0x74 设置单个参数（用于少量参数）
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("70");
            orderDetail.setMsgContent(sb.toString());
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);
        } catch (SQLException ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加设置单个参数指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
    }

    private void initQueryFewParamOrder(DeviceOrder deviceOrder) throws SQLException {
        String[] params = deviceOrder.getParameter().split(";");

        List<DeviceParameter> allDeviceParameter = deviceParameterService.getAllDeviceParameter();

        StringBuilder sb = new StringBuilder();
        sb.append("F1030000000000");
        //报文长度
        sb.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + params.length * 2), 4, '0').toUpperCase());
        //功能码
        sb.append("73");
        //参数个数
        sb.append(StringUtils.leftPad(Integer.toHexString(params.length), 2, '0').toUpperCase());
        for (String param : params) {

            int paramId = Integer.parseInt(param.substring(0, param.indexOf('=')));

            List<DeviceParameter> list = allDeviceParameter.stream().filter(o -> o.getID().equals(paramId)).collect(Collectors.toList());
            if (list.size() > 0) {
                //2字节参数地址
                sb.append(StringUtils.leftPad(Integer.toHexString(list.get(0).getRegisterAddress()), 4, '0').toUpperCase());
            }
        }

        //和校验
        sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
        //结束符
        sb.append("F2");

        try {
            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(1);
            //0x74 设置单个参数（用于少量参数）
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("73");
            orderDetail.setMsgContent(sb.toString());
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);
        } catch (SQLException ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加设置单个参数指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
    }

    private void initSettingAllParamOrder(DeviceOrder deviceOrder) throws SQLException {
        DeviceInfo deviceInfo = deviceInfoService.getDeviceInfoByDeviceID(deviceOrder.getDeviceID());

        if (deviceInfo == null) {
            return;
        }
        int curPackageNumber = 1;
        int totalPackageNumber = 2;
        if (deviceInfo.getHardwareVersion().endsWith("2")) {
            totalPackageNumber = 4;
        }

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {

            fileReader = new FileReader(deviceOrder.getParameter());
            bufferedReader = new BufferedReader(fileReader);
            String line;
            int count = 0;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                sb.append(StringUtils.leftPad(toHexString(Integer.parseInt(split[1].trim())), 8, '0').toUpperCase());
                count++;
                if (count == 218) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("F1030000000000");

                    //报文长度
                    stringBuilder.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + 2 + count * 4), 4, '0').toUpperCase());

                    stringBuilder.append("72");

                    stringBuilder.append(StringUtils.leftPad(Integer.toHexString(count), 2, '0').toUpperCase());

                    stringBuilder.append(StringUtils.leftPad(Integer.toHexString((curPackageNumber - 1) * 218), 4, '0').toUpperCase());

                    stringBuilder.append(sb);
                    //和校验
                    stringBuilder.append(WXMFProtocolUtil.getSumCheckValue(stringBuilder.toString()));
                    //结束符
                    stringBuilder.append("F2");

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderID(deviceOrder.getID());
                    orderDetail.setCurPackageNumber(curPackageNumber);
                    orderDetail.setTotalPackageNumber(totalPackageNumber);
                    orderDetail.setMsgType("72");
                    orderDetail.setMsgContent(stringBuilder.toString());
                    orderDetail.setMsgState(0);
                    orderDetail.setExecuteCount(0);
                    orderDetail.setSort(curPackageNumber);
                    orderDetailService.addOrderDetail(orderDetail);

                    count = 0;
                    sb = new StringBuilder();
                    curPackageNumber++;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("F1030000000000");

            //报文长度
            stringBuilder.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + 2 + count * 4), 4, '0').toUpperCase());

            stringBuilder.append("72");

            stringBuilder.append(StringUtils.leftPad(Integer.toHexString(count), 2, '0').toUpperCase());

            stringBuilder.append(StringUtils.leftPad(Integer.toHexString((curPackageNumber - 1) * 218), 4, '0').toUpperCase());

            stringBuilder.append(sb);
            //和校验
            stringBuilder.append(WXMFProtocolUtil.getSumCheckValue(stringBuilder.toString()));
            //结束符
            stringBuilder.append("F2");

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(curPackageNumber);
            orderDetail.setTotalPackageNumber(totalPackageNumber);
            orderDetail.setMsgType("72");
            orderDetail.setMsgContent(stringBuilder.toString());
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(curPackageNumber);
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(totalPackageNumber);
            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);

            bufferedReader.close();
            fileReader.close();

        } catch (IOException ex) {
            //throw new RuntimeException(ex);

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException exc) {
                    //throw new RuntimeException(exc);
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException exc) {
                    //throw new RuntimeException(exc);
                }
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
    }

    private void initQueryAllParamOrder(DeviceOrder deviceOrder) throws SQLException {

        DeviceInfo deviceInfo = deviceInfoService.getDeviceInfoByDeviceID(deviceOrder.getDeviceID());

        if (deviceInfo == null) {
            return;
        }

        //deviceOrder
        if (deviceInfo.getHardwareVersion().endsWith("1")) {
            //一包不超过218个参数，16通道分2次读写
            deviceOrder.setTotalMsgCount(2);
            deviceOrder.setCurMsgIndex(0);
            //16通道376个参数？
            for (int i = 0; i < 2; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("F1030000000000");
                //报文长度
                if (i == 0) {
                    sb.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + 2), 4, '0').toUpperCase());
                    //功能码
                    sb.append("71");
                    //1字节参数个数
                    sb.append(StringUtils.leftPad(Integer.toHexString(218), 2, '0').toUpperCase());
                    //2字节起始参数地址
                    sb.append(StringUtils.leftPad(Integer.toHexString(0), 4, '0').toUpperCase());
                } else {
                    sb.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + 2), 4, '0').toUpperCase());
                    //功能码
                    sb.append("71");
                    //1字节参数个数
                    sb.append(StringUtils.leftPad(Integer.toHexString(158), 2, '0').toUpperCase());
                    //2字节起始参数地址
                    sb.append(StringUtils.leftPad(Integer.toHexString(218), 4, '0').toUpperCase());
                }
                //和校验
                sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
                //结束符
                sb.append("F2");

                //0x74 设置单个参数（用于少量参数）
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderID(deviceOrder.getID());
                orderDetail.setCurPackageNumber(i + 1);
                orderDetail.setTotalPackageNumber(deviceOrder.getTotalMsgCount());
                orderDetail.setMsgType("71");
                orderDetail.setMsgContent(sb.toString());
                orderDetail.setMsgState(0);
                orderDetail.setExecuteCount(0);
                orderDetail.setSort(deviceOrder.getTotalMsgCount());
                orderDetailService.addOrderDetail(orderDetail);

            }
        } else if (deviceInfo.getHardwareVersion().endsWith("2")) {

            //一包不超过218个参数，32通道分4次读写
            deviceOrder.setTotalMsgCount(4);
            deviceOrder.setCurMsgIndex(0);
            //32通道824个参数？
            for (int i = 0; i < 4; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("F1030000000000");
                //报文长度
                if (i != 3) {
                    sb.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + 2), 4, '0').toUpperCase());
                    //功能码
                    sb.append("71");
                    //1字节参数个数
                    sb.append(StringUtils.leftPad(Integer.toHexString(218), 2, '0').toUpperCase());
                    //2字节起始参数地址
                    sb.append(StringUtils.leftPad(Integer.toHexString(i * 218), 4, '0').toUpperCase());
                } else {
                    sb.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + 2), 4, '0').toUpperCase());
                    //功能码
                    sb.append("71");
                    //1字节参数个数
                    sb.append(StringUtils.leftPad(Integer.toHexString(170), 2, '0').toUpperCase());
                    //2字节起始参数地址
                    sb.append(StringUtils.leftPad(Integer.toHexString(i * 218), 4, '0').toUpperCase());
                }
                //和校验
                sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
                //结束符
                sb.append("F2");

                //0x74 设置单个参数（用于少量参数）
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderID(deviceOrder.getID());
                orderDetail.setCurPackageNumber(i + 1);
                orderDetail.setTotalPackageNumber(deviceOrder.getTotalMsgCount());
                orderDetail.setMsgType("71");
                orderDetail.setMsgContent(sb.toString());
                orderDetail.setMsgState(0);
                orderDetail.setExecuteCount(0);
                orderDetail.setSort(i + 1);
                orderDetailService.addOrderDetail(orderDetail);

            }
        }

        deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
        deviceOrder.setRemark("初始化成功");
        deviceOrderService.updateDeviceOrder(deviceOrder);
    }

    private void initSettingFewParamOrder(DeviceOrder deviceOrder) throws SQLException {

        List<DeviceParameter> allDeviceParameter = deviceParameterService.getAllDeviceParameter();

        String[] params = deviceOrder.getParameter().split(";");

        int paramLength = 0;

        StringBuilder sbParam = new StringBuilder();
        for (String param : params) {
            String[] split = param.split("=");
            List<DeviceParameter> parameterList = allDeviceParameter.stream().filter(o -> o.getID() == Integer.parseInt(split[0])).collect(Collectors.toList());
            if (parameterList.size() > 0) {

                //2字节参数地址
                sbParam.append(StringUtils.leftPad(Integer.toHexString(parameterList.get(0).getRegisterAddress()), 4, '0').toUpperCase());

                StringBuilder sbParamBit = new StringBuilder();
                for (int i = 31; i >= 0; i--) {
                    //
                    if (i >= parameterList.get(0).getStartBit() && i <= parameterList.get(0).getEndBit()) {
                        sbParamBit.append("1");
                    } else {
                        sbParamBit.append("0");
                    }
                }
                long decimalNum = Long.parseLong(sbParamBit.toString(), 2); // 先将二进制字符串转换成十进制数值
                String hexStr = StringUtils.leftPad(Long.toHexString(decimalNum), 8, '0').toUpperCase();
                //4字节有效参数位
                sbParam.append(hexStr);

                //IP地址
                if (isIP(parameterList.get(0).getRegisterAddress())) {
                    String[] ipArr = split[2].split("\\.");
                    if (ipArr.length == 4) {
                        //4字节参数值
                        String strBin = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(ipArr[0])), 8, '0');
                        strBin += StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(ipArr[1])), 8, '0');

                        strBin = StringUtils.rightPad(strBin, strBin.length() + parameterList.get(0).getStartBit(), '0');
                        strBin = StringUtils.leftPad(strBin, 32, '0');
                        sbParam.append(StringUtils.leftPad(Long.toHexString(Long.parseLong(strBin, 2)), 8, '0').toUpperCase());
                        paramLength++;


                        //2字节参数地址
                        sbParam.append(StringUtils.leftPad(Integer.toHexString(parameterList.get(0).getRegisterAddress() + 1), 4, '0').toUpperCase());
                        //4字节有效参数位
                        sbParam.append(hexStr);
                        //4字节参数值
                        strBin = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(ipArr[2])), 8, '0');
                        strBin += StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(ipArr[3])), 8, '0');
                        strBin = StringUtils.rightPad(strBin, strBin.length() + parameterList.get(0).getStartBit(), '0');
                        strBin = StringUtils.leftPad(strBin, 32, '0');
                        sbParam.append(StringUtils.leftPad(Long.toHexString(Long.parseLong(strBin, 2)), 8, '0').toUpperCase());
                        paramLength++;

                    }
                } else if (isPhone(parameterList.get(0).getRegisterAddress())) {
                    //4字节参数值
                    String high = split[2].substring(0, split[2].length() - 8);
                    String low = split[2].substring(split[2].length() - 8);

                    String strBin = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(high)), parameterList.get(0).getEndBit() - parameterList.get(0).getStartBit() + 1, '0');
                    strBin = StringUtils.rightPad(strBin, strBin.length() + parameterList.get(0).getStartBit(), '0');
                    strBin = StringUtils.leftPad(strBin, 32, '0');
                    sbParam.append(StringUtils.leftPad(Long.toHexString(Long.parseLong(strBin, 2)), 8, '0').toUpperCase());
                    paramLength++;

                    //2字节参数地址
                    if (parameterList.get(0).getRegisterAddress() == 364) {
                        sbParam.append(StringUtils.leftPad(Integer.toHexString(parameterList.get(0).getRegisterAddress() + 1), 4, '0').toUpperCase());
                    } else {
                        sbParam.append(StringUtils.leftPad(Integer.toHexString(parameterList.get(0).getRegisterAddress() + 4), 4, '0').toUpperCase());
                    }

                    //4字节有效参数位
                    sbParam.append(hexStr);

                    strBin = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(low)), parameterList.get(0).getEndBit() - parameterList.get(0).getStartBit() + 1, '0');
                    strBin = StringUtils.rightPad(strBin, strBin.length() + parameterList.get(0).getStartBit(), '0');
                    strBin = StringUtils.leftPad(strBin, 32, '0');
                    sbParam.append(StringUtils.leftPad(Long.toHexString(Long.parseLong(strBin, 2)), 8, '0').toUpperCase());
                    paramLength++;

                } else if (parameterList.get(0).getParamType() == 2) {//
                    //设置APN接入点
                    StringBuilder sb = new StringBuilder();
                    int paramValue = Integer.parseInt(split[2]);
                    if (paramValue > 0) {
                        //
                        //24 到 31位是符号位，0=正，非0=负，正负数据类--填写数据
                        for (int i = 0; i < 31 - parameterList.get(0).getEndBit(); i++) {
                            sb.append("0");
                        }
                    } else {
                        for (int i = 0; i < 31 - parameterList.get(0).getEndBit(); i++) {
                            sb.append("1");
                        }
                    }
                    paramValue = Math.abs(paramValue);
                    String strBin = StringUtils.leftPad(Integer.toBinaryString(paramValue), parameterList.get(0).getEndBit() - parameterList.get(0).getStartBit() + 1, '0');

                    strBin = sb + strBin;
                    sbParam.append(StringUtils.leftPad(Long.toHexString(Long.parseLong(strBin, 2)), 8, '0').toUpperCase());
                    paramLength++;
                } else {

                    //4字节参数值
                    String strBin = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(split[2])), parameterList.get(0).getEndBit() - parameterList.get(0).getStartBit() + 1, '0');
                    strBin = StringUtils.rightPad(strBin, strBin.length() + parameterList.get(0).getStartBit(), '0');
                    strBin = StringUtils.leftPad(strBin, 32, '0');
                    sbParam.append(StringUtils.leftPad(Long.toHexString(Long.parseLong(strBin, 2)), 8, '0').toUpperCase());
                    paramLength++;
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        sb.append("F1030000000000");
        //报文长度
        sb.append(StringUtils.leftPad(Integer.toHexString(12 + 1 + paramLength * 10), 4, '0').toUpperCase());
        //功能码
        sb.append("74");

        //参数个数
        sb.append(StringUtils.leftPad(Integer.toHexString(paramLength), 2, '0').toUpperCase());

        sb.append(sbParam);
        //和校验
        sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
        //结束符
        sb.append("F2");

        try {
            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setTotalMsgCount(1);
            //0x74 设置单个参数（用于少量参数）
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("74");
            orderDetail.setMsgContent(sb.toString());
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            orderDetailService.addOrderDetail(orderDetail);

            deviceOrder.setOrderState(DeviceOrder.UNEXECUTED);
            deviceOrder.setRemark("初始化成功");
            deviceOrderService.updateDeviceOrder(deviceOrder);
        } catch (SQLException ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加设置单个参数指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
    }

    private boolean isPhone(Integer registerAddress) {
        switch (registerAddress) {
            case 364:
            case 350:
            case 351:
            case 352:
            case 353:
                return true;
            default:
                return false;
        }
    }

    private boolean isIP(Integer registerAddress) {
        switch (registerAddress) {
            case 298:
            case 329:
            case 332:
            case 335:
                return true;
            default:
                return false;
        }
    }

    private void startListen() {

        Thread serverThread = new Thread(() -> {

            // 监听指定的端口
            ServerSocket serverSocket = null;
            try {
                //开始监听
                serverSocket = new ServerSocket(PORT);
                logger.info("在端口" + PORT + "启动监听成功！");
                while (true) {
                    //等待设备连接
                    Socket socket = serverSocket.accept();
                    //设备连接服务器成功
                    logger.info("客户端" + socket.getRemoteSocketAddress() + "连接成功");

                    //开启新线程处理设备数据的接收发送
                    handleClient(socket);
                }
            } catch (Exception e) {

                //
                logger.error("在端口" + PORT + "启动监听失败，" + ExceptionUtil.getStackTrace(e));
                //关闭ServerSocket
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException ex) {
                        //throw new RuntimeException(ex);
                    }
                }
            }
        });


        serverThread.setDaemon(true);
        serverThread.start();
    }

    private void handleClient(Socket socket) {
        Thread thread = new Thread(() -> {
            // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
            InputStream inputStream = null;
            try {
                //主动发送数据给设备
                //sendMessage("F1030000000000000C6161F2", socket);
                //获取输入流
                inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                int len;
                socket.setSoTimeout(120000);
                while ((len = inputStream.read(bytes)) != -1) {
                    try {

                        byte[] dataBytes = Arrays.copyOf(bytes, len);

                        handleMsg(dataBytes, socket);

                    } catch (Exception ex) {
                        logger.error("handleMsg发生错误，" + ExceptionUtil.getStackTrace(ex));
                    }
                }

                inputStream.close();
                socket.close();
                logger.info("客户端" + socket.getRemoteSocketAddress() + "下线");
            } catch (Exception e) {
                logger.error("读取异常" + e.getMessage());
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    socket.close();
                } catch (Exception ex) {
                    //logToTextArea(ex.getMessage());
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void sendMessage(String msg, Socket socket) {

        try {
            byte[] sendBytes = CommonUil.hexToByteArray(msg);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(sendBytes);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void handleMsg(byte[] dataBytes, Socket socket) throws Exception {

        String msg = CommonUil.byteArrayToHexString(dataBytes);
        logger.info(msg);

        //F31401000032E00050880101240229123009203538364242500C0016001229660042800020028000883200000002000032E00076000000FA321AFFFF240229123005010281100000000000000000FEF4
        //解析协议
        WXMFProtocol wxmfProtocol = WXMFProtocolUtil.createWXMFProtocol(dataBytes);

        switch (wxmfProtocol.getFunctionCode()) {

            case TIMED_REPORT_FUNCTION_CODE://定时报
                parsingMessage(wxmfProtocol.getProtocolData().getDeviceID(), wxmfProtocol.getProtocolData().getBytes());
            case TEST_REPORT_FUNCTION_CODE://测试报
            case ADD_REPORT_FUNCTION_CODE://加报
            case HEARTBEAT_FUNCTION_CODE://心跳
                //应答设备
                answerDevice(wxmfProtocol, socket);
                //更新设备信息
                updateDeviceInfo(wxmfProtocol);
                executeDeviceOrder(socket, wxmfProtocol.getProtocolData().getDeviceID());
                break;
        }

    }

    private void answerDevice(WXMFProtocol wxmfProtocol, Socket socket) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("F1");
        stringBuilder.append("13");
        stringBuilder.append(StringUtils.leftPad(Integer.toHexString(wxmfProtocol.getSerialNumber()), 2, '0').toUpperCase());
        stringBuilder.append(wxmfProtocol.getTelemetryStationAddress());
        switch (wxmfProtocol.getFunctionCode()) {

            case TEST_REPORT_FUNCTION_CODE://心跳
            case TIMED_REPORT_FUNCTION_CODE://定时报


                stringBuilder.append("000C");

                stringBuilder.append(wxmfProtocol.getFunctionCode());

                stringBuilder.append(WXMFProtocolUtil.getSumCheckValue(stringBuilder.toString()));
                stringBuilder.append("F2");

                try {
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(CommonUil.hexToByteArray(stringBuilder.toString()));
                    logger.error("应答" + stringBuilder);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                break;
            case HEARTBEAT_FUNCTION_CODE://心跳

                stringBuilder.append("000E");
                stringBuilder.append(wxmfProtocol.getFunctionCode());
                stringBuilder.append("0000");
                stringBuilder.append(WXMFProtocolUtil.getSumCheckValue(stringBuilder.toString()));
                stringBuilder.append("F2");

                try {
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(CommonUil.hexToByteArray(stringBuilder.toString()));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                break;
        }
    }

    private void parsingMessage(String deviceID, byte[] bytes) {

        try {
            //时间
            String dateStr = CommonUil.byteArrayToHexString(bytes, 0, 5);
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
            Date date = sdf.parse(dateStr);

            //存储间隔
            int interval = bytes[5] & 0xFF;
            int groupNumber = bytes[6] & 0xFF;
            int channelNumber = bytes[7] & 0xFF;

            //查询通道代码
            ParamOption paramOption = new ParamOption();
            paramOption.setParamTypeID(17);
            List<ParamOption> paramOptionList = paramOptionService.getParamOption(paramOption);

            StringBuilder stringBuilder = new StringBuilder();
            int beginIndex = 8;
            for (int i = 0; i < channelNumber; i++) {

                stringBuilder.append("通道").append(i + 1).append(":");

                String channelTypeHex = CommonUil.byteToHexString(bytes[beginIndex + i]);
                String channelType = (bytes[beginIndex + i] & 0xFF) + "";


                List<ParamOption> paramOptions = paramOptionList.stream().filter(o -> Objects.equals(o.getOptionValue(), channelType)).collect(Collectors.toList());
                if (paramOptions.size() > 0) {
                    stringBuilder.append(paramOptions.get(0).getOptionName());
                } else {
                    if (channelTypeHex.startsWith("1")) {
                        stringBuilder.append("水位");
                    } else if (channelTypeHex.startsWith("2")) {
                        stringBuilder.append("闸位");
                    } else if (channelTypeHex.startsWith("3")) {
                        stringBuilder.append("流量");
                    } else if (channelTypeHex.startsWith("4")) {
                        stringBuilder.append("电力/电压");
                    } else if (channelTypeHex.startsWith("5")) {
                        stringBuilder.append("墒情");
                    } else if (channelTypeHex.startsWith("6")) {
                        stringBuilder.append("流速");
                    } else {
                        stringBuilder.append("未知");
                    }
                }
                stringBuilder.append("(").append(channelTypeHex).append(")");

                //数值
                long value = Long.parseLong(CommonUil.byteArrayToHexString(bytes, beginIndex + channelNumber + i * 4, beginIndex + channelNumber + i * 4 + 4), 16);

                stringBuilder.append("=").append(value).append(";");
            }
            TimedReport timedReport = new TimedReport();
            timedReport.setDeviceID(deviceID);
            timedReport.setReportTime(date);

            //  List<TimedReport> timedReportList = timedReportService.getTimedReport(timedReport);
            //  if (timedReportList.size() == 0) {
            //
            //      timedReport.setReportData(stringBuilder.toString());
            //      timedReportService.addTimedReport(timedReport);
            //  }

            timedReport.setReportData(stringBuilder.toString());
            timedReportService.addTimedReport(timedReport);

        } catch (Exception e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }

    }

    private void updateDeviceInfo(WXMFProtocol wxmfProtocol) {
        try {
            DeviceInfo deviceInfo = deviceInfoService.getDeviceInfoByDeviceID(wxmfProtocol.getProtocolData().getDeviceID());
            WXMFProtocolData protocolData = wxmfProtocol.getProtocolData();
            if (deviceInfo == null) {
                deviceInfo = new DeviceInfo();
                deviceInfo.setDeviceName("未知设备");
                deviceInfo.setDeviceID(protocolData.getDeviceID());
                deviceInfo.setProjectID(-1);
                deviceInfo.setStatus("-1");
            }
            deviceInfo.setUpdateTime(new Date());
            deviceInfo.setHardwareVersion(protocolData.getHardwareVersion());
            deviceInfo.setSystemProgramVersion(protocolData.getSystemProgramVersion());
            deviceInfo.setApplicationVersion(protocolData.getApplicationVersion());
            deviceInfo.setProvincesCitiesCode(protocolData.getProvincesCitiesCode());
            deviceInfo.setDeviceAddress(protocolData.getDeviceAddress());
            deviceInfo.setBatteryVoltage(protocolData.getBatteryVoltage());
            deviceInfo.setChargingVoltage(protocolData.getChargingVoltage());
            deviceInfo.setTemperature(protocolData.getTemperature());
            deviceInfo.setHumidity(protocolData.getHumidity());
            deviceInfo.setSignal(protocolData.getSignal());

            if (HEARTBEAT_FUNCTION_CODE.equals(wxmfProtocol.getFunctionCode())) {
                byte[] bytes = protocolData.getBytes();

                int beginIndex = 1;
                while (beginIndex < bytes.length) {

                    String dataType = CommonUil.byteArrayToHexString(bytes, beginIndex, beginIndex + 1);
                    int dataLength = Integer.parseInt(CommonUil.byteArrayToHexString(bytes, beginIndex + 1, beginIndex + 2), 16);

                    String dataValue = "";
                    if (dataType.equals("80")) {
                        dataValue = new String(bytes, beginIndex + 2, dataLength);
                    } else {
                        dataValue = CommonUil.byteArrayToHexString(bytes, beginIndex + 2, beginIndex + 2 + dataLength);
                    }


                    switch (dataType) {
                        case "01":
                            deviceInfo.setIMEI(dataValue);
                            break;
                        case "02":
                            deviceInfo.setCCID(new String(CommonUil.hexToByteArray(dataValue)));
                            break;
                        case "03":
                            deviceInfo.setIMSI(dataValue);
                            break;
                        case "04":
                            deviceInfo.setMSISDN(dataValue);
                            break;
                        case "05":
                            deviceInfo.setAddressEncodingFormat(Integer.parseInt(dataValue, 16) + "");
                            break;
                        case "06":
                            deviceInfo.setHighAddress(dataValue);
                            break;
                        case "07":
                            deviceInfo.setManageChannel(dataValue);
                            break;
                        case "08":
                            deviceInfo.setICCID2(new String(CommonUil.hexToByteArray(dataValue)));
                            break;
                        case "09":
                            deviceInfo.setIMSI2(dataValue);
                            break;
                        case "80":
                            deviceInfo.setDomainName(dataValue);
                            break;
                    }
                    beginIndex = beginIndex + 2 + dataLength;
                }
                HisDeviceInfo hisDeviceInfo = new HisDeviceInfo();

                BeanUtils.copyProperties(hisDeviceInfo, deviceInfo);
                hisDeviceInfoService.addHisDeviceInfo(hisDeviceInfo);

            }


            if (deviceInfo.getID() == null) {
                //未知设备
                deviceInfoService.addDeviceInfo(deviceInfo);
            } else {
                deviceInfoService.updateDeviceInfo(deviceInfo);
            }
            reFreshDeviceTable(deviceInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    synchronized private void reFreshDeviceTable(DeviceInfo deviceInfo) {

        if (deviceInfo.getID() == null) {
            bindEquipTable();
        } else {
            DefaultTableModel deviceInfoTableModel = (DefaultTableModel) this.equipTable.getModel();
            int rowCount = deviceInfoTableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String deviceID = (String) deviceInfoTableModel.getValueAt(i, 1);
                if (deviceID.equals(deviceInfo.getDeviceID())) {
                    deviceInfoTableModel.setValueAt(deviceInfo.getDeviceName(), i, 2);
                    deviceInfoTableModel.setValueAt(simpleDateFormat.format(deviceInfo.getUpdateTime()), i, 3);
                    deviceInfoTableModel.setValueAt(deviceInfo.getHardwareVersion(), i, 4);
                    deviceInfoTableModel.setValueAt(deviceInfo.getSystemProgramVersion(), i, 5);
                    deviceInfoTableModel.setValueAt(deviceInfo.getApplicationVersion(), i, 6);
                    deviceInfoTableModel.setValueAt(deviceInfo.getProvincesCitiesCode(), i, 7);
                    deviceInfoTableModel.setValueAt(deviceInfo.getDeviceAddress(), i, 8);
                    deviceInfoTableModel.setValueAt(deviceInfo.getBatteryVoltage(), i, 9);
                    deviceInfoTableModel.setValueAt(deviceInfo.getChargingVoltage(), i, 10);
                    deviceInfoTableModel.setValueAt(deviceInfo.getTemperature(), i, 11);
                    deviceInfoTableModel.setValueAt(deviceInfo.getHumidity(), i, 12);
                    deviceInfoTableModel.setValueAt(deviceInfo.getSignal(), i, 13);
                    deviceInfoTableModel.setValueAt("", i, 14);
                    break;
                }
            }
        }
    }

    private void executeDeviceOrder(Socket socket, String deviceID) {
        try {
            //根据设备唯一ID获取未执行的指令
            List<DeviceOrder> allDeviceOrder = deviceOrderService.getDeviceOrderByStatusAndDeviceID(DeviceOrder.UNEXECUTED, deviceID);
            if (allDeviceOrder.size() > 0) {
                //设置socket的超时时间
                socket.setSoTimeout(10000);

                //获取输出流
                OutputStream outputStream = socket.getOutputStream();
                //获取输入流
                InputStream inputStream = socket.getInputStream();

                for (DeviceOrder deviceOrder : allDeviceOrder) {
                    try {
                        //查询连续参数
                        if (deviceOrder.getOrderCode().equals("71")) {
                            executeQueryAllParamOrder(outputStream, inputStream, deviceOrder);
                        } else {
                            executeOrder(outputStream, inputStream, deviceOrder);
                        }
                    } catch (Exception e) {
                        //throw new RuntimeException(e);
                        logger.error("执行指令发生异常," + e.getMessage());
                    }
                }
                socket.setSoTimeout(120000);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            //throw new RuntimeException(e);
        }
    }

    private void executeOrder(OutputStream outputStream, InputStream inputStream, DeviceOrder deviceOrder) {

        try {
            deviceOrder.setBeginExecuteTime(new Date());
            //logger.trace("开始执行" + deviceOrder.getDeviceID() + deviceOrder.getOrderName() + deviceOrder.getOrderCode() + "指令");
            logger.info("开始执行" + deviceOrder.getDeviceID() + deviceOrder.getOrderName() + deviceOrder.getOrderCode() + "指令");
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderID(deviceOrder.getID(), OrderDetail.UNEXECUTED);

            for (int i = 0; i < orderDetails.size(); i++) {
                boolean b = executeOrderDetail(inputStream, outputStream, orderDetails.get(i), deviceOrder);
                if (!b) {
                    deviceOrder.setOrderState(DeviceOrder.FAILED);
                    deviceOrder.setCompleteTime(new Date());
                    deviceOrder.setRemark("执行失败，已达最大尝试发送次数");
                    deviceOrderService.updateDeviceOrder(deviceOrder);
                    logger.info("失败执行" + deviceOrder.getDeviceID() + deviceOrder.getOrderName() + deviceOrder.getOrderCode() + "指令");
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderID(deviceOrder.getID());
                    orderDetailService.deleteOrderDetail(orderDetail);
                    refreshOrderTable(deviceOrder);
                    return;
                }
                if (i % 10 == 0) {
                    deviceOrderService.updateDeviceOrder(deviceOrder);
                    refreshOrderTable(deviceOrder);
                }
            }


            deviceOrder.setOrderState(DeviceOrder.SUCCEED);
            deviceOrder.setRemark("指令执行成功！");
            deviceOrder.setCompleteTime(new Date());
            deviceOrderService.updateDeviceOrder(deviceOrder);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetailService.deleteOrderDetail(orderDetail);

            logger.info("成功执行" + deviceOrder.getDeviceID() + deviceOrder.getOrderName() + deviceOrder.getOrderCode() + "指令");
            refreshOrderTable(deviceOrder);
        } catch (Exception e) {
            logger.error("执行" + deviceOrder.getDeviceID() + deviceOrder.getOrderName() + "指令发生错误," + e.getMessage());
            //throw new RuntimeException(e);
        }
    }


    private void executeQueryAllParamOrder(OutputStream outputStream, InputStream inputStream, DeviceOrder deviceOrder) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            deviceOrder.setBeginExecuteTime(new Date());

            logger.info("开始执行" + deviceOrder.getDeviceID() + deviceOrder.getOrderName() + deviceOrder.getOrderCode() + "指令");

            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderID(deviceOrder.getID());
            List<Long> paramValues = new ArrayList<>();

            for (int i = 0; i < orderDetails.size(); i++) {
                boolean b = executeQueryAllParamOrderDetail(inputStream, outputStream, orderDetails.get(i), deviceOrder, paramValues);
                if (!b) {
                    deviceOrder.setOrderState(DeviceOrder.FAILED);
                    deviceOrder.setRemark("执行失败，已达最大尝试发送次数");
                    deviceOrderService.updateDeviceOrder(deviceOrder);

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderID(deviceOrder.getID());
                    orderDetailService.deleteOrderDetail(orderDetail);

                    logger.info("失败执行" + deviceOrder.getDeviceID() + deviceOrder.getOrderName() + deviceOrder.getOrderCode() + "指令");
                    refreshOrderTable(deviceOrder);
                    return;
                }
            }

            DeviceInfo deviceInfo = deviceInfoService.getDeviceInfoByDeviceID(deviceOrder.getDeviceID());
            if (deviceInfo == null) {
                return;
            }
            String fileName = "";
            if (deviceInfo.getHardwareVersion().endsWith("1")) {
                fileName = "exportFile/" + deviceOrder.getDeviceID() + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".dat";
            } else if (deviceInfo.getHardwareVersion().endsWith("2")) {
                fileName = "exportFile/" + deviceOrder.getDeviceID() + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".datx";
            }
            ArrayList<DeviceParamValue> deviceParamValues = new ArrayList<>();
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            Date date = new Date();
            for (int i = 0; i < paramValues.size(); i++) {
                //" 1 , 222 "
                bufferedWriter.write(" " + (i + 1) + " , " + paramValues.get(i) + " ");
                bufferedWriter.newLine();

                DeviceParamValue deviceParamValue = new DeviceParamValue();
                deviceParamValue.setDeviceID(deviceOrder.getDeviceID());
                deviceParamValue.setParamIndex(i + 1);
                deviceParamValue.setParamValue(paramValues.get(i) + "");
                deviceParamValue.setUpdateTime(date);
                deviceParamValues.add(deviceParamValue);
            }
            bufferedWriter.flush();

            bufferedWriter.close();
            fileWriter.close();


            deviceParamValueService.deleteDeviceParamValueByDeviceID(deviceOrder.getDeviceID());
            deviceParamValueService.batchAddDeviceParamValue(deviceParamValues);

            deviceOrder.setOrderState(2);
            deviceOrder.setRemark("指令执行成功！");
            deviceOrder.setCompleteTime(date);
            deviceOrder.setOrderResult(fileName);
            deviceOrderService.updateDeviceOrder(deviceOrder);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetailService.deleteOrderDetail(orderDetail);

            logger.info("成功执行" + deviceOrder.getDeviceID() + deviceOrder.getOrderName() + deviceOrder.getOrderCode() + "指令");
            refreshOrderTable(deviceOrder);
        } catch (Exception e) {
            logger.error("执行" + deviceOrder.getDeviceID() + deviceOrder.getOrderName() + "指令发生错误," + e.getMessage() + ExceptionUtil.getStackTrace(e));
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    //throw new RuntimeException(ex);
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    //
                }
            }
        }
    }

    private boolean executeQueryAllParamOrderDetail(InputStream inputStream, OutputStream outputStream, OrderDetail orderDetail, DeviceOrder deviceOrder, List<Long> paramValues) throws IOException, SQLException {

        deviceOrder.setCurMsgIndex(deviceOrder.getCurMsgIndex() + 1);


        while (orderDetail.getExecuteCount() < 10) {
            //发送次数加1
            orderDetail.setExecuteCount(orderDetail.getExecuteCount() + 1);
            logger.info("开始发送设备(" + deviceOrder.getDeviceID() + ")" + orderDetail.getMsgType() + "指令的第" + orderDetail.getCurPackageNumber() + "包数据");
            byte[] sendBytes = CommonUil.hexToByteArray(orderDetail.getMsgContent());
            outputStream.write(sendBytes);

            WXMFProtocol wxmfProtocol = null;
            while (true) {
                try {
                    byte[] bytes = new byte[1024];
                    int len = inputStream.read(bytes);
                    byte[] dataBytes = Arrays.copyOf(bytes, len);
                    logger.info("收到设备(" + deviceOrder.getDeviceID() + CommonUil.byteArrayToHexString(dataBytes) + ")回执");
                    wxmfProtocol = WXMFProtocolUtil.createWXMFProtocol(dataBytes);
                    break;
                } catch (SocketTimeoutException e) {
                    //读取超时，尝试下次发送数据
                    logger.error("设备(" + deviceOrder.getDeviceID() + ")读取超时，尝试下次发送数据");
                    break;
                } catch (IOException e) {
                    logger.error("设备(" + e.getMessage() + ")读取异常");
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    logger.error("设备(" + ExceptionUtil.getStackTrace(e) + ")解析异常");
                    //不是后台协议，继续读取
                    throw new RuntimeException(e);
                }
            }

            if (wxmfProtocol != null) {
                if (wxmfProtocol.getProtocolData().getResponse().equals("01") && wxmfProtocol.getFunctionCode().equals(orderDetail.getMsgType())) {

                    updateDeviceInfo(wxmfProtocol);

                    //解析设备参数
                    byte[] bytesData = wxmfProtocol.getProtocolData().getBytes();
                    int length = (bytesData.length - 3) / 4;
                    for (int i = 0; i < length; i++) {
                        paramValues.add(Long.parseLong(CommonUil.byteArrayToHexString(bytesData, i * 4 + 3, i * 4 + 3 + 4), 16));
                    }

                    //数据发送成功
                    orderDetail.setMsgState(OrderDetail.SUCCEED);
                    orderDetailService.updateOrderDetail(orderDetail);
                    return true;

                }
            }
            orderDetailService.updateOrderDetail(orderDetail);
        }

        //尝试次数超过10次，直接结束本次指令
        orderDetail.setMsgState(OrderDetail.FAILED);
        orderDetailService.updateOrderDetail(orderDetail);

        return false;
    }

    private boolean executeOrderDetail(InputStream inputStream, OutputStream outputStream, OrderDetail orderDetail, DeviceOrder deviceOrder) throws SQLException, InterruptedException, IOException {


        deviceOrder.setCurMsgIndex(deviceOrder.getCurMsgIndex() + 1);

        while (orderDetail.getExecuteCount() < 10) {
            //发送次数加1


            orderDetail.setExecuteCount(orderDetail.getExecuteCount() + 1);
            logger.info("开始发送设备(" + deviceOrder.getDeviceID() + ")" + orderDetail.getMsgType() + "指令的第" + orderDetail.getCurPackageNumber() + "包数据");
            if (deviceOrder.getOrderCode().equals("75")) {

                orderDetail.setMsgContent(initSetTimeMsgContent());
            }
            byte[] sendBytes = CommonUil.hexToByteArray(orderDetail.getMsgContent());
            Thread.sleep(100);
            outputStream.write(sendBytes);


            while (true) {
                try {
                    byte[] bytes = new byte[1024];
                    int len = inputStream.read(bytes);
                    byte[] dataBytes = Arrays.copyOf(bytes, len);
                    logger.info("收到设备(" + deviceOrder.getDeviceID() + ")回执，" + CommonUil.byteArrayToHexString(dataBytes));
                    //System.out.println(CommonUil.byteArrayToHexString(dataBytes));
                    WXMFProtocol wxmfProtocol = WXMFProtocolUtil.createWXMFProtocol(dataBytes);

                    if (wxmfProtocol.getProtocolData().getResponse().equals("01")) {

                        if (wxmfProtocol.getFunctionCode().equals(orderDetail.getMsgType())) {
                            updateDeviceInfo(wxmfProtocol);

                            //数据发送成功
                            orderDetail.setMsgState(OrderDetail.SUCCEED);
                            orderDetailService.updateOrderDetail(orderDetail);

                            if (deviceOrder.getOrderCode().equals("73")) {
                                saveFewParameter(deviceOrder, wxmfProtocol);
                            } else if (deviceOrder.getOrderCode().equals("70")) {
                                saveFewParameterWith70(deviceOrder, wxmfProtocol);
                            } else if (deviceOrder.getOrderCode().equals("78")) {
                                saveRealData(deviceOrder, wxmfProtocol);
                            } else if (deviceOrder.getOrderCode().equals("91")) {
                                saveFlowCapacityRelationship(deviceOrder, wxmfProtocol);
                            }
                            return true;
                        } else {
                            logger.error("设备(" + deviceOrder.getDeviceID() + ")回执功能码不匹配，继续读取");
                        }
                    } else {
                        logger.error("设备(" + deviceOrder.getDeviceID() + ")回执执行失败");
                    }
                    break;
                } catch (SocketTimeoutException e) {
                    //读取超时，尝试下次发送数据
                    logger.error("设备(" + deviceOrder.getDeviceID() + ")读取超时，尝试下次发送数据");
                    break;
                } catch (IOException e) {
                    logger.error("设备(" + e.getMessage() + ")读取异常");
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    logger.error("设备(" + ExceptionUtil.getStackTrace(e) + ")解析异常");
                    //不是后台协议，继续读取
                    throw new RuntimeException(e);
                }
            }


            orderDetailService.updateOrderDetail(orderDetail);
        }

        //尝试次数超过10次，直接结束本次指令
        orderDetail.setMsgState(OrderDetail.FAILED);
        orderDetailService.updateOrderDetail(orderDetail);

        return false;
    }

    private void saveFlowCapacityRelationship(DeviceOrder deviceOrder, WXMFProtocol wxmfProtocol) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {

            String fileName = "exportFile/" + deviceOrder.getDeviceID() + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".txt";
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            byte[] bytesData = wxmfProtocol.getProtocolData().getBytes();
            //1个字节断面总折点数（最多63个，一个折点8个字节）+8*N字节数据
            //（水位面积：其中前4字节是水位0.01m，后4字节面积单位0.01m2）
            //（水位流量：其中前4字节是水位0.01m，后4字节流量单位0.001m3/s）
            int amount = bytesData[0] & 0xFF;
            for (int i = 0; i < amount; i++) {
                //数值
                long water = Long.parseLong(CommonUil.byteArrayToHexString(bytesData, i * 8 + 1, i * 8 + 5), 16);
                long flowCapacity = Long.parseLong(CommonUil.byteArrayToHexString(bytesData, i * 8 + 5, i * 8 + 9), 16);

                bufferedWriter.write((i + 1) + "\t" + water + "\t" + flowCapacity);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();

            bufferedWriter.close();
            fileWriter.close();

            deviceOrder.setOrderResult(fileName);

        } catch (Exception ex) {
            logger.error("saveFlowCapacityRelationship发生异常," + ex.getMessage());
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    //throw new RuntimeException(ex);
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    //
                }
            }
        }
    }

    /**
     * 保存实时数据
     *
     * @param deviceOrder  指令对象
     * @param wxmfProtocol 后台协议报文对象
     */
    private void saveRealData(DeviceOrder deviceOrder, WXMFProtocol wxmfProtocol) {
        try {
            byte[] bytesData = wxmfProtocol.getProtocolData().getBytes();

            //通道数量
            int channelAmount = bytesData[0] & 0xFF;

            //查询通道代码
            ParamOption paramOption = new ParamOption();
            paramOption.setParamTypeID(17);//?
            List<ParamOption> paramOptionList = paramOptionService.getParamOption(paramOption);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < channelAmount; i++) {
                //(bytes[beginIndex + i] & 0xFF) + "";
                String channelType = (bytesData[i + 1] & 0xFF) + "";
                String channelTypeHex = CommonUil.byteToHexString(bytesData[i + 1]);

                List<ParamOption> paramOptions = paramOptionList.stream().filter(o -> Objects.equals(o.getOptionValue(), channelType)).collect(Collectors.toList());
                if (paramOptions.size() > 0) {
                    stringBuilder.append(paramOptions.get(0).getOptionName());
                } else {
                    if (channelTypeHex.startsWith("1")) {
                        stringBuilder.append("水位");
                    } else if (channelTypeHex.startsWith("2")) {
                        stringBuilder.append("闸位");
                    } else if (channelTypeHex.startsWith("3")) {
                        stringBuilder.append("流量");
                    } else if (channelTypeHex.startsWith("4")) {
                        stringBuilder.append("电力/电压");
                    } else if (channelTypeHex.startsWith("5")) {
                        stringBuilder.append("墒情");
                    } else if (channelTypeHex.startsWith("6")) {
                        stringBuilder.append("流速");
                    } else {
                        stringBuilder.append("未知");
                    }
                }
                stringBuilder.append("(").append(channelTypeHex).append(")");

                //数值
                long value = Long.parseLong(CommonUil.byteArrayToHexString(bytesData, 1 + channelAmount + i * 4, 1 + channelAmount + i * 4 + 4), 16);

                stringBuilder.append("=").append(value).append(";");

            }

            deviceOrder.setOrderResult(stringBuilder.toString());

        } catch (Exception ex) {

            logger.error("saveFewParameterWith70发生异常," + ex.getMessage());
        }
    }

    private String initSetTimeMsgContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("F1030000000000");
        //报文长度
        sb.append(StringUtils.leftPad(Integer.toHexString(12 + 7), 4, '0').toUpperCase());
        //功能码
        sb.append("75");

        //数据域
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String time = sdf.format(date);
        int day = date.getDay();
        if (day == 0) {
            time += "07";
        } else {

            time += "0" + day;
        }
        sb.append(time);

        //和校验
        sb.append(WXMFProtocolUtil.getSumCheckValue(sb.toString()));
        //结束符
        sb.append("F2");

        return sb.toString();
    }

    private void saveFewParameter(DeviceOrder deviceOrder, WXMFProtocol wxmfProtocol) throws SQLException {
        byte[] bytesData = wxmfProtocol.getProtocolData().getBytes();
        int length = (bytesData.length - 1) / 6;
        for (int i = 0; i < length; i++) {
            //            //寄存器地址
            //            int registerAddress = Integer.parseInt(CommonUil.byteArrayToHexString(bytesData, i * 6 + 1, i * 6 + 1 + 2), 16);
            //            //参数个数
            //            int paramValue = Integer.parseInt(CommonUil.byteArrayToHexString(bytesData, i * 6 + 1 + 2, i * 6 + 1 + 6), 16);
            //
            //            DeviceParameter deviceParameter = new DeviceParameter();
            //            deviceParameter.setRegisterAddress(registerAddress);
            //            List<DeviceParameter> deviceParameters = deviceParameterService.getDeviceParameter(deviceParameter);
            //            if(deviceParameters)
            //
            //
            //            DeviceParamValue deviceParamValue = new DeviceParamValue();
            //            deviceParamValue.setDeviceID(deviceOrder.getDeviceID());
            //            deviceParamValue.setParamIndex(registerAddress);
            //            deviceParamValue.setParamValue(paramValue);
            //            deviceParamValue.setUpdateTime(new Date());
            //            deviceParamValueService.addDeviceParamValue(deviceParamValue);
        }
    }

    private void saveFewParameterWith70(DeviceOrder deviceOrder, WXMFProtocol wxmfProtocol) {
        try {
            byte[] bytesData = wxmfProtocol.getProtocolData().getBytes();
            int length = (bytesData.length - 1) / 10;
            String orderResult = "";
            for (int i = 0; i < length; i++) {
                //寄存器地址
                int registerAddress = Integer.parseInt(CommonUil.byteArrayToHexString(bytesData, i * 10 + 1, i * 10 + 1 + 2), 16);

                //有效参数位
                long parameterBits = Long.parseLong(CommonUil.byteArrayToHexString(bytesData, i * 10 + 1 + 2, i * 10 + 1 + 2 + 4), 16);
                String parameterBitsStr = StringUtils.leftPad(Long.toBinaryString(parameterBits), 32, '0');
                int startBit = -1;
                int endBit = -1;
                for (int j = 0; j < 32; j++) {
                    if (parameterBitsStr.charAt(j) == '1') {
                        if (endBit == -1) {
                            endBit = 32 - j - 1;
                        }
                        startBit = 32 - j - 1;
                    }
                }

                //参数值????

                long paramValueLong = Long.parseLong(CommonUil.byteArrayToHexString(bytesData, i * 10 + 1 + 2 + 4, i * 10 + 1 + 2 + 8), 16);

                String paramValueBin = StringUtils.leftPad(Long.toBinaryString(paramValueLong), 32, '0');

                //
                DeviceParameter deviceParameter = new DeviceParameter();
                deviceParameter.setRegisterAddress(registerAddress);
                deviceParameter.setStartBit(startBit);
                deviceParameter.setEndBit(endBit);
                List<DeviceParameter> deviceParameters = deviceParameterService.getDeviceParameter(deviceParameter);

                //|| registerAddress == 299 || registerAddress == 330|| registerAddress == 333 || registerAddress == 336
                if (deviceParameters.size() > 0) {

                    DeviceParamValue deviceParamValue = new DeviceParamValue();
                    deviceParamValue.setDeviceID(deviceOrder.getDeviceID());
                    deviceParamValue.setParamIndex(deviceParameters.get(0).getID());
                    String paramValueStr = "";
                    if (registerAddress == 298 || registerAddress == 329 || registerAddress == 332 || registerAddress == 335) {

                        paramValueBin = paramValueBin.substring(31 - endBit, 31 - startBit + 1);

                        paramValueStr += Integer.parseInt(paramValueBin.substring(0, 8), 2) + ".";
                        paramValueStr += Integer.parseInt(paramValueBin.substring(8), 2) + ".";


                        paramValueLong = Long.parseLong(CommonUil.byteArrayToHexString(bytesData, (i + 1) * 10 + 1 + 2 + 4, (i + 1) * 10 + 1 + 2 + 8), 16);
                        paramValueBin = StringUtils.leftPad(Long.toBinaryString(paramValueLong), 32, '0');

                        paramValueBin = paramValueBin.substring(31 - endBit, 31 - startBit + 1);
                        paramValueStr += Integer.parseInt(paramValueBin.substring(0, 8), 2) + ".";
                        paramValueStr += Integer.parseInt(paramValueBin.substring(8), 2);

                        i++;

                    } else if (registerAddress == 350 || registerAddress == 351 || registerAddress == 352 || registerAddress == 353 || registerAddress == 364) {
                        int paramValue = Integer.parseInt(paramValueBin.substring(31 - endBit, 31 - startBit + 1), 2);
                        paramValueStr += paramValue;
                        paramValueLong = Long.parseLong(CommonUil.byteArrayToHexString(bytesData, (i + 1) * 10 + 1 + 2 + 4, (i + 1) * 10 + 1 + 2 + 8), 16);
                        paramValueBin = StringUtils.leftPad(Long.toBinaryString(paramValueLong), 32, '0');

                        paramValue = Integer.parseInt(paramValueBin.substring(31 - endBit, 31 - startBit + 1), 2);
                        paramValueStr += paramValue;
                        i++;

                    } else if (deviceParameters.get(0).getParamType() == 2) {//24 到 31位是符号位，0=正，非0=负，正负数据类--填写数据
                        //？
                        int paramValue = Integer.parseInt(paramValueBin.substring(31 - endBit, 31 - startBit + 1), 2);
                        if (Integer.parseInt(paramValueBin.substring(0, 31 - endBit + 1), 2) > 0) {
                            paramValueStr = "-" + paramValue;
                        } else {
                            paramValueStr = paramValue + "";
                        }

                    } else {
                        //？
                        int paramValue = Integer.parseInt(paramValueBin.substring(31 - endBit, 31 - startBit + 1), 2);
                        paramValueStr = paramValue + "";
                    }
                    deviceParamValue.setParamValue(paramValueStr);
                    deviceParamValue.setUpdateTime(new Date());
                    deviceParamValueService.addDeviceParamValue(deviceParamValue);

                    ParamOption paramOption = new ParamOption();
                    paramOption.setParamTypeID(deviceParameters.get(0).getParamType());
                    final String paramValueStrFinal = paramValueStr;
                    List<ParamOption> paramOptionList = paramOptionService.getParamOption(paramOption).stream().filter(o -> o.getOptionValue().equals(paramValueStrFinal)).collect(Collectors.toList());
                    if (paramOptionList.size() > 0) {
                        orderResult += deviceParameters.get(0).getParamName() + "=" + paramOptionList.get(0).getOptionName() + ";";
                    } else {
                        orderResult += deviceParameters.get(0).getParamName() + "=" + paramValueStrFinal + ";";
                    }
                }
            }

            deviceOrder.setOrderResult(orderResult);

        } catch (Exception ex) {

            logger.error("saveFewParameterWith70发生异常," + ex.getMessage());
        }

    }

    private boolean initUpgradeDeviceOrder(DeviceOrder deviceOrder) {
        try {
            //获取升级包编号;
            int beginIndex = deviceOrder.getParameter().indexOf("PackageID=");
            int endIndex = deviceOrder.getParameter().indexOf(";", beginIndex);
            int packageID = Integer.parseInt(deviceOrder.getParameter().substring(beginIndex, endIndex).split("=")[1]);

            //获取升级包信息
            PackageVersion packageVersion = packageVersionService.getPackageVersionByID(packageID);
            if (packageVersion == null) {
                modifyOrderInfo(deviceOrder, "无法升级，升级包不存在!");
                return false;
            }

            //获取当前设备信息
            DeviceInfo deviceInfo = deviceInfoService.getDeviceInfoByDeviceID(deviceOrder.getDeviceID());
            if (deviceInfo == null) {
                modifyOrderInfo(deviceOrder, "无法升级，设备不存在!");
                return false;
            }

            //判断硬件版本是否一致
            if (!deviceInfo.getHardwareVersion().equals(packageVersion.getHardwareVersion())) {
                modifyOrderInfo(deviceOrder, "无法升级，硬件版本不一致!");
                return false;
            }

            if (deviceInfo.getApplicationVersion().equals(packageVersion.getApplicationVersion())) {
                modifyOrderInfo(deviceOrder, "无法升级，应用程序版本号一致!");
                return false;
            }


            //功能码0x61（读取设备信息）：
            if (!addObtainDeviceInfoOrder(deviceOrder)) {
                return false;
            }

            //功能码0x62（格式化缓冲区）：
            if (!addFormatBufferOrder(deviceOrder)) {
                return false;
            }

            byte[] bytesFile = getWriteData(deviceOrder, packageVersion);
            if (bytesFile == null) {
                return false;
            }

            //功能码0x63（写程序）：
            if (!addWriteDataOrder(deviceOrder, packageVersion, bytesFile)) {
                return false;
            }

            //功能码0x64（效验缓冲区）：
            if (!addCheckBufferOrder(deviceInfo, deviceOrder, bytesFile)) {
                return false;
            }

            //功能码0x65（更新应用程序）
            if (!addUpdateApplicationOrder(deviceOrder)) {
                return false;
            }
            deviceOrder.setCurMsgIndex(0);
            deviceOrder.setOrderState(1);
            deviceOrder.setRemark("指令初始化成功，等待执行");
            deviceOrderService.updateDeviceOrder(deviceOrder);

            return true;

        } catch (Exception ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化失败!");
            } catch (Exception e) {
                logger.error(ex.getMessage());
            }
            logger.error(ex.getMessage());
        }

        return false;
    }

    private boolean addUpdateApplicationOrder(DeviceOrder deviceOrder) {
        try {
            //指令总包数+1
            deviceOrder.setTotalMsgCount(deviceOrder.getTotalMsgCount() + 1);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("65");
            orderDetail.setMsgContent("F1030000000000000C6565F2");
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            if (orderDetailService.addOrderDetail(orderDetail) == 0) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加更新应用程序指令失败!");
            } catch (Exception e) {
                logger.error(ex.getMessage());
            }
            logger.error(ex.getMessage());
        }

        return false;
    }

    private byte[] getWriteData(DeviceOrder deviceOrder, PackageVersion packageVersion) {
        FileInputStream fis = null;
        File file = new File(packageVersion.getPackagePath());
        byte[] bytesFile = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(bytesFile);
            fis.close();
        } catch (Exception ex) {
            try {
                if (fis != null) {
                    fis.close();
                }
                modifyOrderInfo(deviceOrder, "指令初始化，添加写数据指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(deviceOrder + "指令初始化，添加写数据指令失败!，" + ex.getMessage());

            return null;
        }
        return bytesFile;
    }

    private boolean addCheckBufferOrder(DeviceInfo deviceInfo, DeviceOrder deviceOrder, byte[] bytesFile) {
        try {
            int length = 512000;
            if ('6' == deviceInfo.getHardwareVersion().charAt(3)) {
                length = 1024000;
            }
            if (bytesFile.length < length) {

                byte[] bytes = Arrays.copyOf(bytesFile, length);

                for (int i = bytesFile.length; i < length; i++) {
                    //FF
                    bytes[i] = -1;
                }

                String applicationSumCheckValue = WXMFProtocolUtil.getApplicationSumCheckValue(bytes);
                String msgContent = "F1030000000000001064" + applicationSumCheckValue;
                msgContent = msgContent + WXMFProtocolUtil.getSumCheckValue(msgContent) + "F2";

                //指令总包数+1
                deviceOrder.setTotalMsgCount(deviceOrder.getTotalMsgCount() + 1);

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderID(deviceOrder.getID());
                orderDetail.setCurPackageNumber(1);
                orderDetail.setTotalPackageNumber(1);
                orderDetail.setMsgType("64");
                orderDetail.setMsgContent(msgContent);
                orderDetail.setMsgState(0);
                orderDetail.setExecuteCount(0);
                orderDetail.setSort(deviceOrder.getTotalMsgCount());
                if (orderDetailService.addOrderDetail(orderDetail) == 0) {
                    modifyOrderInfo(deviceOrder, "指令初始化，添加校验缓冲区指令失败!");
                    return false;
                }
                return true;
            }
        } catch (Exception ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加校验缓冲区指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }

        return false;
    }

    private boolean addWriteDataOrder(DeviceOrder deviceOrder, PackageVersion packageVersion, byte[] bytesFile) {

        try {
            int totalPackageNumber = bytesFile.length / 512;
            if (bytesFile.length % 512 != 0) {
                totalPackageNumber++;
            }
            int curPackageNumber = 0;
            StringBuilder stringBuilder = null;
            for (int i = 0; i < bytesFile.length; i++) {
                if (i % 512 == 0) {
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(CommonUil.byteToHexString(bytesFile[i]));

                if ((i + 1) % 512 == 0 || i == bytesFile.length - 1) {

                    String lengthHex = StringUtils.leftPad(toHexString(stringBuilder.length() / 2 + 16), 4, '0').toUpperCase();
                    String curPackageNumberHex = StringUtils.leftPad(toHexString(curPackageNumber), 4, '0').toUpperCase();
                    String dataLengthHex = StringUtils.leftPad(toHexString(stringBuilder.length() / 2), 4, '0').toUpperCase();

                    String msgContent = "F1030000000000" + lengthHex + "63" + curPackageNumberHex + dataLengthHex + stringBuilder;
                    msgContent += WXMFProtocolUtil.getSumCheckValue(msgContent) + "F2";

                    //指令总包数+1
                    deviceOrder.setTotalMsgCount(deviceOrder.getTotalMsgCount() + 1);

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderID(deviceOrder.getID());
                    orderDetail.setCurPackageNumber(++curPackageNumber);
                    orderDetail.setTotalPackageNumber(totalPackageNumber);
                    orderDetail.setMsgType("63");
                    orderDetail.setMsgContent(msgContent);
                    orderDetail.setMsgState(0);
                    orderDetail.setExecuteCount(0);
                    orderDetail.setSort(deviceOrder.getTotalMsgCount());
                    if (orderDetailService.addOrderDetail(orderDetail) == 0) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加写数据指令失败!");
            } catch (Exception e) {
                logger.error(ex.getMessage());
            }
            logger.error(ex.getMessage());
        }
        return false;
    }

    private void modifyOrderInfo(DeviceOrder deviceOrder, String remark) throws SQLException {
        deviceOrder.setOrderState(3);
        deviceOrder.setRemark(remark);
        deviceOrderService.updateDeviceOrder(deviceOrder);
    }

    /**
     * @param deviceOrder
     * @return
     * @throws SQLException
     */
    private boolean addFormatBufferOrder(DeviceOrder deviceOrder) throws SQLException {

        try {
            //指令总包数+1
            deviceOrder.setTotalMsgCount(deviceOrder.getTotalMsgCount() + 1);
            //功能码0x62（格式化缓冲区）：：
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("62");
            orderDetail.setMsgContent("F1030000000000000C6262F2");
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            if (orderDetailService.addOrderDetail(orderDetail) > 0) {
                return true;
            }
        } catch (Exception ex) {
            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加格式化缓冲区指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
        return false;
    }

    private boolean addObtainDeviceInfoOrder(DeviceOrder deviceOrder) {

        try {
            //指令总包数+1
            deviceOrder.setTotalMsgCount(1);
            //功能码0x61（读取设备信息）：
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(deviceOrder.getID());
            orderDetail.setCurPackageNumber(1);
            orderDetail.setTotalPackageNumber(1);
            orderDetail.setMsgType("61");
            orderDetail.setMsgContent("F1030000000000000C6161F2");
            orderDetail.setMsgState(0);
            orderDetail.setExecuteCount(0);
            orderDetail.setSort(deviceOrder.getTotalMsgCount());
            if (orderDetailService.addOrderDetail(orderDetail) > 0) {
                return true;
            }
        } catch (SQLException ex) {

            try {
                modifyOrderInfo(deviceOrder, "指令初始化，添加获取设备信息指令失败!");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.error(ex.getMessage());
        }
        return false;
    }


    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        //logToTextArea("窗体加载");
    }

    public void bindOrderTable() {

        try {
            List<DeviceOrder> deviceOrders = deviceOrderService.getAllDeviceOrder().stream().sorted((o1, o2) -> o2.getDistributeTime().compareTo(o1.getDistributeTime())).collect(Collectors.toList());
            DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
            model.getDataVector().removeAllElements();
            for (DeviceOrder deviceOrder : deviceOrders) {
                Vector<Object> objects = new Vector<>();
                objects.add(deviceOrder.getID());
                objects.add(deviceOrder.getDeviceID());
                objects.add(deviceOrder.getOrderName());
                objects.add(deviceOrder.getOrderCode());
                objects.add(simpleDateFormat.format(deviceOrder.getDistributeTime()));
                objects.add(deviceOrder.getBeginExecuteTime() != null ? simpleDateFormat.format(deviceOrder.getBeginExecuteTime()) : "");
                objects.add(deviceOrder.getCompleteTime() != null ? simpleDateFormat.format(deviceOrder.getCompleteTime()) : "");
                objects.add(deviceOrder.getOrderState());
                objects.add(deviceOrder.getParameter());
                objects.add(deviceOrder.getCurMsgIndex());
                objects.add(deviceOrder.getTotalMsgCount());
                objects.add(deviceOrder.getRemark());
                model.addRow(objects);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
    }

    synchronized public void refreshOrderTable(DeviceOrder deviceOrder) {

//        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
//
//        boolean isExist = false;
//        int rowCount = model.getRowCount();
//        for (int i = 0; i < rowCount; i++) {
//            Integer id = (Integer) model.getValueAt(i, 0);
//            if (id == deviceOrder.getID()) {
//                model.setValueAt(deviceOrder.getDeviceID(), i, 1);
//                model.setValueAt(deviceOrder.getOrderName(), i, 2);
//                model.setValueAt(deviceOrder.getOrderCode(), i, 3);
//                model.setValueAt((simpleDateFormat.format(deviceOrder.getDistributeTime())), i, 4);
//                model.setValueAt(deviceOrder.getBeginExecuteTime() != null ? simpleDateFormat.format(deviceOrder.getBeginExecuteTime()) : "", i, 5);
//                model.setValueAt(deviceOrder.getCompleteTime() != null ? simpleDateFormat.format(deviceOrder.getCompleteTime()) : "", i, 6);
//                model.setValueAt(deviceOrder.getOrderState(), i, 7);
//                model.setValueAt(deviceOrder.getParameter(), i, 8);
//                model.setValueAt(deviceOrder.getCurMsgIndex(), i, 9);
//                model.setValueAt(deviceOrder.getTotalMsgCount(), i, 10);
//                model.setValueAt(deviceOrder.getRemark(), i, 11);
//                isExist = true;
//                break;
//            }
//        }
//        if (!isExist) {
//            bindOrderTable();
//        }

    }


    public void bindEquipTable() {

        try {
            List<DeviceInfo> allDeviceInfo = deviceInfoService.getAllDeviceInfo();

            DefaultTableModel model = (DefaultTableModel) this.equipTable.getModel();

            Vector dataVector = model.getDataVector();
            dataVector.removeAllElements();
            for (DeviceInfo deviceInfo : allDeviceInfo) {
                Vector<Object> objects = new Vector<>();
                objects.add(deviceInfo.getID());
                objects.add(deviceInfo.getDeviceID());
                objects.add(deviceInfo.getDeviceName());
                objects.add(simpleDateFormat.format(deviceInfo.getUpdateTime()));
                objects.add(deviceInfo.getHardwareVersion());
                objects.add(deviceInfo.getSystemProgramVersion());
                objects.add(deviceInfo.getApplicationVersion());
                objects.add(deviceInfo.getProvincesCitiesCode());
                objects.add(deviceInfo.getDeviceAddress());
                objects.add(deviceInfo.getBatteryVoltage());
                objects.add(deviceInfo.getChargingVoltage());
                objects.add(deviceInfo.getTemperature());
                objects.add(deviceInfo.getHumidity());
                objects.add(deviceInfo.getSignal());
                objects.add("");
                model.addRow(objects);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }


    }

    private void btnParamSet(ActionEvent e) {
        // TODO add your code here

        ParamSetFrame paramSetFrame = new ParamSetFrame(this, true);
        paramSetFrame.setVisible(true);

    }


    private void orderTableMouseReleased(MouseEvent e) {
        // TODO add your code here
        if (e.isPopupTrigger()) {
            this.orderTablePopupMenu.show(this.orderTable, e.getX(), e.getY());
        }
    }

    private void orderTableDelete(ActionEvent e) {
        // TODO add your code here
        DefaultTableModel model = (DefaultTableModel) this.orderTable.getModel();

        int selectedRow = this.orderTable.getSelectedRow();

        Integer orderId = (Integer) model.getValueAt(selectedRow, 0);

        int result = JOptionPane.showConfirmDialog(this, "你确定要删除当前记录吗", "删除", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            try {
                model.removeRow(selectedRow);
                deviceOrderService.deleteDeviceOrderByID(orderId);
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    private void btnProgramUpgrade(ActionEvent e) {
        // TODO add your code here
        UpgradeFrame upgradeFrame = new UpgradeFrame(this, true);
        upgradeFrame.setVisible(true);
    }

    private void btnQueryAllParam(ActionEvent e) {
        // TODO add your code here

        try {
            List<DeviceInfo> allDeviceInfo = deviceInfoService.getAllDeviceInfo();
            Object[] objects = allDeviceInfo.toArray();
            Object result = JOptionPane.showInputDialog(this, "请选择设备", "查询全部参数", JOptionPane.DEFAULT_OPTION, null, objects, objects[0]);
            if (result != null) {
                //jta.append(result.toString());

                DeviceInfo deviceInfo = (DeviceInfo) result;

                DeviceOrder deviceOrder = new DeviceOrder();
                deviceOrder.setDeviceID(deviceInfo.getDeviceID());
                deviceOrder.setOrderName("查询全部参数");
                deviceOrder.setOrderCode("71");
                deviceOrder.setOrderType("1");
                deviceOrder.setDistributeTime(new Date());
                deviceOrder.setOrderState(0);
                deviceOrder.setRemark("指令添加成功!");

                try {
                    deviceOrderService.addDeviceOrder(deviceOrder);
                    bindOrderTable();

                } catch (SQLException ex) {
                    //throw new RuntimeException(ex);
                }

            }
        } catch (SQLException ex) {
            //throw new RuntimeException(ex);
        }
    }

    private void btnSetAllParam(ActionEvent e) {
        // TODO add your code here

        ParamSetAllFrame paramSetAllFrame = new ParamSetAllFrame(this);
        paramSetAllFrame.setVisible(true);

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mainPanel = new JPanel();
        tabbedPane = new JTabbedPane();
        equipScrollPane = new JScrollPane();
        equipTable = new JTable();
        orderPanel = new JPanel();
        orderTableScrollPane = new JScrollPane();
        orderTable = new JTable();
        panel2 = new JPanel();
        btnParamSet = new JButton();
        btnProgramUpgrade = new JButton();
        btnQueryAllParam = new JButton();
        btnSetAllParam = new JButton();
        scrollPane2 = new JScrollPane();
        packageTable = new JTable();
        logScrollPane = new JScrollPane();
        logTextArea = new JTextArea();
        orderTablePopupMenu = new JPopupMenu();
        orderTableDeleteMenuItem = new JMenuItem();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/\u8bbe\u5907\u7ba1\u7406.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 39));
        setTitle("\u65e0\u9521\u6dfc\u5b5a\u8bbe\u5907\u914d\u7f6e\u7ba1\u7406\u8f6f\u4ef6");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mainPanel ========
        {
            mainPanel.setPreferredSize(new Dimension(1000, 700));
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            //======== tabbedPane ========
            {

                //======== equipScrollPane ========
                {

                    //---- equipTable ----
                    equipTable.setPreferredSize(null);
                    equipTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"\u5e8f\u53f7", "\u8bc6\u522b\u7801", "\u8bbe\u5907\u540d\u79f0", "\u6700\u8fd1\u4e0a\u7ebf\u65f6\u95f4", "\u786c\u4ef6\u7248\u672c", "\u7cfb\u7edf\u7a0b\u5e8f\u7248\u672c", "\u5e94\u7528\u7a0b\u5e8f\u7248\u672c", "\u7701\u5e02\u5730\u5740", "\u672c\u673a\u5730\u5740", "\u7535\u6c60\u7535\u538b", "\u5145\u7535\u7535\u538b", "\u6e29\u5ea6", "\u6e7f\u5ea6", "\u4fe1\u53f7\u5f3a\u5ea6", "IP\u5f52\u5c5e"}));
                    {
                        TableColumnModel cm = equipTable.getColumnModel();
                        cm.getColumn(0).setPreferredWidth(25);
                        cm.getColumn(4).setPreferredWidth(50);
                        cm.getColumn(7).setPreferredWidth(50);
                        cm.getColumn(8).setPreferredWidth(50);
                        cm.getColumn(9).setPreferredWidth(50);
                        cm.getColumn(10).setPreferredWidth(50);
                        cm.getColumn(11).setPreferredWidth(25);
                        cm.getColumn(12).setPreferredWidth(25);
                        cm.getColumn(13).setPreferredWidth(50);
                        cm.getColumn(14).setPreferredWidth(50);
                    }
                    equipScrollPane.setViewportView(equipTable);
                }
                tabbedPane.addTab("\u8bbe\u5907\u4fe1\u606f", equipScrollPane);

                //======== orderPanel ========
                {
                    orderPanel.setLayout(new BorderLayout());

                    //======== orderTableScrollPane ========
                    {

                        //---- orderTable ----
                        orderTable.setGridColor(new Color(51, 255, 0));
                        orderTable.setModel(new DefaultTableModel(new Object[][]{{null, null, null, null, null, null, null, null, null, null, null, null},}, new String[]{"\u5e8f\u53f7", "\u8bc6\u522b\u7801", "\u6307\u4ee4\u7c7b\u522b", "\u6807\u8bc6", "\u4e0b\u53d1\u65f6\u95f4", "\u6267\u884c\u65f6\u95f4", "\u5b8c\u6210\u65f6\u95f4", "\u72b6\u6001", "\u53c2\u6570", "\u5f53\u524d\u5305", "\u603b\u5305\u6570", "\u5907\u6ce8"}));
                        {
                            TableColumnModel cm = orderTable.getColumnModel();
                            cm.getColumn(0).setPreferredWidth(30);
                            cm.getColumn(1).setPreferredWidth(100);
                            cm.getColumn(3).setPreferredWidth(35);
                            cm.getColumn(7).setPreferredWidth(35);
                        }
                        orderTable.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseReleased(MouseEvent e) {
                                orderTableMouseReleased(e);
                            }
                        });
                        orderTableScrollPane.setViewportView(orderTable);
                    }
                    orderPanel.add(orderTableScrollPane, BorderLayout.CENTER);

                    //======== panel2 ========
                    {
                        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

                        //---- btnParamSet ----
                        btnParamSet.setText("\u53c2\u6570\u8bbe\u7f6e");
                        btnParamSet.addActionListener(e -> btnParamSet(e));
                        panel2.add(btnParamSet);

                        //---- btnProgramUpgrade ----
                        btnProgramUpgrade.setText("\u5347\u7ea7\u7a0b\u5e8f");
                        btnProgramUpgrade.addActionListener(e -> btnProgramUpgrade(e));
                        panel2.add(btnProgramUpgrade);

                        //---- btnQueryAllParam ----
                        btnQueryAllParam.setText("\u67e5\u8be2\u5168\u90e8\u53c2\u6570");
                        btnQueryAllParam.addActionListener(e -> btnQueryAllParam(e));
                        panel2.add(btnQueryAllParam);

                        //---- btnSetAllParam ----
                        btnSetAllParam.setText("\u8bbe\u7f6e\u5168\u90e8\u53c2\u6570");
                        btnSetAllParam.addActionListener(e -> btnSetAllParam(e));
                        panel2.add(btnSetAllParam);
                    }
                    orderPanel.add(panel2, BorderLayout.NORTH);
                }
                tabbedPane.addTab("\u6307\u4ee4\u4fe1\u606f", orderPanel);

                //======== scrollPane2 ========
                {

                    //---- packageTable ----
                    packageTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"\u7a0b\u5e8f\u5305\u7f16\u53f7", "\u7a0b\u5e8f\u5305\u540d\u79f0", "\u786c\u4ef6\u7248\u672c\u53f7", "\u7cfb\u7edf\u8f6f\u4ef6\u7248\u672c\u53f7", "\u5e94\u7528\u7a0b\u5e8f\u7248\u672c\u53f7"}));
                    {
                        TableColumnModel cm = packageTable.getColumnModel();
                        cm.getColumn(1).setPreferredWidth(300);
                    }
                    scrollPane2.setViewportView(packageTable);
                }
                tabbedPane.addTab("\u5347\u7ea7\u5305\u7ba1\u7406", scrollPane2);
            }
            mainPanel.add(tabbedPane);

            //======== logScrollPane ========
            {
                logScrollPane.setBorder(new TitledBorder("\u65e5\u5fd7"));

                //---- logTextArea ----
                logTextArea.setRows(8);
                logTextArea.setEditable(false);
                logTextArea.setBorder(null);
                logScrollPane.setViewportView(logTextArea);
            }
            mainPanel.add(logScrollPane);
        }
        contentPane.add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //---- orderTableDeleteMenuItem ----
        orderTableDeleteMenuItem.setText("\u5220\u9664\u9009\u4e2d\u8bb0\u5f55");
        orderTableDeleteMenuItem.addActionListener(e -> orderTableDelete(e));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JScrollPane equipScrollPane;
    private JTable equipTable;
    private JPanel orderPanel;
    private JScrollPane orderTableScrollPane;
    private JTable orderTable;
    private JPanel panel2;
    private JButton btnParamSet;
    private JButton btnProgramUpgrade;
    private JButton btnQueryAllParam;
    private JButton btnSetAllParam;
    private JScrollPane scrollPane2;
    private JTable packageTable;
    private JScrollPane logScrollPane;
    private JTextArea logTextArea;
    private JPopupMenu orderTablePopupMenu;
    private JMenuItem orderTableDeleteMenuItem;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
