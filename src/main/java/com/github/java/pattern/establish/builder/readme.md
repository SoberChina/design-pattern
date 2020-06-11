#### 建造者模式

建造者模式:分离复杂对象的构建和表示。

当然使用Builder模式我们可以建立不同的builder对象，某个功能关注某几项属性，可以按组自由封装。

类似的我们可以新建一个多个参数的`Person`类:
```java

public class Person {

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 名称
     */
    private String name;

    /**
     * 座右铭
     */
    private String motto;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

```
然后我们可以新加一个builder类

```java
public final class PersonBuilder {
    private Integer age;
    private String name;
    private String motto;
    private String address;
    private String telephone;
    private String email;

    private PersonBuilder() {
    }

    public static PersonBuilder aPerson() {
        return new PersonBuilder();
    }

    public PersonBuilder age(Integer age) {
        this.age = age;
        return this;
    }

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder motto(String motto) {
        this.motto = motto;
        return this;
    }

    public PersonBuilder address(String address) {
        this.address = address;
        return this;
    }

    public PersonBuilder telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public PersonBuilder email(String email) {
        this.email = email;
        return this;
    }

    public Person build() {
        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        person.setMotto(motto);
        person.setAddress(address);
        person.setTelephone(telephone);
        person.setEmail(email);
        return person;
    }
}

```
#### 总结 
我们使用的时候可以直接用builder构建对象。自由组合对象，可读性远构造函数可比，组合对象时在调用builder的时候才开始创建我们想要的对象。
在`effective java`书中提到遇到多个构造器参数时要考虑使用Builder模式，弥补直接调用构造易读性差、弥补javaBeans模式构造对象过程中可能处于不一致的状态(复杂对象set 方法被分到了几个调用)。
