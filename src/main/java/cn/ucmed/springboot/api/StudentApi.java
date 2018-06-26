
package cn.ucmed.springboot.api;

import cn.ucmed.springboot.dto.ApiResponse;
import cn.ucmed.springboot.entity.Student;
import cn.ucmed.springboot.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Api(tags = "学生相关接口")
@RestController
@RequestMapping("/Student")
public class StudentApi {

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping(value = "insert")
    @ApiOperation(value = "增加学生", httpMethod = "POST", notes = "增加学生接口", response = ApiResponse.class)
    public ResponseEntity<ApiResponse> insert(
            @ApiParam(name = "name", value = "学生姓名", required = true) @RequestParam(value = "name") String name,
            @ApiParam(name = "kid", value = "班级编号", required = true) @RequestParam(value = "kid") int kid) {
        Student student = new Student();
        student.setName(name);
        student.setkId(kid);
        return ApiResponse.responseSuccess(studentService.insert(student));
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    @ApiOperation(value = "删除学生", httpMethod = "POST", notes = "删除学生接口", response = ApiResponse.class)
    public ResponseEntity<ApiResponse> delete(
            @ApiParam(name = "id", value = "学生编号", required = true) @RequestParam(value = "id") int id
    ) {
        return ApiResponse.responseSuccess(studentService.delete(id));
    }

    @ResponseBody
    @RequestMapping(value = "update")
    @ApiOperation(value = "修改学生", httpMethod = "POST", notes = "修改学生接口", response = ApiResponse.class)
    public ResponseEntity<ApiResponse> update(
            @ApiParam(name = "id", value = "学生编号", required = true) @RequestParam(value = "id") int id,
            @ApiParam(name = "name", value = "学生姓名", required = true) @RequestParam(value = "name") String name,
            @ApiParam(name = "kid", value = "班级编号", required = true) @RequestParam(value = "kid") int kid
    ) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setkId(kid);
        return ApiResponse.responseSuccess(studentService.update(student));
    }

    @ResponseBody
    @RequestMapping(value = "select")
    @ApiOperation(value = "查找学生", httpMethod = "POST", notes = "查找学生接口", response = ApiResponse.class)
    public ResponseEntity<ApiResponse> select(
            @ApiParam(name = "id", value = "学生编号", required = true) @RequestParam(value = "id") int id
    ) {
        return ApiResponse.responseSuccess(studentService.select(id));
    }

    @ResponseBody
    @RequestMapping(value = "selectList")
    @ApiOperation(value = "查找学生列表", httpMethod = "POST", notes = "查找学生列表接口", response = ApiResponse.class)
    public ResponseEntity<ApiResponse> selectList(
            @ApiParam(name = "name", value = "学生姓名", required = true) @RequestParam(value = "name") String name,
            @ApiParam(name = "start", value = "起始", required = true) @RequestParam(value = "start") int start,
            @ApiParam(name = "size", value = "数量", required = true) @RequestParam(value = "size") int size
    ) {
        Student student = new Student();
        student.setName(name);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("student", student);
        map.put("start", start);
        map.put("size", size);
        return ApiResponse.responseSuccess(studentService.selectList(map));
    }
}
