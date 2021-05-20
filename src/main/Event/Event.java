package main.Event;

import common.Coordinate;
import common.TU;
import main.魂土;
import utils.ScreenCaptureUtils.ScreenCaptureUtils;
import utils.randomUtuls.RandomUtils;
import utils.tupianxiangshidu.BMPLoaderUtils;
import utils.userBehaviorUtils.UserBehaviorUtils;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Auther ctq
 * @Date 2020/12/19
 */
//随机事件
public class Event {

    public static Map<String,Coordinate> suc = new HashMap<>();
    static {
        suc.put("世界窗口",new Coordinate(216,49,256,73));
        suc.put("世界频道",new Coordinate(50,150,134,213));
        suc.put("世界关闭",new Coordinate(756,378,790,467));
        suc.put("世界范围",new Coordinate(164,246,663,665));
        suc.put("随机点击",new Coordinate(1478, 250,1617,467 ));
        suc.put("快速点击",new Coordinate(1133, 497,1689,749 ));
        //1478  《-----》  250
        //1617  《-----》  467
    }

    public static void rd(boolean flag_q_one){

        //自动点击
        if(flag_q_one)
        {
            onChickOne(flag_q_one);
            return;
        }


        int i =  randomCommon(1, 4);
        if(i==1)
        {
            //随机点击一次（3）
            System.out.println("随机点击一次");
            onChickOne(flag_q_one);
        }
        else if(i==2)
        {
            //随机滑动一次 （2）
            int i1 = randomCommon(1, 3);
            System.out.println("随机滑动"+i1 +"次");
            for (int j = 0; j < i1 ; j++) {
                huadongOne();
            }
        }
        else if(i==3)
        {
            //打开聊天窗口翻滚（1）有点难度
            System.out.println("随机聊天窗口翻滚");
            openChat();

        }
        else if(i>3)
        {
            System.out.println("啥都不做放置呗");
        }


    }


    //打开聊天窗口翻滚（1）有点难度
    private static void openChat() {
        try {
             Robot robot = new Robot();
            //首先查询是否有世界屏道这个截图
            String K ="世界窗口";
            Coordinate V = suc.get("世界窗口");
            ScreenCaptureUtils.imageoutput(V.getX(), V.getY(), V.getX1(), V.getY2(),K+ TU.wei,TU.peishu1);
            if(BMPLoaderUtils.compareImage(K+ TU.wei,K+ TU.wei_s,魂土.similarity))
            {
                //System.out.println("相同");
                int[] np = 魂土.getNotRepeatPoint(V.getX(), V.getX1(), V.getY(), V.getY2(), TU.peishu1, K);
                int numGrid =   np[0];
                int numGrid1 =  np[1];
                robot.mouseMove(numGrid,numGrid1);
                UserBehaviorUtils.chlik_user(robot);
                //等待1到3秒
                //等待
                robot.delay(RandomUtils.getRandomNum(2,3));
                K ="世界频道";
                V = suc.get("世界频道");
                np = 魂土.getNotRepeatPoint(V.getX(), V.getX1(), V.getY(), V.getY2(), TU.peishu1, K);
                numGrid =   np[0];
                numGrid1 =  np[1];
                robot.mouseMove(numGrid,numGrid1);
                魂土.jccfl(numGrid,numGrid1,K);
                UserBehaviorUtils.chlik_user(robot);
                //获取世界频道范围
                K ="世界范围";
                V = suc.get("世界范围");
                np = 魂土.getNotRepeatPoint(V.getX(), V.getX1(), V.getY(), V.getY2(), TU.peishu1, K);
                numGrid =   np[0];
                numGrid1 =  np[1];
                //移动
                robot.mouseMove(numGrid,numGrid1);
                //等待
                robot.delay(RandomUtils.getRandomNum(2,3));
                //鼠标按下
                robot.mousePress(InputEvent.BUTTON1_MASK);
                //移动
                robot.mouseMove(numGrid,numGrid1+randomCommon(30,70));
                //鼠标回弹
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                robot.delay(RandomUtils.getRandomNum(1,2));
                K ="世界关闭";
                V = suc.get("世界关闭");
                np = 魂土.getNotRepeatPoint(V.getX(), V.getX1(), V.getY(), V.getY2(), TU.peishu1, K);
                numGrid =   np[0];
                numGrid1 =  np[1];
                //移动
                robot.mouseMove(numGrid,numGrid1);
                //等待
                robot.delay(RandomUtils.getRandomNum(1,3));
                //点击
                魂土.jccfl(numGrid,numGrid1,K);
                UserBehaviorUtils.chlik_user(robot);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }



    }


    private static void onChickOne(boolean flag_q_one) {
        try {
            Robot robot = new Robot();
            Random random = new Random();
            //防止点击小怪
            String K = flag_q_one? "快速点击":"随机点击";
            Coordinate V = suc.get(K);
            int[] np = 魂土.getNotRepeatPoint(V.getX(), V.getX1(), V.getY(), V.getY2(), TU.peishu, K);
            int numGrid =   np[0];
            int numGrid1 =  np[1];
            robot.mouseMove(numGrid,numGrid1);
            List<Integer> list = 魂土.list;
            int i = random.nextInt(list.size());
            TimeUnit.SECONDS.sleep(list.get(i));
            System.out.println(list.get(i)+"秒");
            魂土.jccfl(numGrid,numGrid1,K);
            UserBehaviorUtils.chlik_user(robot);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private static void huadongOne(){
        try {
            Robot robot = new Robot();
            魂土.waitTime(robot);
            Random random = new Random();

            int start_x = RandomUtils.getNumGrid(1484, 1653, TU.peishu);
            int start_y = RandomUtils.getNumGrid(261, 548, TU.peishu);
            //获取第二个元素
            int end_x = RandomUtils.getNumGrid(1484, 1653, TU.peishu);
            int end_y = RandomUtils.getNumGrid(261, 548, TU.peishu);
            int count = 10;
            boolean f = true;
            for(int i = 0; i <count; i ++){
                int x =((end_x * i)/ count)+(start_x *(count-i)/ count);
                int y =((end_y * i)/ count)+(start_y *(count-i)/ count);
                if(f)
                {
                    robot.mouseMove(x,y);
                    f= false;
                }

                //鼠标按下
                robot.mousePress(InputEvent.BUTTON1_MASK);
                //开始执行
                robot.delay(RandomUtils.getRandomNum(0,1));
            }
            //鼠标回弹
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static int randomCommon(int min, int max){
        return  (int) (Math.random() * (max - min)) + min;
    }


    public static void main(String[] args) throws Exception {
       // onChickOne();
       // huadongOne();
       // openChat();
       // System.out.println(randomCommon(0,2));
        // 756,845,1778,937
        //1532  《-----》  826
        //1734  《-----》  961
        Robot robot = new Robot();
        for (int i = 0; i < 10; i++) {
            int[] d = 魂土.getNotRepeatPoint(1532, 1734, 826, 961, TU.peishu, "测试");
            robot.mouseMove(d[0],d[1]);
            robot.delay(RandomUtils.getChilkRelease(0.5,1.5));
        }

    }
}
