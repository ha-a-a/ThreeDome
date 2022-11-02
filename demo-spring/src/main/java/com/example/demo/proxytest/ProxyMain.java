package com.example.demo.proxytest;

import com.example.demo.proxytest.cglibproxy.CglibProxyFactory;
import com.example.demo.proxytest.cglibproxy.UserDao;
import com.example.demo.proxytest.dynamicproxy.ProxyFactory;
import com.example.demo.proxytest.staticproxy.IUserDao;
import com.example.demo.proxytest.staticproxy.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/5/28
 * @Desc
 */
public class ProxyMain {
    private static Logger log = LoggerFactory.getLogger(ProxyMain.class);

    public static void main(String[] args) {
        // 静态代理,在代码运行前，.class文件就已经存在

//        IUserDao userDao = new UserDaoImpl();
//        UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
//        userDaoProxy.save();ProxyGenerator
        // 将jdk生成的代理对象输出到磁盘中，填入ProxyGenerator中的saveGeneratedFiles变量中的参数
        // 见D:\myspace\ThreeDome\com\sun\proxy\\$Proxy.class
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 动态代理 jdk api
        IUserDao userDao = new UserDaoImpl();
        log.info("userDao={}", userDao.getClass());
        IUserDao proxyInstance = (IUserDao) new ProxyFactory(userDao).getProxyInstance();
        log.info("instance={}", proxyInstance.getClass());
        proxyInstance.save();

        // 将cglib生成的代理对象输出到磁盘中
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\myspace\\ThreeDome\\demo-spring\\src\\main\\java");
        // cglib代理又叫子类代理，运行时在内存中生成子类对象实现对目标对象的扩展，既能代理实现接口的对象也能代理没实现接口的对象。
        // 常用spring aop,在Spring的AOP编程中:
        //如果加入容器的目标对象有实现接口,用JDK代理
        //如果目标对象没有实现接口,用Cglib代理
        UserDao cglibUserDao = new UserDao();
        UserDao cglibProxyInstance = (UserDao) new CglibProxyFactory(cglibUserDao).getProxyInstance();
        cglibProxyInstance.save();
        // final的类（异常）和方法（直接调用目标类的方法）不能被代理
        cglibProxyInstance.update();
    }
}
