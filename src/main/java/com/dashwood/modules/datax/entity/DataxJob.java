package com.dashwood.modules.datax.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * Datax执行类
 * @author 陈喜骋
 */
@Data
public class DataxJob implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fileName; // 文件名称
    private String readConf; // 源端配置json
    private String writeConf; // 目的端配置json
    private String settingConf; // datax执行参数 json
}
