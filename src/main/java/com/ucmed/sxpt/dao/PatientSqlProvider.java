package com.ucmed.sxpt.dao;

import com.ucmed.sxpt.entity.Patient;
import org.apache.ibatis.jdbc.SQL;

public class PatientSqlProvider {

    /**
     *
     * @mbg.generated
     */
    public String insertSelective(Patient record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_patient");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getCardNo() != null) {
            sql.VALUES("card_no", "#{cardNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCardType() != null) {
            sql.VALUES("card_type", "#{cardType,jdbcType=VARCHAR}");
        }
        
        if (record.getPatientId() != null) {
            sql.VALUES("patient_id", "#{patientId,jdbcType=VARCHAR}");
        }
        
        if (record.getPatientName() != null) {
            sql.VALUES("patient_name", "#{patientName,jdbcType=VARCHAR}");
        }
        
        if (record.getPatientPhone() != null) {
            sql.VALUES("patient_phone", "#{patientPhone,jdbcType=VARCHAR}");
        }
        
        if (record.getPatientIdcard() != null) {
            sql.VALUES("patient_idcard", "#{patientIdcard,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    /**
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(Patient record) {
        SQL sql = new SQL();
        sql.UPDATE("t_patient");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getCardNo() != null) {
            sql.SET("card_no = #{cardNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCardType() != null) {
            sql.SET("card_type = #{cardType,jdbcType=VARCHAR}");
        }
        
        if (record.getPatientId() != null) {
            sql.SET("patient_id = #{patientId,jdbcType=VARCHAR}");
        }
        
        if (record.getPatientName() != null) {
            sql.SET("patient_name = #{patientName,jdbcType=VARCHAR}");
        }
        
        if (record.getPatientPhone() != null) {
            sql.SET("patient_phone = #{patientPhone,jdbcType=VARCHAR}");
        }
        
        if (record.getPatientIdcard() != null) {
            sql.SET("patient_idcard = #{patientIdcard,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}