package com.example.demo.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author tangmengyue
 * @ClassName HelloWorldFromGroovy.java
 * @Description TODO
 * @createTime 2023年05月09日 18:34:00
 */
public class MainGroovy {
    public static void main(String[] args) throws IOException {
//        testStaticCompile();
        testConfigure();
    }

    public static void testGroovyShell() {
        GroovyShell groovyShell = new GroovyShell();
        Script parse = groovyShell.parse("package com.example.demo.groovy\n" +
                "\n" +
                "def HelloWorld(){\n" +
                " println \"helloword!!\"\n" +
                "}");
        parse.invokeMethod("HelloWorld", null);
    }

    /**
     * 应用程序和script之间传递数据
     *
     * @param something
     * @throws IOException
     */
    public static void testBindScript(String something) throws IOException {
        Binding binding = new Binding();
        binding.setProperty("something", something);
        GroovyShell groovyShell = new GroovyShell(binding);
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        Script script = groovyShell.parse(new File(path + "script/HelloWorld.groovy"));
        script.invokeMethod("HelloSomething", null);
    }

    /**
     * 继承script类，自定义script的基类MyScript
     * Binding不生效
     */
    public static void testConfigure() {
        GroovyService groovyService = new GroovyService();
        groovyService.customGroovyConfigure();
    }

    /**
     * src下的groovy自动编译为class文件
     */
    public static void testStaticCompile() {
        MyStaticCompileScript myScript1 = new MyStaticCompileScript();
        myScript1.setProperty("name", "hhhh");
        myScript1.myScriptMethod();
    }
}
