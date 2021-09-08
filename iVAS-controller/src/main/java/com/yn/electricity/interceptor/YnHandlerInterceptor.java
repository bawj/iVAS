package com.yn.electricity.interceptor;

import Aladdin.Hasp;
import Aladdin.HaspStatus;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.yn.electricity.utils.BizBusinessUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ClassName: YNHandlerInterceptor
 *
 * @author zzs
 * Date: 2021/1/20 14:29
 * Description:
 */
@Slf4j
@Component
public class YnHandlerInterceptor extends HandlerInterceptorAdapter {

    private LocalDateTime time = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        hasp();
        return true;
    }


    private void hasp() {
        if (time != null){
            Duration betweenCreate = LocalDateTimeUtil.between(time, LocalDateTime.now());
            long l = betweenCreate.toMinutes();
            if (l < 30){
                log.info("#YnHandlerInterceptor.hasp# return");
                return;
            }
        }
        log.info("#YnHandlerInterceptor.hasp# 检查加密狗是否过期");
        long feature = 3005;
        Hasp hasp = new Hasp(feature);
        String vendorCode = "QUDEtJPNN+hhcx2xYT78ImG6yur38xMXVY7OuyePF+gFLDA836i0AsY7flBmOIj5kCxvZqHHyXj7QgSm" +
                "B/l5hQxFNQtzK1PDNkcpgdHmALQ+mzyzCVU3laLI9JTwSRQjlEz8Jqch9ryBxuYUhFZ+KVcZEwkY2ekM" +
                "mUoXT/H1S7cyRRp5ztwAAWssEJcAjQOSWRZSWy1xjV3WTCoiI686ue6V3QHkYOpdLizi07lEfJrLcEUm" +
                "yn/c7kJ/2zkgM+d1zbQNelA+M/9wBlfvE9wosYgd5MBOgRIMPsK9ZUFO/71ZnG1N2ctZui66xY4eHjyD" +
                "NxDJzUA7csH4vu0lFae9QHLnhcU9SnAfWbURyyCkUyK3nrojiet/PyiwLtw8dMcqQuDkscaFtj0uKRfD" +
                "0R3wJdA3XUd2N0HofvEsG6xExjagBgItG6iYCz5/EKVHRSVrUf0BuLmW2QkWZc7npQvgYticKg5C664g" +
                "ObJKL78ikf3UKjbx3iF6eT+xj4XZapN/0J8RL35i/sQLRD5H1+wDtdYoC2Tk3FFs7C9iMVx/qAxaSOTq" +
                "9/INl5QPcZKXVOPE+UjpOlr5ENp1sk6DTTA/zSAD96t8vu9WED0dDAJaWCVQ4DCaDoOw14LrvGwRvrDl" +
                "jpTZBY9t9dzAg8gCgt6CzbNyO93NQZdm3UKWg4C7SuDRQcupYF40d9B9f49BmqsyWgjHxyqmVNLP+N15" +
                "XOAZhSqIXrnsJvbJpIZ+pBVS3rs1G5xvm1wS6Y4jNYAaZHrv6VtXKcr6ql4rJtJKy75JbM/tbpr13IND" +
                "naKbSudyS3G7qiB848T4gPmUHpQ3EMIBAdkLukxl+Nq5Gko3p/EhrUKKMEvPiOayDIx2H00YW6aivxHf" +
                "2h8wAFotm4/zGjehlRdKrObQb/BsWcUB2EpqGbuQOzxwsEsl4t6M49/g1YTYji7Kiyf3tQKyGdJUYagy" +
                "Nx3Vu5T9FrfWYD+EEseqYQ==";
        hasp.login(vendorCode);
        int status = hasp.getLastError();
        if (HaspStatus.HASP_STATUS_OK != status) {
            if (HaspStatus.HASP_FEATURE_EXPIRED == status) {
                BizBusinessUtils.bizBusinessException("加密授权已过期");
            } else {
                BizBusinessUtils.bizBusinessException("加密授权读取失败,错误码:" + status);
            }
        } else {
            hasp.logout();
        }
        time = LocalDateTime.now();
    }

}
