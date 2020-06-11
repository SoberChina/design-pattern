package com.github.java.pattern.establish.abstractFatory;

/**
 * @author liweigao
 * @date 2020/6/10 下午9:05
 */
public class AppointmentFactory extends AbstractFactory {
    @Override
    Essential prepareEssential() {
        return new IdentityCard();
    }

    @Override
    Car prepareCar() {
        Car car =new Audi();
        car.startUp();
        return car;
    }
}
