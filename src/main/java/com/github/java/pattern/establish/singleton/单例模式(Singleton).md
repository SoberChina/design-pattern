#### 单例模式
单例模式：只有单实例的意思，在应用场景中只需要一个实例，比如读取
配置文件`PropertyManager`,没必要有多个实例。**单例类只能有一个实例，
且由单例类自己来创建并提供给调用者**。像几年前做支付对接银联的，证书秘钥
都是由一个统一的对象去管理的。调用者只关心配置好相应的证书，调用该类对象
提供的方法即可。
##### 单例的几种实现方式：
###### 1. 懒汉、线程不安全
Lazy 初始化、线程不安全
```java

public class Singleton {

    private static Singleton instance;
    //防止多个实例
    private Singleton (){}
    /**
     * 自己实现
     * 提供给调用者实例
     * @return instance
     */
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```
###### 2. 懒汉、线程安全
Lazy 初始化、(synchronized)线程安全
```java

public class Singleton {

    private static Singleton instance;

    private Singleton(){}

    /**
     * 采用 synchronize 关键字保证线程安全性
     * @return instance
     */
    public static synchronized Singleton  getInstance(){
        if(null ==instance){
            instance = new Singleton();
        }
     return instance ;
    }
}

```
###### 3. 饿汉式
线程安全
```java
public class Singleton {

    //类装载时完成初始化操作 ,类加载到内存中后，会实例化一个单例对象，jvm保证了线程安全
    private static final Singleton instance= new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }
}
```
###### 4. 双重检查锁
Lazy 初始化 、(synchronized)线程安全
```java

public class Singleton {

    //volatile 防止指令重排序。
    private static volatile Singleton instance;

    private Singleton (){}

    public static Singleton getInstance(){
        if(null == instance){
            synchronized (Singleton.class){
                if(null == instance){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
###### 5. 静态内部类
Lazy 初始化、线程安全
```java
public class Singleton {
    /**
     *  Singleton 类装载的时候因为没有显示的调用 SingletonHelper 对象，instance不会被实例化
     *  加载外部类时不会加载内部类，这样可以实现懒加载
     *  在调用getInstance()方法时，才会去加载SingletonHelper 类
     */
    private static class SingletonHelper{
        private static final Singleton instance = new Singleton();
    }
    private Singleton (){}
    public static Singleton getInstance(){
        return SingletonHelper.instance;
    }
}
```

######  6. 枚举类
线程安全、防止反射
```java
public enum  Singleton {
    INSTANCE;
}
```

###### 总结
   单例模式这几种虽说有在某种条件下可以达到一致，但是我们在追求细致上，
   在有线程安全的情况下的单例模式，是否不是真正意义上的单例模式呢？当然
   不是，在适用的业务场景下适用适当的模式才是最好的。牛逼的架构不是
   设计出来的，是慢慢演化出来的，跑离开业务来空谈架构那是扯淡。在
   ***《Effective Java》书中作者建议 用私有构造器或者枚举类型来强化
   Singleton 属性***。在追求极致的情况下，用规范的写法和设计修炼内功。
