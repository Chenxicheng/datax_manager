package com.dashwood.modules.datax.test;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class Test {

    @org.junit.Test
    public void test1 () {
        String dir = System.getProperty("user.dir");
        String dataxDir = StringUtils.substringBeforeLast(dir, File.separator);
        System.out.println(dataxDir);
    }

}
