package JSR;

import java.io.File;
import java.io.FileOutputStream;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.RecordComponentVisitor;


public class POPTest implements Opcodes {

    public static byte[] dump () throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

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
            Label label1 = new Label();
            Label label2 = new Label();

            methodVisitor.visitJumpInsn(JSR, label1);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn("Y4tacker666");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitJumpInsn(JSR, label2);
            methodVisitor.visitIntInsn(BIPUSH, 6);
            methodVisitor.visitVarInsn(ISTORE, 0);
            methodVisitor.visitIntInsn(BIPUSH, 6);
            methodVisitor.visitVarInsn(ISTORE, 1);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            methodVisitor.visitLdcInsn("6+6=?");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            methodVisitor.visitVarInsn(ILOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitInsn(IADD);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitVarInsn(ASTORE, 0);
            methodVisitor.visitVarInsn(RET, 0);
            methodVisitor.visitMaxs(10, 10);
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

