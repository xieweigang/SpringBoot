package com.ucmed.sxpt.api;

import com.ucmed.sxpt.dao.UserMapper;
import com.ucmed.sxpt.entity.User;
import com.ucmed.sxpt.util.ApiResponse;
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
import java.util.List;
import java.util.Map;

@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @RequestMapping(value = "getUserList")
    @ApiOperation(value = "查找用户接口", httpMethod = "POST", notes = "查找用户接口", response = ApiResponse.class)
    public ResponseEntity<ApiResponse> insert(
            @ApiParam(name = "phone", value = "卡号", required = true) @RequestParam(value = "phone") String phone) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        List<User> userList = userMapper.selectListByPhone(hashMap);
        return ApiResponse.responseSuccess(userList);
    }
}
