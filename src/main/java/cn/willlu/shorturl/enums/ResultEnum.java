package cn.willlu.shorturl.enums;

/**
 * @author willlu.zheng
 * @date 2019-11-24
 */
public enum ResultEnum {

    UNKNOWN_ERROR(-1, false, "Unknown Error"),
    NOT_FOUNT(404, false, "Not Fount Resource"),
    ERROR(500, false, "Internal Server Error"),
    SUCCESS(200, true, "ok");

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