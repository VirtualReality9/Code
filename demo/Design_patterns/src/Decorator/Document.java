package Decorator;

/**
 * 抽象构件
 */
abstract class Document {
    abstract void display();
}
/**
 * 具体构件
 */
class PurchaserRequest extends Document {
    public void display() {
        System.out.println("PurchaserRequest的display()方法");
    }
}
/**
 * 抽象装饰类
 */
class Decorator extends Document {
    private Document document;
    public Decorator(Document document) {
        this.document = document;
    }
    public void display() {
        document.display();
    }
}
/**
 * 具体装饰类
 */
class Approve extends Decorator {
    public Approve(Document document) {
        super(document);
        System.out.println("增加审批功能");
    }
    public void approve() {
        System.out.println("Approve的approve()方法");
    }
}
class RemoveFile extends Decorator {
    public RemoveFile(Document document) {
        super(document);
        System.out.println("增加删除文件功能");
    }
    public void remove() {
        System.out.println("RemoveFile的remove()方法");
    }
}
/**
 * 客户端
 */
class Client1 {
    public static void main(String[] args) {
       Document document = new PurchaserRequest();
       Approve approve = new Approve(document);
       RemoveFile removeFile = new RemoveFile(approve);
       removeFile.display();
       removeFile.remove();
    }
}
