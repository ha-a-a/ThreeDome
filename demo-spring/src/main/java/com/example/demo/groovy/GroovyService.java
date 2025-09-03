package com.example.demo.groovy;

import com.alibaba.fastjson.JSONObject;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilerConfiguration;

/**
 * @author tangmengyue
 * @Description
 * @createTime 2023年05月23日 11:00:00
 */
public class GroovyService {

    public void customGroovyConfigure() {
        CompilerConfiguration customConfig = new CompilerConfiguration();
        customConfig.setScriptBaseClass(MyScript.class.getName());
        Binding binding = new Binding();
        GroovyShell customShell = new GroovyShell(this.getClass().getClassLoader(), binding, customConfig);
        Script greet = customShell.parse("greet()");
        // property不生效
        greet.setProperty("name", "tmy");
        boolean b = greet instanceof MyScript;
        System.out.println("greet from MyScript:" + b);
        Object greetRun = greet.run();
        System.out.println("return greet data=" + JSONObject.toJSONString(greetRun));
        // 每次parse都会生成新的script对象，对应一个新的script${n}.groovy文件
        Script reply = customShell.parse("reply()");
        boolean isTure = reply instanceof MyScript;
        System.out.println("reply from MyScript:" + isTure);
        reply.setProperty("replyName", "tmy-fake");
        Object replyRun = reply.run();
        System.out.println("return reply data=" + JSONObject.toJSONString(replyRun));
    }
}
