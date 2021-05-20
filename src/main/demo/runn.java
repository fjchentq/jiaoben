package main.demo;

/**
 * @Auther ctq
 * @Date 2020/6/20
 */
public class runn  implements  Runnable{

    @Override
    public void run() {

        try {
           // Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"running");
    }
}
