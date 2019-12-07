package cn.willlu.shorturl.aspect;

import cn.willlu.shorturl.entity.TbReportLog;
import cn.willlu.shorturl.repository.TbReportLogRepository;
import cn.willlu.shorturl.service.AsyncExecuteService;
import cn.willlu.shorturl.utils.IpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author willlu.zheng
 * @date 2019/12/7
 */
@Slf4j
@Aspect
@Component
public class ReportLogAspect {

    @Autowired
    private TbReportLogRepository reportLogRepository;

    @Pointcut("(@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.GetMapping )" +
            " || @annotation(org.springframework.web.bind.annotation.PostMapping )" +
            " || @annotation(org.springframework.web.bind.annotation.PutMapping )" +
            " || @annotation(org.springframework.web.bind.annotation.PatchMapping )" +
            " || @annotation(org.springframework.web.bind.annotation.DeleteMapping ))")
    public void controller() {
    }

    @Around("controller()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        JoinPointContext ctx = new JoinPointContext(joinPoint);
        long start = System.nanoTime();
        try {
            final Object returnValue = joinPoint.proceed();
            reportLog(ctx, start, returnValue);
            return returnValue;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void reportLog(JoinPointContext ctx, long start, final Object returnValue) {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();

        String userAgent = request.getHeader("user-agent");
        String ip = IpUtil.getRemoteAddr(request);
        String url = request.getRequestURL().toString();
        String method = request.getMethod();

        Map<String, String[]> map = request.getParameterMap();

        log.info("The ReportLog Run: RequestUrl={}, RequestMethod={}, RequestParams={}, RequestIp={}, UserAgent={}", url, method, JSON.toJSONString(map), ip, userAgent);


        AsyncExecuteService.submit(() -> {

            TbReportLog reportLog = reportLogRepository.findByIpAndUrlAndMethod(ip, url, method);

            if (reportLog == null) {
                reportLog = new TbReportLog();
                reportLog.setIp(ip);
                reportLog.setPv(0);
                reportLog.setUv(1);
                reportLog.setUrl(url);
                reportLog.setMethod(method);
                reportLog.setUserAgent(userAgent);
                reportLog.setVersion(0);
                reportLog.setUserAgent(userAgent);
            }

            reportLog.setVersion(reportLog.getVersion() + 1);
            reportLog.setPv(reportLog.getPv() + 1);
            reportLog.setUpdateAt(new Date());

            reportLogRepository.save(reportLog);

        });

        long stop = System.nanoTime();
        long diffNanos = stop - start;
        long diff = TimeUnit.MILLISECONDS.convert(diffNanos, TimeUnit.NANOSECONDS);
        log.info("The ReportLog JoinPoint RunTime = {}ms", diff);

    }

}
