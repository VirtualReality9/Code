package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoList {

    private int todoId;
    private String todoTime;
    private String todoContent;
    private String todoStatus;
    private String todoNote;
    private Employee employee;
}
