package Adapter.ClassAdapter;

/**
 * 目标抽象类
 */
interface Target {
    void request();
}

/**
 * 适配器类
 */
class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest();
    }
}

/**
 * 适配者类
 */
class Adaptee {
    public void specificRequest() {
        System.out.println("类适配器：通过适配器连接目标抽象类和适配者类");
    }
}

/**
 * 客户端
 */
class Client {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }
}