package com.unexpect.Result;


import com.unexpect.pojo.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一API响应结果封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code;
    private String message;
    private Object data;
    private Meta meta;
    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    // getter setter 省略，构造方法省略
    // 操作成功返回数据
    public static Result succ(Object data) {
        return succ(new Meta(200,"操作成功"), data);
    }

    public static Result succ(String msg) {
        return succ(new Meta(200,msg), null);
    }


    public static Result succ(Meta meta, Object data) {
        Result r = new Result();
        r.setData(data);
        r.setMeta(meta);
        return r;
    }

    public static Result succ(String msg, Object data) {
        return succ(new Meta(200,msg),data);
    }

    // 操作异常返回
    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setMeta(new Meta(code,msg));
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {
        return fail(400,msg,null);
    }

    public static Result fail(int code, String msg) {
        return fail(code,msg,"null");
    }

    public static Result fail(String msg, Object data) {
        return fail(400,msg,data);
    }
}
