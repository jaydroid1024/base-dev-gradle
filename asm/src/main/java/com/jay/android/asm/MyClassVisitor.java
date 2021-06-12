package com.jay.android.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


/**
 * @author jaydroid
 * @version 1.0
 * @date 6/4/21
 */
class MyClassVisitor extends ClassVisitor {


    /**
     * Constructs a new {@link ClassVisitor}.
     *
     * @param api          the ASM API version implemented by this visitor. Must be one of {@link
     *                     Opcodes#ASM4}, {@link Opcodes#ASM5}, {@link Opcodes#ASM6} or {@link Opcodes#ASM7}.
     * @param classVisitor the class visitor to which this visitor must delegate method calls. May be
     */
    public MyClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    /**
     * Visits the header of the class.
     *
     * @param version    the class version. The minor version is stored in the 16 most significant bits,
     *                   and the major version in the 16 least significant bits.
     * @param access     the class's access flags (see {@link Opcodes}). This parameter also indicates if
     *                   the class is deprecated.
     * @param name       the internal name of the class (see {@link Type#getInternalName()}).
     * @param signature  the signature of this class. May be {@literal null} if the class is not a
     *                   generic one, and does not extend or implement generic classes or interfaces.
     * @param superName  the internal of name of the super class (see {@link Type#getInternalName()}).
     *                   For interfaces, the super class is {@link Object}. May be {@literal null}, but only for the
     *                   {@link Object} class.
     * @param interfaces the internal names of the class's interfaces (see {@link
     *                   Type#getInternalName()}). May be {@literal null}.
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    /**
     * Visits a method of the class. This method <i>must</i> return a new {@link MethodVisitor}
     * instance (or {@literal null}) each time it is called, i.e., it should not return a previously
     * returned visitor.
     *
     * @param access     the method's access flags (see {@link Opcodes}). This parameter also indicates if
     *                   the method is synthetic and/or deprecated.
     * @param name       the method's name.
     * @param descriptor the method's descriptor (see {@link Type}).
     * @param signature  the method's signature. May be {@literal null} if the method parameters,
     *                   return type and exceptions do not use generic types.
     * @param exceptions the internal names of the method's exception classes (see {@link
     *                   Type#getInternalName()}). May be {@literal null}.
     * @return an object to visit the byte code of the method, or {@literal null} if this class
     * visitor is not interested in visiting the code of this method.
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);

        if (!"<init>".equals(name) && mv != null) {
            //增加方法耗时
            mv = new MyMethodVisitor(mv);
        }

        Type
        return mv;
    }

    class MyMethodVisitor extends MethodVisitor {

        /**
         * Constructs a new {@link MethodVisitor}.
         *
         * @param api           the ASM API version implemented by this visitor. Must be one of {@link
         *                      Opcodes#ASM4}, {@link Opcodes#ASM5}, {@link Opcodes#ASM6} or {@link Opcodes#ASM7}.
         * @param methodVisitor the method visitor to which this visitor must delegate method calls. May
         */
        public MyMethodVisitor(MethodVisitor methodVisitor) {
            super(Opcodes.ASM5, methodVisitor);
        }

        @Override
        public void visitCode() {
            mv.visitCode();

            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitVarInsn(Opcodes.LSTORE, 0);
        }

        @Override
        public void visitInsn(int opcode) {
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {


                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                mv.visitVarInsn(Opcodes.LSTORE, 2);
                Label label7 = new Label();
                mv.visitLabel(label7);
                mv.visitLineNumber(23, label7);
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
                mv.visitInsn(Opcodes.DUP);
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
                mv.visitLdcInsn("all time is ");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                mv.visitVarInsn(Opcodes.LLOAD, 2);
                mv.visitVarInsn(Opcodes.LLOAD, 0);
                mv.visitInsn(Opcodes.LSUB);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);


            }
            mv.visitInsn(opcode);

        }
    }
}
