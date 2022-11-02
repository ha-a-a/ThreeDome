package com.example.demo.log.aop;

import com.example.demo.log.OperateLogService;
import com.example.demo.log.pojo.OperateLog;
import com.example.demo.log.pojo.OperateLogPojo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.eclipse.jetty.server.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/19
 * @Desc
 */
@Aspect
@Component
@Slf4j
public class OperateLogAspect {
    @Autowired
    OperateLogService operateLogService;

    @Pointcut("@annotation(com.example.demo.log.pojo.OperateLog)")

    private void pointCutMethod() {
    }

    @After("pointCutMethod()")
    private void after(JoinPoint joinPoint) throws Exception {
        log.info("开始记录操作日志");
        OperateLogPojo operateLogPojo = createOperateLogPojo(joinPoint);
        operateLogService.save(operateLogPojo);
        log.info("操作日志入库成功");
    }

    private OperateLogPojo createOperateLogPojo(JoinPoint joinPoint) {
        OperateLogPojo operateLogPojo = new OperateLogPojo();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        for (Object arg:args){
            if (arg.getClass().equals(Request.class)){
                HttpServletRequest request= (HttpServletRequest) arg;
                // todo 用户相关信息
                String ipAddress = request.getRemoteAddr();
                String remoteHost = request.getRemoteHost();
                int remotePort = request.getRemotePort();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                        ipAddress = inet.getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
                operateLogPojo.setIp(ipAddress);
                break;
            }
        }
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        for (Method method : methods) {
            if (!signature.getName().equals(method.getName())) {
                continue;
            }
            OperateLog annotation = method.getAnnotation(OperateLog.class);
            operateLogPojo.setModuleName(annotation.module());
            operateLogPojo.setWebName(annotation.webName());
            operateLogPojo.setMemo(annotation.desc());
            operateLogPojo.setAction(annotation.action());
            operateLogPojo.setRemark(annotation.remark());
            break;
        }
        return operateLogPojo;
    }
}
