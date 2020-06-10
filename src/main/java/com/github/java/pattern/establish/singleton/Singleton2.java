package com.github.java.pattern.establish.singleton;

/**
 * 懒汉式、线程安全
 * @author liweigao
 * @date 2020/6/9 下午2:10
 */
public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2(){}

    /**
     * 采用 synchronize 关键字保证线程安全性
     * @return instance
     */
    public static synchronized Singleton2  getInstance(){
        if(null ==instance){
            instance = new Singleton2();
        }
     return instance ;
    }
}
