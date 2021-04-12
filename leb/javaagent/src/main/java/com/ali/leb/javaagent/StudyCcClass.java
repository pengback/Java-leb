package com.ali.leb.javaagent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

import java.io.FileOutputStream;

/**
 * @Author: aliber
 * @Date: 2021/3/11 上午12:29
 **/
public class StudyCcClass {
    public static void main(String[] args) {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.ali.leb.javaagent.Study");
        try {
            ctClass.addField(CtField.make("private int age;", ctClass));
            //添加setAge方法
            ctClass.addMethod(CtMethod.make("public void setAge(int age){this.age = age;}", ctClass));
            //添加getAge方法
            ctClass.addMethod(CtMethod.make("public int getAge(){return this.age;}", ctClass));
            //将ctClass生成字节数组，并写入文件
            byte[] byteArray = ctClass.toBytecode();
            FileOutputStream output = new FileOutputStream("./out/Study.class");
            output.write(byteArray);
            output.close();
            System.out.println("文件写入成功!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
