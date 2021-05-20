package utils.userBehaviorUtils;

import common.TU;
import utils.randomUtuls.RandomUtils;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;

/**
 *  点击事件
 */
public class UserBehaviorUtils {

    //点击事件

    /**
     * 模拟点击
     * @param robot
     */
    public static void chlik_user(Robot robot)
    {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(RandomUtils.getChilkRelease(0.2,0.5));
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public   static  String geturl(String urc) throws Exception
    {
        return new File("").getCanonicalPath()+"\\picture\\" + urc;
    }

    public   static  String geturl(String urc,int i) throws Exception
    {
        return new File("").getCanonicalPath()+"\\picture\\"+"\\成果\\" + urc;
    }
}
