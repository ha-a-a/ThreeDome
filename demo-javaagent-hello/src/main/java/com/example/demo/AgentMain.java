package com.example.demo;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

/**
 * @description: jvm链接agent
 * @author: mengyuetang
 * @email: 
 * @date: 2022/1/18 15:18
 */
public class AgentMain {


    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        // 获取运行的虚拟机
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            String id = vmd.id();
            String jarName = "D:\\myspace\\ThreeDome\\demo-javaagent\\target\\demo-javaagent-0.0.1-SNAPSHOT.jar";
            System.out.println("id = [" + id + "]" + ",jarName = [" + jarName + "]");
            VirtualMachine attach = VirtualMachine.attach(id);
            attach.loadAgent(jarName, "com.example.demo.Hello,hello");
            attach.detach();
        }

    }

}

