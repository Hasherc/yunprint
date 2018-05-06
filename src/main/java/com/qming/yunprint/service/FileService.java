package com.qming.yunprint.service;

import com.qming.yunprint.dao.FileDao;
import com.qming.yunprint.model.FileEntity;
import com.qming.yunprint.util.DocUtil;
import com.qming.yunprint.util.FUtil;
import com.qming.yunprint.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-08-22:14
 */
@Service
public class FileService {
    @Autowired
    FileDao fileDao;
    @Autowired
    FUtil fileUtil;
    @Autowired
    DocUtil docUtil;
    public String upload(int userId, MultipartFile multipartFile) {
        try {
            String md5;
            md5 = fileUtil.getMD5(multipartFile.getInputStream());
            FileEntity fileEntity = fileDao.getFileByMd5(md5);
            if (fileEntity != null){
                return fileEntity.getFileUuid();
            }
            File file = new File(FUtil.fileFolderPath+ md5.replace(
                    "\\","").replace("/","").
                    replace("+","").
                    replace("=","") + "." +fileUtil.getFileType(multipartFile.getOriginalFilename()));
            if(!file.exists()){
                file.createNewFile();
            }
            multipartFile.transferTo(file);
            String documentId = docUtil.createDocument(file, multipartFile.getOriginalFilename(), fileUtil.getFileType(multipartFile.getOriginalFilename()));
            if (!docUtil.waitToPublished(documentId)){
                return null;
            }
            String fileUuid = UUIDUtil.getUUID();
            fileEntity = docUtil.getDocument(documentId);
            if (fileEntity == null){
                return null;
            }
            fileEntity.setFileUuid(fileUuid);
            fileEntity.setUploadTime(new Date());
            fileEntity.setType(fileUtil.getFileType(multipartFile.getOriginalFilename()));
            fileEntity.setOwnerId(userId);
            fileEntity.setName(multipartFile.getOriginalFilename());
            fileEntity.setMd5(md5);
            fileDao.insertFile(fileEntity);
            return fileUuid;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FileEntity> getFileList(Set<String> fileUuids){
        List<FileEntity> fileEntitis = new ArrayList<>();
        for (String uuid : fileUuids) {
            FileEntity fileEntity = fileDao.getFileByUuid(uuid);
            fileEntitis.add(fileEntity);
        }
        return fileEntitis;
    }
}
