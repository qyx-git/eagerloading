package com.springboottest.test01.hotloader;

import java.io.*;

/**
 * 自定义类加载器来实现java类的热加载
 */
public class HotLoader extends ClassLoader {

    //要加载的类的classpath
    private String classPath;

    public HotLoader(String classPath) {
        //先调用父类的classLoader
        super(ClassLoader.getSystemClassLoader());
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] data = this.loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    /**
     * 加载class文件中内容
     *
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        //获取类路径
        name = name.replace(".", "//");
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(classPath + name + ".class"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int b = 0;
            while((b=fileInputStream.read())!=-1){
                byteArrayOutputStream.write(b);
            }
            fileInputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
