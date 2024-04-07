package com.wxmf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.error("error 错误信息，不会影响系统运行");
        logger.warn("warn 警告信息,可能会发生问题");
        logger.info("info 运行信息，数据连接、网络连接、I0操作等等");
        logger.debug("debug 调试信息，一般在开发中使用，记录程序变量参数传递信息等等");
        logger.trace("trace 追踪信息，记录程序所有的流程信息");
    }
}