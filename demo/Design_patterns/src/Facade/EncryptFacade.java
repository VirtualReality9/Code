package Facade;

/**
 * 外观类
 */
class EncryptFacade {
    private FileReader fileReader = new FileReader();
    private CipherMachine cipherMachine = new CipherMachine();
    private FileWriter fileWriter = new FileWriter();

    public void method() {
        String fileFrom = fileReader.read("test.txt");
        String encryptFile = cipherMachine.encrypt(fileFrom);
        fileWriter.write(encryptFile, "encrypt.txt");
    }
}
/**
 * 子系统
 */
class FileReader {
    public String read(String fileNameFrom) {
        System.out.println("读取源文件");
        return "";
    }
}
class CipherMachine {
    public String encrypt(String fileNameFrom) {
        System.out.println("加密");
        return "";
    }
}
class FileWriter {
    public void write(String encryptText, String fileNameTo) {
        System.out.println("保存加密文件");
    }
}
/**
 * 客户端
 */
class Client1 {
    public static void main(String[] args) {
        EncryptFacade encryptFacade = new EncryptFacade();
        encryptFacade.method();
    }
}