package com.dashwood.modules.datax.service.impl;

import com.dashwood.commen.utils.JsonFileUtils;
import com.dashwood.modules.datax.croe.DataxEnginService;
import com.dashwood.modules.datax.entity.DataXJobCounter;
import com.dashwood.modules.datax.entity.DataxConfig;
import com.dashwood.modules.datax.service.DataxTaskService;
import com.dashwood.modules.datax.service.RunDataxServcie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RunDataxServcieImpl implements RunDataxServcie {
    @Autowired
    private DataxConfig dataxConfig;
    @Autowired
    private DataxTaskService dataxTaskService;

    @Override
    public void run(String fileName, String readConf, String writeConf, String settingConf) {
        String filePath = dataxConfig.getJsonpath() + fileName;
        DataXJobCounter dataXJobCounter = new DataXJobCounter();
        if (!JsonFileUtils.verificateJsonFileExit(filePath)) {
            try {
                JsonFileUtils.generateJsonFile(filePath, readConf, writeConf, settingConf);
                dataxTaskService.doTask(fileName, filePath);
            } catch (Exception e) {
                e.printStackTrace();
                dataXJobCounter.setHasException(true);
                dataXJobCounter.setErrorMsg(e.getMessage());
            }
        }
    }

    @Override
    public void test() {
        dataxTaskService.test();
    }
}
