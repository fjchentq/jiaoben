package common;

import java.util.*;


public class BaiGuiYeXinRen {
    public static List<Coordinate> coordinates = new ArrayList<>();

    public static List<Coordinate> friends_list = new ArrayList<>();
    public static  Random r = new Random();
    static {
        coordinates.add(new Coordinate(355,465,402,533));
        coordinates.add(new Coordinate(728,465,795,533));
        coordinates.add(new Coordinate(1105,465,1173,533));

        friends_list.add(new Coordinate(541,294,708,609));
        friends_list.add(new Coordinate(850,297,1004,618));
    }

    public static int getRendom(List<Coordinate> list){
        int a=r.nextInt(list.size());
        return a;
    }


    public static void main(String[] args) {
        System.out.println(111);
    }
}
