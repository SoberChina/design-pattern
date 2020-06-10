package com.github.java.pattern.establish.singleton;

/**
 * 懒汉式、线程不安全
 * @author liweigao
 * @date 2020/6/9 下午1:29
 */
public class Singleton1 {

    private static Singleton1 instance;
    //防止多个实例
    private Singleton1(){}
    /**
     * 自己实现
     * 提供给调用者实例
     * @return instance
     */
    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
