package com.looptry.wanandroid;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class Test2 {

    public void doNothing() throws Exception {
        String clsName = "java.lang.Double";
        Class cl = Class.forName(clsName);

        System.out.print("package ");
        System.out.print(cl.getPackage().getName() + ";");
        System.out.print("\n");
        System.out.print("\n");
        int clMod = cl.getModifiers();
        System.out.print(Modifier.toString(clMod));
        System.out.print(" ");
        System.out.print("class");
        System.out.print(" ");
        System.out.print(cl.getSimpleName());
        System.out.print("{");
        System.out.print("\n");
        printFields(cl);
        printConstructor(cl);
        printFuncs(cl);
        System.out.print("}");


    }

    private void printFields(Class c) {
        Field[] fields = c.getDeclaredFields();
        //分别打印字段modifies type name
        for (Field field : fields) {
            System.out.print("      ");
            String modDesc = Modifier.toString(field.getModifiers());
            System.out.print(modDesc);
            System.out.print(" ");
            System.out.print(field.getType().getName());
            System.out.print(" ");
            System.out.print(field.getName());
            System.out.print(";\n");
        }
    }

    private void printConstructor(Class c) throws Exception {
        Constructor[] constructors = c.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.print("      ");
            String modDesc = Modifier.toString(constructor.getModifiers());
            System.out.print(modDesc);
            System.out.print(" ");
            System.out.print(constructor.getName());
            System.out.print("(");
            Class[] pt = constructor.getParameterTypes();
            for (int i = 0; i < pt.length; i++) {
                if (i > 0) {
                    System.out.print(",");
                }
                String s = " " + pt[i].getName();
                System.out.print(s);
            }
            System.out.print(");\n");
        }
    }


    private void printFuncs(Class c) throws Exception {

    }



    @FunctionalInterface
    interface A {
        void toDo();
    }

}
