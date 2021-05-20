package main.demo;

import sun.rmi.runtime.Log;
import utils.randomUtuls.RandomUtils;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.*;

/**
 * @Auther ctq
 * @Date 2020/12/2
 */
public class d6 {
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 1l, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(Integer.MAX_VALUE), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 1000; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread() + "----->" );

                });
            }
            TimeUnit.SECONDS.sleep(2l);
        }



    }
}
