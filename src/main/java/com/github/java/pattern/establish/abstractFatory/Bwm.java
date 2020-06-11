package com.github.java.pattern.establish.abstractFatory;

import java.time.LocalDateTime;

/**
 * @author liweigao
 * @date 2020/6/10 下午2:05
 */
public class Bwm implements Car {

    @Override
    public void startUp() {
        System.out.println("时间：" + LocalDateTime.now() + "宝马车启动");
    }

    @Override
    public void drive() {
        System.out.println("宝马车开始走了");
    }
}
