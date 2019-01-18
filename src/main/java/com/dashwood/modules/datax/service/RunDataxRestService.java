package com.dashwood.modules.datax.service;


import com.alibaba.fastjson.JSONObject;
import com.dashwood.modules.datax.entity.DataxJob;

public interface RunDataxRestService {

    JSONObject run(DataxJob dataxJob);

}
