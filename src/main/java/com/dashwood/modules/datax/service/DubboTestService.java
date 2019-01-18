package com.dashwood.modules.datax.service;


import com.alibaba.fastjson.JSONObject;
import com.dashwood.modules.datax.entity.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public interface DubboTestService {

    JSONObject test(User user);

}
