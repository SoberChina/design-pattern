#### 抽象工厂模式

抽象工厂模式:工厂方法模式对于同一类的对象进行构建，当我们想要对
**一组**对象进行管理构建时，为了更好地扩展，我们就需要用到了抽象
工厂模式
比如 沿用工厂模式的类。暴发户为了掩饰身份，
- 开会的时候需要开奔驰、携带公文包
- 出门旅游的时候开宝马车、携带生活衣服。

出门的时候管家根据暴发户给的钥匙去准备相关的东西，我们把公文
包、衣服等统称为必需品(Essential)，我们建立EssenTial抽象类
```java

public interface  Essential {

    /**
     * 简单的一个打印方法
     */
    void print();
}
```
实现衣服和会议包类
```java

public class Clothes implements Essential{
    @Override
    public void print() {
        System.out.println("带着上衣服去旅行");
    }
}

public class MeetingPackage implements Essential {
    @Override
    public void print() {
        System.out.println("带着会议包去开会");
    }
}
```
建立抽象工厂
```java

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
```
旅行和会议实现抽象工厂
```java
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

```
写的更细一些我们根据这几个抽象工厂的实现类添加一个工厂方法模式
```java

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
            default:
                throw new RuntimeException("未知 driveKey:" + driveKey);

        }
        return abstractFactory;
    }
}

```
给管家钥匙进行测试  

```java
AbstractFactory abstractFactory = Factory.INSTANCE.getFactory(DriveKey.BMW);
Car car = abstractFactory.prepareCar();
Essential essential = abstractFactory.prepareEssential();
essential.print();
car.drive();

```
```log
时间：2020-06-10T17:52:12.390宝马车启动
带着会议包去开会
宝马车开始走了
```

如果新加一组事件。暴发户要出去约会，要开着奥迪车去约会，
管家帮着准备车和身份证(别瞎想，为了构造这个时间条件)。
我们需要实现抽象类准备相关事宜
```java
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

public class IdentityCard implements Essential{

    @Override
    public void print() {
        System.out.println("身份证已准备好，祝老板成功！");
    }
}
```
稍微改一下工厂方法的类
```java

case AUDI:
    abstractFactory = new AppointmentFactory();
    break;

```
暴发户给到管家奥迪的车钥匙，管家心情澎湃，希望暴发户今天不要回来了。  

```java
AbstractFactory abstractFactory = Factory.INSTANCE.getFactory(DriveKey.AUDI);
Car car = abstractFactory.prepareCar();
Essential essential = abstractFactory.prepareEssential();
essential.print();
car.drive();  
```
```log

时间：2020-06-10T18:11:31.087奥迪车启动
身份证已准备好，祝老板成功！
奥迪车开始走了
```
当看到正好有三辆车都实现了一遍。如果说我想回老家需要怎么解决呢？

本章节是着重写抽象工厂的例子。上面的封装的DriveKey 是跟着车来的，
再一个这个工厂方法也可以不需要 。比如我们可以直接`   AbstractFactory abstractFactory
 = new MeetingFactory();`来获取我们想要的对象。当然你可以根据
 这组事件来做区分。

 #### 抽象工厂模式vs工厂方法模式

 根据上面的例子我们可以得出结论，工厂方法模式在扩展某个类型时很方便
 比如说我们新建的约会的对象，还可以扩展其他类。抽象工厂则不然，我们
 在抽象工厂中新加一个事件(就是说在准备必须品和车的时候再加一个事件)，
 在实现的具体工厂里面要重新实现一遍。
