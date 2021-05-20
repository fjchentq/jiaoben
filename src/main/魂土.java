package main;

import common.Coordinate;
import common.TU;
import main.Event.Event;
import utils.ScreenCaptureUtils.ScreenCaptureUtils;
import utils.randomUtuls.RandomUtils;
import utils.tupianxiangshidu.BMPLoaderUtils;
import utils.userBehaviorUtils.UserBehaviorUtils;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 阴阳师魂11以及御灵（业原火）
 */
//#ce066ff来个手动挖土司机
//#ce066ff挖土来手动司机
//#ce066ff来个手动挖土司机
//#ce066ff  #cff3333
//屯童  (堆攻击)蛇（火灵）鬼屯
//有工具蛇（工具吞）（8000+ 散件功） 嘛（140速度以下）和座敷火灵（140速度以下） 不用你点怪 19s

public class 魂土 {
    //相似度（%）
    public static final int similarity = 85;
    //刷几次（0为无限刷）
    private static int  count = 0;
    //值点击一次
    private static int flag_s = 0;
    //是否自动点击魂土
    private static boolean flag_s_one = true   ;
    //是否快速点击
   // private static boolean flag_q_one = true ;
    private static boolean flag_q_one = false ;
    public static List<Integer> list = new ArrayList<>();
    public static Map<String,Coordinate> suc = new HashMap<>();

    static {
        //1115  《-----》  866
        //1734  《-----》  984
        suc.put("结束随机",new Coordinate(1315,909,1734,984));
       // suc.put("结束随机",new Coordinate(1315,909,1434,984));
        fullList(list,1,0);
        fullList(list,1,3);
        fullList(list,1,4);
    }
    private static void  fullList(List<Integer> list , int count,int i_name)
    {
        for (int j = 0; j < count; j++) {
            list.add(i_name);
        }
    }

    //启动任务
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        shuaHun10(robot,false);
    }

    public static void shuaHun10(Robot robot,boolean flag) throws Exception
    {
        Map<String, Coordinate> suc = TU.suc;

        //获取挑战结束的
        Coordinate tzjs = 魂土.suc.get("结束随机");

        Random random = new Random();
        while (true) {
           waitTime(robot);
            suc.forEach((K,V)->{
                ScreenCaptureUtils.imageoutput(V.getX(), V.getY(), V.getX1(), V.getY2(),K+ TU.wei,TU.peishu1);
                try {
                    if(BMPLoaderUtils.compareImage(K+ TU.wei,K+ TU.wei_s,similarity))
                    {
                        System.out.println(K);
                        //System.out.println(count++);
                        int[] np = getNotRepeatPoint(V.getX(), V.getX1(), V.getY(), V.getY2(), TU.peishu1, K);
                        int numGrid =   np[0];
                        int numGrid1 =  np[1];
                        if (K.equals("")){
                            robot.mouseMove(numGrid-75,numGrid1-75);
                        }
                        else if (K.equals("组队挑战接受")  || K.equals("录像") || K.equals("录像_备注") || K.equals("挑战结束"))
                        {
                            for(int i = 0 ;i<7;i++)
                            {
                                flag_s+=1;
                                np = getNotRepeatPoint(tzjs.getX(), tzjs.getX1(), tzjs.getY(), tzjs.getY2(), TU.peishu, K);
                                int numGrid2 = np[0];
                                int numGrid3 = np[1];
                                robot.mouseMove(numGrid2,numGrid3);
                                jccfl(numGrid2,numGrid3,K);
                                UserBehaviorUtils.chlik_user(robot);
                                robot.delay(RandomUtils.getRandomNum(0,1,true));
                            }
                        }
                        else if (K.equals("挑战"))
                        {
                            flag_s+=1;
                            robot.mouseMove(numGrid,numGrid1);
                            //System.out.println(K);
                        }
                        else if (K.equals("自动") && flag_s ==0)
                        {
                            return;
                        }
                        else if (K.equals("自动") && flag_s >0 && !flag_s_one)
                        {
                            return;
                        }
                        else if (K.equals("自动") && flag_s >0 && flag_s_one)
                        {
                            //防止下次在点击
                            flag_s = 0;
                            Event.rd(flag_q_one);
                            return;
                        }
                        //活动
                        else if (K.equals("活动御魂"))
                        {
//                            numGrid1+=200;
                            robot.mouseMove(numGrid,numGrid1);
                        }
                        else {
                        robot.mouseMove(numGrid,numGrid1);
                        }
                        jccfl(numGrid,numGrid1,K);
                        UserBehaviorUtils.chlik_user(robot);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            });
        }
    }



    //检测重复
    private static Set<String> setStr= new HashSet<>();
    private static Set<String> setStr1= new HashSet<>();
    //错误提示阈值
    private static int errorInfo = 3;

    //检测重复率
    public static void jccfl(int a,int b,String name)
    {
        //
        int s1 = setStr1.size();
        setStr1.add(a+""+b);
        int s2 = setStr1.size();
      //  System.out.println("坐标x="+a +"y="+b);
        if(s1==s2)
        {
            System.out.println("错误:重复了组件为"+name);
            errorInfo--;
        }
        if(errorInfo<0)
        {
            System.out.println("可能封号！！！！");
        }
    }

    /**
     * 获取一个不重复的坐标
     */
    public static int[] getNotRepeatPoint(int x, int x1 ,int y ,int y1,float db,String name )
    {
        int a = RandomUtils.getNumGrid(x, x1, db);
        int b = RandomUtils.getNumGrid(y,y1,db);
        int s1 = setStr.size();
        setStr.add(a+""+b);
        int s2 = setStr.size();
        if(s1==s2 && whileD(name))
        {
            //System.out.println("重复了,组件为"+name+"正在重新刷新");
           return getNotRepeatPoint(x,x1,y,y1,db,name);
        }
        int[] rs = new int[2];
        rs[0]=a;
        rs[1]=b;
        return rs;
    }

    /**
     * 设置白名单 不会触发点击事件
     * @param name
     * @return
     */
    private static boolean whileD(String name) {
        List<String> list = new ArrayList<>();
        list.add("自动");
        for (String s : list) {
            if(s.equals(name))
            {
                return false;
            }
        }

        return true;
    }


    /**
     * 等待
     * @param robot
     */
    public static void waitTime(Robot robot){
        //开始执行
        robot.delay(RandomUtils.getRandomNum(0,1));
    }



}
