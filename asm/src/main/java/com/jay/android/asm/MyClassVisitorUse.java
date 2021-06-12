package com.jay.android.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;


/**
 * @author jaydroid
 * @version 1.0
 * @date 6/4/21
 */
class MyClassVisitorUse {
    public static void main(String[] args) throws Exception {

        ClassReader classReader = new ClassReader("com/jay/android/asm/ASM");

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        ClassVisitor classVisitor = new MyClassVisitor(classWriter);

        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();

        //输出
        File f = new File("/Users/xuejiewang/AndroidStudioProjects/Jay/base-dev-gradle/asm/build/classes/java/main/com/jay/android/asm/ASM.class");
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        fileOutputStream.write(data);
        fileOutputStream.close();

        System.out.println("MyClassVisitorUse,输出");
    }
}
