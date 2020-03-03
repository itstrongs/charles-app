package com.charles.myapp.learn.rxjava;

import java.util.concurrent.Executors;

/**
 * Created by itstrongs on 2018/1/9.
 */

public class Schedulers {

    private static final Scheduler ioScheduler = new Scheduler(Executors.newSingleThreadExecutor());

    public static Scheduler io() {
        return ioScheduler;
    }
}
