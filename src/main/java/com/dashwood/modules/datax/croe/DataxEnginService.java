package com.dashwood.modules.datax.croe;


import com.dashwood.modules.datax.entity.DataXJobCounter;

/**
 * datax引擎执行类
 * @author 陈喜骋
 */
public interface DataxEnginService {
	
	/**
	 * 执行datax.py的路径
	 */

    /**
     * 执行datax
     * @param
     * @return DataXJobCounter dataX统计结果
     */
    DataXJobCounter execute(String fileName) ;
    
    
    @Deprecated
    void executeJar(String jobJson, String jobId);


}
