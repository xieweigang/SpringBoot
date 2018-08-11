package com.ucmed.sxpt.entity;

import java.util.Date;

public class User {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 医院编号
     */
    private String hospId;

    /**
     * 微信号
     */
    private String openId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 用户id
     * @return id 用户id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 用户id
     * @param id 用户id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 手机号码
     * @return phone 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 医院编号
     * @return hosp_id 医院编号
     */
    public String getHospId() {
        return hospId;
    }

    /**
     * 医院编号
     * @param hospId 医院编号
     */
    public void setHospId(String hospId) {
        this.hospId = hospId == null ? null : hospId.trim();
    }

    /**
     * 微信号
     * @return open_id 微信号
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 微信号
     * @param openId 微信号
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}