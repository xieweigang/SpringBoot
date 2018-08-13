package com.ucmed.sxpt.dao;

import com.ucmed.sxpt.entity.Patient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PatientMapper {
    /**
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_patient",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_patient (id, user_id, ",
        "card_no, card_type, ",
        "patient_id, patient_name, ",
        "patient_phone, patient_idcard, ",
        "create_time, update_time)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{cardNo,jdbcType=VARCHAR}, #{cardType,jdbcType=VARCHAR}, ",
        "#{patientId,jdbcType=VARCHAR}, #{patientName,jdbcType=VARCHAR}, ",
        "#{patientPhone,jdbcType=VARCHAR}, #{patientIdcard,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(Patient record);

    /**
     *
     * @mbg.generated
     */
    @InsertProvider(type=PatientSqlProvider.class, method="insertSelective")
    int insertSelective(Patient record);

    /**
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, user_id, card_no, card_type, patient_id, patient_name, patient_phone, patient_idcard, ",
        "create_time, update_time",
        "from t_patient",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="card_no", property="cardNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_type", property="cardType", jdbcType=JdbcType.VARCHAR),
        @Result(column="patient_id", property="patientId", jdbcType=JdbcType.VARCHAR),
        @Result(column="patient_name", property="patientName", jdbcType=JdbcType.VARCHAR),
        @Result(column="patient_phone", property="patientPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="patient_idcard", property="patientIdcard", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Patient selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    @UpdateProvider(type=PatientSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Patient record);

    /**
     *
     * @mbg.generated
     */
    @Update({
        "update t_patient",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "card_no = #{cardNo,jdbcType=VARCHAR},",
          "card_type = #{cardType,jdbcType=VARCHAR},",
          "patient_id = #{patientId,jdbcType=VARCHAR},",
          "patient_name = #{patientName,jdbcType=VARCHAR},",
          "patient_phone = #{patientPhone,jdbcType=VARCHAR},",
          "patient_idcard = #{patientIdcard,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Patient record);
}