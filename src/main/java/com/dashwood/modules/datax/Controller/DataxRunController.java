package com.dashwood.modules.datax.Controller;

import com.alibaba.fastjson.JSONObject;
import com.dashwood.modules.datax.entity.DataxJob;
import com.dashwood.modules.datax.service.RunDataxRestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * datax rest请求 Controller
 * @author 陈喜骋
 */
@RestController
@RequestMapping("datax")
public class DataxRunController {

    private RunDataxRestService runDataxRestService;

    /**
     * 运行datax
     * @param dataxJob
     * @return
     */
    @RequestMapping(value="run", method = RequestMethod.POST)
    public JSONObject run (DataxJob dataxJob) {
        JSONObject jsonObject = runDataxRestService.run(dataxJob);
        return jsonObject;
    }

}
