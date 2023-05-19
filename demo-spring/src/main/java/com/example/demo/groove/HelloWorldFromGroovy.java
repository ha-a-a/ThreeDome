package com.example.demo.groove;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.ant.Groovy;

/**
 * @author tangmengyue
 * @ClassName HelloWorldFromGroovy.java
 * @Description TODO
 * @createTime 2023年05月09日 18:34:00
 */
public class HelloWorldFromGroovy {
    public static void main(String[] args) {
        GroovyShell groovyShell = new GroovyShell();
        Script parse = groovyShell.parse("package com.example.demo.groove\n" +
                "\n" +
                "def HelloWorld(){\n" +
                " println \"helloword!!\"\n" +
                "}");
        parse.invokeMethod("HelloWorld",null);
    }
}
