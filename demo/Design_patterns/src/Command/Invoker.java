package Command;

/**
 * 请求发送者
 */
public class Invoker {
    private Command command;
    //构造注入
    public Invoker(Command command) {
        this.command = command;
    }
    //Setter注入
    public void setCommand(Command command) {
        this.command = command;
    }
    //发送请求
    public void send() {
        System.out.println("发出请求，也仅仅完成了发出请求的操作，其实并不知道具体请求接收者是谁，完成了发起者和接收者的解耦！");
        command.execute();
    }
}

/**
 * 抽象命令类
 */
abstract class Command {
    abstract void execute();
}

/**
 * 具体命令类
 */
class ConcreteCommand extends Command {
    private Receiver receiver = new Receiver();
    public void execute() {
        receiver.method();
    }
}
class ConcreteCommandA extends Command {
    private ReceiverA receiver = new ReceiverA();
    public void execute() {
        receiver.method();
    }
}
/**
 * 请求接收者
 */
class Receiver {
    public void method() {
        System.out.println("通过命令模式，请求接收者和请求发起者不见面，也可以完成业务！");
    }
}
class ReceiverA {
    public void method() {
        System.out.println("这是第二个请求接收者，证明一个请求发起者可以对应多个请求接收者！");
    }
}
/**
 * 客户端
 */
class Client {
    public static void main(String[] args) {
        Command concreteCommand = new ConcreteCommand();
        Invoker invoker = new Invoker(concreteCommand);
        invoker.send();
        System.out.println("--------这是分割线--------");
        Command concreteCommandA = new ConcreteCommandA();
        invoker.setCommand(concreteCommandA);
        invoker.send();
    }
}