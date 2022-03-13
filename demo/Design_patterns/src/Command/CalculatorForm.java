package Command;

/**
 * 加法计算器：完成加法，实现撤销、恢复功能
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 请求发起者
 */
class CalculatorForm {
    private AbstractCommand command;
    public CalculatorForm(AbstractCommand command) {
        this.command = command;
    }
    public void setCommand(AbstractCommand command) {
        this.command = command;
    }
    //加法
    public void call(int value) {
        command.execute(value);
    }
    //撤销
    public void undo() {
        command.undo();
    }
    //恢复
    public void redo() {
        command.redo();
    }
}

/**
 * 抽象命令类
 */
abstract class AbstractCommand {
    abstract void execute(int value);
    abstract void undo();
    abstract void redo();
}

/**
 * 具体命令类
 */
class AddCommand extends AbstractCommand {
    private Adder adder = new Adder();
    public void execute(int value) {
        adder.add(value);
    }
    public void undo() {
        adder.undo();
    }
    public void redo() {
        adder.redo();
    }
}

/**
 * 请求接收者
 */
class Adder {
    private int result = 0, value;
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> stackA = new Stack<>();
    public void add(int value) {
        stack.push(value);
        result += value;
        this.value = value;
        System.out.println("现在的result = " + result);
    }

    public void undo() {
        value = stack.pop();
        stackA.push(value);
        result -= value;
        System.out.println("撤销后的result = " + result);
    }

    public void redo() {
        value = stackA.pop();
        result += value;
        System.out.println("恢复后的result = " + result);
    }
}

/**
 * 客户端
 */
class ClientA {
    public static void main(String[] args) {
        AddCommand command = new AddCommand();
        CalculatorForm calculatorForm = new CalculatorForm(command);
        calculatorForm.call(2);
        calculatorForm.call(5);
        calculatorForm.undo();
        calculatorForm.undo();
        calculatorForm.redo();
        calculatorForm.redo();
    }
}
