/*
 * Created by JFormDesigner on Thu Dec 28 10:12:09 CST 2023
 */

package com.wxmf.ui;

import java.awt.event.*;

import com.wxmf.pojo.DeviceInfo;
import com.wxmf.pojo.DeviceOrder;
import com.wxmf.pojo.PackageVersion;
import com.wxmf.service.DeviceInfoService;
import com.wxmf.service.DeviceOrderService;
import com.wxmf.service.PackageVersionService;
import com.wxmf.service.impl.DeviceInfoServiceImpl;
import com.wxmf.service.impl.DeviceOrderServiceImpl;
import com.wxmf.service.impl.PackageVersionServiceImpl;

import java.awt.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class UpgradeFrame extends JDialog {

    DeviceInfoService deviceInfoService = new DeviceInfoServiceImpl();

    DeviceOrderService deviceOrderService = new DeviceOrderServiceImpl();
    PackageVersionService packageVersionService = new PackageVersionServiceImpl();

    public UpgradeFrame() {
        initComponents();
    }

    private MainFrame mainFrame;

    public UpgradeFrame(MainFrame owner, boolean modal) {

        super(owner, modal);

        mainFrame = owner;

        initComponents();

        initDevice();

        initPackageVersion();

    }

    private void initPackageVersion() {
        try {
            List<PackageVersion> packageVersions = packageVersionService.getAllPackageVersion();
            Vector<PackageVersion> vector = new Vector<>(packageVersions);
            this.comboBoxPackageVersion.setModel(new DefaultComboBoxModel(vector));
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
    }

    private void initDevice() {

        try {
            List<DeviceInfo> allDeviceInfo = deviceInfoService.getAllDeviceInfo();
            Vector<DeviceInfo> vector = new Vector<>(allDeviceInfo);
            this.comboBoxDevice.setModel(new DefaultComboBoxModel(vector));
            //this.comboBoxDevice.setSelectedIndex(-1);
            getDeviceInfo();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
    }

    private void comboBoxDeviceItemStateChanged(ItemEvent e) {
        // TODO add your code here
        getDeviceInfo();
    }

    private void getDeviceInfo() {
        DeviceInfo selectedItem = (DeviceInfo) this.comboBoxDevice.getSelectedItem();
        this.lblHardwareVersionValue.setText(selectedItem.getHardwareVersion());
        this.lblSystemProgramVersionValue.setText(selectedItem.getSystemProgramVersion());
        this.lblApplicationVersionValue.setText(selectedItem.getApplicationVersion());
    }

    private void btnAdd(ActionEvent e) {
        // TODO add your code here

        DeviceInfo deviceInfo = (DeviceInfo) this.comboBoxDevice.getSelectedItem();
        PackageVersion packageVersion = (PackageVersion) this.comboBoxPackageVersion.getSelectedItem();


        //判断硬件版本是否一致
        if (!deviceInfo.getHardwareVersion().equals(packageVersion.getHardwareVersion())) {
            JOptionPane.showMessageDialog(this, "无法升级，硬件版本不一致", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (deviceInfo.getApplicationVersion().equals(packageVersion.getApplicationVersion())) {
            JOptionPane.showMessageDialog(this, "无法升级，应用程序版本号一致!", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DeviceOrder deviceOrder = new DeviceOrder();
        deviceOrder.setDeviceID(deviceInfo.getDeviceID());
        deviceOrder.setOrderName("程序升级");
        deviceOrder.setOrderCode("65");
        deviceOrder.setOrderType("2");
        deviceOrder.setDistributeTime(new Date());
        deviceOrder.setOrderState(0);
        deviceOrder.setParameter("PackageID=" + packageVersion.getID() + ";");
        deviceOrder.setRemark("指令添加成功");

        try {
            deviceOrderService.addDeviceOrder(deviceOrder);
            mainFrame.refreshOrderTable(deviceOrder);
        } catch (SQLException ex) {
            //throw new RuntimeException(ex);
        }
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panelBottom = new JPanel();
        btnAdd = new JButton();
        panelCenter = new JPanel();
        panel1 = new JPanel();
        lblDeviceName = new JLabel();
        comboBoxDevice = new JComboBox();
        panel4 = new JPanel();
        lblHardwareVersion = new JLabel();
        lblHardwareVersionValue = new JLabel();
        panel5 = new JPanel();
        lblSystemProgramVersion = new JLabel();
        lblSystemProgramVersionValue = new JLabel();
        panel6 = new JPanel();
        lblApplicationVersion = new JLabel();
        lblApplicationVersionValue = new JLabel();
        panel7 = new JPanel();
        lblPackageVersion = new JLabel();
        comboBoxPackageVersion = new JComboBox();

        //======== this ========
        setTitle("\u5347\u7ea7\u7a0b\u5e8f");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panelBottom ========
        {
            panelBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));

            //---- btnAdd ----
            btnAdd.setText("\u6dfb\u52a0");
            btnAdd.addActionListener(e -> btnAdd(e));
            panelBottom.add(btnAdd);
        }
        contentPane.add(panelBottom, BorderLayout.PAGE_END);

        //======== panelCenter ========
        {
            panelCenter.setPreferredSize(new Dimension(350, 280));
            panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));

            //======== panel1 ========
            {
                panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- lblDeviceName ----
                lblDeviceName.setText("\u8bbe\u5907\u540d\u79f0");
                panel1.add(lblDeviceName);

                //---- comboBoxDevice ----
                comboBoxDevice.setPreferredSize(new Dimension(250, 30));
                comboBoxDevice.addItemListener(e -> comboBoxDeviceItemStateChanged(e));
                panel1.add(comboBoxDevice);
            }
            panelCenter.add(panel1);

            //======== panel4 ========
            {
                panel4.setPreferredSize(new Dimension(350, 27));
                panel4.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- lblHardwareVersion ----
                lblHardwareVersion.setText("\u786c\u4ef6\u7248\u672c\u53f7\uff1a");
                panel4.add(lblHardwareVersion);

                //---- lblHardwareVersionValue ----
                lblHardwareVersionValue.setText("XXXXXX");
                panel4.add(lblHardwareVersionValue);
            }
            panelCenter.add(panel4);

            //======== panel5 ========
            {
                panel5.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- lblSystemProgramVersion ----
                lblSystemProgramVersion.setText("\u7cfb\u7edf\u8f6f\u4ef6\u7248\u672c\u53f7\uff1a");
                panel5.add(lblSystemProgramVersion);

                //---- lblSystemProgramVersionValue ----
                lblSystemProgramVersionValue.setText("XXXXXX");
                panel5.add(lblSystemProgramVersionValue);
            }
            panelCenter.add(panel5);

            //======== panel6 ========
            {
                panel6.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- lblApplicationVersion ----
                lblApplicationVersion.setText("\u5e94\u7528\u8f6f\u4ef6\u7248\u672c\u53f7\uff1a");
                panel6.add(lblApplicationVersion);

                //---- lblApplicationVersionValue ----
                lblApplicationVersionValue.setText("XXXXXXX");
                panel6.add(lblApplicationVersionValue);
            }
            panelCenter.add(panel6);

            //======== panel7 ========
            {
                panel7.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- lblPackageVersion ----
                lblPackageVersion.setText("\u5347\u7ea7\u7a0b\u5e8f");
                panel7.add(lblPackageVersion);

                //---- comboBoxPackageVersion ----
                comboBoxPackageVersion.setPreferredSize(new Dimension(250, 30));
                panel7.add(comboBoxPackageVersion);
            }
            panelCenter.add(panel7);
        }
        contentPane.add(panelCenter, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panelBottom;
    private JButton btnAdd;
    private JPanel panelCenter;
    private JPanel panel1;
    private JLabel lblDeviceName;
    private JComboBox comboBoxDevice;
    private JPanel panel4;
    private JLabel lblHardwareVersion;
    private JLabel lblHardwareVersionValue;
    private JPanel panel5;
    private JLabel lblSystemProgramVersion;
    private JLabel lblSystemProgramVersionValue;
    private JPanel panel6;
    private JLabel lblApplicationVersion;
    private JLabel lblApplicationVersionValue;
    private JPanel panel7;
    private JLabel lblPackageVersion;
    private JComboBox comboBoxPackageVersion;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
