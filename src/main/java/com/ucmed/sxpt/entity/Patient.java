package com.ucmed.sxpt.entity;

import java.util.Date;

public class Patient {
    /**
     * 患者id
     */
    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 卡类型，0医保卡2健康卡3省内外地社保卡
     */
    private String cardType;

    /**
     * 患者编号
     */
    private String patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 患者手机
     */
    private String patientPhone;

    /**
     * 身份证证号
     */
    private String patientIdcard;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 患者id
     * @return id 患者id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 患者id
     * @param id 患者id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 卡号
     * @return card_no 卡号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 卡号
     * @param cardNo 卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * 卡类型，0医保卡2健康卡3省内外地社保卡
     * @return card_type 卡类型，0医保卡2健康卡3省内外地社保卡
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * 卡类型，0医保卡2健康卡3省内外地社保卡
     * @param cardType 卡类型，0医保卡2健康卡3省内外地社保卡
     */
    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    /**
     * 患者编号
     * @return patient_id 患者编号
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * 患者编号
     * @param patientId 患者编号
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    /**
     * 患者姓名
     * @return patient_name 患者姓名
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * 患者姓名
     * @param patientName 患者姓名
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    /**
     * 患者手机
     * @return patient_phone 患者手机
     */
    public String getPatientPhone() {
        return patientPhone;
    }

    /**
     * 患者手机
     * @param patientPhone 患者手机
     */
    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone == null ? null : patientPhone.trim();
    }

    /**
     * 身份证证号
     * @return patient_idcard 身份证证号
     */
    public String getPatientIdcard() {
        return patientIdcard;
    }

    /**
     * 身份证证号
     * @param patientIdcard 身份证证号
     */
    public void setPatientIdcard(String patientIdcard) {
        this.patientIdcard = patientIdcard == null ? null : patientIdcard.trim();
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