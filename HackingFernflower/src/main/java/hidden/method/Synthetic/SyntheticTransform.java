package hidden.method.Synthetic;

import org.objectweb.asm.*;

import java.io.*;

class HidMethodVisitor extends ClassVisitor {
    public HidMethodVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (name.equals("abc")){
            mv.visitAttribute(new SyntheticAttribute());
        }
        return mv;

    }

    @Override
    public void visitEnd() {

        super.visitEnd();
    }
}

public class SyntheticTransform {
    public static byte[] readBytes(String filepath) throws Exception {
        File file = new File(filepath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File Not Exist: " + filepath);
        }

        // 打开文件对象并创建文件输入流
        FileInputStream fis = new FileInputStream(file);

        // 定义每次输入流读取到的字节数对象
        int a = 0;

        // 定义缓冲区大小
        byte[] bytes = new byte[1024];

        // 创建二进制输出流对象
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // 循环读取文件内容
        while ((a = fis.read(bytes)) != -1) {
            // 截取缓冲区数组中的内容，(bytes, 0, a)其中的0表示从bytes数组的
            // 下标0开始截取，a表示输入流read到的字节数。
            out.write(bytes, 0, a);
        }
        return out.toByteArray();
    }


    public static void main(String[] args) throws Exception{
        String filepath = "HelloWorld.class";
        byte[] bytes1 = readBytes(filepath);

        //（1）构建ClassReader
        ClassReader cr = new ClassReader(bytes1);
        //（2）构建ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        //（3）串连ClassVisitor
        int api = Opcodes.ASM8;
        ClassVisitor cv = new HidMethodVisitor(api,cw);
        //（4）结合ClassReader和ClassVisitor
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);

        //（5）生成byte[]
        byte[] bytes2 = cw.toByteArray();

        new FileOutputStream(new File("HelloWorld2.class")).write(bytes2);

    }
}
