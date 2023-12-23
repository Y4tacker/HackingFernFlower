import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.TraceClassVisitor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class generateASMCode {
    public static void main(String[] args) throws Exception{
        //需要处理的Class
        String inputFilename = "./target/classes/Test.class";
        // 输出文件的名字
        String outputFilename = "output.txt";
        FileInputStream fileInputStream = new FileInputStream(new File(inputFilename));
        // SKIP_DEBUG:用于指示ClassReader在读取类文件时是否跳过调试信息。调试信息包括源代码行号、局部变量名称和范围等信息
        // SKIP_FRAMES:指示ClassReader在读取类文件时是否跳过帧信息。帧信息是用于存储方法调用和异常处理的数据结构。如果指定了SKIP_FRAMES常量，那么在读取类文件时将会跳过帧信息，从而减少读取和处理的时间和内存消耗
        // EXPAND_FRAMES：指示在生成类文件时是否应该展开帧。帧用于在Java类文件中表示方法的执行状态，包括操作数栈和局部变量表的内容。如果指定了EXPAND_FRAMES常量，那么在生成类文件时将会展开帧信息，从而确保生成的类文件包含完整的帧信息
        int parsingOptions =  ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        Printer printer = new ASMifier();
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFilename));
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, printWriter);
        new ClassReader(fileInputStream).accept(traceClassVisitor, parsingOptions);
    }
}
