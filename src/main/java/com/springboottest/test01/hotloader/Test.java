package com.springboottest.test01.hotloader;

public class Test {
    public static void main(String[] args) {

                //.getContextClassLoader().getResource("/").getPath();
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("//").getPath());
    }
}
