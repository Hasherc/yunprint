package com.qming.yunprint.dao;

import com.qming.yunprint.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-06-22:56
 */
@Repository
@Mapper
public interface UserDao {
    String TABLE_NAME = "user";
    String INSERT_FIELDS = "nick_name,phone_num,password,last_login_time,email,address,balance,status ";
    String SELECT_FIELDS = "id," + INSERT_FIELDS;

    @Select("SELECT " + SELECT_FIELDS + "from " + TABLE_NAME + " where id =#{ id}" )
    User getUserById(int id);
    @Select("SELECT " + SELECT_FIELDS + "from " + TABLE_NAME + " where password = #{password} and (phone_num = #{phoneNum} or email = #{email}) " )
    User getUser(@Param("phoneNum") String phoneNum, @Param("email") String email, @Param("password") String password);
    @Select("SELECT " + SELECT_FIELDS + "from " + TABLE_NAME + " where id =#{ id}" )

    @Insert("INSERT " + TABLE_NAME + "(" + INSERT_FIELDS + ") values"
            + "(#{nickName},#{phoneNum},#{password},#{lastLoginTime},#{email},#{address},#{balance},#{status})")
    void insertUser(User user);

    @Select("SELECT count(id) from "+ TABLE_NAME + " where phone_num = #{phoneNum} or email = #{email}"  )
    int existUser(@Param("phoneNum") String phoneNum, @Param("email") String email);
    @Select("SELECT balance from "+ TABLE_NAME + " where id = #{id}"  )
    double getBalance(@Param("id") int id);
    @Update("update " + TABLE_NAME + " set balance = #{balance} where id = #{id}")
    int updateBalance(@Param("id") int id, @Param("balance") double balance);
}
