package com.dashwood.commen.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


public class JsonFileUtils {
    /**
     * json模板
     */
    private static final String JOB_JSON = "{"
            +"\"job\": {"
            +"\"content\": ["
            + "{"
            +  "\"reader\": ${datax.readJson},"
            +  "\"writer\": ${datax.writeJson}"
            + "}"
            + "],"
            +  "\"setting\": ${datax.settingJson} "
            +"}}";
	
    public static void generateJsonFile(String fileNamePath, String readJson, String writeJson, String settingJson) throws Exception {
        try {
        	String jobJSON = JOB_JSON;
            jobJSON = jobJSON.replace("${datax.readJson}", readJson)
                                .replace("${datax.writeJson}", writeJson)
                                .replace("${datax.settingJson}", settingJson);
            File jsonFile = new File(fileNamePath+".json");
//            Files.write(jobJSON.getBytes(), jsonFile);
//            Files.write(jobJSON.getBytes(Charsets.UTF_8), jsonFile);
            PrintWriter printWriter =new PrintWriter(new FileWriter(jsonFile,true),false);//第二个参数为true，从文件末尾写入 为false则从开头写入
            printWriter.println(jobJSON);
            printWriter.close();//关闭输入流
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
    }

    
    public static boolean verificateJsonFileExit(String fileNamePath) {
        File file = new File(fileNamePath+".json");
        return file.exists();
    }

    
    public static void deleteJsonFile(String fileNamePath) {
        File file = new File(fileNamePath+".json");
        if (file.exists()) {
            file.delete();
        }
    }
	
	
}
