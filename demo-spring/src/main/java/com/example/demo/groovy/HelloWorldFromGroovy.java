package com.example.demo.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author tangmengyue
 * @ClassName HelloWorldFromGroovy.java
 * @Description TODO
 * @createTime 2023年05月09日 18:34:00
 */
public class HelloWorldFromGroovy {
    public static void main(String[] args) throws IOException {
        testGroovyShell();
        testBindScript("groovy");
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

    public static void testBindScript(String something) throws IOException {
        Binding binding = new Binding();
        binding.setProperty("something", something);
        GroovyShell groovyShell = new GroovyShell(binding);
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        Script script = groovyShell.parse(new File(path + "script/HelloWorld.groovy"));
        script.invokeMethod("HelloSomething", null);
    }

    public static void testConfigure() {
        CompilerConfiguration customConfig = new CompilerConfiguration();
        customConfig.setScriptBaseClass("MyScript");
        GroovyShell customShell = new GroovyShell(ClassUtils.getDefaultClassLoader(), new Binding(), customConfig);
        Script greet = customShell.parse("greet");
        greet.setProperty("name", "tmy");
        greet.run();

        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        compilerConfiguration.setScriptBaseClass("MyScript1");
        GroovyShell compilerShell = new GroovyShell(ClassUtils.getDefaultClassLoader(), new Binding(), compilerConfiguration);
        Script myScriptMethod = compilerShell.parse("myScriptMethod");
        myScriptMethod.setProperty("name", "by yourself");
        myScriptMethod.run();

    }
}
