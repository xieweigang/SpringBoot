package cn.ucmed.springboot.dao;

import cn.ucmed.springboot.entity.Klass;
import org.apache.ibatis.jdbc.SQL;

public class KlassSqlProvider {

    public String insertSelective(Klass record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("klass");
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Klass record) {
        SQL sql = new SQL();
        sql.UPDATE("klass");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}