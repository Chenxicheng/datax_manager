package com.dashwood.modules.datax.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.dashwood.commen.utils.JsonFileUtils;
import com.dashwood.modules.datax.entity.DataXJobCounter;
import com.dashwood.modules.datax.entity.DataxConfig;
import com.dashwood.modules.datax.entity.DataxJob;
import com.dashwood.modules.datax.service.DataxTaskService;
import com.dashwood.modules.datax.service.RunDataxRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


//@Path("datax")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML})
@Slf4j
@Service
public class RunDataxRestServiceImpl implements RunDataxRestService {
    @Autowired
    private DataxConfig dataxConfig;
    @Autowired
    private DataxTaskService dataxTaskService;

//    @POST
//    @Path("run")
    @Override
    public JSONObject run(DataxJob dataxJob) {
        JSONObject resultJSON = new JSONObject();
        String fileName = dataxJob.getFileName();
        String filePath = dataxConfig.getJsonpath() + fileName;
        DataXJobCounter dataXJobCounter = new DataXJobCounter();
        if (!JsonFileUtils.verificateJsonFileExit(filePath)) {
            try {
                JsonFileUtils.generateJsonFile(filePath, dataxJob.getReadConf(), dataxJob.getWriteConf(), dataxJob.getSettingConf());
                dataxTaskService.doTask(fileName, filePath);
            } catch (Exception e) {
                log.error(e.getMessage());
                dataXJobCounter.setHasException(true);
                dataXJobCounter.setErrorMsg(e.getMessage());
                resultJSON.put("status", false);
                resultJSON.put("result", dataXJobCounter);
                return resultJSON;
            }
        }
        resultJSON.put("status", true);
        resultJSON.put("message", "正在执行");
        return resultJSON;
    }
}
