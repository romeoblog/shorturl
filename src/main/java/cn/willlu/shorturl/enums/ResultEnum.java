package cn.willlu.shorturl.enums;

/**
 * @author willlu.zheng
 * @date 2019-11-24
 */
public enum ResultEnum {

    UNKNOWN_ERROR(-1, false, "未知错误"),
    ERROR(1020, false, "网路错误"),
    SUCCESS(10000, true, "成功");

    private Integer code;
    private Boolean status;
    private String msg;

    ResultEnum(Integer code, Boolean status, String msg) {
        this.code = code;
        this.status = status;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}