package com.platform.rp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

public class FileUtil {
	Logger logger = Logger.getLogger(this.getClass());

    private File file;
    
    /**
     * 保存文件
     * @return
     */
    public boolean saveFile(InputStream is,String fileSavePath,String filename) {
        boolean flag = true;
        if (!mkdirsOnNoExists(fileSavePath)) {// 目录创建不成功，直接返回
        	logger.error("目录创建不成功");
            return false;
        }
        OutputStream os = null;
        try {
            file = new File(fileSavePath + filename);
            os = new FileOutputStream(file);
            byte[] b = new byte[2048];
            int bytesRead = 0;
            while ((bytesRead = is.read(b)) != -1) {
                os.write(b, 0, bytesRead);
            }
            // decoder.decodeBuffer(is, os);
            os.flush();
        } catch (IOException e) {
        	logger.error("保存ZIP文件",e);
            flag = false;
        } finally {
            if (os != null) {
                try {
					os.close();
					is.close();
				} catch (IOException e) {
					logger.error("保存ZIP文件",e);
				}
            }
        }
        return flag;
    }
    

    /**
     * 解压ZIP文件
     * @param records
     * @return
     */
    public Map<String,List<String[]>> zipFile(File file)throws Exception,IOException{
        ZipFile zipFile = null;// 实例化ZipFile，每一个zip压缩文件都可以表示为一个ZipFile
        ZipEntry zipEntry = null;
        InputStream is = null;
        CSVReader reader = null;
        Map<String,List<String[]>>  records=new HashMap<String,List<String[]>>();
        try {
        	zipFile = new ZipFile(file);
        	Enumeration<? extends ZipEntry> enu = zipFile.entries();
            while (enu.hasMoreElements()) {
                zipEntry = (ZipEntry) enu.nextElement();
                String csvFileName = zipEntry.getName();
                
                is = zipFile.getInputStream(zipEntry);
                reader = new CSVReader(new InputStreamReader(is,"utf-8"), ',', '"', 0);
                
                List<String[]> data = reader.readAll();
                if(data.size() > 0){
                	records.put(csvFileName, data);                	
                }
            }
        } catch (Exception e) {
            logger.error("zipFile()",e);
            throw e;
        } finally {
        	if (reader != null) {
                try {
                	reader.close();
				} catch (IOException e) {
					throw e;
				}
            }
        	if (is != null) {
                try {
                	is.close();
				} catch (IOException e) {
					throw e;
				}
            }
            if (zipFile != null) {
                try {
					zipFile.close();
				} catch (IOException e) {
					throw e;
				}
            }
        }
        return records;
    }
   

    /**
     * 如果文件存在，删除
     */
    public void deleteFileOnExists() {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 如果存放ZIP文件的路径不存在，进行创建
     * @return
     */
    public boolean mkdirsOnNoExists(String fileSavePath) {
        boolean flag = true;
        File dir = new File(fileSavePath);
        if (!dir.exists()) {
            flag = dir.mkdirs();
        }
        return flag;
    }
    
    
    /**
 	 * 将要上传的文件全部打包成一个zip文件
 	 * 
 	 * @author fanhl
 	 */
 	public File zipFolder(String zipFilePath,String zipName) {
 		
 		//File dir = new File(mApp.getFilesDir() + "/db");
 		 File destFile = new File(zipFilePath + zipName+ ".zip");
// 		 if(destFile.exists())
//	 		   destFile.delete();
 		File dir = new File(zipFilePath);
 		File[] inputFiles = dir.listFiles();
 		if (inputFiles == null) {
 			return null;
 		}
 		//以下方法是为了解决将zip包也压缩到zip包中，用此方法就可以只将csv文件压缩到zip中区
 		int index = 0;
 		int pos = 0;
 		for (int i = 0; i < inputFiles.length; i++) {
 			if (inputFiles[i].getName().endsWith("csv")){
 				index++;
 			}
 		}
 		File[] modFiles = new File[index];
 		for (int i = 0; i < index; i++) {
 			if (inputFiles[i].getName().endsWith("csv")){
 				modFiles[pos] = inputFiles[i];
 				pos++;
 			}
		}
 		 

 		
      /*ZipTool zipTool = new ZipTool(); //将多个csv文件压缩成一个zip包

 		try {
 			zipTool.zipFiles(modFiles, destFile);
 		} catch (ZipException e) {
 			logger.error("MFileUtil.zipFolder()",e);
 		//	logger.info(TAG+"同步时上传的文件压缩失败");
 			return null;
 		}*/
 		//将此文件夹中的csv文件删除
 		for(File file:modFiles){
 			file.delete();
 		}
 		
 		//返回压缩文件包
 		return destFile;
 	}
 	
 	
 	 /**
 	 * 将要上传的文件全部打包成一个zip文件
 	 * 
 	 * @author fanhl
 	 */
 	public File zipFolder(String zipFilePath) {
 		//File dir = new File(mApp.getFilesDir() + "/db");
 		 File destFile = new File(zipFilePath + "VO.zip");
 		 if(destFile.exists())
	 		   destFile.delete();
 		File dir = new File(zipFilePath);
 		File[] inputFiles = dir.listFiles();
 		if (inputFiles == null) {
 			return null;
 		}
 		int index = 0;
 		int pos = 0;
 		for (int i = 0; i < inputFiles.length; i++) {
 			if (inputFiles[i].getName().endsWith("csv")){
 				index++;
 			}
 		}
 		File[] modFiles = new File[index];
 		for (int i = 0; i < index; i++) {
 			if (inputFiles[i].getName().endsWith("csv")){
 				modFiles[pos] = inputFiles[i];
 			}
 			pos++;
		}
 		 

 		
      /*ZipTool zipTool = new ZipTool(); //将多个csv文件压缩成一个zip包

 		try {
 			zipTool.zipFiles(modFiles, destFile);
 		} catch (ZipException e) {
 			logger.error("MFileUtil.zipFolder()",e);
 		//	logger.info(TAG+"同步时上传的文件压缩失败");
 			return null;
 		}*/
 		
 		
 		//返回压缩文件包
 		return destFile;
 	}
    
    
 	
 	/**
 	 * 清空上传用文件夹
 	 * 
 	 * @author fanhl
 	 */
 	public void clearFolder(String zipFilePath) {
 		File dir = new File(zipFilePath);
 		File[] files = dir.listFiles();
 		if (files == null) {
 			return;
 		}

 		for (File file : files) {
 			if (file != null && file.isFile() && file.exists()) {
 				file.delete();
 			}
 		}
 	}
    
    
 	 public void deleteFile(File file) {  
		if (file.exists()) {// 判断文件是否存在
			if (file.isFile()) {// 判断是否是文件
				file.delete();// 删除文件
			} else if (file.isDirectory()) {// 否则如果它是一个目录
				File[] files = file.listFiles();// 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件
					this.deleteFile(files[i]);// 把每个文件用这个方法进行迭代
				}
				file.delete();// 删除文件夹
			}
		}
	}
    

 	 
 	 

}

