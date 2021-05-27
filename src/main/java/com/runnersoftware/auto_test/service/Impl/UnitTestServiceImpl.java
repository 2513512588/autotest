package com.runnersoftware.auto_test.service.Impl;

import com.runnersoftware.auto_test.service.TestService;
import org.apache.catalina.connector.OutputBuffer;
import org.springframework.stereotype.Service;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.Collections;

@Service
public class UnitTestServiceImpl implements TestService{

    static String PATH = "D:\\test\\";

    @Override
    public Boolean comile(Object expect, String code, Object[] args) throws Exception {
        // javqComile 是java程序里的java编译器类
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();


        // StandardJavaFileManager 对象主要负责
        // 编译文件对象的创建，编译的参数等等，我们只对它做些基本设置比如编译 CLASSPATH 等。
        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);

        // JavaFileObject 为类文件上进行操作的工具的文件抽象
        String className = "TestClazz";

        String javaSource = "public class TestClazz {   " +
                "public static Object test(Object[] args){" +
                    code +
                "}" +
            "}";
        JavaFileObject javaFileObject = new StringObject(className, javaSource);

        // Array Map Set 等都属于Itreable类型
        Iterable<String> options = Arrays.asList("-d", PATH);

        Iterable<? extends JavaFileObject> files = Collections.singletonList(javaFileObject);

        StringWriter stringWriter = new StringWriter();

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();


        // 通过一些选项，javafileObject， classPath 来获取JvaComiler.ComilationTask
        JavaCompiler.CompilationTask task = javaCompiler.getTask(stringWriter,
                standardJavaFileManager, diagnostics, options, null, files);

        // 将Class 在内存中编译
        Boolean result = task.call();

        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
            System.out.println("diagnostics = " + diagnostic.getLineNumber());

        }


        if (!result){
            throw new Exception("编译失败!");
        }

//        System.out.println(new CompilationResult(stringWriter.toString()));

        // 通过类名 加载class
        ClassLoader classLoader = new ClassClassLoader(getClass().getClassLoader());
//        ClassLoader classLoader = getClass().getClassLoader();
        Class<?> cls = classLoader.loadClass(className);

//        Object[] objects = Arrays.copyOf(args, args.length + 1);
//        objects[args.length] = null;
        Object [] objects = new Object[]{args};

        // java反射机制 method.setAcessible 设置允许访问
        String methodName = "test";
        Method method = cls.getMethod(methodName, Object[].class);
        method.setAccessible(true);

        String result1 = method.invoke(null, objects).toString();
        System.out.println("result1 = " + result1);
//        return expect.equals(result1);
        return  result1.equals(expect);
    }



    private static class StringObject extends SimpleJavaFileObject {
        private String contents = null;

        public StringObject(String clasName, String contents) throws Exception {
            super(URI.create("String:///" + clasName + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
            this.contents = contents;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return contents;
        }
    }

    private static class ClassClassLoader extends ClassLoader {

        ClassClassLoader(ClassLoader parent) {
            super(parent);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            //这个classLoader的主要方法
            String classPath = name.replace(".", "\\") + ".class";//将包转为目录
            String classFile = PATH + classPath;//拼接完整的目录
            Class<?> clazz = null;
            try {
                byte[] data = getClassFileBytes(classFile);
                clazz = defineClass(name, data, 0, data.length);
                if (null == clazz) {//如果在这个类加载器中都不能找到这个类的话，就真的找不到了


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return clazz;

        }

        private byte[] getClassFileBytes(String classFile) throws Exception {
            //采用NIO读取
            FileInputStream fis = new FileInputStream(classFile);
            FileChannel fileC = fis.getChannel();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableByteChannel outC = Channels.newChannel(baos);
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (true) {
                int i = fileC.read(buffer);
                if (i == 0 || i == -1) {
                    break;
                }
                buffer.flip();
                outC.write(buffer);
                buffer.clear();
            }
            fis.close();
            return baos.toByteArray();
        }
    }

}
