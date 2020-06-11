package com.github.java.pattern.establish.abstractFatory;

/**
 * @author liweigao
 * @date 2020/6/10 下午8:41
 */
public enum Factory {

    INSTANCE;

    public AbstractFactory getFactory(DriveKey driveKey) {
        AbstractFactory abstractFactory;
        switch (driveKey) {
            case BMW:
                abstractFactory = new TravelFactory();
                break;
            case BENZ:
                abstractFactory = new MeetingFactory();
                break;
            case AUDI:
                abstractFactory = new AppointmentFactory();
                break;
            default:
                throw new RuntimeException("未知 driveKey:" + driveKey);

        }
        return abstractFactory;
    }

    public static void main(String[] args) {
//        AbstractFactory abstractFactory = new MeetingFactory();
        AbstractFactory abstractFactory = Factory.INSTANCE.getFactory(DriveKey.AUDI);
        Car car = abstractFactory.prepareCar();
        Essential essential = abstractFactory.prepareEssential();
        essential.print();
        car.drive();
    }
}
