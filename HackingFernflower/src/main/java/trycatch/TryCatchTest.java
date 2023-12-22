package trycatch;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;


public class TryCatchTest implements Opcodes {
    public static byte[] dump () throws Exception {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MethodVisitor mv;

        cw.visit(V1_6, ACC_PUBLIC | ACC_SUPER, "HelloWorld", null, "java/lang/Object", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC|ACC_STATIC, "abc", "()V", null, null);
            mv.visitCode();
            Label startLabel = new Label();
            Label endLabel = new Label();
            Label handlerLabel = new Label();

            mv.visitTryCatchBlock(startLabel, endLabel, handlerLabel, "java/lang/Exception");
            mv.visitLabel(startLabel);
            mv.visitInsn(ICONST_M1);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("1");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitLabel(endLabel);
            mv.visitLabel(handlerLabel);
            mv.visitInsn(RETURN);



            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "HelloWorld", "abc", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }


        cw.visitEnd();

        return cw.toByteArray();
    }


    public static void main(String[] args) throws Exception{
        new FileOutputStream(new File("./output/HelloWorld.class")).write(dump());
    }
}