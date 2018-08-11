package com.ucmed.sxpt.dao;

import com.ucmed.sxpt.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * @mbg.generated 2018-08-11
     */
    @Delete({
            "delete from user",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * @mbg.generated 2018-08-11
     */
    @Insert({
            "insert into user (id, phone, ",
            "password, hosp_id, ",
            "open_id, create_time, ",
            "update_time)",
            "values (#{id,jdbcType=INTEGER}, #{phone,jdbcType=VARCHAR}, ",
            "#{password,jdbcType=VARCHAR}, #{hospId,jdbcType=VARCHAR}, ",
            "#{openId,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
            "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(User record);

    /**
     * @mbg.generated 2018-08-11
     */
    @InsertProvider(type = UserSqlProvider.class, method = "insertSelective")
    int insertSelective(User record);

    /**
     * @mbg.generated 2018-08-11
     */
    @Select({
            "select",
            "id, phone, password, hosp_id, open_id, create_time, update_time",
            "from user",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hosp_id", property = "hospId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    User selectByPrimaryKey(Integer id);

    /**
     * @mbg.generated 2018-08-11
     */
    @UpdateProvider(type = UserSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    /**
     * @mbg.generated 2018-08-11
     */
    @Update({
            "update user",
            "set phone = #{phone,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},",
            "hosp_id = #{hospId,jdbcType=VARCHAR},",
            "open_id = #{openId,jdbcType=VARCHAR},",
            "create_time = #{createTime,jdbcType=TIMESTAMP},",
            "update_time = #{updateTime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);

    // 分页+条件查询
    @Select("select * from user where phone=#{phone} ")
    @Results(value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hosp_id", property = "hospId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<User> selectListByPhone(Map<String, Object> hashMap);
}