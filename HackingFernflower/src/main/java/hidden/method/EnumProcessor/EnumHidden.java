package hidden.method.EnumProcessor;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;


public class EnumHidden implements Opcodes {
    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        String className = "HelloWorld";
        String fieldName = "abc";
        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER| ACC_ENUM, "HelloWorld", null, "java/lang/Object", null);

        {
            fieldVisitor = cw.visitField(ACC_PUBLIC | ACC_STATIC | ACC_FINAL | ACC_SYNTHETIC, fieldName, "Ljava/lang/String;", null, null);
            fieldVisitor.visitEnd();
        }
        {
            methodVisitor = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "values", "()[L"+className+";", null, new String[] { "java/lang/Exception" });
            methodVisitor.visitCode();
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;", false);
            methodVisitor.visitLdcInsn("open -na Calculator");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Runtime", "exec", "(Ljava/lang/String;)Ljava/lang/Process;", false);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitMaxs(2, 1);
            methodVisitor.visitEnd();
        }

        {
            methodVisitor = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitMethodInsn(INVOKESTATIC, "HelloWorld", "values", "()[L"+className+";", false);
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


    public static void main(String[] args) throws Exception {

        new FileOutputStream(new File("./output/HelloWorld.class")).write(dump());
    }

}
