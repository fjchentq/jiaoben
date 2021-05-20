package common;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther ctq
 * @Date 2020/9/23
 */
public class Huodong {
    public static Map<String,Coordinate> suc = new HashMap<>();

    public static final String wei="1.jpg";
    public static final String wei_s=".jpg";

    public static final float peishu = 1f;
    public static final float peishu1 = 1.25f;
    static {
        suc.put("活动挑战", new Coordinate(1309,719,1362,762));
        suc.put("挑战", new Coordinate(1302,702,1370,750));
        suc.put("分享时间",new Coordinate(1311,637,1369,683));
        suc.put("挑战结束",new Coordinate(754,706,852,721));
        suc.put("御林",new Coordinate(1292,701,1360,755));
        suc.put("接受任务",new Coordinate(982,494,991,502));
        suc.put("组队挑战",new Coordinate(1392,730,1428,763));
        suc.put("组队挑战接受",new Coordinate(520,141,623,217));
        suc.put("自动",new Coordinate(93,756,109,772));
        suc.put("业原火",new Coordinate(1282,710,1358,770));

    }

    public static void main(String[] args) throws Exception {
    }
}
