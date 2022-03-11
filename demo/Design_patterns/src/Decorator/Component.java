package Decorator;

/**
 * 抽象构件
 */
abstract class Component {
    abstract void display();
}
/**
 * 具体构件
 */
class Window extends Component {
    public void display() {
        System.out.println("Window的display()方法");
    }
}
class TextBox extends Component {
    public void display() {
        System.out.println("TextBox的display()方法");
    }
}
class ListBox extends Component {
    public void display() {
        System.out.println("ListBox的display()方法");
    }
}
/**
 * 抽象装饰类
 */
abstract class ComponentDecorator extends Component {
    private Component component;
    public ComponentDecorator(Component component) {
        this.component = component;
    }
    public void display() {
        component.display();
    }
}
/**
 * 具体装饰类
 */
class ScrollBarDecorator extends ComponentDecorator {
    public ScrollBarDecorator(Component component) {
        super(component);
    }
    public void display() {
        super.display();
        setScrollBar();
    }
    public void setScrollBar() {
        System.out.println("ScrollBarDecorator的setScrollBar()方法");
    }
}
class BlackBorderDecorator extends ComponentDecorator {
    public BlackBorderDecorator(Component component) {
        super(component);
    }
    public void display() {
        super.display();
        setScrollBar();
    }
    public void setScrollBar() {
        System.out.println("BlackBorderDecorator的setScrollBar()方法");
    }
}
/**
 * 客户端
 */
class Client {
    public static void main(String[] args) {
        /**
         * 可以多次装饰，需要将上次装饰好的Component作为参数传入具体装饰类
         */
        //具体构件
        Component component = new Window();
        //具体装饰类
        Component concreteDecorator = new ScrollBarDecorator(component);
        //concreteDecorator.display();
        //两次装饰
        Component concreteDecorator1 = new BlackBorderDecorator(concreteDecorator);
        concreteDecorator1.display();
    }
}