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
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}



