package Singleton;

// IoDH技术
public class IoDHLoadBalancer {
    private IoDHLoadBalancer() {}
    private static class HolderClass {
        private final static IoDHLoadBalancer instance = new IoDHLoadBalancer();
    }

    public IoDHLoadBalancer getInstance() {
        return HolderClass.instance;
    }

}
