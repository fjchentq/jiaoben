package main.demo;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther ctq
 * @Date 2020/6/20
 */
public class main2 {
    public static void main(String[] args) {
        //newScheduledThreadPool 定时
        System.out.println(System.currentTimeMillis());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        //执行一次
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟3秒");
                System.out.println(System.currentTimeMillis());
            }
        },3, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟1秒,每3秒执行一次");
                System.out.println(System.currentTimeMillis());
            }
        },1,3, TimeUnit.SECONDS);

    }
}
