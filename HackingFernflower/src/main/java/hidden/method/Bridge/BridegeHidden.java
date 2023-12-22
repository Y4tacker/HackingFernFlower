package hidden.method.Bridge;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;

public class BridegeHidden implements Opcodes {
    public static byte[] dump () throws Exception {
        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        String className = "HelloWorld";
        String fieldName = "abc";
        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "HelloWorld", null, "java/lang/Object", null);

        {
            fieldVisitor = cw.visitField(ACC_PUBLIC | ACC_STATIC, fieldName, "Ljava/lang/String;", null, null);
            fieldVisitor.visitEnd();
        }
        {
            methodVisitor = cw.visitMethod(ACC_PUBLIC | ACC_STATIC| ACC_BRIDGE, "abc", "()V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitFieldInsn(GETSTATIC, "HelloWorld", fieldName, "Ljava/lang/String;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(2, 0);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "HelloWorld", "abc", "()V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitTypeInsn(NEW, "HelloWorld");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "HelloWorld", "<init>", "()V", false);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "HelloWorld", "abc", "()V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(2, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitLdcInsn("123");
            methodVisitor.visitFieldInsn(PUTSTATIC, "HelloWorld", fieldName, "Ljava/lang/String;");
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(1, 0);
            methodVisitor.visitEnd();
        }


        cw.visitEnd();

        return cw.toByteArray();
    }


    public static void main(String[] args) throws Exception{
        new FileOutputStream(new File("./output/HelloWorld.class")).write(dump());
    }
}