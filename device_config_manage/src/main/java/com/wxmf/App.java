package com.wxmf;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.wxmf.pojo.DeviceOrder;
import com.wxmf.ui.CheckListCellRenderer;
import com.wxmf.ui.CheckValue;
import com.wxmf.ui.MainFrame;
import com.wxmf.ui.MyComboBox;
import com.wxmf.utils.CommonUil;
import com.wxmf.utils.WXMFProtocol;
import com.wxmf.utils.WXMFProtocolUtil;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;
import static jdk.nashorn.internal.objects.NativeMath.cos;
import static jdk.nashorn.internal.objects.NativeMath.sin;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {

//        String hexString = "414E6666"; // 以IEEE 754标准表示的十六进制浮点数
//        int intValue = Integer.parseInt(hexString, 16); // 将十六进制字符串转换为十进制整数
//        float floatValue = Float.intBitsToFloat(intValue); // 将整数转换为浮点数
//        System.out.println("Hex string: " + hexString);
//        System.out.println("Float value: " + floatValue);

//        Integer i = Integer.parseInt("A7402980", 16);
        //Long l = Long.parseLong("A7402980", 16);

        //UIManager.setLookAndFeel(new FlatIntelliJLaf());
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        //String msg = "7E7E000001000002000032002602461C240407105802F1F100010000025AF0F02404071058091BFFFFFFA120000624C83812122103F5EB";
        //WXMFProtocol wxmfProtocol = WXMFProtocolUtil.createWXMFProtocol(CommonUil.hexToByteArray(msg));
    }


}



