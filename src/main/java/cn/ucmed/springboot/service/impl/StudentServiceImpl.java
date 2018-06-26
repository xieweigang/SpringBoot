package cn.ucmed.springboot.service.impl;

import cn.ucmed.springboot.dao.StudentMapper;
import cn.ucmed.springboot.dto.StudentDto;
import cn.ucmed.springboot.entity.Student;
import cn.ucmed.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int delete(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public Student select(Integer id) {
        return studentMapper.selectById(id);
    }

    @Override
    public List<Student> selectList(Map<String, Object> map) {
        return studentMapper.selectListByName(map);
    }
}
