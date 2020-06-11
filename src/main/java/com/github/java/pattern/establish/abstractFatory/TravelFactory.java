package com.github.java.pattern.establish.abstractFatory;

/**
 * @author liweigao
 * @date 2020/6/10 下午8:11
 */
public class TravelFactory extends AbstractFactory {
    @Override
    Essential prepareEssential() {
        return new MeetingPackage();
    }

    @Override
    Car prepareCar() {
        Car car = new Bwm();
        car.startUp();
        return car;
    }
}
