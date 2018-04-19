package com.springboottest.test01.hotloader;

/**
 * 封装加载类的信息
 */
public class LoadInfo {
    private HotLoader hotLoader;

    //记录要加载类的时间戳-->加载时间
    private long loadTime;

    private BaseManager manager;

    public LoadInfo(HotLoader hotLoader, long loadTime) {
        this.hotLoader = hotLoader;
        this.loadTime = loadTime;
    }

    public void setHotLoader(HotLoader hotLoader) {
        this.hotLoader = hotLoader;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public HotLoader getHotLoader() {
        return hotLoader;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public BaseManager getManager() {
        return manager;
    }

    public void setManager(BaseManager manager) {
        this.manager = manager;
    }
}
