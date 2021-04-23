package com.runnersoftware.auto_test;

import org.junit.jupiter.api.Test;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.Collections;


public class TestComiles {

    private final String javaSource = "class Solution {   " +
            " public static String sayHello() {                return \"hello world\";        } " +
            "}";

    // path 是你想把.class 放在哪里的路径，
    private final String path = "D:\\test\\";

    @Test
    public void comile() throws Exception {
        // javqComile 是java程序里的java编译器类
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        // StandardJavaFileManager 对象主要负责
        // 编译文件对象的创建，编译的参数等等，我们只对它做些基本设置比如编译 CLASSPATH 等。
        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);

        // JavaFileObject 为类文件上进行操作的工具的文件抽象
        String className = "Solution";

        String javaSource2 = "class Solution {   " +
                " public static String sayHello(int i) {                return \"hello world\" + i;        } " +
                "}";
        JavaFileObject javaFileObject = new StringObject(className, javaSource2);

        // Array Map Set 等都属于Itreable类型
        Iterable<String> options = Arrays.asList("-d", path);

        Iterable<? extends JavaFileObject> files = Collections.singletonList(javaFileObject);

        // 通过一些选项，javafileObject， classPath 来获取JvaComiler.ComilationTask
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null,
                standardJavaFileManager, null, options, null, files);

        // 将Class 在内存中编译
        Boolean result = task.call();

        // 通过类名 加载class
        ClassLoader classLoader = new ClassClassLoader(getClass().getClassLoader());
//        ClassLoader classLoader = getClass().getClassLoader();
        Class<?> cls = classLoader.loadClass(className);

        // java反射机制 method.setAcessible 设置允许访问
        String methoName = "sayHello";
        Method method = cls.getMethod(methoName, int.class);
        method.setAccessible(true);

        Object[] args = new Object[]{2};
        args[0] = 1;

        Object obj = method.invoke(null, args);

        System.out.println(obj.toString());
    }

    public static void main(String[] args) {

        try {
            new TestComiles().comile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class StringObject extends SimpleJavaFileObject {
        private String contents = null;

        public StringObject(String clasName, String contents) throws Exception {
            super(URI.create("String:///" + clasName + Kind.SOURCE.extension), Kind.SOURCE);
            this.contents = contents;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return contents;
        }
    }

    private class ClassClassLoader extends ClassLoader {

        ClassClassLoader(ClassLoader parent) {
            super(parent);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            //这个classLoader的主要方法
            String classPath = name.replace(".", "\\") + ".class";//将包转为目录
            String classFile = path + classPath;//拼接完整的目录
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


