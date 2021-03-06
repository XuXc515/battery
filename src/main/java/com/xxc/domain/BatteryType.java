package com.xxc.domain;

import java.io.Serializable;
import java.util.Date;

public class BatteryType implements Serializable {

    private Integer id;
    private String typeId;
    private String batteryV;
    private String batterySoc;
    private String deviceHeight;
    private String deviceWidth;
    private String deviceMaterial;
    private String batteryType;
    private String batteryAh;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
    private Integer deleteStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.id
     *
     * @return the value of tb_batterytype.id
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.id
     *
     * @param id the value for tb_batterytype.id
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.type_id
     *
     * @return the value of tb_batterytype.type_id
     * @mbggenerated
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.type_id
     *
     * @param typeId the value for tb_batterytype.type_id
     * @mbggenerated
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.battery_V
     *
     * @return the value of tb_batterytype.battery_V
     * @mbggenerated
     */
    public String getBatteryV() {
        return batteryV;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.battery_V
     *
     * @param batteryV the value for tb_batterytype.battery_V
     * @mbggenerated
     */
    public void setBatteryV(String batteryV) {
        this.batteryV = batteryV == null ? null : batteryV.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.battery_SOC
     *
     * @return the value of tb_batterytype.battery_SOC
     * @mbggenerated
     */
    public String getBatterySoc() {
        return batterySoc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.battery_SOC
     *
     * @param batterySoc the value for tb_batterytype.battery_SOC
     * @mbggenerated
     */
    public void setBatterySoc(String batterySoc) {
        this.batterySoc = batterySoc == null ? null : batterySoc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.battery_height
     *
     * @return the value of tb_batterytype.battery_height
     * @mbggenerated
     */
    public String getDeviceHeight() {
        return deviceHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.battery_height
     *
     * @param deviceHeight the value for tb_batterytype.battery_height
     * @mbggenerated
     */
    public void setDeviceHeight(String deviceHeight) {
        this.deviceHeight = deviceHeight == null ? null : deviceHeight.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.battery_width
     *
     * @return the value of tb_batterytype.battery_width
     * @mbggenerated
     */
    public String getDeviceWidth() {
        return deviceWidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.battery_width
     *
     * @param deviceWidth the value for tb_batterytype.battery_width
     * @mbggenerated
     */
    public void setDeviceWidth(String deviceWidth) {
        this.deviceWidth = deviceWidth == null ? null : deviceWidth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.battery_material
     *
     * @return the value of tb_batterytype.battery_material
     * @mbggenerated
     */
    public String getDeviceMaterial() {
        return deviceMaterial;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.battery_material
     *
     * @param deviceMaterial the value for tb_batterytype.battery_material
     * @mbggenerated
     */
    public void setDeviceMaterial(String deviceMaterial) {
        this.deviceMaterial = deviceMaterial == null ? null : deviceMaterial.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.battery_type
     *
     * @return the value of tb_batterytype.battery_type
     * @mbggenerated
     */
    public String getBatteryType() {
        return batteryType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.battery_type
     *
     * @param batteryType the value for tb_batterytype.battery_type
     * @mbggenerated
     */
    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType == null ? null : batteryType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.battery_AH
     *
     * @return the value of tb_batterytype.battery_AH
     * @mbggenerated
     */
    public String getBatteryAh() {
        return batteryAh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.battery_AH
     *
     * @param batteryAh the value for tb_batterytype.battery_AH
     * @mbggenerated
     */
    public void setBatteryAh(String batteryAh) {
        this.batteryAh = batteryAh == null ? null : batteryAh.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.create_time
     *
     * @return the value of tb_batterytype.create_time
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.create_time
     *
     * @param createTime the value for tb_batterytype.create_time
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.update_time
     *
     * @return the value of tb_batterytype.update_time
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.update_time
     *
     * @param updateTime the value for tb_batterytype.update_time
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_batterytype.delete_time
     *
     * @return the value of tb_batterytype.delete_time
     * @mbggenerated
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_batterytype.delete_time
     *
     * @param deleteTime the value for tb_batterytype.delete_time
     * @mbggenerated
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @Override
    public String toString() {
        return "BatteryType{" +
                "id=" + id +
                ", typeId='" + typeId + '\'' +
                ", batteryV='" + batteryV + '\'' +
                ", batterySoc='" + batterySoc + '\'' +
                ", batteryHeight='" + deviceHeight + '\'' +
                ", batteryWidth='" + deviceWidth + '\'' +
                ", batteryMaterial='" + deviceMaterial + '\'' +
                ", batteryType='" + batteryType + '\'' +
                ", batteryAh='" + batteryAh + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                ", deleteStatus=" + deleteStatus +
                '}';
    }
}