package cn.willlu.shorturl.utils;

import cn.willlu.shorturl.common.ResultMsg;
import cn.willlu.shorturl.enums.ResultEnum;

/**
 * @author willlu.zheng
 * @date 2019-11-24
 */
public class ResultUtil {

    public static ResultMsg ok(Object object) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(ResultEnum.SUCCESS.getCode());
        resultMsg.setMsg(ResultEnum.SUCCESS.getMsg());
        resultMsg.setData(object);
        return resultMsg;
    }

    public static ResultMsg success() {
        return ok(null);
    }

    public static ResultMsg error(Integer code, String msg) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(code);
        resultMsg.setMsg(msg);
        return resultMsg;
    }
}