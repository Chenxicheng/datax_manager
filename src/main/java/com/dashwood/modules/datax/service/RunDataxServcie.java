package com.dashwood.modules.datax.service;

/**
 * 执行datax
 */
public interface RunDataxServcie {
    /**
     * 执行datax
     * @param fileName 文件名称
     * @param readConf 源端配置
     * @param writeConf 目的端配置
     * @param settingConf 设置信息配置
     */
    void run(String fileName, String readConf, String writeConf, String settingConf);

    void test();
}
