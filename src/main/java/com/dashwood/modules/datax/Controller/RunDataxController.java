package com.dashwood.modules.datax.Controller;

import com.alibaba.fastjson.JSONObject;
import com.dashwood.modules.datax.service.RunDataxServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="datax")
public class RunDataxController {

    @Autowired
    private RunDataxServcie runDataxServcie;

    /**
     * 执行datax
     * @param fileName 文件名称
     * @param readConf 源端配置
     * @param writeConf 目的端配置
     * @param settingConf 设置信息配置
     * @return
     */
    @RequestMapping(value = "run", method = RequestMethod.POST)
    public JSONObject run(String fileName, String readConf, String writeConf, String settingConf) {
        JSONObject json = new JSONObject();
        runDataxServcie.run(fileName, readConf, writeConf, settingConf);
        json.put("status", true);
        json.put("message", "success");
        return json;
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public JSONObject test() {
        JSONObject json = new JSONObject();
        runDataxServcie.test();
        json.put("status", true);
        json.put("message", "success");
        return json;
    }

}
