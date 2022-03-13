package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NavigationBar implements Serializable {

    private int nbid;
    private String navname;
    private int fatherNode;
    private int order;
    private String path;
    private Integer permissionId;
    // 模块集合
    private List<NavigationBar> childList;
}
