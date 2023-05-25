package com.example.demo.groovy;

import groovy.lang.*;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
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
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ScriptException, ResourceException, InterruptedException {
//        testGroovyShell();
//        testBindScript("tttt");
//        testConfigure();
//        testClassLoader();
        testDynamicLoader();
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
        Script script = groovyShell.parse(new File(path + "/script/HelloWorld.groovy"));
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

    public static void testClassLoader() throws InstantiationException, IllegalAccessException, IOException {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        // 内存溢出
        Class foo1 = groovyClassLoader.parseClass("class Foo{}");
        Class foo2 = groovyClassLoader.parseClass("class Foo{}");
        System.out.println("foo name=" + (foo1.getName().equals("Foo")) + "," + (foo2.getName().equals("Foo")));
        System.out.println("foo1==foo2 - " + (foo1 == foo2));
        // from File
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        File file1 = new File(path + "script/Foo.groovy");
        File file2 = new File(path + "script/Foo.groovy");
        Class myScript1 = groovyClassLoader.parseClass(file1);
        Class myScript2 = groovyClassLoader.parseClass(file2);
        System.out.println("myScript name=" + (myScript1.getName().equals("Foo"))
                + "," + (myScript2.getName().equals("Foo")));
        System.out.println("myScript1==myScript2 - " + (myScript1 == myScript2));
        GroovyObject foo = (GroovyObject) myScript1.newInstance();
        foo.setProperty("name", "aaaaaa");
        foo.invokeMethod("printlnName", null);
    }

    /**
     * class会动态加载ReloadingTest.groovy，然后生成相同的class，不同的新groovyObject
     */
    public static void testDynamicLoader() throws IOException, ScriptException, ResourceException, InterruptedException {
        GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine("/Users/tangmengyue/tmyspace/sohuspace/project/ThreeDome/demo-spring/src/main/resources" + "/script");
        Binding binding = new Binding();
        int i = 0;
        while (i < 100) {
            GroovyObject groovyObject = (GroovyObject) groovyScriptEngine.run("ReloadingTest.groovy", binding);
            System.out.println("run object=" + groovyObject);
            groovyObject.setProperty("name", "tmma" + i);
            groovyObject.invokeMethod("sayHello", null);
            Thread.sleep(1000);
            i++;
        }
    }
}
