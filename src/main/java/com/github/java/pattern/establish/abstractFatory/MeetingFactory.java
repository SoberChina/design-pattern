package com.github.java.pattern.establish.abstractFatory;

/**
 * @author liweigao
 * @date 2020/6/10 下午8:05
 */
public class MeetingFactory extends AbstractFactory {
    @Override
    Essential prepareEssential() {
        return new Clothes();
    }

    @Override
    Car prepareCar() {
        Car car = new BenZ();
        car.startUp();
        return car;
    }
}
