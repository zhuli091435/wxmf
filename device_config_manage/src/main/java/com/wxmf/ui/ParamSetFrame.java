/*
 * Created by JFormDesigner on Tue Dec 26 13:37:04 CST 2023
 */

package com.wxmf.ui;

import javax.swing.border.*;
import javax.swing.table.*;

import com.wxmf.pojo.*;
import com.wxmf.service.*;
import com.wxmf.service.impl.*;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Brainrain
 */
public class ParamSetFrame extends JDialog {

    DeviceInfoService deviceInfoService = new DeviceInfoServiceImpl();
    DeviceOrderService deviceOrderService = new DeviceOrderServiceImpl();
    DeviceParamService deviceParamService = new DeviceParamServiceImpl();
    DeviceParamValueService deviceParamValueService = new DeviceParamValueServiceImpl();

    ParamOptionService paramOptionService = new ParamOptionServiceImpl();
    MainFrame mainFrame;

    public ParamSetFrame(MainFrame owner, boolean modal) {
        super(owner, modal);

        mainFrame = owner;

        initComponents();
        List<DeviceInfo> allDeviceInfo = null;
        try {
            allDeviceInfo = deviceInfoService.getAllDeviceInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Vector<DeviceInfo> vector = new Vector<>(allDeviceInfo);
        this.devComboBox.setModel(new DefaultComboBoxModel(vector));

        bindDeviceParam();
    }

    public ParamSetFrame() {


    }

    private void bindDeviceParam() {
        try {
            DeviceInfo deviceInfo = (DeviceInfo) this.devComboBox.getSelectedItem();

            List<DeviceParam> allDeviceParam = deviceParamService.getAllDeviceParam();

            List<DeviceParamValue> deviceParamValues = deviceParamValueService.getDeviceParamValueByDeviceID(deviceInfo.getDeviceID());

            List<ParamOption> allParamOption = paramOptionService.getAllParamOption();

            DefaultTableModel model = (DefaultTableModel) this.paramTable.getModel();
            model.getDataVector().removeAllElements();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (DeviceParam deviceParam : allDeviceParam) {
                Vector<Object> objects = new Vector<>();
                objects.add(deviceParam.getParamIndex());
                objects.add(deviceParam.getRegisterAddress());
                objects.add(deviceParam.getParamName());

                List<DeviceParamValue> collect = deviceParamValues.stream().filter(o -> o.getParamIndex().equals(deviceParam.getParamIndex())).collect(Collectors.toList());
                if (collect.size() > 0) {
                    objects.add(simpleDateFormat.format(collect.get(0).getUpdateTime()));
                    if (deviceParam.getParamType().startsWith("Radio")) {
                        String optionValue = collect.get(0).getParamValue() + "";
                        switch (deviceParam.getParamType()) {
                            case "Radio_Type":
                            case "Radio_Code":
                            case "Radio_Station":
                                optionValue = Integer.toHexString(Integer.parseInt(collect.get(0).getParamValue())).toUpperCase();
                                break;
                            default:
                                break;
                        }
                        final String value = optionValue;
                        List<ParamOption> paramOptions = allParamOption.stream().filter(o -> o.getParamTypeID().equals(deviceParam.getParamType()) && o.getOptionValue().equals(value)).collect(Collectors.toList());
                        if (paramOptions.size() > 0) {
                            objects.add(paramOptions.get(0));
                        } else {
                            objects.add(null);
                        }
                    } else {
                        objects.add(collect.get(0).getParamValue());
                    }

                } else {
                    objects.add(null);//参数时间
                    objects.add(null);//参数数值
                }
                model.addRow(objects);
            }


            this.paramTable.getColumnModel().getColumn(4).setCellEditor(new ParamSetTableCellEditor(allDeviceParam, deviceParamValues, allParamOption));

        } catch (SQLException e) {

        }

    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
    }

    private void btnAddOrder(ActionEvent e) {
        // TODO add your code here

        DefaultTableModel model = (DefaultTableModel) this.paramTable.getModel();
        int rowCount = model.getRowCount();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            Object value = model.getValueAt(i, 3);
            if (value != null && !value.equals("")) {
                Integer registerAddress = (Integer) model.getValueAt(i, 1);
                stringBuilder.append(StringUtils.leftPad(Integer.toHexString(registerAddress), 4, '0').toUpperCase()).append("=").append(value).append(";");
            }
        }

        if (stringBuilder.length() == 0) {
            JOptionPane.showMessageDialog(this, "请先设置参数", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DeviceInfo deviceInfo = (DeviceInfo) this.devComboBox.getSelectedItem();


        DeviceOrder deviceOrder = new DeviceOrder();
        deviceOrder.setDeviceID(deviceInfo.getDeviceID());
        deviceOrder.setOrderName("参数设置");
        deviceOrder.setOrderCode("74");
        deviceOrder.setOrderType("1");
        deviceOrder.setDistributeTime(new Date());
        deviceOrder.setOrderState(0);
        deviceOrder.setParameter(stringBuilder.toString());
        deviceOrder.setRemark("指令添加成功");


        try {
            deviceOrderService.addDeviceOrder(deviceOrder);
            mainFrame.refreshOrderTable(deviceOrder);
        } catch (SQLException ex) {
            //throw new RuntimeException(ex);
        }
        this.dispose();

    }

    private void devComboBoxItemStateChanged(ItemEvent e) {
        // TODO add your code here
        bindDeviceParam();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        devComboBox = new JComboBox();
        scrollPane1 = new JScrollPane();
        paramTable = new JTable();
        panel3 = new JPanel();
        btnAddOrder = new JButton();

        //======== this ========
        setTitle("\u53c2\u6570\u8bbe\u7f6e");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- label1 ----
                label1.setText("\u8bbe\u5907\u540d\u79f0");
                panel2.add(label1);

                //---- devComboBox ----
                devComboBox.setPreferredSize(new Dimension(250, 30));
                devComboBox.addItemListener(e -> devComboBoxItemStateChanged(e));
                panel2.add(devComboBox);
            }
            panel1.add(panel2);

            //======== scrollPane1 ========
            {
                scrollPane1.setBorder(new TitledBorder("\u53c2\u6570\u5217\u8868"));

                //---- paramTable ----
                paramTable.setPreferredScrollableViewportSize(new Dimension(750, 400));
                paramTable.setGridColor(new Color(255, 153, 153));
                paramTable.setDragEnabled(true);
                paramTable.setFocusable(false);
                paramTable.setModel(new DefaultTableModel(
                        new Object[][]{
                        },
                        new String[]{
                                "\u5e8f\u53f7", "\u5bc4\u5b58\u5668\u5730\u5740", "\u53c2\u6570\u540d\u79f0", "\u4e0a\u6b21\u67e5\u8be2\u65f6\u95f4", "\u53c2\u6570\u503c"
                        }
                ) {
                    boolean[] columnEditable = new boolean[]{
                            false, false, false, true, true
                    };

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                {
                    TableColumnModel cm = paramTable.getColumnModel();
                    cm.getColumn(0).setMaxWidth(45);
                    cm.getColumn(0).setPreferredWidth(45);
                    cm.getColumn(1).setMaxWidth(80);
                    cm.getColumn(1).setPreferredWidth(80);
                }
                scrollPane1.setViewportView(paramTable);
            }
            panel1.add(scrollPane1);

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- btnAddOrder ----
                btnAddOrder.setText("\u65b0\u589e");
                btnAddOrder.addActionListener(e -> btnAddOrder(e));
                panel3.add(btnAddOrder);
            }
            panel1.add(panel3);
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JComboBox devComboBox;
    private JScrollPane scrollPane1;
    private JTable paramTable;
    private JPanel panel3;
    private JButton btnAddOrder;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
