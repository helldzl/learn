package com.moon.mybatis.service;

/**
 * Created at 2021/1/28
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
public class StringTests {

    public static void main(String[] args) {
        String s1 = "a1";
        String s2 = "a2";
        String s9 = s1 + "a2";
        System.out.println(s9 == "a1a2");
        System.out.println(s9.intern() == "a1a2");
        System.out.println(s9 == s9.intern());

//        String str1 = new StringBuilder("hel`````````````````").append("lo").toString();
//        String str2 = new StringBuilder("ja1111").append("va").toString();
//        System.out.println(str1.intern() == str1); // true
//        System.out.println(str2.intern() == str2); // false

    }

}
