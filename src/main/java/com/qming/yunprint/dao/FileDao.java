package com.qming.yunprint.dao;

import com.qming.yunprint.model.FileEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-06-22:56
 */
@Repository
@Mapper
public interface FileDao {
    String TABLE_NAME = "FILE";
    String INSERT_FIELDS = "owner_id,file_uuid,name,type,upload_time,pages,documentId,coverUrl,md5 ";
    String SELECT_FIELDS = "id," + INSERT_FIELDS;

    @Select("SELECT " + SELECT_FIELDS + "from " + TABLE_NAME + " where id =#{ id}" )
    FileEntity getFileById(int id);

    @Insert("INSERT " + TABLE_NAME + "(" + INSERT_FIELDS + ") values"
    + "(#{ownerId},#{fileUuid},#{name},#{type},#{uploadTime},#{pages},#{documentId},#{coverUrl},#{md5})")
    int insertFile(FileEntity file);

    @Delete("DELETE from " + TABLE_NAME + " where file_uuid = #{fileUuid}")
    int deleteFile(String fileUuid);
    @Select("SELECT " + SELECT_FIELDS + " from " + TABLE_NAME + " where md5=#{md5}")
    FileEntity getFileByMd5(String md5);
    @Select("SELECT " + SELECT_FIELDS + " from " + TABLE_NAME + " where file_uuid=#{uuid}")
    FileEntity getFileByUuid(String uuid);
}
