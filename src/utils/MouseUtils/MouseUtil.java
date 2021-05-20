package utils.MouseUtils;

import java.awt.*;

public class MouseUtil {
    /**
     * 屏幕x坐标
     */
    private  final  static Integer screenWidth=((int) Toolkit.getDefaultToolkit().getScreenSize().width);
    /**
     * 屏幕y坐标
     */
    private  final  static Integer screenHeight = ((int) Toolkit.getDefaultToolkit().getScreenSize().height);

    /**
     * 获取当前鼠标的坐标 --调试
     */
    public static int[] getmousePoint()
    {
        PointerInfo pinfo = MouseInfo.getPointerInfo();
        Point p = pinfo.getLocation();
        int x = (int) p.getX();
        int y = (int) p.getY();
        System.out.println(x+"  《-----》  "+y);
        return new int[]{x,y};
    }

    //245  《-----》  144
    //344  《-----》  206
    //获取鼠标地址
    public static void main(String[] args) throws  Exception {
        getmousePoint();
    }
}
