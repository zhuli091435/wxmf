package com.wxmf.ui;

import com.wxmf.pojo.DeviceParam;
import com.wxmf.pojo.DeviceParamValue;
import com.wxmf.pojo.ParamOption;
import com.wxmf.service.DeviceParamService;
import com.wxmf.service.DeviceParamValueService;
import com.wxmf.service.ParamOptionService;
import com.wxmf.service.impl.DeviceParamServiceImpl;
import com.wxmf.service.impl.DeviceParamValueServiceImpl;
import com.wxmf.service.impl.ParamOptionServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class ParamSetTableCellEditor extends AbstractCellEditor implements TableCellEditor {


    DeviceParamService deviceParamService = new DeviceParamServiceImpl();

    DeviceParamValueService deviceParamValueService = new DeviceParamValueServiceImpl();

    ParamOptionService paramOptionService = new ParamOptionServiceImpl();
    private int row;

    List<Container> containers = new ArrayList<>();

    private void createWorkingModeComboBox() {
        //工作模式<0-3>【0：兼容/调试维修；1：自报/自报；2：查询/自报确认；3：调试/查询应答】
        Vector<String> stringVector = new Vector<String>();

        stringVector.add("兼容/调试维修");
        stringVector.add("自报/自报");
        stringVector.add("查询/自报确认");
        stringVector.add("调试/查询应答");
        JComboBox<String> stringJComboBox = new JComboBox<>(stringVector);
        stringJComboBox.setSelectedIndex(-1);
        containers.add(stringJComboBox);
    }

    private void createProtocolComboBox() {
        Vector<Object> objects = new Vector<>();
        objects.add("SZY206-2012 水资源规约");
        objects.add("SL 651-2014 水文规约");
        objects.add("水文旧协议");
        objects.add("精简协议");
        objects.add("水情专用协议");
        objects.add("水情通用协议");
        objects.add("SDXHL协议");
        objects.add("WH协议");
        objects.add("南水协议");
        objects.add("SL 427-2008 水资源规约");
        objects.add("重庆市水文监测数据通信规约");
        objects.add("四川水文规约 SCSW008-2011");
        objects.add("大坝协议");
        JComboBox<Object> objectJComboBox = new JComboBox<>(objects);
        objectJComboBox.setSelectedIndex(-1);
        //objectJComboBox.setRenderer(new CheckComboBoxRenderer());
        containers.add(objectJComboBox);
    }

    private void createRainStartTimeComboBox() {
        Vector<String> vector = new Vector<>();
        for (int i = 0; i < 24; i++) {
            vector.add(StringUtils.leftPad(i + "", 2, '0') + ":00");
        }
        JComboBox<String> stringJComboBox = new JComboBox<>(vector);
        stringJComboBox.setSelectedIndex(-1);
        containers.add(stringJComboBox);
    }

    public ParamSetTableCellEditor(List<DeviceParam> allDeviceParam, List<DeviceParamValue> deviceParamValues, List<ParamOption> allParamOption) {

        for (DeviceParam deviceParam : allDeviceParam) {


            if (deviceParam.getParamType().startsWith("Radio")) {


                List<ParamOption> paramOptions = allParamOption.stream().filter(o -> o.getParamTypeID().equals(deviceParam.getParamType())).collect(Collectors.toList());
                Vector<ParamOption> paramOptionVector = new Vector<>(paramOptions);
                JComboBox<ParamOption> paramOptionJComboBox = new JComboBox<>(paramOptionVector);
                List<DeviceParamValue> deviceParamValueList = deviceParamValues.stream().filter(o -> o.getParamIndex().equals(deviceParam.getParamIndex())).collect(Collectors.toList());
                if (deviceParamValueList.size() > 0) {
                    String optionValue = deviceParamValueList.get(0).getParamValue() + "";
                    switch (deviceParam.getParamType()) {
                        case "Radio_Type":
                        case "Radio_Code":
                        case "Radio_Station":
                            optionValue = Integer.toHexString(Integer.parseInt(deviceParamValueList.get(0).getParamValue())).toUpperCase();
                            break;
                        default:
                            break;
                    }
                    final String value = optionValue;
                    List<ParamOption> paramOptionList = paramOptions.stream().filter(o -> o.getOptionValue().equals(value)).collect(Collectors.toList());
                    if (paramOptionList.size() > 0) {
                        paramOptionJComboBox.setSelectedIndex(paramOptionList.get(0).getOptionIndex());
                    } else {
                        paramOptionJComboBox.setSelectedIndex(-1);
                    }

                } else {
                    paramOptionJComboBox.setSelectedIndex(-1);
                }

                //paramOptionJComboBox.setMaximumSize(new Dimension(100, 200));
                containers.add(paramOptionJComboBox);

            } else if (deviceParam.getParamType().startsWith("Check")) {


            } else {
                List<DeviceParamValue> collect = deviceParamValues.stream().filter(o -> o.getParamIndex().equals(deviceParam.getParamIndex())).collect(Collectors.toList());
                if (collect.size() > 0) {
                    containers.add(new JTextField(collect.get(0).getParamValue() + ""));
                } else {
                    containers.add(new JTextField());
                }
            }

//            if (deviceParam.getRegisterAddress() == 1) {
//                createRainStartTimeComboBox();
//            } else if (deviceParam.getRegisterAddress() == 3) {
//                //工作模式<0-3>【0：兼容/调试维修；1：自报/自报；2：查询/自报确认；3：调试/查询应答】
//                createWorkingModeComboBox();
//            } else if (deviceParam.getRegisterAddress() == 9) {
//                createProtocolComboBox();
//            } else {
//                containers.add(new JTextField(deviceParam.));
//            }
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        this.row = row;
        return this.containers.get(row);
    }

    @Override
    public Object getCellEditorValue() {

        Container container = this.containers.get(row);
        if (container instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) container;
            return comboBox.getSelectedItem();
        } else {
            JTextField jTextField = (JTextField) container;
            return jTextField.getText();
        }
    }
}
