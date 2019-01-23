package com.dashwood.modules.datax.entity;


import java.io.Serializable;

/**
 * DataX执行结果统计
 * 实体类
 * @author 陈喜骋
 */
public class DataXJobCounter implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 正在运行 0
     */
    public static final String RUNNING = "0"; // 正在运行
    /**
     * 完成 1
     */
    public static final String COMPLETE = "1"; // 完成

    /**
     * 执行异常 true
     */
    public static final boolean HAVE_EXCEPTION = true;
    /**
     * 执行正常 false
     */
    public static final boolean NO_HAVE_EXCEPTION = false;

    private String fileName; // 生成json文件名称
    private String status; // 状态
    private String taskStartTimeStamp; // 任务启动时刻
    private String taskEndTimeStamp; // 任务结束时刻
    private String taskSpeedPerSecond; // 任务平均流量
    private String taskWriterSpeedPerSecond; // 任务写入速度
    private String taskTotalCosts; // 任务总计耗时
    private String taskTotalReadRecords; // 读出记录总数
    private String taskTotalErrorRecords; // 错误记录总数
    private String taskWaitWriterTime; // 写入延迟
    private String taskWaitReaderTime; // 读出延迟
    private String taskTotalDataSize; // 数据大小 单位bytes
    private boolean hasException; // 是否有异常错误
    private String errorMsg; // 异常错误信息
    private String hdfsFileName; // hdfs文件名称

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskStartTimeStamp() {
        return taskStartTimeStamp;
    }

    public void setTaskStartTimeStamp(String taskStartTimeStamp) {
        this.taskStartTimeStamp = taskStartTimeStamp;
    }

    public String getTaskEndTimeStamp() {
        return taskEndTimeStamp;
    }

    public void setTaskEndTimeStamp(String taskEndTimeStamp) {
        this.taskEndTimeStamp = taskEndTimeStamp;
    }

    public String getTaskSpeedPerSecond() {
        return taskSpeedPerSecond;
    }

    public void setTaskSpeedPerSecond(String taskSpeedPerSecond) {
        this.taskSpeedPerSecond = taskSpeedPerSecond;
    }

    public String getTaskWriterSpeedPerSecond() {
        return taskWriterSpeedPerSecond;
    }

    public void setTaskWriterSpeedPerSecond(String taskWriterSpeedPerSecond) {
        this.taskWriterSpeedPerSecond = taskWriterSpeedPerSecond;
    }

    public String getTaskTotalCosts() {
        return taskTotalCosts;
    }

    public void setTaskTotalCosts(String taskTotalCosts) {
        this.taskTotalCosts = taskTotalCosts;
    }

    public String getTaskTotalReadRecords() {
        return taskTotalReadRecords;
    }

    public void setTaskTotalReadRecords(String taskTotalReadRecords) {
        this.taskTotalReadRecords = taskTotalReadRecords;
    }

    public String getTaskTotalErrorRecords() {
        return taskTotalErrorRecords;
    }

    public void setTaskTotalErrorRecords(String taskTotalErrorRecords) {
        this.taskTotalErrorRecords = taskTotalErrorRecords;
    }

    public String getTaskWaitWriterTime() {
        return taskWaitWriterTime;
    }

    public void setTaskWaitWriterTime(String taskWaitWriterTime) {
        this.taskWaitWriterTime = taskWaitWriterTime;
    }

    public String getTaskWaitReaderTime() {
        return taskWaitReaderTime;
    }

    public void setTaskWaitReaderTime(String taskWaitReaderTime) {
        this.taskWaitReaderTime = taskWaitReaderTime;
    }

    public String getTaskTotalDataSize() {
        return taskTotalDataSize;
    }

    public void setTaskTotalDataSize(String taskTotalDataSize) {
        this.taskTotalDataSize = taskTotalDataSize;
    }

    public boolean isHasException() {
        return hasException;
    }

    public void setHasException(boolean hasException) {
        this.hasException = hasException;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "DataXJobCounter{" +
                "status='" + status + '\'' +
                ", taskStartTimeStamp='" + taskStartTimeStamp + '\'' +
                ", taskEndTimeStamp='" + taskEndTimeStamp + '\'' +
                ", taskSpeedPerSecond='" + taskSpeedPerSecond + '\'' +
                ", taskWriterSpeedPerSecond='" + taskWriterSpeedPerSecond + '\'' +
                ", taskTotalCosts='" + taskTotalCosts + '\'' +
                ", taskTotalReadRecords='" + taskTotalReadRecords + '\'' +
                ", taskTotalErrorRecords='" + taskTotalErrorRecords + '\'' +
                ", taskWaitWriterTime='" + taskWaitWriterTime + '\'' +
                ", taskWaitReaderTime='" + taskWaitReaderTime + '\'' +
                ", taskTotalDataSize='" + taskTotalDataSize + '\'' +
                ", hasException=" + hasException +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

	public String getHdfsFileName() {
		return hdfsFileName;
	}

	public void setHdfsFileName(String hdfsFileName) {
		this.hdfsFileName = hdfsFileName;
	}
}
