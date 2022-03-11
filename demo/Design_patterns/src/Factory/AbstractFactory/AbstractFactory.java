package Factory.AbstractFactory;

/**
 * 思考：抽象工厂模式是否符合“开闭原则”？【从增加新的产品等级结构和增加新的产品族两方面思考】
 * 回答：增加新的产品等级结构需要在原有工厂类（抽象工厂和具体工厂都需要）进行修改，可以对新的产品等级结构进行生产，会违反“开闭原则”。
 *      增加新的产品族需要新建具体工厂，不会违反“开闭原则”。
 */
public class AbstractFactory {
    /**
     * 抽象工厂
     */
    abstract class ControllerFactory {
        abstract OperationController createOperationController();
        abstract InterfaceController createInterfaceController();
    }
    /**
     * 具体工厂
     */
    // Symbian
    class SymbianControllerFactory extends ControllerFactory {

        @Override
        OperationController createOperationController() {

            return new SymbianOperationController();
        }

        @Override
        InterfaceController createInterfaceController() {
            return new SymbianInterfaceController();
        }
    }
    // Android
    class AndroidControllerFactory extends ControllerFactory {

        @Override
        OperationController createOperationController() {
            return new AndroidOperationController();
        }

        @Override
        InterfaceController createInterfaceController() {
            return new AndroidInterfaceController();
        }
    }
    // WindowsMobile
    class WindowsMobileControllerFactory extends ControllerFactory {

        @Override
        OperationController createOperationController() {
            return new WindowsMobileOperationController();
        }

        @Override
        InterfaceController createInterfaceController() {
            return new WindowsMobileInterfaceController();
        }
    }
    /**
     * 抽象产品
     */
    abstract class OperationController {

    }
    abstract class InterfaceController {

    }
    /**
     * 具体产品
     */
    // Symbian
    class SymbianOperationController extends OperationController {
        public SymbianOperationController() {
            System.out.println("SymbianOperationController");
        }
    }
    class SymbianInterfaceController extends InterfaceController {
        public SymbianInterfaceController() {
            System.out.println("SymbianInterfaceController");
        }
    }
    // Android
    class AndroidOperationController extends OperationController {
        public AndroidOperationController() {
            System.out.println("AndroidOperationController");
        }
    }
    class AndroidInterfaceController extends InterfaceController {
        public AndroidInterfaceController() {
            System.out.println("AndroidInterfaceController");
        }
    }
    // WindowsMobile
    class WindowsMobileOperationController extends OperationController {
        public WindowsMobileOperationController() {
            System.out.println("WindowsMobileOperationController");
        }
    }
    class WindowsMobileInterfaceController extends InterfaceController {
        public WindowsMobileInterfaceController() {
            System.out.println("WindowsMobileInterfaceController");
        }
    }

    public static void main(String[] args) {
        AbstractFactory abstractFactory = new AbstractFactory();
        ControllerFactory controllerFactory = abstractFactory.new SymbianControllerFactory();
        controllerFactory.createOperationController();
        controllerFactory.createInterfaceController();
    }
}
