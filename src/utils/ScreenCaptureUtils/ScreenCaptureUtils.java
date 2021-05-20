package utils.ScreenCaptureUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import common.*;
import utils.userBehaviorUtils.UserBehaviorUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Map;

/**
 *截图
 */
public class ScreenCaptureUtils {
    /**
     * 截图
     * @param x x地址
     * @param y y地址
     * @param x1 x1地址
     * @param y1 y1地址
     * @param urlName 输入输出路径
     * @throws Exception
     */
    public static  void imageoutput(int x , int y ,int x1,int y1 , String urlName,float peishu){
        try {
            urlName= UserBehaviorUtils.geturl(urlName);
            Robot robot = new Robot();
            BufferedImage screenCapture = robot.createScreenCapture(new Rectangle((int)(x*peishu), (int)(y*peishu), (int)((x1-x)*peishu), (int)((y1-y)*peishu)));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(urlName));
            JPEGImageEncoder encoder   =   JPEGCodec.createJPEGEncoder(bos);
            encoder.encode(screenCapture);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //截图成功结果
    public static  void imageoutput(int x , int y ,int x1,int y1 , String urlName,float peishu,int i){
        try {
            urlName= UserBehaviorUtils.geturl(urlName,i);
            Robot robot = new Robot();
            BufferedImage screenCapture = robot.createScreenCapture(new Rectangle((int)(x*peishu), (int)(y*peishu), (int)((x1-x)*peishu), (int)((y1-y)*peishu)));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(urlName));
            JPEGImageEncoder encoder   =   JPEGCodec.createJPEGEncoder(bos);
            encoder.encode(screenCapture);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//首先笔记本转换125% 获取坐标
//然后笔记本转换100% 截图

/*    public static  final  int TZX =1283;
    public static  final  int TZY =681;
    public static  final  int TZX1 =1462;
    public static  final  int TZY1 =756;*/
    public static void main(String[] args) throws Exception {
        //Thread.sleep(3000);
        Map<String, Coordinate> suc = TU.suc;
        suc.forEach((k,v)->{
          if(k.equals("活动御魂_f"))
          {
              //yyyy_MM_dd_HH_mm_ss
              imageoutput(v.getX(),v.getY(),v.getX1(),v.getY2(),k + TU.wei_s, TU.peishu1);
          }
        });
    }
}
