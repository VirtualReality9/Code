package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    private Integer permissionId;
    private String permissionName;
    private String keyword;
    private String permissionUrl;
    //一个权限包含多个角色
    // private Set<com.unexpect.oa.pojo.Role> permission_roles = new HashSet<com.unexpect.oa.pojo.Role>(0) ;
}
