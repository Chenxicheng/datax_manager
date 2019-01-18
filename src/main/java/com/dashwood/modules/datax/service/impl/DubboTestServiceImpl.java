package com.dashwood.modules.datax.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.dashwood.modules.datax.entity.User;
import com.dashwood.modules.datax.service.DubboTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("dubbo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML})
@Slf4j
public class DubboTestServiceImpl implements DubboTestService {
    @Override
    @POST
    @Path("test")
    public JSONObject test(@RequestBody User user) {
        log.info(user.getUsername() + "===" + user.getPassword());
        JSONObject json = new JSONObject();
        json.put("message", "success");
        return json;
    }

}
