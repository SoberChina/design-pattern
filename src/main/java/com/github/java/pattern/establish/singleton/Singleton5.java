package com.github.java.pattern.establish.singleton;

/**
 * @author liweigao
 * @date 2020/6/9 下午3:10
 */
public class Singleton5 {
    /**
     *  Singleton5 类装载的时候因为没有显示的调用 SingletonHelper 对象，instance不会被实例化
     *  加载外部类时不会加载内部类，这样可以实现懒加载
     *  在调用getInstance()方法时，才会去加载SingletonHelper 类
     */
    private static class SingletonHelper{
        private static final Singleton5 instance = new Singleton5();
    }
    private Singleton5 (){}
    public static Singleton5 getInstance(){
        return SingletonHelper.instance;
    }
}
