package main;


import common.*;
import utils.ScreenCaptureUtils.ScreenCaptureUtils;
import utils.randomUtuls.RandomUtils;
import utils.tupianxiangshidu.BMPLoaderUtils;
import utils.userBehaviorUtils.UserBehaviorUtils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class 活动 {

    static int numGrid;
    static int numGrid1;
    public static Map<String,Coordinate> suc = new HashMap<>();


    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        suc.put("接受任务",new Coordinate(982,494,991,502));
        start(robot);
    }
    private static void start(Robot robot) throws Exception {
        int i =0;

        while (true)
        {
            System.out.println("刷新次数"+ ++i);
            魂土.waitTime(robot);
            suc.forEach((K,V)->{
                ScreenCaptureUtils.imageoutput(V.getX(), V.getY(), V.getX1(), V.getY2(),K+ TU.wei,TU.peishu1);
                try {
                    if(BMPLoaderUtils.compareImage(K+ TU.wei,K+ TU.wei_s, 魂土.similarity) && K.equals("接受任务"))
                    {
                        numGrid = RandomUtils.getNumGrid(V.getX(), V.getX1(), TU.peishu1);
                        numGrid1 = RandomUtils.getNumGrid(V.getY(), V.getY2(), TU.peishu1);
                        robot.mouseMove(numGrid,numGrid1);
                    }
                    else
                    {
                        waitTime(robot);
                        numGrid = RandomUtils.getNumGrid(1492, 1531, 1f);
                        numGrid1 = RandomUtils.getNumGrid(933, 977, 1);
                        robot.mouseMove(numGrid,numGrid1);
                    }
                    UserBehaviorUtils.chlik_user(robot);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            });
        }
    }

    /**
     * 等待
     * @param robot
     */
    public static void waitTime(Robot robot){
        //开始执行
        robot.delay(RandomUtils.getRandomNum(30,60));
    }
}
