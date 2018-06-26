package cn.ucmed.springboot.dao;

import cn.ucmed.springboot.entity.Student;
import org.apache.ibatis.jdbc.SQL;

public class StudentSqlProvider {

    public String insertSelective(Student record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("student");
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getkId() != null) {
            sql.VALUES("k_id", "#{kId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Student record) {
        SQL sql = new SQL();
        sql.UPDATE("student");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getkId() != null) {
            sql.SET("k_id = #{kId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}