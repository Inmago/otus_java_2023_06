package ru.otus.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.Arrays;

@SuppressWarnings("java:S106")
public class MyClassLoaderDemo {
    public static void main(String[] args)
            throws IOException,
                    InvocationTargetException,
                    NoSuchMethodException,
                    InstantiationException,
                    IllegalAccessException {
        new MyClassLoaderDemo().start();
    }

    private void start()
            throws IOException,
                    NoSuchMethodException,
                    InvocationTargetException,
                    InstantiationException,
                    IllegalAccessException {
        var loader = new MyClassLoader();
        Class<?> clazz = loader.defineClass("ru.otus.classloader.ClassForLoading");
        System.out.println("methods:");
        Arrays.stream(clazz.getMethods()).forEach(method -> System.out.println(method.getName()));
        Constructor<?> constructor = clazz.getConstructor();
        Object object = constructor.newInstance();
        System.out.println("------");

        clazz.getMethod("action").invoke(object);
    }

    private static class MyClassLoader extends ClassLoader {
        Class<?> defineClass(String className) throws IOException {
            var file = new File(getFileName(className));
            byte[] bytecode = Files.readAllBytes(file.toPath());
            return super.defineClass(className, bytecode, 0, bytecode.length);
        }

        String getFileName(String className) {
            return "myClass"
                    + File.separator
                    + className.substring(className.lastIndexOf('.') + 1)
                    + ".class";
        }
    }
}
