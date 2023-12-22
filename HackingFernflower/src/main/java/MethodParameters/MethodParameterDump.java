package MethodParameters;

import org.objectweb.asm.*;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
class MethodParameterAttribute extends Attribute {
    public int length;
    public int index;

    public MethodParameterAttribute(int length,int index) {
        super("MethodParameters");
        this.length = length;
        this.index = index;
    }
    @Override
    protected ByteVector write(ClassWriter cw, byte[] code, int len, int maxStack, int maxLocals) {
        ByteVector byteVector = new ByteVector();
        byteVector.putByte(length);
        for (int i = 0; i < length; i++) {
            byteVector.putByte(0);
            byteVector.putByte(5);
            byteVector.putByte(0);
            byteVector.putByte(8);
        }

        return byteVector;
    }
}

public class MethodParameterDump implements Opcodes {
    public static byte[] dump () throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor mv;
        AnnotationVisitor annotationVisitor0;

        String className = "佛祖在此";
        String superName = "java/lang/Object";
        String[] classInterface = null;
        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, className, null, superName, classInterface);
        {
            String fieldNamee = "耐心一点再看看吧！";
//            fieldNamee  =
//                "\n                 _ooOoo_                脚    静心致远    耐\n" +
//                        "                o8888888o               本               心\n" +
//                        "                88\" . \"88               小               推\n" +
//                        "                (| -_- |)               子               敲\n" +
//                        "                O\\  =  /O               且               智\n" +
//                        "             ____/`---'\\____            慢               慧\n" +
//                        "           .'  \\\\|     |//  `.          躁               照\n" +
//                        "          /  \\\\|||  :  |||//  \\\n" +
//                        "         /  _||||| -:- |||||-  \\\n" +
//                        "         |   | \\\\\\  -  /// |   |\n" +
//                        "         | \\_|  ''\\---/''  |   |\n" +
//                        "         \\  .-\\__  `-`  ___/-. /\n" +
//                        "       ___`. .'  /--.--\\  `. . __\n" +
//                        "    .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n" +
//                        "   | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
//                        "   \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
//                        "======`-.____`-.___\\_____/___.-`____.-'======\n" +
//                        "                 `=---='\n" +
//                        "           佛祖保佑       Y4test\n" +
//                        "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
//                        "               \n";
            fieldVisitor = cw.visitField(ACC_PUBLIC | ACC_STATIC | ACC_FINAL| ACC_SYNTHETIC, fieldNamee, "Ljava/lang/String;", null, null);
            fieldVisitor.visitEnd();
        }

        {
            mv = cw.visitMethod(ACC_PUBLIC, "蓝队大哥先喝口茶吧", "(Ljava/lang/String;Ljava/lang/String;I)V", null, null);
            mv.visitAttribute(new MethodParameterAttribute(3, 5));
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "split", "(Ljava/lang/String;)[Ljava/lang/String;", false);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitInsn(AALOAD);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 6);
            mv.visitEnd();
        }
////
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
            mv.visitTypeInsn(NEW, className);
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, className, "<init>", "()V", false);
            mv.visitLdcInsn("笑了=急了");
            mv.visitLdcInsn("=");
            mv.visitInsn(ICONST_0);
            mv.visitMethodInsn(INVOKEVIRTUAL, className, "蓝队大哥先喝口茶吧", "(Ljava/lang/String;Ljava/lang/String;I)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 1);
            mv.visitEnd();
        }


        cw.visitEnd();

        return cw.toByteArray();
    }




    public static void main(String[] args) throws Exception{
        int ch1 = 0;
        int ch2 = 0;
        int ch3 = 0;
        int ch4 = 3;
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        System.out.println(((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0)));
        new FileOutputStream(new File("./output/佛祖在此.class")).write(dump());
    }
}