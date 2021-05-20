package main;

import common.*;
import utils.ScreenCaptureUtils.ScreenCaptureUtils;
import utils.randomUtuls.RandomUtils;
import utils.tupianxiangshidu.BMPLoaderUtils;
import utils.userBehaviorUtils.UserBehaviorUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class 百鬼夜行自动刷 {

    static boolean flag_s = false;
    static boolean flag_s_ss = false;
    static boolean flag_ss_ss = false;
    static List<Date> baigui_in = new ArrayList<>();
    static int friend_list =0;
    static int numGrid;
    static int numGrid1;
    //启动任务
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        start(robot,false);
    }

    private static void start(Robot robot, boolean b) {

        Map<String, Coordinate> suc = Baiguiyexing.suc;

        while (true) {
            魂土.waitTime(robot);
            suc.forEach((K,V)->{
                ScreenCaptureUtils.imageoutput(V.getX(), V.getY(), V.getX1(), V.getY2(),K+ TU.wei,TU.peishu1);
                try {
                    if(BMPLoaderUtils.compareImage(K+ TU.wei,K+ TU.wei_s, 魂土.similarity))
                    {
                     numGrid = RandomUtils.getNumGrid(V.getX(), V.getX1(), TU.peishu1);
                     numGrid1 = RandomUtils.getNumGrid(V.getY(), V.getY2(), TU.peishu1);
                    if(K.equals("百鬼夜行图标"))
                    {
                        flag_s= true;
                        魂土.waitTime(robot);
                        return;
                    }
//                    else if((K.equals("百鬼_邀请好友") && flag_s))
//                    {
//                        good_friend(robot);
//                        return;
//                    }
//                    else if (( baigui_in.size()>1 &&
//                            (baigui_in.get(baigui_in.size()-1).getTime()-baigui_in.get(baigui_in.size()-2).getTime() <2000 )))
//                    {
//                        baigui_in.clear();
//                        suc.forEach((K1,V2)->{
//                            if(K1.equals("百鬼_邀请好友"))
//                            {
//                                numGrid = RandomUtils.getNumGrid(V2.getX(), V2.getX1(), TU.peishu1);
//                                numGrid1 = RandomUtils.getNumGrid(V2.getY(), V2.getY2(), TU.peishu1);
//                            }
//                        });
//                        good_friend(robot);
//                        return;
//                    }
                    else if (K.equals("百鬼进入")  && flag_s)
                    {
                        baigui_in.add(new Date());
                        robot.mouseMove(numGrid,numGrid1);
                    }
                    else if (K.equals("接受任务"))
                    {
                        robot.mouseMove(numGrid,numGrid1);
                    }
                    else if(K.equals("百鬼开始"))
                    {
                        //查询加成人物并点击
                        List<Coordinate> coordinates = BaiGuiYeXinRen.coordinates;
                        Coordinate coordinate = coordinates.get(BaiGuiYeXinRen.getRendom(coordinates));
                        int numGrid_1 = RandomUtils.getNumGrid(coordinate.getX(), coordinate.getX1(), TU.peishu1);
                        int numGrid_2= RandomUtils.getNumGrid(coordinate.getY(), coordinate.getY2(), TU.peishu1);
                        robot.mouseMove(numGrid_1,numGrid_2);
                        魂土.waitTime(robot);
                        UserBehaviorUtils.chlik_user(robot);

                        //点击开始刷图
                        robot.mouseMove(numGrid,numGrid1);
                        flag_s_ss=true;
                    }
                    else if(K.equals("百鬼契约书")){
                        flag_s=false;
                        flag_s_ss=false;
                        //截图 查看获取成果
                      /*  Map<String, Coordinate> suc_c = BaiGguiyexingClick.suc;
                        Coordinate v = suc_c.get("获取成果");
                        ScreenCaptureUtils.imageoutput(v.getX(),v.getY(),v.getX1(),v.getY2(),new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").
                                format(new Date()) + TU.wei_s, TU.peishu1,1);
                        TimeUnit.SECONDS.sleep(7);*/
                        robot.mouseMove(numGrid,numGrid1);
                    }
                    //开始乱点
                    else if (K.equals("百鬼开始_点击") && flag_s_ss){
                            Map<String, Coordinate> suc1 = BaiGguiyexingClick.suc;
                            Coordinate cd = suc1.get("点击范围");
                            int numGrid_1 = RandomUtils.getNumGrid(cd.getX(), cd.getX1(), TU.peishu1);
                            int numGrid_2= RandomUtils.getNumGrid(cd.getY(), cd.getY2(), TU.peishu1);
                            robot.mouseMove(numGrid_1,numGrid_2);
                            UserBehaviorUtils.chlik_user(robot);
                        return;
                    }
                    else {
                        return;
                    }
                     UserBehaviorUtils.chlik_user(robot);
                    }}catch (Exception e)
                {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void good_friend(Robot robot) throws Exception{
        System.out.println("开始等待。。"+System.currentTimeMillis());
        robot.delay(3000);
        //Thread.sleep(3000);
        System.out.println("百鬼_邀请好友..."+friend_list+"...."+System.currentTimeMillis());
        robot.mouseMove(numGrid,numGrid1);
        UserBehaviorUtils.chlik_user(robot);
        魂土.waitTime(robot);

        //判断是否是点击3次
        if((friend_list+1)%3==0)
        {
            魂土.waitTime(robot);
            robot.mouseWheel(1);
        }
        //随机点击一个
        List<Coordinate> coordinates = BaiGuiYeXinRen.friends_list;
        Coordinate coordinate = coordinates.get(BaiGuiYeXinRen.getRendom(coordinates));
        int numGrid_1 = RandomUtils.getNumGrid(coordinate.getX(), coordinate.getX1(), TU.peishu1);
        int numGrid_2= RandomUtils.getNumGrid(coordinate.getY(), coordinate.getY2(), TU.peishu1);
        robot.mouseMove(numGrid_1,numGrid_2);
        魂土.waitTime(robot);
        UserBehaviorUtils.chlik_user(robot);
        friend_list++;
    }
}
