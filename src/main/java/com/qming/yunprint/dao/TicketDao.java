package com.qming.yunprint.dao;

import com.qming.yunprint.model.Ticket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-08-21:11
 */
@Mapper
@Repository
public interface TicketDao {
    String TABLE_NAME = "ticket";
    String INSERT_FIELDS = "owner_id,expired,status,ticket";
    String SELECT_FIELDS = "id," + INSERT_FIELDS;
    @Select("SELECT " + SELECT_FIELDS + " FROM " + TABLE_NAME + " where ticket = #{ticket}")
    Ticket getTicket(String ticket);

    @Update("UPDATE " + TABLE_NAME + " SET status = 1 where ticket = #{ticket}")
    int updateTicketExpired(String ticket);

    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ") values (#{ownerId},#{expired},#{status},#{ticket})")
    int insertTicket(Ticket ticket);
}
