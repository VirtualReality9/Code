package Facade;

/**
 * 外观角色
 */
class Facade {
    private SubSystemA subSystemA = new SubSystemA();
    private SubSystemB subSystemB = new SubSystemB();
    private SubSystemC subSystemC = new SubSystemC();

    public void method() {
        subSystemA.methodA();
        subSystemB.methodB();
        subSystemC.methodC();
    }
}
/**
 * 子系统角色
 */
class SubSystemA {
    public void methodA() {
        System.out.println("SubSystemA的methodA()方法");
    }
}
class SubSystemB {
    public void methodB() {
        System.out.println("SubSystemB的methodB()方法");
    }
}
class SubSystemC {
    public void methodC() {
        System.out.println("SubSystemC的methodC()方法");
    }
}
/**
 * 客户端
 */
class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.method();
    }
}