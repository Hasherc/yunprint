package com.qming.yunprint.util;

import org.apache.commons.io.FileExistsException;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-09-13:26
 */
@Component
public class FUtil {
    //@Value("${print.file.folder}")
//    public static String fileFolderPath= "/home/cloud_print/";
    public static String fileFolderPath= "E://yun_print//";

    public  String downToDisk(String fileUuid, MultipartFile file) throws FileExistsException {
        createRootDir();
        File tempFile = new File(fileFolderPath, fileUuid);
        if(tempFile.exists()){
            throw new FileExistsException();
        }
        try {
            tempFile.createNewFile();
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile.getPath();
    }
    public String getFileType(String fileName) {
        if (!fileName.contains(".")) {
            return "";
        }
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileType;
    }
    public void createRootDir() {
        File file = new File(fileFolderPath);
        file.mkdirs();
    }
    public  boolean deleteFile(String uuid) {
        File file = new File(fileFolderPath,uuid);
        if (file.exists() && file.isFile() && file.delete()) {
            return true;
        } else {
            return false;
        }
    }
    public String getMD5(InputStream inputStream){
        try {
            byte[] bytes = DigestUtils.md5Digest(inputStream);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
