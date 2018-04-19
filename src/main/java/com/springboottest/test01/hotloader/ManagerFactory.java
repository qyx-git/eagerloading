package com.springboottest.test01.hotloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载manager的工厂
 */
public class ManagerFactory {
    //记录热加载类的加载信息
    private static final Map<String, LoadInfo> loadTimeMap = new HashMap<String, LoadInfo>();

    //要加载类的classpath
//    private static final String CLASS_PATH = Thread.currentThread().getContextClassLoader().getResource("//").getPath();
   private static final String CLASS_PATH = "/Users/qyx/Desktop/workspace/springboot1.x_test/target/classes/";

    //实现热加载文件类的全路径(包名+类名)
     public static final String MY_MANAGEMENT = "com.springboottest.test01.hotloader.MyManagement";

    public static BaseManager getManager(String className) {
        File loadFile = new File(CLASS_PATH + className.replaceAll(".", "/") + ".class");
        long lastModified = loadFile.lastModified();
//        loadeTimeMap不包含className为key的LoadInfo信息，证明这个类没有被加载，name需要把这个类加载到JVM
        if(loadTimeMap.get(className) == null){
            load(className,lastModified);
            //时间戳变化了，也要重新加载这个类读到JVM
        }else if(loadTimeMap.get(className).getLoadTime()!=lastModified){
            //如果被加载过，查看加载时间，如果该类已经被修改，则重新加载
            load(className,lastModified);
        }
        return loadTimeMap.get(className).getManager();
    }
    private static void load(String className, long lastModified) {
        HotLoader myClassLoader = new HotLoader(CLASS_PATH);
        Class<?> loadClass = null;
        try {
            loadClass = myClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        BaseManager manager = newInstance(loadClass);
        LoadInfo loadInfo = new LoadInfo(myClassLoader, lastModified);
        loadInfo.setManager(manager);
        loadTimeMap.put(className, loadInfo);
    }
    //以反射的方式创建BaseManager子类对象
    private static BaseManager newInstance(Class<?> loadClass) {
        try {
            return (BaseManager)loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
