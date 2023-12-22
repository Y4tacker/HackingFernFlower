package DOS;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;


public class DOS implements Opcodes {
    public static byte[] dump () throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;
        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "HelloWorld", null, "java/lang/Object", null);


        {
            methodVisitor = cw.visitMethod(ACC_PUBLIC | ACC_STATIC , "abc", "(Ljava/lang/String)Ljava/lang/String;", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }

        cw.visitEnd();

        return cw.toByteArray();
    }


    public static void main(String[] args) throws Exception{
        new FileOutputStream(new File("./output/HelloWorld.class")).write(dump());
    }
}