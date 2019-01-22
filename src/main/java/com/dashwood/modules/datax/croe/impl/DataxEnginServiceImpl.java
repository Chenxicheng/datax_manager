package com.dashwood.modules.datax.croe.impl;


import com.dashwood.modules.datax.croe.DataxEnginService;
import com.dashwood.modules.datax.entity.DataXJobCounter;
import com.dashwood.modules.datax.entity.DataxConfig;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

/**
 * DataxEnginService实现类
 * @author 陈喜骋
 *
 */
@Service("dataxEnginService")
@Slf4j
public class DataxEnginServiceImpl implements DataxEnginService {
    private String sajccLine; // StandAloneJobContainerCommunicator 统计信息行
    private String exceptionLine;
    @Autowired
    private DataxConfig dataxConfig;
  
    @Override
    public DataXJobCounter execute(String fileName){

        Process process = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String line = null;
        DataXJobCounter dataXJobCounter = new DataXJobCounter();
        dataXJobCounter.setStatus(DataXJobCounter.RUNNING);
        try {
//            String cmd = "python "+DATAX_PATH+" "+ JobJsonGenerator.JOB_JSON_PATH+fileName+".json";
//            String cmd = String.format("python %s %s.json", dataxConfig.getBinPath(), dataxConfig.getJsonPath());
//            System.out.println(cmd);
//            log.info(cmd);p
            process = new ProcessBuilder("python", dataxConfig.getBinpath(), dataxConfig.getJsonpath()+fileName+".json").start();//Runtime.getRuntime().exec(cmd);
            isr = new InputStreamReader(process.getInputStream(), Charsets.UTF_8);
            br = new BufferedReader(isr);
            BufferedReader reader = br;
            boolean flag = false;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("completed successfully")) {
                    flag = true;
                }
                if (line != null && line.contains("DataXException")) {
                    if (line.contains("Description:")) {
                        dataXJobCounter.setHasException(DataXJobCounter.HAVE_EXCEPTION);
                        this.exceptionLine = line;
                    }
                }
                if (line != null && line.contains("HdfsWriter$Job")) {
                    if (line.contains("finish rename file")) {
                        dataXJobCounter.setHdfsFileName(getHdfsFileName(line));
                    }
                }
                if (flag) {
                    readExecuteOutputLine(line, dataXJobCounter);
                }
            }
            if (dataXJobCounter.isHasException()) {
                dataXJobCounter.setErrorMsg(getErrorMsg(this.exceptionLine));
            }
        } catch (IOException e) {
            dataXJobCounter.setHasException(true);
            dataXJobCounter.setErrorMsg(e.getMessage());
        } finally {
            if (process!=null) {
                process.destroy();
            }
            if (isr != null) {
                try {
                    isr.close();
                    br.close();
                } catch (IOException e) {
                    dataXJobCounter.setHasException(true);
                    dataXJobCounter.setErrorMsg(e.getMessage());
                }
            }
        }
        dataXJobCounter.setFileName(fileName);
        dataXJobCounter.setStatus(DataXJobCounter.COMPLETE);
        return dataXJobCounter;
    }
    
    /**
     * 暂不使用
     */
    @Deprecated
    @Override
    public void executeJar(String jobJson, String jobId) {
    	Process process = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String line = null;
        try {
            process = new ProcessBuilder().command("java","-Djava.ext.dirs=D:\\workspace\\d2d\\lib -classpath D:\\workspace\\d2d\\bin com.alibaba.datax.core.Main", jobJson).start();
            //process = Runtime.getRuntime().exec("java -Djava.ext.dirs=D:\\workspace\\d2d\\lib -classpath D:\\workspace\\d2d\\bin com.alibaba.datax.core.Main "+jobJson);//new ProcessBuilder("java",  "-Djava.ext.dirs=D:\\workspace\\d2d\lib", "", jobJson, jobId).start();//Runtime.getRuntime().exec(cmd);
            isr = new InputStreamReader(process.getInputStream());
            br = new BufferedReader(isr);
            BufferedReader reader = br;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                //readExecueOutputLineStatus(line, dataxJobStatus);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (process!=null) {
                process.destroy();
            }
        }
    }

    /**
     * 读取输出日志行，获得dataX任务执行完成后的统计结果
     * @param line 输出日志信息
     * @param dataXJobCounter DataXJobCounter
     */
    private void readExecuteOutputLine(String line, DataXJobCounter dataXJobCounter) {
        if (line.contains("StandAloneJobContainerCommunicator")) {
            this.sajccLine = line;
        }
        if (line.contains("任务启动时刻")) {
            dataXJobCounter.setTaskStartTimeStamp(line.substring(line.indexOf(":")+1, line.length()).trim());
        } else if (line.contains("任务结束时刻")) {
            dataXJobCounter.setTaskEndTimeStamp(line.substring(line.indexOf(":")+1, line.length()).trim());
        } else if (line.contains("任务总计耗时")) {
            dataXJobCounter.setTaskTotalCosts(line.substring(line.indexOf(":")+1, line.length()).trim());
        } else if (line.contains("任务平均流量")) {
            dataXJobCounter.setTaskSpeedPerSecond(line.substring(line.indexOf(":")+1, line.length()).trim());
        } else if (line.contains("记录写入速度")) {
            dataXJobCounter.setTaskWriterSpeedPerSecond(line.substring(line.indexOf(":")+1, line.length()).trim());
        } else if (line.contains("读出记录总数")) {
            dataXJobCounter.setTaskTotalReadRecords(line.substring(line.indexOf(":")+1, line.length()).trim());
        } else if (line.contains("读写失败总数")) {
            dataXJobCounter.setTaskTotalErrorRecords(line.substring(line.indexOf(":") + 1, line.length()).trim());
            if (this.sajccLine != null && !this.sajccLine.isEmpty()) {
            	 List<String> list = Splitter.on("|").omitEmptyStrings().trimResults().splitToList(this.sajccLine);
                 String dataSize = list.get(0);
                 String waitWriter = list.get(3);
                 String waitReader = list.get(4);
                 dataXJobCounter.setTaskTotalDataSize(dataSize.substring(dataSize.indexOf("records,") + 8, dataSize.indexOf("bytes")).trim());
                 dataXJobCounter.setTaskWaitWriterTime(waitWriter.substring(waitWriter.indexOf("WaitWriterTime") + 14, waitWriter.length()).trim());
                 dataXJobCounter.setTaskWaitReaderTime(waitReader.substring(waitReader.indexOf("WaitReaderTime") + 14, waitReader.length()).trim());
            }
           
        }

    }
    
    /**
     * 获取错误信息
     * @param line
     * @return
     */
    private String getErrorMsg(String line) {
        String errorMsg = line.substring(line.indexOf("Description:")+12, line.length());
        errorMsg = errorMsg.replace("DataX", "");
        return errorMsg;
    }
    
    /**
     * 获取落得hdfs文件名称
     * @param line
     * @return
     */
    private String getHdfsFileName (String line) {
    		String[] array = line.split("/");
		String lastString = array[array.length-1];
		return lastString.substring(0, lastString.indexOf("]"));
    		
    }
    
    @Deprecated
    private String bytes2kb(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        if (returnValue > 1)
            return (returnValue + "MB");
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return (returnValue + "KB");
    }

    public static void main(String[] args) {
//        DataxEnginService dataxEnginService = new DataxEnginServiceImpl();
//        String s = "{\"job\": {\"content\": [{\"reader\": {\"name\":\"mysqlreader\",\"parameter\":{\"column\":[\"id\",\"create_time\",\"modify_time\",\"title\",\"author\",\"click\",\"content\",\"deleted\",\"status\",\"summary\",\"top\",\"topic\",\"type\",\"category\",\"user\"],\"connection\":[{\"jdbcUrl\":[\"jdbc:mysql://192.168.30.10:3306/datax\"],\"table\":[\"article\"]}],\"password\":\"password\",\"username\":\"root\"}},\"writer\": {\"name\":\"mysqlwriter\",\"parameter\":{\"column\":[\"id\",\"create_time\",\"modify_time\",\"title\",\"author\",\"click\",\"content\",\"deleted\",\"status\",\"summary\",\"top\",\"topic\",\"type\",\"category\",\"user\"],\"connection\":[{\"jdbcUrl\":\"jdbc:mysql://192.168.30.10:3306/data_x_1538_0\",\"table\":[\"article\"]}],\"password\":\"password\",\"postSql\":[],\"preSql\":[],\"session\":[],\"username\":\"root\",\"writeMode\":\"replace\"}}}],\"setting\": {\"speed\":{\"channel\":\"5\"},\"errorLimit\":{\"record\":0}} }}";
//        dataxEnginService.executeJar(s, Thread.currentThread().getId()+"");
        new Thread(()->{
            DataxEnginService dataxEnginService = new DataxEnginServiceImpl();
            DataXJobCounter dataxJobStatus = dataxEnginService.execute("6a226fa870cf4041a05b8f4ab12a89cc");
            System.out.println(dataxJobStatus);
        }).start();

        new Thread(()->{
            DataxEnginService dataxEnginService = new DataxEnginServiceImpl();
            DataXJobCounter dataxJobStatus = dataxEnginService.execute("7ed406bba87e4497a42cdf7ecd54a5b0");
            System.out.println(dataxJobStatus);
        }).start();
    }


}
