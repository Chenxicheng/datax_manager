package com.dashwood.modules.datax.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="send")
public class SendController {
    @Autowired
    private Sender sender;

    @RequestMapping("hello")
    public String send (String name) {
        sender.send(name);
        return "success";
    }

}
