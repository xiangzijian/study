package com.xxx;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {
    public static void main(String[] args) {
        /*try {
            RandomAccessFile file = new RandomAccessFile("E:\\data\\aaa.txt", "rw");
            FileChannel inChannel = file.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1);
            int bytesRead = inChannel.read(buf);
            buf.flip();
            while (bytesRead != -1) {
                System.out.println("Read：" + bytesRead);
                int a=1;

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                System.out.println();
                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            file.close();
        } catch (Exception e) {

        }*/
        try {
            testTransferFrom();
        } catch (Exception e) {

        }
    }

    public static void test() throws Exception {
        /**新建一个byteBuffer对象并对其分配空间*/
        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
        RandomAccessFile file = new RandomAccessFile("E:\\data\\aaa.txt", "rw");
        FileChannel inChannel = file.getChannel();
        /**通过channel向buffer中写数据 此时从channel中向byte写数据 故而byte已经转化为写模式*/
        int size = inChannel.read(byteBuffer);
        while (size != -1) {
            /**现在需要向buffer中读数据 会把limit 设置成刚才写模式下的position  position会设置为0*/
            byteBuffer.flip();
            /**检查buffer是否已经读完*/
            while (byteBuffer.hasRemaining()) {
                /**从buffer读出数据 这里每调用一次buffer的position 会++1 直到现在的limit*/
                System.out.print((char) byteBuffer.get());
            }
            /**这里清除数据所以 会重置position 为0 limit会设置为capacity  如果不清除那么会读不进数据 因为当前的limit等于position*/
            byteBuffer.clear();
            size = inChannel.read(byteBuffer);
        }

    }

    public static void testScatter() throws Exception {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(10);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(2);
        ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};
        RandomAccessFile file = new RandomAccessFile("E:\\data\\aaa.txt", "rw");
        FileChannel inChannel = file.getChannel();
        long size = inChannel.read(byteBuffers);
        while (size != -1) {
            for (ByteBuffer byteBuffer : byteBuffers) {
                /**现在需要向buffer中读数据 会把limit 设置成刚才写模式下的position  position会设置为0*/
                byteBuffer.flip();
                /**检查buffer是否已经读完*/
                while (byteBuffer.hasRemaining()) {
                    /**从buffer读出数据 这里每调用一次buffer的position 会++1 直到现在的limit*/
                    System.out.print((char) byteBuffer.get());
                }
                /**这里清除数据所以 会重置position 为0 limit会设置为capacity  如果不清除那么会读不进数据 因为当前的limit等于position*/
                byteBuffer.clear();
            }
            size = inChannel.read(byteBuffers);
        }
    }

    public static void testTransferFrom() throws Exception {
        RandomAccessFile file1 = new RandomAccessFile("E:\\data\\aaa.txt", "rw");
        RandomAccessFile file2 = new RandomAccessFile("E:\\data\\bbb.txt", "rw");
        FileChannel fromChannel1 = file1.getChannel();
        FileChannel toChannel = file2.getChannel();
        long position = toChannel.size();
        long count = toChannel.size()+fromChannel1.size();
        toChannel.transferFrom(fromChannel1, position, count);
        readChannel(toChannel);
    }

    private static void readChannel(FileChannel channel) throws Exception {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate((int) channel.size());
        int size = channel.read(byteBuffer1);
        while (size != -1) {
            /**现在需要向buffer中读数据 会把limit 设置成刚才写模式下的position  position会设置为0*/
            byteBuffer1.flip();
            /**检查buffer是否已经读完*/
            while (byteBuffer1.hasRemaining()) {
                /**从buffer读出数据 这里每调用一次buffer的position 会++1 直到现在的limit*/
                System.out.print((char) byteBuffer1.get());
            }
            /**这里清除数据所以 会重置position 为0 limit会设置为capacity  如果不清除那么会读不进数据 因为当前的limit等于position*/
            byteBuffer1.clear();
            size = channel.read(byteBuffer1);
        }
    }
}
