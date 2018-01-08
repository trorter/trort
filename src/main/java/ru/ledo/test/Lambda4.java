package ru.ledo.test;

import com.sun.tools.javac.util.Convert;

public class Lambda4 {
    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            System.out.println("outerNum = " + outerNum);
            return String.valueOf(from);
        };
        stringConverter1.conver(1);

        Converter<Integer, String> stringConvert2 = (from) -> {
            outerStaticNum = 72;
            System.out.println("outerStaticNum = " + outerStaticNum);
            return  String.valueOf(from);
        };
        stringConvert2.conver(1);
    }
}
