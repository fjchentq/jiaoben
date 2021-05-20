package main.demo;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;
/**
 * @Auther ctq
 * @Date 2020/11/5
 */
public class d5 {

    //测试文中文字成功
    public static void main(String[] args) throws Exception {

        String property = System.getProperty("user.dir");

        //加载待读取图片
        File imageFile = new File("C:\\Users\\ctq520\\Desktop\\xxproject\\1.PNG");
        //创建tess对象
        ITesseract instance = new Tesseract();
        //设置训练文件目录
        instance.setDatapath(property+"/tessdata");
        //设置训练语言
        instance.setLanguage("chi_sim");
        //执行转换
        String result = instance.doOCR(imageFile);

        System.out.println(result);
    }
}
