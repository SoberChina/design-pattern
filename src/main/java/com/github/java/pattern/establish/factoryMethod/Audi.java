package com.github.java.pattern.establish.factoryMethod;

import java.time.LocalDateTime;

/**
 * @author liweigao
 * @date 2020/6/10 下午2:04
 */
public class Audi implements Car {
    @Override
    public void startUp() {
        System.out.println("时间：" + LocalDateTime.now() + "奥迪车启动");
    }

    @Override
    public void drive() {
        System.out.println("奥迪车开始走了");
    }
}
