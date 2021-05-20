package utils.randomUtuls;

import common.TU;

import java.util.Random;

/**
 * 随机时间
 */
public class RandomUtils {

    private  final  static Random random = new Random();
    /**
     * 获取随机
     * @param fristTime 最开始时间
     * @param lastTime 最后时间
     * @return
     */
    public static int getRandomNum( int fristTime, int lastTime )
    {
        double i = fristTime+   random.nextDouble()*( lastTime -fristTime );
        return  (int)( i *1000);
    }

    /**
     * 获取随机
     * @param fristTime 最开始时间
     * @param lastTime 最后时间
     * @return
     */
    public static int getRandomNum( int fristTime, int lastTime ,boolean f )
    {
        double i = fristTime+   random.nextDouble()*( lastTime -fristTime );
        return  (int)( i *200);
    }



    /**
     * 点击释放随机
     * @param fristTime
     * @param lastTime
     * @return
     */
    public static int getChilkRelease( double fristTime, double lastTime)
    {
        double i = ( (fristTime+   random.nextDouble()*( lastTime -fristTime )));
        return  (int) (i*1000);
    }

    public static int getNumGrid( int fristTime, int lastTime,float peishu )
    {
        double i =( (fristTime+   random.nextDouble()*( lastTime -fristTime ))* peishu);
        return  (int) i;
    }



    public static void main(String[] args) {
        System.out.println(getNumGrid(100,200, TU.peishu));
    }
}
