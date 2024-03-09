package com.nbicocchi.tutorials.nio;

public class SystemProperties {

    public static void main(String[] args) {
        // full list
        // https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
        System.out.println(System.getProperty("path.separator"));
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.version"));
    }
}
