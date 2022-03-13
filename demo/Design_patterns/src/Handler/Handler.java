package Handler;

/**
 * 抽象处理类
 */
abstract class Handler {
    protected Handler successor;
    public Handler() {
    }
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
    abstract void handler(int day);
}

/**
 * 具体处理类
 */
class ConcreteHandlerA extends Handler {
    public ConcreteHandlerA() {
    }
    public void handler(int day) {
        if (day < 3) {
            System.out.println("ConcreteHandlerA完成了业务。");
        } else {
            this.successor.handler(day);
        }
    }
}
class ConcreteHandlerB extends Handler {
    public ConcreteHandlerB() {
    }
    public void handler(int day) {
        if (day >= 3 && day < 10) {
            System.out.println("ConcreteHandlerB完成了业务。");
        } else {
            this.successor.handler(day);
        }
    }
}
class ConcreteHandlerC extends Handler {
    public ConcreteHandlerC() {
    }
    public void handler(int day) {
        if (day >= 10 && day < 30) {
            System.out.println("ConcreteHandlerC完成了业务。");
        } else {
            System.out.println("对不起，请假时间太长，无法完成业务！");
        }
    }
}
/**
 * 客户端
 */
class Client {
    public static void main(String[] args) {
        Handler A, B, C;
        A = new ConcreteHandlerA();
        B = new ConcreteHandlerB();
        C = new ConcreteHandlerC();
        A.setSuccessor(B);
        B.setSuccessor(C);

        A.handler(2);
        A.handler(5);
        A.handler(15);
        A.handler(31);
    }
}