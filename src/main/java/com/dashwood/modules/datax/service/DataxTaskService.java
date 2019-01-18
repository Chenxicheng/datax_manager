package com.dashwood.modules.datax.service;

/**
 * datax任务异步执行service
 */
public interface DataxTaskService {

    void doTask(String fileName, String filePath);

    void test();
}
