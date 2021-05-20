package main;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class 测试 {

    public static void main(String[] args) throws Exception {
        Date date = new Date();
        Thread.sleep(3000);
        Date date1 = new Date();
        System.out.println(date1.getTime()-date.getTime());
    }
}
