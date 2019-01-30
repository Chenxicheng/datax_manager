# Dubbo架构

![](http://dubbo.apache.org/docs/zh-cn/user/sources/images/dubbo-architecture.jpg)
 
- 节点角色说明：
    - Provider: 暴露服务的服务提供方。
    - Consumer: 调用远程服务的服务消费方。
    - Registry: 服务注册与发现的注册中心。
    - Monitor: 统计服务的调用次调和调用时间的监控中心。
    - Container: 服务运行容器。
- 调用关系说明：
    - 0、服务容器负责启动，加载，运行服务提供者。
    - 1、服务提供者在启动时，向注册中心注册自己提供的服务。
    - 2、服务消费者在启动时，向注册中心订阅自己所需的服务。
    - 3、注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
    - 4、服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
    - 5、服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。
### [Dubbo官方文档](http://dubbo.apache.org/zh-cn/docs/user/preface/)

### [Dubbox](https://github.com/dangdangdotcom/dubbox)

Dubbox 需导入在本地maven仓库中，才可在pom.xml引入依赖。

- 在github上下载压缩包，解压
    ```
    eg: D:\dubbox-master
    ```
- 打开cmd命令窗口，执行maven命令
    ```
    eg:
    mvn install -f D:\dubbox-master\pom.xml -D maven.test.skip=true
    ```
- 编译完成后，每个文件夹下，会生成对应的target文件，里面放着我们需要的jar包、war包等
    
    ![](https://github.com/Chenxicheng/datax_manager/blob/master/study/img/TIM%E6%88%AA%E5%9B%BE20190123150549.png?raw=true)
    
    ![](https://github.com/Chenxicheng/datax_manager/blob/master/study/img/TIM截图20190123150612.png?raw=true)
- 导入本地maven仓库中，执行maven命令：
    ```
    eg:
    mvn install:install-file -Dfile=D:\dubbox-master\dubbo\target\dubbo-2.8.4.jar -DgroupId=com.alibaba -DartifactId=dubbo -Dversion=2.8.4 -Dpackaging=jar -DgeneratePom=true
    ```
- 导入成功后，在本地maven仓库可进行查看
    ![](https://github.com/Chenxicheng/datax_manager/blob/master/study/img/TIM截图20190123151032.png?raw=true)
    
    ![](https://github.com/Chenxicheng/datax_manager/blob/master/study/img/TIM截图20190123151049.png?raw=true)
    
    ![](https://github.com/Chenxicheng/datax_manager/blob/master/study/img/TIM截图20190123151110.png?raw=true)
    
- 在系统pom.xml中进行依赖引入
    ```
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>dubbo</artifactId>
        <version>2.8.4</version>
    </dependency>
    ```

    


