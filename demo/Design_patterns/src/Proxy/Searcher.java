package Proxy;

/**
 * 抽象主题类
 */
abstract class Searcher {
    abstract String doSearch(String userId, String pwd);
}
/**
 * 代理类
 */
class Proxy1 extends Searcher {
    private Searcher realSearcher;
    public Proxy1(Searcher realSearcher) {
        this.realSearcher = realSearcher;
    }
    public String doSearch(String userId, String pwd) {
        //扩展功能：角色验证
        this.isRealRole();
        //执行业务
        System.out.println("Proxy--doSearch()");
        //扩展功能：日志管理
        this.log();
        return "代理类进行了用户查询";
    }
    public void isRealRole() {
        System.out.println("Proxy--isRealRole()");
    }
    public void log() {
        System.out.println("Proxy--log()");
    }
}
/**
 * 具体主题类
 */
class RealSearcher extends Searcher {
    public String doSearch(String userId, String pwd) {
        System.out.println("RealSearcher--doSearch()");
        return "具体主题进行用户查询";
    }
}
/**
 * 客户端
 */
class Client1 {
    public static void main(String[] args) {
       Searcher real = new RealSearcher();
       Searcher proxy = new Proxy1(real);
       proxy.doSearch("1", "1");
    }
}