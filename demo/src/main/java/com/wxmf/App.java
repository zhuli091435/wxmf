package com.wxmf;

import com.fazecast.jSerialComm.SerialPort;
import com.wxmf.dao.impl.PackageVersionDaoImpl;
import com.wxmf.pojo.PackageVersion;

import java.io.File;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //
        //System.out.println( "Hello World!" );

        SerialPort[] serialPorts = SerialPort.getCommPorts();//查找所有串口
        for(SerialPort port:serialPorts){
            System.out.println("Port:"+port.getSystemPortName());//打印串口名称，如COM4
            System.out.println("PortDesc:"+port.getPortDescription());//打印串口类型，如USB Serial
            System.out.println("PortDesc:"+port.getDescriptivePortName());//打印串口的完整类型，如USB-SERIAL CH340(COM4)
        }
        SerialPort serialPort = serialPorts[0];//获取到第一个串口
        serialPort.setBaudRate(112500);//设置波特率为112500
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 1000, 1000);//设置超时
        serialPort.serRTS();//设置RTS。也可以设置DTR
        serialPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);//设置串口的控制流，可以设置为disabled，或者CTS, RTS/CTS, DSR, DTR/DSR, Xon, Xoff, Xon/Xoff等
        serialPort.setComPortParameters(112500, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);//一次性设置所有的串口参数，第一个参数为波特率，默认9600；第二个参数为每一位的大小，默认8，可以输入5到8之间的值；第三个参数为停止位大小，只接受内置常量，可以选择(ONE_STOP_BIT, ONE_POINT_FIVE_STOP_BITS, TWO_STOP_BITS)；第四位为校验位，同样只接受内置常量，可以选择 NO_PARITY, EVEN_PARITY, ODD_PARITY, MARK_PARITY,SPACE_PARITY。
        if(!serialPort.isOpen){
            boolean isCommOpeded = serialPort.openPort()//判断串口是否打开，如果没打开，就打开串口。打开串口的函数会返回一个boolean值，用于表明串口是否成功打开了
        }
        serialPort.closePort();//关闭串口。该函数同样会返回一个boolean值，表明串口是否成功关闭

    }
}
