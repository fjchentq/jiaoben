package utils;

import java.util.Random;
/**
 * @Auther ctq
 * @Date 2020/12/17
 */
public class RandomUtil {

    public static final char[] ARR = {'1','2','3','4','5','6','7','8','9','0',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w',
            'x','y','z'};

    public static String random(Integer count){
        Random rand = new Random();
        StringBuilder str = new StringBuilder();
        while (count != 0){
            str.append(ARR[rand.nextInt(ARR.length)]);
            count--;
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(RandomUtil.random(8));
    }

}
