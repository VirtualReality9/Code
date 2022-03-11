package Bridge;

/**
 * 抽象类
 */
abstract class DataChangeUtil {
    protected DataBase dataBase;
    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }
    abstract void operation();
}
/**
 * 扩充抽象类
 */
class TxtUtil extends DataChangeUtil {
    @Override
    public void operation() {
        dataBase.dataBase();
        System.out.println("Txt");
    }
}
class XmlUtil extends DataChangeUtil {
    @Override
    public void operation() {
        dataBase.dataBase();
        System.out.println("Xml");
    }
}
class PdfUtil extends DataChangeUtil {
    @Override
    public void operation() {
        dataBase.dataBase();
        System.out.println("Pdf");
    }
}
/**
 * 实现类接口
 */
interface DataBase {
    void dataBase();
}
class MySQL implements DataBase {
    @Override
    public void dataBase() {
        System.out.println("连接MySQL");
    }
}
class Oracle implements DataBase {
    @Override
    public void dataBase() {
        System.out.println("连接Oracle");
    }
}
/**
 * 客户端
 */
class Client {
    public static void main(String[] args) {
        DataChangeUtil dataChangeUtil = new TxtUtil();
        DataBase dataBase = new MySQL();
        dataChangeUtil.setDataBase(dataBase);
        dataChangeUtil.operation();
    }
}