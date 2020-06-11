package com.github.java.pattern.establish.abstractFatory;

/**
 * @author liweigao
 * @date 2020/6/10 下午8:05
 */
public abstract class AbstractFactory {

    /**
     * 准备必需品
     *
     * @return essential
     */
    abstract Essential prepareEssential();

    /**
     * 准备车
     *
     * @return car
     */
    abstract Car prepareCar();
}
