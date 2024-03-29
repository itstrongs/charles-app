package com.charles.credit.assist.helper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年07月29日 18:44
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        //如果异常时在AsyncTask里面的后台线程抛出的
        //那么实际的异常仍然可以通过getCause获得
        Throwable cause = ex;
        while(null!=cause){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        //stacktraceAsString就是获取的carsh堆栈信息
        final String stacktraceAsString = result.toString();
        printWriter.close();
    }
}
