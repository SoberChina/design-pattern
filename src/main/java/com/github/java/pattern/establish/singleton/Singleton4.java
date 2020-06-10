package com.github.java.pattern.establish.singleton;

/**
 * @author liweigao
 * @date 2020/6/9 下午3:02
 */
public class Singleton4 {

    //volatile 防止指令重排序。
    private static volatile Singleton4 instance;

    private Singleton4 (){}

    public static Singleton4 getInstance(){
        if(null == instance){
            synchronized (Singleton4.class){
                if(null == instance){
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
