package cn.willlu.shorturl.aspect;

import cn.willlu.shorturl.common.ResultMsg;
import cn.willlu.shorturl.enums.ResultEnum;
import cn.willlu.shorturl.utils.UrlUtil;
import cn.willlu.shorturl.web.vo.LongUrlVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

/**
 * @author willlu.zheng
 * @date 2019-11-25
 */
@Aspect
@Component
public class ParameterPretreatmentAspect {

    @Around("execution(* cn.willlu.shorturl.web.controller.ShortUrlController.compress(..))")
    public Object parameterPretreatmentUrl(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        LongUrlVO longUrlVO = (LongUrlVO) args[0];
        try {
            String url = longUrlVO.getLongUrl();

            if (!UrlUtil.checkHttpUrl(url)) {
                throw new MalformedURLException();
            }

            longUrlVO.setLongUrl(UrlUtil.autoCompletionUrl(url));

            return joinPoint.proceed(new Object[]{longUrlVO});

        } catch (MalformedURLException e) {
            return new ResultMsg(ResultEnum.ERROR.getCode(), "url is not valid", null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return new ResultMsg(ResultEnum.ERROR.getCode(), "unknown error", null);
        }
    }

}
