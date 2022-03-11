package Factory.FactoryMethod;

/**
 * 问：工厂方法模式中的工厂方法能否为静态方法？为什么？
 * 答：不可以。静态方法不继承，调用父类的静态方法不会执行子类的同名静态方法。
 * 问：客户端代码直接通过反射机制生成产品对象，再定义产品对象时使用抽象类型。
 * 答：在代码中可以直接通过反射创建对象，但是如果复杂对象初始化，每一次都需要进行初始化操作。
 */
public class FactoryMethod {
    /**
     * 抽象产品
     */
    abstract class Animal {

    }
    /**
     * 具体产品
     */
    class Dog extends Animal {

    }
    class Cat extends Animal {

    }
    /**
     * 抽象工厂（核心）
     */
    interface Factory {
        Animal factoryMethod();
    }
    /**
     * 具体工厂
     */
    class DogFactory implements Factory {
        @Override
        public Animal factoryMethod() {
            return new Dog();
        }
    }
    class CatFactory implements Factory {
        @Override
        public Animal factoryMethod() {
            return new Cat();
        }
    }
}
