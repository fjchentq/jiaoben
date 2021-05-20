package main.demo.likou;

import java.util.*;

/**
 * @Auther ctq
 * @Date 2020/12/22
 */
public class d1 {

    public static void main(String[] args) {
        System.out.println( CheckPermutation("adc","acd"));
    }

    public static boolean CheckPermutation(String s1, String s2) {

        String[] split = s1.split("");
        Arrays.sort(split);

        String[] split2 = s2.split("");
        Arrays.sort(split2);

        return  Arrays.toString(split).equals(Arrays.toString(split2));
    }

}
