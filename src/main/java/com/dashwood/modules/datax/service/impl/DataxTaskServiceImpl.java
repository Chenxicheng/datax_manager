package com.dashwood.modules.datax.service.impl;

import com.dashwood.commen.utils.JsonFileUtils;
import com.dashwood.modules.datax.croe.DataxEnginService;
import com.dashwood.modules.datax.entity.DataXJobCounter;
import com.dashwood.modules.datax.entity.DataxConfig;
import com.dashwood.modules.datax.service.DataxTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataxTaskServiceImpl implements DataxTaskService {
    @Autowired
    private DataxEnginService dataxEnginService;

    @Override
    @Async("taskExecutor")
    public void doTask(String fileName, String filePath) {
        DataXJobCounter dataXJobCounter = new DataXJobCounter();
        dataXJobCounter = dataxEnginService.execute(fileName);
        JsonFileUtils.deleteJsonFile(filePath);
    }

    @Async("taskExecutor")
    @Override
    public void test() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("================= 异步 ==================");
    }


}
