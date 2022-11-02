package com.example.demo.contoller;

import com.example.demo.service.UserRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/25
 * @Desc
 */
@RestController
@RequestMapping("/redis")
public class UserRedisController {
    public static final Logger logger = LoggerFactory.getLogger(UserRedisController.class);

    @Autowired
    UserRedisService userRedisService;

    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("RedisSessionId", "CookieTestInfo");
        response.addCookie(cookie);
        String value = cookie.getValue();
        logger.info("cookie value :{}", value);
        return value;
    }

    @GetMapping("/getCookie")
    public Map<String,String> getCookie(HttpServletRequest request) {
        Map<String,String> map = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie != null) {
                map.put(cookie.getName(),cookie.getValue());
                logger.info("cookie name:{}, cookie value:{}", cookie.getValue(),cookie.getName());
            }
        }
        return map;
    }
    @GetMapping("/setSession")
    public Map<String,Object> setSession(HttpServletRequest request){
        request.getSession().setAttribute("testKey","testValue");
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("testKey","testValue");
        return map;
    }
    @GetMapping("/getSession")
    public Object getSessions(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("testKey",request.getSession().getId());
        map.put("testValue",request.getSession().getAttribute("testKey"));
        return map;
    }
}