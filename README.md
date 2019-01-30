# datax_manager

基于springboot搭建的分布式执行datax系统，该系统提供远程调用
datax使用接口和返回结果。提供dubbo调用和rest调用两种方式。

## 系统使用

- 下载后，将系统目录放在datax目录下
    ```
    eg: D:\datax\datax_manager
    ```
- *resources/application.yml* 中进行配置
    ```
    server:
      port: 8078 # 系统端口
    dubbo: 
      registry:
        address: zookeeper:// 你的zookeeper 地址
      port: 30001 # 注册dubbo端口
    ```
- *resources/jar/dataxManager.jar* 导入到你的系统中。

- 提供Dubbo服务，执行datax

    你的系统若使用提供dubbo服务，在你的系统的dubbo配置文件中加入：
    ```
    <dubbo:reference interface="com.dashwood.modules.datax.service.RunDataxRestService" id="runDataxRestService" check="false" timeout="15000"/>
    ```
    即可消费dubbo服务
    返回结果为JsonObject类型
    ```
    返回参数：
        status: boolean true 成功 false 失败
        成功：
            message: string 信息
        失败：result: 
                hasException: boolean false 有错误异常
                errorMsg: string 错误异常信息   
    ```

- Dubbo返回Datax执行结果
    你的系统若获得datax执行结果，实现*dataxManager.jar*中*DataxResultService*接口
    ```
    eg:
    @Service("dataxResultService")
    public class DataxResultServiceImpl implements DataxResultService {
        @Override
        public void getDataxResult(DataXJobCounter dataXJobCounter) {...}
    }
    ```
    在你的系统dubbo配置文件中加入：
    ```
    <dubbo:service timeout="20000" interface="com.dashwood.modules.datax.service.DataxResultService" ref="dataxResultService"/>
    ```
    即可提供dubbo服务，获取datax返回结果
- Rest 接口，执行datax
    系统启动后，执行datax Rest接口路径：
    ```
    http://你的IP:8078/datax/run
    POST 请求
    请求参数：
        fileName: string
        readConf: string JSON字符串
        writeConf: string JSON字符串
        settingConf: string JSON字符串
    返回参数：
        status: boolean true 成功 false 失败
        成功：
            message: string 信息
        失败：result: 
                hasException: boolean false 有错误异常
                errorMsg: string 错误异常信息
    ```
 - 执行datax实体类 *DataxJob*
    
    ```
    private String fileName; // 文件名称
    private String readConf; // 源端配置json
    private String writeConf; // 目的端配置json
    private String settingConf; // datax执行参数 json    
    ```
    相关datax json配置参见datax技术说明
- datax执行结果实体类 *DataXJobCounter*
    ```
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
    ``` 
- 部署生产环境
    
    - 生成jar包：在系统目录下执行maven命令 *mvn clean package*
    - 生成的jar在系统目录的target目录中 *datax_manager 0.0.1-SNAPSHOT.jar*
    - 在生产环境下的datax目录下，创建任意名称的目录，将jar放入其中
    - 生产环境运行jar *java -jar datax_manager 0.0.1-SNAPSHOT.jar*
    
## 相关技术
- [Datax](https://github.com/alibaba/DataX)
- [Dubbo](https://github.com/Chenxicheng/datax_manager/blob/master/study/Dubbo.md)
- [zookeeper](https://github.com/Chenxicheng/datax_manager/blob/master/study/Zookeeper.md)
- [maven](https://github.com/Chenxicheng/datax_manager/blob/master/study/Maven.md)



