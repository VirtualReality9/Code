package Proxy;

/**
 * 抽象主题类
 */
abstract class Subject {
    abstract void rent();
}

/**
 * 代理类
 */
class Proxy extends Subject {
    private Subject realSubject;
    public Proxy(Subject subject) {
        this.realSubject = subject;
    }
    public void rent() {
        System.out.println("Proxy--rent()");
        realSubject.rent();
    }
}
/**
 * 具体主题类
 */
class RealSubject extends Subject {
    public void rent() {
        System.out.println("RealSubject--rent()");
    }
}
/**
 * 客户端
 */
class Client {
    public static void main(String[] args) {
        //不使用代理模式
        Subject subject1 = new RealSubject();
        subject1.rent();
        System.out.println("------------------");
        //使用代理模式
        Subject proxy = new Proxy(subject1);
        proxy.rent();
    }
}
