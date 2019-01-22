package com.dashwood;

import com.dashwood.modules.datax.entity.DataxConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@ImportResource({"classpath:dubbo.xml"})
public class DataxManagerApplication {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(" ......................阿弥陀佛......................\n"+
                "                       _oo0oo_                      \n"+
                "                      o8888888o                     \n"+
                "                      88\" . \"88                     \n"+
                "                      (| -_- |)                     \n"+
                "                      0\\  =  /0                     \n"+
                "                   ___/‘---’\\___                   \n"+
                "                  .' \\|       |/ '.                 \n"+
                "                 / \\\\|||  :  |||// \\                \n"+
                "                / _||||| -卍-|||||_ \\               \n"+
                "               |   | \\\\\\  -  /// |   |              \n"+
                "               | \\_|  ''\\---/''  |_/ |              \n"+
                "               \\  .-\\__  '-'  ___/-. /              \n"+
                "             ___'. .'  /--.--\\  '. .'___            \n"+
                "         .\"\" ‘<  ‘.___\\_<|>_/___.’>’ \"\".          \n"+
                "       | | :  ‘- \\‘.;‘\\ _ /’;.’/ - ’ : | |        \n"+
                "         \\  \\ ‘_.   \\_ __\\ /__ _/   .-’ /  /        \n"+
                "    =====‘-.____‘.___ \\_____/___.-’___.-’=====     \n"+
                "                       ‘=---=’                      \n"+
                "                                                    \n"+
                "....................佛祖保佑 ,永无BUG...................");

        SpringApplication.run(DataxManagerApplication.class, args);
    }

    /**
     * 获取datax所在路径
     * 注入Bean
     * @return
     */
    @Bean
    public DataxConfig dataxConfig(){
        DataxConfig dataxConfig = new DataxConfig();
        String dir = System.getProperty("user.dir");
        String dataxDir = StringUtils.substringBeforeLast(dir, File.separator);
        dataxConfig.setBinpath(dataxDir+File.separator+"bin"+File.separator+"datax.py");
        dataxConfig.setJsonpath(dataxDir+File.separator+"job"+ File.separator);
        return dataxConfig;

    }

    /**
     * 配置线程池
     */
    @EnableAsync
    @Configuration
    class TaskPoolConfig {
        @Bean("taskExecutor")
        public Executor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(50);
            executor.setMaxPoolSize(100);
            executor.setQueueCapacity(200);
            executor.setKeepAliveSeconds(30000);
            executor.setThreadNamePrefix("taskExecutor-");
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return executor;
        }
    }
}

