package cn.ucmed.springboot.service;

import cn.ucmed.springboot.entity.Student;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface StudentService {
    int insert(Student student);

    int delete(Integer id);

    int update(Student student);

    Student select(Integer id);

    List<Student> selectList(Map<String, Object> map);
}
