package Bridge;

/**
 * 实际抽象类
 */
abstract class Abstraction {
    //实现类接口
    protected Implementor implementor;
    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }
    abstract void operation();
}

/**
 * 具体抽象类
 */
class RefinedAbstraction extends Abstraction {
    @Override
    public void operation() {
        //具体抽象类中调用具体实现类的方法
        implementor.operationImpl();
    }
}

/**
 * 实现类接口
 */
interface Implementor {
    void operationImpl();
}

/**
 * 具体实现类
 */
class ConcreteImplementA implements Implementor {
    @Override
    public void operationImpl() {

    }
}

class ConcreteImplementB implements Implementor {
    @Override
    public void operationImpl() {

    }
}

/**
 * 客户端
 */
//class Client {
//    public static void main(String[] args) {
//        Abstraction abstraction = new RefinedAbstraction();
//        Implementor implementor = new ConcreteImplementA();
//        abstraction.setImplementor(implementor);
//        abstraction.operation();
//    }
//}
