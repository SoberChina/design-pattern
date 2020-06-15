#### 原型模式
原型模式: 指创建重复的对象，同时保证性能。利用已存在的对象，快速创建相同的对象。比如当我们需要重复对象时，java提供的clone()方法，让我们快速地创建重复对象。
当然需要区分深浅拷贝的问题。比如我们为了防止对象在执行子任务时不影响该对象(执行 a b c 三个作业)，防止下层方法修改对象。这时候就需要复制对象。
深浅拷贝[可以点这里](https://blog.csdn.net/SoberChina/article/details/81207883)

>  Object提供的native clone()方法，仅仅返回该对象的副本，只是对于变量引用的复制，对于修改引用对象的属性，不会影响引用修改。clone为浅copy

衣服
```java

public class Clothes implements Cloneable {
    /**
     * 品牌
     */
    private String brand;
    /**
     * 尺寸
     */
    private String size;
    /**
     * 兜
     */
    private List<Pocket> pockets;

    @Override
    public String toString() {
        return "Clothes{" +
                "brand='" + brand + '\'' +
                ", size='" + size + '\'' +
                ", pockets=" + pockets.toString() +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<Pocket> getPockets() {
        return pockets;
    }

    public void setPockets(List<Pocket> pockets) {
        this.pockets = pockets;
    }


}

```
扣子
```java

public class Pocket implements Cloneable {

    /**
     * 长
     */
    private int length;

    /**
     * 宽
     */
    private int wide;

    /**
     * 颜色
     */
    private String colour;

    @Override
    public String toString() {
        return "Pocket{" +
                "length=" + length +
                ", wide=" + wide +
                ", colour='" + colour + '\'' +
                '}';
    }

    public Pocket(int length, int wide, String colour) {
        this.length = length;
        this.wide = wide;
        this.colour = colour;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWide() {
        return wide;
    }

    public void setWide(int wide) {
        this.wide = wide;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
```

测试默认提供的clone方法。
```java

public class MainTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Clothes clothes = new Clothes();

        clothes.setBrand("森马");
        clothes.setSize("L");

        clothes.setPockets(Arrays.asList(new Pocket(10, 30, "绿色"),
                new Pocket(10, 30, "绿色"),
                new Pocket(10, 30, "黄色")));

        Clothes copyCloths = (Clothes) clothes.clone();
        clothes.getPockets().get(0).setColour("黑色");
        System.out.println("原始对象: " + clothes.toString());
        System.out.println("copy对象: " + copyCloths.toString());
    }
}

```
```java

原始对象: Clothes{brand='森马', size='L', pockets=[Pocket{length=10, wide=30, colour='黑色'}, Pocket{length=10, wide=30, colour='绿色'}, Pocket{length=10, wide=30, colour='黄色'}]}
copy对象: Clothes{brand='森马', size='L', pockets=[Pocket{length=10, wide=30, colour='黑色'}, Pocket{length=10, wide=30, colour='绿色'}, Pocket{length=10, wide=30, colour='黄色'}]}
```
当我们修改原始对象值时，copy对象的值对应的也变了。因为我们修改了对象值。默认clone方法克隆对象的时将引用对象的引用复制一份。

现在我们稍微改动一下我们的clone方法。
修改Clothes类 的clone方法即可(因为常量池的原因String是一个特殊的引用对象)
```java

@Override
  protected Object clone() throws CloneNotSupportedException {

      Clothes clothes = (Clothes) super.clone();
      if (this.pockets != null) {

          List<Pocket> pockets = new ArrayList<>();
          for(Pocket pocket: this.pockets){
              pockets.add((Pocket)pocket.clone());
          }
          clothes.setPockets(pockets);
      }
      return clothes;
  }

```
在执行,输出
```log
原始对象: Clothes{brand='森马', size='L', pockets=[Pocket{length=10, wide=30, colour='黑色'}, Pocket{length=10, wide=30, colour='绿色'}, Pocket{length=10, wide=30, colour='黄色'}]}
copy对象: Clothes{brand='森马', size='L', pockets=[Pocket{length=10, wide=30, colour='绿色'}, Pocket{length=10, wide=30, colour='绿色'}, Pocket{length=10, wide=30, colour='黄色'}]}
```
发现调用clone()后，修改原来的值不变。简单的实现了一个深copy，如果这么写对于非String类型的引用类型对象进行逐一克隆的话。那么只有对象引用够深就可以一直写下去。当我们新加一个引用对象时，是不是就需要改动我们的clone方法。

采用序列化方式实现深拷贝。具体实现可以[点这里](https://blog.csdn.net/SoberChina/article/details/81207883);

##### 总结
  - clone 方法可以实现对象复制，在特定的场景下实现特定的需求。
  - 原型模式在复制对象的时候不一定要比新建对象快，只是减少了对象属性复制的操作。  
