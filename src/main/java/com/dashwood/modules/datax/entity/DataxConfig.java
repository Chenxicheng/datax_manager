package com.dashwood.modules.datax.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * datax插件路径配置解析
 * @author 陈喜骋
 */
@Data
//@Component
//@ConfigurationProperties(prefix = "datax")
public class DataxConfig {
    private String binpath;
    private String jsonpath;
}
