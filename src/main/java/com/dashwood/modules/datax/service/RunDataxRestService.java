package com.dashwood.modules.datax.service;


import com.alibaba.fastjson.JSONObject;
import com.dashwood.modules.datax.entity.DataxJob;

/**
 * 执行datax Service
 * 对外提供dubbo接口
 * @author 陈喜骋
 */
public interface RunDataxRestService {

    /**
     * 执行方法
     * @param dataxJob
     * @return
     */
    JSONObject run(DataxJob dataxJob);

}
