package com.github.java.pattern.establish.singleton;

/**
 * 饿汉式
 * @author liweigao
 * @date 2020/6/9 下午2:22
 */
public class Singleton3 {

    //类装载时完成初始化操作 类加载到内存中后，会实例化一个单例对象，jvm保证了线程安全
    private static final Singleton3 instance= new Singleton3();

    private Singleton3(){}

    public static Singleton3 getInstance(){
        return instance;
    }
}
