package com.nbicocchi.exercises.nio;

public class PortableDesktop {
    public static String portableDesktop() {
        return  String.format("%s%s%s", System.getProperty("user.home"), System.getProperty("file.separator"), "Desktop");
    }
}
