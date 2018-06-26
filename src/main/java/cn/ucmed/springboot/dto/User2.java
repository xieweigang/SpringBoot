package cn.ucmed.springboot.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 *
 * @Description fastjson测试实体类
 * @author Administrator
 * @date 2017-4-21 下午9:14:27
 * @version V1.3.1
 */
public class User2 {
    private Integer id;
    private String name;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //不出现
    @JSONField(serialize=false)
    private String remark;//备注信息

    public User2() {
    }

    public User2(Integer id, String name, Date createTime, String remark) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.remark = remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
