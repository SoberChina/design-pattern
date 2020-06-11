package com.github.java.pattern.establish.factoryMethod;

import java.time.LocalDateTime;

/**
 * @author liweigao
 * @date 2020/6/10 上午11:50
 */
public class BenZ implements Car {

    @Override
    public void startUp() {
        System.out.println("时间：" + LocalDateTime.now() + "奔驰车启动");
    }

    @Override
    public void drive() {
        System.out.println("奔驰车开始走了");
    }
}
