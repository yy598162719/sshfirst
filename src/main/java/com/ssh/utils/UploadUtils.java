package com.ssh.utils;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.components.File;

import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传的工具类:
 */
public class UploadUtils {
    /**
     * 获得唯一文件名
     * @param fileName
     * @return
     */
    public static String getUUIDFileName(String fileName){
        // aaa.txt  --- xxsekluowerjlsdjr.txt
        int idx = fileName.lastIndexOf(".");
        // 获得文件的扩展名
        String extention = fileName.substring(idx);
        // 生成唯一的文件名：
        return UUID.randomUUID().toString().replace("-", "")+extention;
    }

    /**
     * 获得目录分离的路径:
     */
    public static String getPath(String uuidFileName){
        // 通过唯一文件名 获得 hashCode值.
        int code1 = uuidFileName.hashCode();
        // 让hashCode值&0xf:得到一级目录
        int d1 = code1 & 0xf;
        // 让hashCode 右移 4位 得到一个新的值
        int code2 = code1 >>> 4;
        // 让新的值 & 0xf; :得到二级目录.
        int d2 = code2 & 0xf;
        return "/"+d1+"/"+d2;
    }

    /**
     * 上传文件封装
     */
    public static String upload(String path,java.io.File upload,String uploadFileName) throws IOException {
        //获得唯一的文件名
        String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
        String str = path + UploadUtils.getPath(uuidFileName);
        java.io.File file = new java.io.File(str);
        //如果路径不存在
        if (!file.exists()) {
            //创建路径
            file.mkdirs();
        }
        java.io.File destFile = new java.io.File(str + "/" + uuidFileName);
        FileUtils.copyFile(upload, destFile);
        return str + "/" + uuidFileName;
    }

    public static void main(String[] args) {
        System.out.println("ea7e8c4eb01f4b7796fc8a27f944d845.txt".hashCode());
    }
}

