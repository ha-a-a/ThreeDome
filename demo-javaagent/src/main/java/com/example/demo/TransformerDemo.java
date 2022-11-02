package com.example.demo;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * @description: 反射class
 * @author: mengyuetang
 * @email: 
 * @date: 2022/1/29 17:50
 */
public class TransformerDemo implements ClassFileTransformer {
    // 指定要修改的函数
    private String needEditClassName;
    private String editMethod;

    public TransformerDemo(String modifiedClassName, String modifiedClassMethod) {

        this.needEditClassName = modifiedClassName;
        this.editMethod = modifiedClassMethod;
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        System.out.println("TransformerDemo begin,needEditClassName=" + needEditClassName + ",editMethod=" + editMethod);
        try {
            ClassPool classPool = new ClassPool(true);
            classPool.insertClassPath("D:\\myspace\\ThreeDome\\demo-javaagent-hello\\target\\classes");
            CtClass ctClass = classPool.get(needEditClassName);
            CtMethod method = ctClass.getDeclaredMethod(editMethod);
            String source = "{System.out.println(\"hello transformer!\");}";
            method.setBody(source);
            byte[] bytes = ctClass.toBytecode();
            ctClass.detach();
            return bytes;
        } catch (IOException | CannotCompileException | NotFoundException e) {
            System.out.println("TransformerDemo error," + e.getMessage());
            e.printStackTrace();
        }
        return new byte[0];
    }
}
