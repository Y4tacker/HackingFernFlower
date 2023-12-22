package JSR;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;

public class SwapTest implements Opcodes {

    public static byte[] dump () throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        MethodVisitor methodVisitor;

        classWriter.visit(V1_6, ACC_PUBLIC | ACC_SUPER, "Y4Calculator", null, "java/lang/Object", null);

        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "calc", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            Label label1 = new Label();
            methodVisitor.visitJumpInsn(JSR, label0);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn("1");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitLabel(label0);
            methodVisitor.visitJumpInsn(JSR, label1);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitInsn(SWAP);
            methodVisitor.visitVarInsn(ASTORE, 0);
            methodVisitor.visitVarInsn(RET, 0);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(3, 2);
            methodVisitor.visitEnd();
        }

        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitMethodInsn(INVOKESTATIC, "Y4Calculator", "calc", "()V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(0, 1);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }

    public static void main(String[] args) throws Exception {try {
        byte[] bytes = POPTest.dump();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./output/Y4Calculator.class"));
        fileOutputStream.write(bytes);
    } catch (Exception e) {
        e.printStackTrace();
    }


    }
}



