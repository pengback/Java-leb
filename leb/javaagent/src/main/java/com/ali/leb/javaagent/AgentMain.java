package com.ali.leb.javaagent;

import javassist.ClassPool;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @Author: aliber
 * @Date: 2021/3/8 下午11:26
 **/
public class AgentMain {

    public static void premain(String arg, Instrumentation instrumentation) {
        System.out.println("Hello Agent Permain");

        // 基于工具 在运行时修改 class 字节码，就是所谓的插桩

        final ClassPool pool = new ClassPool();
        pool.appendSystemPath();

        // 添加类加载过滤器
        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println(className);
                return null;
            }
        });

    }

}
