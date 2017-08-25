/*
 * @(#)ZipTool.java
 *
 * Copyright (c) 2013-2014 ZhongShiAn
 */
package com.platform.rp.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩处理公共类
 * 
 * @version 1.0
 */
public class ZipTool {
	/** 字节流缓冲区 */
    private static final int BUFFER = 2048;
    /** 压缩级别  */
    private int level = 0;

    /**
     * 构造函数
     */
    public ZipTool() {
    }

    /**
     * 压缩级别设定
     * 
     * @param level 压缩级别
     */
    public void setLevel(int level) {
        this.level = level;

    }

    /**
     * 压缩一个文件或者一个文件夹
     * 
     * @param inputFile 要压缩的文件
     * @param outputFile 压缩后的文件
     * @throws ZipException 异常
     */
    public void zipFile(File inputFile, File outputFile) throws ZipException {
        try {
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(
                outputFile), BUFFER);
            ZipOutputStream out = new ZipOutputStream(bout);
            zip(out, inputFile, inputFile.getName());
            out.close();
        }
        catch (IOException ex) {
            throw new ZipException(ex.getMessage());
        }
    }

    /**
     * 压缩一组文件
     * 
     * @param inputFiles 待压缩的文件
     * @param outputFile 压缩后的文件
     * @throws ZipException 异常
     */
    public void zipFiles(File[] inputFiles, File outputFile) throws ZipException {
        try {
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(outputFile),
                BUFFER);
            ZipOutputStream out = new ZipOutputStream(bout);
            for (int i = 0; i < inputFiles.length; i++) {
                zip(out, inputFiles[i], inputFiles[i].getName());
            }
            out.close();
        }
        catch (IOException ex) {
            throw new ZipException(ex.getMessage());
        }
    }
    
    /**
     * 压缩一组文件
     * 
     * @param inputFiles 待压缩的文件
     * @param outputFile 压缩后的文件
     * @throws ZipException 异常
     */
    public void zipFiles(InputStream[] inputStream, OutputStream outputStream) throws ZipException {
        try {
            BufferedOutputStream bout = new BufferedOutputStream(outputStream,
                BUFFER*100);
            ZipOutputStream out = new ZipOutputStream(bout);
            for (int i = 0; i < inputStream.length; i++) {	
            	zipLeapInputStream(out, inputStream[i], new Date().getTime()+"_"+i+".jpg");
            	inputStream[i].close();
            }
            out.close();
        }
        catch (IOException ex) {
        	ex.printStackTrace();
            throw new ZipException(ex.getMessage());
        }
    }

    /**
     * 解压文件
     *
     * @param inputFile 待解压的文件
     * @param outputFile 解压后的文件
     * @throws ZipException 异常
     */
    public void unZipFile(File inputFile, File outputFile) throws ZipException {
        try {
            FileInputStream tin = new FileInputStream(inputFile);
            CheckedInputStream cin = new CheckedInputStream(tin, new CRC32());
            BufferedInputStream bufferIn = new BufferedInputStream(cin, BUFFER);
            ZipInputStream in = new ZipInputStream(bufferIn);
            ZipEntry z = in.getNextEntry();

            if (!outputFile.exists()) {
            	outputFile.mkdirs();
            }
            while (z != null) {
                String name = z.getName();
                if (z.isDirectory()) {
                    File f = new File(outputFile.getPath() + File.separator + name);
                    f.mkdir();
                }
                else {
                    File f = new File(outputFile.getPath() + File.separator + name);
                    f.createNewFile();
                    FileOutputStream out = new FileOutputStream(f);
                    byte data[] = new byte[BUFFER];
                    int b;

                    while ( (b = in.read(data, 0, BUFFER)) != -1) {
                        out.write(data, 0, b);
                    }
                    out.close();
                }
                z = in.getNextEntry();
            }

            in.close();
        }

        catch (IOException ex) {
            throw new ZipException(ex.getMessage());
        }

    }

    /**
     * 文件压缩处理
     * 
     * @param out 压缩输出流
     * @param zipFile 待压缩文件
     * @param zipName 压缩后文件名
     * @throws ZipException 异常
     */
    private void zip(ZipOutputStream out, File zipFile, String zipName) throws
         ZipException {
        if (level != 0) {
            out.setLevel(level);
        }
        if (zipFile.isDirectory()) {
            zipDirectory(out, zipFile, zipName);
        }
        else {
            if (null==zipName || "".equals(zipName)) {
            	zipName = zipFile.getName();
            }
            zipLeapFile(out, zipFile, zipName);
        }

    }

    /**
     * 目录压缩处理
     * 
     * @param out 压缩输出流
     * @param zipFile 待压缩文件
     * @param zipName 压缩后文件名
     * @throws ZipException 异常
     */
    private void zipDirectory(ZipOutputStream out, File zipFile, String zipName) throws
         ZipException {
        File[] files = zipFile.listFiles();
        if (level != 0) {
            out.setLevel(level);
        }
        try {
            out.putNextEntry(new ZipEntry(zipName + "/"));
        }
        catch (IOException ex) {
            throw new ZipException(ex.getMessage());
        }
        if (null==zipName || "".equals(zipName)) {
        	zipName = new String();
        }
        else {
        	zipName = zipName + "/";
        }

        for (int i = 0; i < files.length; i++) {
            zip(out, files[i], zipName + files[i].getName());
        }

    }

    /**
     * 单文件压缩处理
     * 
     * @param out 压缩输出流
     * @param zipFile 待压缩文件
     * @param zipName 压缩后文件名
     * @throws ZipException 异常
     */
    private void zipLeapFile(ZipOutputStream out, File zipFile, String zipName) throws
         ZipException {
        if (level != 0) {
            out.setLevel(level);
        }
        try {
            out.putNextEntry(new ZipEntry(zipName));
            FileInputStream in = new FileInputStream(zipFile);
            BufferedInputStream bufferIn = new BufferedInputStream(in, BUFFER);
            byte[] data = new byte[BUFFER];
            int b;
            while ( (b = bufferIn.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, b);
            }
            bufferIn.close();
        }
        catch (IOException ex) {
            throw new ZipException(ex.getMessage());
        }
    }
    
    /**
     * 单文件流压缩
     * @param out
     * @param inputStream
     * @param zipName
     * @throws ZipException
     */
    private void zipLeapInputStream(ZipOutputStream out, InputStream inputStream, String zipName) throws
    ZipException {
	   if (level != 0) {
	       out.setLevel(level);
	   }
	   try {
	       out.putNextEntry(new ZipEntry(zipName));
	       BufferedInputStream bufferIn = new BufferedInputStream(inputStream, BUFFER);
	       byte[] data = new byte[BUFFER];
	       int b;
	       while ( (b = bufferIn.read(data, 0, BUFFER)) != -1) {
	           out.write(data, 0, b);
	       }
	       bufferIn.close();
	   }
	   catch (IOException ex) {
	       throw new ZipException(ex.getMessage());
	   }
	}
    
    public static void main(String[] args) throws ZipException {
    	ZipTool tool = new ZipTool();
    	String zipfile = "/cfts/zip输入";
    	String zip = "/cfts/zipFilePath/中文.zip";
    	
    	File[] inputFiles = new File[2];
    	inputFiles[0] = new File("/cfts/zip输入/中文.txt");
    	inputFiles[1] = new File("/cfts/zip输入/中文1.txt");
    	File outputFile = new File(zip);
//    	
    	tool.zipFiles(inputFiles, outputFile);
//    	
//    	zipfile = "D:\\test_1";
//    	zip = "D:\\test_f.zip";
//    	inputFile = new File(zipfile);
//    	outputFile = new File(zip);
//    	tool.zipFile(inputFile, outputFile);
    	
    	zipfile = "/cfts/zipFilePath/ex中文";
//    	zip = "D:\\test_f.zip";
    	File inputFile = new File(zip);
    	outputFile = new File(zipfile);
    	tool.unZipFile(inputFile, outputFile);
    }
}