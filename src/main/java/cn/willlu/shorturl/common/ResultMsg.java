package cn.willlu.shorturl.common;

import lombok.Data;

/**
 * @author willlu.zheng
 * @date 2019-11-24
 */
@Data
public class ResultMsg<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResultMsg() {
        super();
    }

    public ResultMsg(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}