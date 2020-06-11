package com.github.java.pattern.establish.factoryMethod;

/**
 * @author liweigao
 * @date 2020/6/10 下午2:15
 */
public class CarFactory {

    public Car getCarAndStepUP(DriveKey driveKey) {
        Car car;
        switch (driveKey) {
            case BMW:
                car = new Bwm();
                break;
            case BENZ:
                car = new BenZ();
                break;
            case AUDI:
                car = new Audi();
                break;
            default:
                throw new RuntimeException("未知 driveKey:" + driveKey);


        }
        //启动
        car.startUp();
        return car;
    }

    public static void main(String[] args) {
        new CarFactory().getCarAndStepUP(DriveKey.BENZ).drive();
    }
}
