package com.deepglint.library.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * Created by gaofengdeng 2020/4/7
 **/
public class FileTools {

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        return deleteFile(new File(fileName));
    }

    public static boolean deleteFile(File file) {
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
//                Logger.d("删除单个文件" + file.getAbsolutePath() + "成功！");
                return true;
            } else {
//                Logger.d("删除单个文件" + file.getAbsolutePath() + "失败！");
                return false;
            }
        } else {
//            Logger.d("删除单个文件失败：" + file.getAbsolutePath() + "不存在！");
            return false;
        }
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        return delete(new File(fileName));
    }

    public static boolean delete(File file) {
        if (!file.exists()) {
//            Logger.d("删除文件失败:" + file.getAbsolutePath() + "不存在！");
            return false;
        } else {
            if (file.isFile()) return deleteFile(file);
            else return deleteDirectory(file, true);
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        return deleteDirectory(new File(dir), true);
    }

    public static boolean deleteDirectory(String dir, boolean deleteSelf) {
        return deleteDirectory(new File(dir), deleteSelf);
    }

    public static boolean deleteDirectory(File dirFile, final boolean deleteSelf) {
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
//            Logger.d("删除目录失败：" + dirFile.getAbsolutePath() + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return deleteSelf || !pathname.isHidden();
            }
        });
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i]);
                if (!flag) break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i], true);
                if (!flag) break;
            }
        }
        if (!flag) {
//            Logger.d("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (deleteSelf && dirFile.delete()) {
//            Logger.d("删除目录" + dirFile.getAbsolutePath() + "成功！");
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteEmptyDirectory(File dirFile) {
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
//            Logger.d("删除目录失败：" + dirFile.getAbsolutePath() + "不存在！");
            return false;
        }
        boolean flag = true;
        FileFilter notHiddenFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.isHidden();
            }
        };

        File[] files = dirFile.listFiles(notHiddenFilter);
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                File[] fs = files[i].listFiles(notHiddenFilter);
                if (fs == null || fs.length == 0) {
                    flag = deleteDirectory(files[i], true);
                    if (!flag) break;
                }
            }
        }

        return flag;
    }

    public static boolean write2File(String path, String content){
        File file = new File(path);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.flush();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void writToFile(File file, String content) throws IOException {
        try (Sink fileSink = Okio.sink(file);
             BufferedSink bufferedSink = Okio.buffer(fileSink)) {
            bufferedSink.write(content.getBytes());
            bufferedSink.write("\n".getBytes());
        }
    }

    public static void writToFile(File file, ByteBuffer buffer) throws IOException {
        try (Sink fileSink = Okio.sink(file);
             BufferedSink bufferedSink = Okio.buffer(fileSink)) {
            bufferedSink.write(buffer);
        }
    }

    public static void readFromFile(File file, StringBuilder stringBuilder) throws IOException {
        try (Source fileSource = Okio.source(file);
             BufferedSource bufferedSource = Okio.buffer(fileSource)) {

            while (true) {
                String line = bufferedSource.readUtf8Line();
                if (line == null) break;
                stringBuilder.append(line);
            }
        }
    }
}
