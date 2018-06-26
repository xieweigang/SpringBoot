package cn.ucmed.springboot.dto;

import java.util.Date;

/**
 *
 * @Description 测试实体类
 * @author Administrator
 * @date 2017-4-21 下午9:14:27
 * @version V1.3.1
 */
public class User {
    private Integer id;
    private String name;
    private Date createTime;

    public User() {
    }

    public User(Integer id, String name, Date createTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
