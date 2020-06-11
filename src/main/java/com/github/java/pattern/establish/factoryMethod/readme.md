#### 工厂方法模式

工厂方法模式:封装创建对象的细节，中间可能有很多其他的操作，比如日志记录、数据权限等操作。单例也算是一种工厂模式，唯一的区别就是单例返回的是同一个对象，工厂其实不然每次可能需要返回
新的对象。

例如：暴发户有很多车:奔驰(Benz)、宝马(Bmw)、奥迪(Audi)。每次出门的时候需要选择用哪个车。
>当然车是不变的，但是对于车的状态来说是变化的我们理解为
每次启动的时候返回的都是新对象

每次出门的时候暴发户都是想着要开哪一辆车，在没用模式前，可能每次都要去`new Audi();` , `new Bmw();` ...这时候暴发户就很烦，就跟管家说:"每次我拿什么钥匙的时候你就把相应的车开过来"。我们可以把车钥匙理解为驱动。为了方便扩展我们先定义这些车的一个父类
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
当然在前一章我们学习了单例模式，那么这个工厂类是不是可以优化呢？业务场景中没必要使用多实例的`CarFactory`。
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
#### 总结
在`Effective java`书中大牛总结出静态工厂方法的几大优势：
- 有名称，构造器没有确切的描述被返回的对象，具有合适名称的静态工厂方法显然更适用，客户端代码也更易读;

- 不用再每次调用他们的时候都创建一个新的对象，可以使用预先构建好的实例，或者将构建好的实例缓存起来，进行复用，从而避免创建不必要的重复对象。类似于`Boolean.valueof(boolean)`,`Integer.valueof(int)`；

- 可以返回基类的任何子类型对象；
- 返回对象的类可以随着每次调用发生变化，根据上面的例子我们可以根据`driveKey`返回不同的`Car`子类；
- 方法返回的对象所属的类。在编写包涵该静态工厂方法的类时可以不存在，列如 JDBC API 多个服务提供者实现
一个服务。系统为服务提供者实现一个服务，系统为服务器提供者的客户端提供多个实现，并让他们从实现中解耦会出来；服务提供者框架中有三个组件:接口服务（Service Interface 提供者实现的 `java.sql.Driver`）、提供者注册API(Provider Registration API 提供者用来注册实现的 `com.mysql.jdbc.Driver`) 、服务访问API(Service Access API 客户端用来获取服务的实例 `java.sql.DriverManager`) 。

缺点：
  - 类如果没有public或者protected的构造器，就不能被子类实例化。
  - 我们很难找到这些工厂类，需要某个人我们很明确的知道是哪个类。这个类对应的工厂类首先不是必须有的，再一个定义命名满天飞很难发现他们
