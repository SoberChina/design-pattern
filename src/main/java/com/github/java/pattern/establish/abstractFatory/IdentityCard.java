package com.github.java.pattern.establish.abstractFatory;

/**
 * @author liweigao
 * @date 2020/6/10 下午9:06
 */
public class IdentityCard implements Essential{

    @Override
    public void print() {
        System.out.println("身份证已准备好，祝老板成功！");
    }
}
