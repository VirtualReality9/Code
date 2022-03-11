package Prototype.WeeklyLog;

import java.io.*;

class Customer implements Cloneable {
    private Address address;
    private String name;
    private String password;

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Customer clone() {
        Object obj = null;
        try {
            if (obj == null)
                obj = super.clone();
        } catch (Exception e) {
            System.out.println("克隆失败");
            return null;
        }
        return (Customer) obj;
    }
}

class DeepCustomer implements Serializable {
    private Address address;
    private String name;
    private String password;

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public DeepCustomer deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(bao);

        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (DeepCustomer) ois.readObject();
    }
}

//class DeepCustomer2 implements Cloneable {
//    private Address address;
//    private String name;
//    private String password;
//
//    public Address getAddress() {
//        return address;
//    }
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public DeepCustomer2 clone() {
//        Object obj = null;
//        try {
//            if (obj == null) {
//                obj = (DeepCustomer2) super.clone();
//            }
//        } catch (Exception e) {
//            System.out.println("克隆失败");
//            return null;
//        }
//        return (DeepCustomer2) obj;
//    }
//}

class Address implements Serializable, Cloneable{
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
