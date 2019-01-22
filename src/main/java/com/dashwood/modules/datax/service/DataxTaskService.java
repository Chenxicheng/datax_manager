package com.dashwood.modules.datax.service;

/**
 * datax任务异步执行service
 * @author 陈喜骋
 */
public interface DataxTaskService {

    void doTask(String fileName, String filePath);

}
