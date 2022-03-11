package Prototype.WeeklyLog;

class WeeklyLog implements Cloneable {

    private String name;
    private String data;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public WeeklyLog clone() {
        Object obj = null;
        try {
            obj = super.clone();
            return (WeeklyLog) obj;
        } catch (CloneNotSupportedException e) {
            System.out.println("不支持复制");
            return null;
        }
    }
}

class Client {
    public static void main(String[] args) {
        WeeklyLog log_pre = new WeeklyLog();
        log_pre.setName("张无忌");
        log_pre.setData("第十二周");
        log_pre.setContent("本周很忙，一直加班");

        System.out.println("****周报****");
        System.out.println("周次：" +  log_pre.getData());
        System.out.println("姓名：" +  log_pre.getName());
        System.out.println("内容：" +  log_pre.getContent());
        System.out.println("--------------------------------");

        WeeklyLog log_new = log_pre.clone();
        log_new.setData("第十三周");

        System.out.println("****周报****");
        System.out.println("周次：" + log_new.getData());
        System.out.println("姓名：" + log_new.getName());
        System.out.println("内容：" + log_new.getContent());

        //思考
        System.out.println(log_pre == log_new);
        System.out.println(log_pre.getData() == log_new.getData());
        System.out.println(log_pre.getName() == log_new.getName());
        System.out.println(log_pre.getContent() == log_new.getContent());
    }
}
