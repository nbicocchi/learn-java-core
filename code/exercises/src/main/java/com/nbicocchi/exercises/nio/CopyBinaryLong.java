package com.nbicocchi.exercises.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CopyBinaryLong {
    public static void copyBinaryLong(String src, String dst) {
        try (FileChannel inChannel = FileChannel.open(Paths.get(src), StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get(dst), StandardOpenOption.CREATE)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) != -1) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
