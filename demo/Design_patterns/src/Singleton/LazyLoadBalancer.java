package Singleton;

public class LazyLoadBalancer {
    private volatile static LazyLoadBalancer instance = null;
    private LazyLoadBalancer() {}

    //延迟加载技术
    public static LazyLoadBalancer getInstance() {
        //双重检查锁定
        if (instance == null) {
            synchronized (LazyLoadBalancer.class) {
                if (instance == null)
                    instance = new LazyLoadBalancer();
            }
        }
        return instance;
    }
}
