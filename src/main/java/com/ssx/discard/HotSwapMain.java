package com.ssx.discard;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.util.HotSwapAgent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class HotSwapMain {

    public static void main(String[] args) throws IOException, NotFoundException, CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        AService aService = new AService();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if ("exit".equals(line)) {
                return;
            }
            if ("hotswap".equals(line)) {
                try(FileInputStream classfile = new FileInputStream("D:/hot/AService.class")) {
                    CtClass makeClass = classPool.makeClass(classfile, false);
                    HotSwapAgent.redefine(AService.class, makeClass);
                }
            }
            aService.print();
        }
    }
}
