package com.dashwood.modules.datax.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dashwood.commen.utils.JsonFileUtils;
import com.dashwood.modules.datax.croe.DataxEnginService;
import com.dashwood.modules.datax.entity.DataXJobCounter;
import com.dashwood.modules.datax.service.DataxResultService;
import com.dashwood.modules.datax.service.DataxTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * datax异步执行Service
 * @author 陈喜骋
 */
@Service
@Slf4j
public class DataxTaskServiceImpl implements DataxTaskService {
    @Autowired
    private DataxEnginService dataxEnginService;

    @Autowired
    private DataxResultService dataxResultService; // 平台提供dubbo接口

    @Override
    @Async("taskExecutor")
    public void doTask(String fileName, String filePath) {
        log.info("======================  datax执行开始 ==========================");
        DataXJobCounter dataXJobCounter = new DataXJobCounter();
        dataXJobCounter = dataxEnginService.execute(fileName);
        JsonFileUtils.deleteJsonFile(filePath);
        String json = JSONObject.toJSONString(dataXJobCounter);
        log.info("======================  datax执行完毕 ==========================");
        log.info(json);
        dataxResultService.getDataxResult(dataXJobCounter);
    }

}
