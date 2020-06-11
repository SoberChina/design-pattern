#### 工厂方法模式

工厂方法模式:封装创建对象的细节，中间可能有很多其他的操作，
比如日志记录、数据权限等操作。单例也算是一种工厂模式，
唯一的区别就是单例返回的是同一个对象，工厂其实不然每次返回
新的对象。

例如：暴发户有很多车:奔驰(Benz)、宝马(Bmw)、奥迪(Audi)。
每次出门的时候需要选择用哪个车。
>当然车是不变的，但是对于车的状态来说是变化的我们理解为
每次启动的时候返回的都是新对象

每次出门的时候暴发户都是想着要开哪一辆车，在没用模式前，
可能每次都要去`new Audi();` , `new Bmw();` ...
这时候暴发户就很烦，就跟管家说:"每次我拿什么钥匙的时候
你就把相应的车开过来"。我们可以把车钥匙理解为驱动。
为了方便扩展我们先定义这些车的一个父类
```java
public interface Car {

    /**
     * 启动
     */
    void startUp();

    /**
     * 驾驶
     */
    void drive();
}
```
每辆车实现该接口
```java
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

```
管家执行暴发户的指令，首先要根据车的钥匙获取车辆，然后再启动车。


```java

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
}

```
然后交给暴发户执行 drive()方法
```java

public static void main(String[] args) {
    new CarFactory().getCarAndStepUP(DriveKey.BENZ).drive();
}

```
```log

时间：2020-06-10T14:24:43.502奔驰车启动
奔驰车开始走了
```
当然在前一章我们学习了单例模式，那么这个工厂类是不是可以优化呢？
业务场景中没必要使用多实例的`CarFactory`。
```java
public enum  CarFactory {

    INSTANCE;

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
        CarFactory.INSTANCE.getCarAndStepUP(DriveKey.AUDI).drive();

    }
}
```
```log 
时间：2020-06-10T14:43:54.973奥迪车启动
奥迪车开始走了
```


