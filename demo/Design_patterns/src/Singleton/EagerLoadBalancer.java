package Singleton;

import java.util.List;

public class EagerLoadBalancer {
    private static EagerLoadBalancer instance = new EagerLoadBalancer();
    private EagerLoadBalancer() {}

    public static EagerLoadBalancer getInstance() {
        return instance;
    }
}
