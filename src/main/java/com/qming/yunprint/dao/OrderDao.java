package com.qming.yunprint.dao;

import com.qming.yunprint.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-06-22:56
 */

@Mapper
@Repository
public interface OrderDao {
    //order是mysql关键字,所以前面要加一个表名
    String TABLE_NAME = "yun_print.order";
    String INSERT_FIELDS = "uuid,owner_id,file_uuid,price,status,create_time,over_time,colorful,duplex,phone_num,copies,name,address,page ";
    String SELECT_FIELDS = "id," + INSERT_FIELDS;

    @Select("SELECT " + SELECT_FIELDS + "from " + TABLE_NAME + " where id =#{id}" )
    Order getOrderById(int id);
    @Select("SELECT " + SELECT_FIELDS + "from " + TABLE_NAME + " where uuid =#{uuid}" )
    List<Order> getOrdersByUuid(String uuid);

    @Insert("INSERT " + TABLE_NAME + "(" + INSERT_FIELDS + ") values"
            + "(#{uuid},#{ownerId},#{fileUuid},#{price},#{status},#{createTime},#{overTime},#{colorful},#{duplex},#{phoneNum},#{copies},#{name},#{address},#{page})")
    int insertOrder(Order order);

    @Update("UPDATE  " + TABLE_NAME + " SET status = 0 Where uuid = #{uuid}")
    int deleteOrder(String uuid);

    @Select("SELECT count(*) from " + TABLE_NAME + " where uuid =#{uuid}" )
    int isExist(String uuid);

    @Select("SELECT status from " + TABLE_NAME + " where uuid =#{uuid}" )
    int selectStatus(String uuid);
    @Select("SELECT sum(price) from " + TABLE_NAME + " where uuid =#{uuid}" )
    double getPrice(String uuid);
    @Select("SELECT " + SELECT_FIELDS + " from " + TABLE_NAME + " where owner_id = #{userId}")
    List<Order> getOrdersByUserId(int userId);
}
