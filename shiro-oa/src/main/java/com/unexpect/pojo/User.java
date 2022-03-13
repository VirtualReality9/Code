package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Integer userId;
    private String userPhone;
    private String userPassword;
    private String userName;
    private Employee employee;
    private String salt;
    private String profilePhoto;
    private String userAddress;
    private String contact;
    private List<NavigationBar> navigationBars;
    //多對多
    private List<Role> roles;
    //一对多
    private List<Permission> permissions;

}
