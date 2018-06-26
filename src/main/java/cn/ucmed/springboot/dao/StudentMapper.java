package cn.ucmed.springboot.dao;

import cn.ucmed.springboot.dto.StudentDto;
import cn.ucmed.springboot.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    @Delete({
            "delete from student",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into student (name, k_id)",
            "values (#{name,jdbcType=VARCHAR}, #{kId,jdbcType=INTEGER})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(Student record);

    @InsertProvider(type = StudentSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(Student record);

    @Select({
            "select",
            "id, name, k_id",
            "from student",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "k_id", property = "kId", jdbcType = JdbcType.INTEGER)
    })
    Student selectByPrimaryKey(Integer id);

    @UpdateProvider(type = StudentSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Student record);

    @Update({
            "update student",
            "set name = #{name,jdbcType=VARCHAR},",
            "k_id = #{kId,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Student record);

    // 根据id查一条
    @Select("select * from student where id = #{id,jdbcType=INTEGER}")
    @Results(value = {
            @Result(property = "id", column = "s_id"),
            @Result(property = "name", column = "s_name"),
            @Result(property = "kId", column = "k_id")
    })
    Student selectById(Integer id);

    // 分页+条件查询
    @Select("select * from student where name like concat('%',#{student.name},'%') limit #{start}, #{size}")
    @Results(value = {
            @Result(property = "id", column = "s_id"),
            @Result(property = "name", column = "s_name"),
            @Result(property = "kId", column = "k_id")
    })
    List<Student> selectListByName(Map<String, Object> map);
}