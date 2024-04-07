/*
 * Created by JFormDesigner on Fri Dec 29 09:20:42 CST 2023
 */

package com.wxmf.ui;

import com.wxmf.pojo.DeviceInfo;
import com.wxmf.pojo.DeviceOrder;
import com.wxmf.service.DeviceInfoService;
import com.wxmf.service.DeviceOrderService;
import com.wxmf.service.impl.DeviceInfoServiceImpl;
import com.wxmf.service.impl.DeviceOrderServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author Brainrain
 */
public class ParamSetAllFrame extends JDialog {

    DeviceInfoService deviceInfoService = new DeviceInfoServiceImpl();

    DeviceOrderService deviceOrderService = new DeviceOrderServiceImpl();

    MainFrame mainFrame;

    public ParamSetAllFrame(MainFrame owner) {
        super(owner, true);
        mainFrame = owner;
        initComponents();


        bindDeviceInfo();

//        for (int i = 0; i < 10; i++) {
//            CheckValue cValue = new CheckValue();
//            cValue.value = "测试_" + i;
//            if (i % 3 == 0) {
//                cValue.bolValue = true;
//            }
//            this.devComboBox.addItem(cValue);
//        }
//        this.devComboBox.setRenderer(new CheckListCellRenderer());
//        this.devComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
    }

    private void bindDeviceInfo() {
        try {
            List<DeviceInfo> allDeviceInfo = deviceInfoService.getAllDeviceInfo();
            Vector<DeviceInfo> vector = new Vector<>(allDeviceInfo);
            this.devComboBox.setModel(new DefaultComboBoxModel(vector));
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
    }

    private void btnAddOrder(ActionEvent e) {
        // TODO add your code here

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            DeviceInfo deviceInfo = (DeviceInfo) this.devComboBox.getSelectedItem();


            File file = jFileChooser.getSelectedFile();

            if (file == null) {
                JOptionPane.showMessageDialog(this, "请先上传参数文件", "参数文件", JOptionPane.WARNING_MESSAGE);
                return;
            }

            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String fileName = "importFile/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + deviceInfo.getDeviceID() + ".dat";
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
            //bufferedWriter

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();

            DeviceOrder deviceOrder = new DeviceOrder();
            deviceOrder.setDeviceID(deviceInfo.getDeviceID());
            deviceOrder.setOrderName("设置全部参数");
            deviceOrder.setOrderCode("72");
            deviceOrder.setOrderType("1");
            deviceOrder.setDistributeTime(new Date());
            deviceOrder.setOrderState(0);
            deviceOrder.setParameter(fileName);
            deviceOrder.setRemark("指令添加成功");
            deviceOrderService.addDeviceOrder(deviceOrder);
            mainFrame.refreshOrderTable(deviceOrder);

            this.dispose();

        } catch (IOException ex) {
            //throw new RuntimeException(ex);
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException exc) {
                    //throw new RuntimeException(exc);
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException exc) {
                    //throw new RuntimeException(exc);
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
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
        } catch (SQLException ex) {
            //throw new RuntimeException(ex);
            System.out.println(ex);
        }

    }

    private void btnImport(ActionEvent e) {
        // TODO add your code here

        int res = jFileChooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            this.txtFileName.setText(jFileChooser.getSelectedFile().getName());
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        devComboBox = new JComboBox();
        panel4 = new JPanel();
        label2 = new JLabel();
        txtFileName = new JTextField();
        btnImport = new JButton();
        panel3 = new JPanel();
        btnAddOrder = new JButton();

        //======== this ========
        setTitle("\u8bbe\u7f6e\u5168\u90e8\u53c2\u6570");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

            //======== panel2 ========
            {
                panel2.setPreferredSize(new Dimension(313, 40));
                panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- label1 ----
                label1.setText("\u8bbe\u5907\u540d\u79f0");
                panel2.add(label1);

                //---- devComboBox ----
                devComboBox.setPreferredSize(new Dimension(250, 30));
                panel2.add(devComboBox);
            }
            panel1.add(panel2);

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- label2 ----
                label2.setText("\u53c2\u6570\u6587\u4ef6");
                panel4.add(label2);

                //---- txtFileName ----
                txtFileName.setPreferredSize(new Dimension(250, 30));
                txtFileName.setEnabled(false);
                panel4.add(txtFileName);

                //---- btnImport ----
                btnImport.setText("\u4e0a\u4f20");
                btnImport.addActionListener(e -> btnImport(e));
                panel4.add(btnImport);
            }
            panel1.add(panel4);

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
    private JPanel panel4;
    private JLabel label2;
    private JTextField txtFileName;
    private JButton btnImport;
    private JPanel panel3;
    private JButton btnAddOrder;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    JFileChooser jFileChooser = new JFileChooser();
}
