package com.springboottest.test01.hotloader;

public class MyManagement implements BaseManager{

    @Override
    public void logic() {
        System.out.println("我是自定义的类加载");
        System.out.println("haha");
    }
}
