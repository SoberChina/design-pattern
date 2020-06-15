package com.github.java.pattern.establish.prototype.v3;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author liweigao
 * @date 2020/6/15 下午7:51
 */
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

class Clothes implements Cloneable, Serializable {
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
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);

            return ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

class Pocket implements Cloneable, Serializable {

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