package main.demo;

import utils.DHttp;
import utils.randomUtuls.RandomUtils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @Auther ctq
 * @Date 2020/12/19
 */
public class d7 extends Frame {

    public static void main(String[] args) throws Exception {

        String post = DHttp.httpRequest(" http://47.114.115.170:8083/fzbankservice", "POST", null);
        System.out.println(post);

    }
}
