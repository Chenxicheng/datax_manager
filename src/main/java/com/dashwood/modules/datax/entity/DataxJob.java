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

    private String fileName;
    private String readConf;
    private String writeConf;
    private String settingConf;
}
