package cn.willlu.shorturl.exception;

import cn.willlu.shorturl.common.ResultMsg;
import cn.willlu.shorturl.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author willlu.zheng
 * @date 2019-11-24
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = NotFountResourceException.class)
    public ResultMsg notFountResourceException(HttpServletRequest req, NotFountResourceException ex) {
        log.error(ExceptionUtils.getMessage(ex));
        return new ResultMsg(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg(), null);
    }


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultMsg globalExceptionHandler(HttpServletRequest req, Exception ex) {
        log.error(ExceptionUtils.getMessage(ex), ex);
        return new ResultMsg(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg(), ex.getMessage());
    }

}
