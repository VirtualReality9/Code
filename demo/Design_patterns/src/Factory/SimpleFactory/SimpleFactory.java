package Factory.SimpleFactory;

class UnSupportedShapeException extends Exception {
    public UnSupportedShapeException(String msg) {
        System.out.println("产生了异常：" + msg);
    }
}

public class SimpleFactory {
    /**
     * 抽象产品角色
     */
    abstract class Animal {
        public void animal() {
            System.out.println("这是一只小动物。");
        }
        abstract void call();
        abstract void run();
    }
    /**
     * 具体产品角色
     */
    class Cat extends Animal {
        public Cat() {
            System.out.println("这是一只小猫。");
        }
        @Override
        public void call() {
            System.out.println("小猫在叫。");
        }
        @Override
        public void run() {
            System.out.println("小猫在跑。");
        }
    }
    class Dog extends Animal {
        public Dog() {
            System.out.println("这是一只小狗。");
        }
        @Override
        public void call() {
            System.out.println("小狗在叫。");
        }
        @Override
        public void run() {
            System.out.println("小狗在跑。");
        }
    }
    /**
     * 工厂角色
     */
    class Factory {
        public Animal getAnimal(String arg) {
            Animal animal = null;
            if ("cat".equals(arg)) {
                animal = new Cat();
            } else if ("dog".equals(arg)) {
                animal = new Dog();
            } else {
                throw new RuntimeException("暂时无法生成这种类型！");
            }
            return animal;
        }
    }

    /**
     * 测试内部类实例化
     */
    static class Test {

    }

    /**
     * 抽象绘图工具
     */
    abstract class drawingUtils {
        abstract void draw();//绘制
        abstract void erase();//擦除
    }

    /**
     * 具体几何图形
     */
    class Circle extends drawingUtils {
        public Circle() {
            System.out.println("通过工厂产生一个圆。");
        }
        @Override
        void draw() {
            System.out.println("绘制一个圆。");
        }
        @Override
        void erase() {
            System.out.println("擦除一个圆。");
        }
    }
    class Square extends drawingUtils {
        public Square() {
            System.out.println("通过工厂产生一个方形。");
        }
        @Override
        void draw() {
            System.out.println("绘制一个方形。");
        }
        @Override
        void erase() {
            System.out.println("擦除一个方形。");
        }
    }
    class Triangle extends drawingUtils {
        public Triangle() {
            System.out.println("通过工厂产生一个三角形。");
        }
        @Override
        void draw() {
            System.out.println("绘制一个三角形。");
        }
        @Override
        void erase() {
            System.out.println("擦除一个三角形。");
        }
    }

    /**
     * 图形工厂
     */
    class DrawingFactory {
        drawingUtils factory(String type) throws UnSupportedShapeException {
            drawingUtils drawing;
            if (type.equals("circle")) {
                drawing = new Circle();
            } else if (type.equals("square")) {
                drawing = new Square();
            } else if (type.equals("triangle")) {
                drawing = new Triangle();
            } else {
                throw new UnSupportedShapeException("不支持产生该图形！");
            }
            return drawing;
        }
    }

    public static void main(String[] args) throws UnSupportedShapeException {
        SimpleFactory simpleFactory = new SimpleFactory();
        DrawingFactory drawingFactory = simpleFactory.new DrawingFactory();
        Circle circle = (Circle) drawingFactory.factory("circle");
        Square square = (Square) drawingFactory.factory("square");
        Triangle triangle = (Triangle) drawingFactory.factory("triangle");
        drawingUtils line = drawingFactory.factory("line");
    }
}
