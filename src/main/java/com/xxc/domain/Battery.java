package com.xxc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Battery implements Serializable {

    private Integer id;
    private String userId;
    private String projectId;
    private String deviceId;
    private String statusId;
    private String typeId;
    private String deviceName;
    private BigDecimal batteryPrice;
    private String address;
    private Integer status;
    private String deviceRepair;
    private String faultDetail;
    private String rentalTime;
    private Date startTime;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
    private Long loginTime;
    private String longitude;
    private String latitude;
    private String isOnline;
    private String protocolVersion;
    private String deviceVersion;
    private String verifyMethod;
    private String deviceType;
    private Integer deleteStatus;

    private BatteryType batteryType;
    private BatteryStatus batteryStatus;
    private BatteryTime batteryTime;

    public BatteryType getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(BatteryType batteryType) {
        this.batteryType = batteryType;
    }

    public BatteryStatus getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(BatteryStatus batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public BatteryTime getBatteryTime() {
        return batteryTime;
    }

    public void setBatteryTime(BatteryTime batteryTime) {
        this.batteryTime = batteryTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.id
     *
     * @return the value of tb_battery.id
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.id
     *
     * @param id the value for tb_battery.id
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.user_id
     *
     * @return the value of tb_battery.user_id
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.user_id
     *
     * @param userId the value for tb_battery.user_id
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.project_id
     *
     * @return the value of tb_battery.project_id
     * @mbggenerated
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.project_id
     *
     * @param projectId the value for tb_battery.project_id
     * @mbggenerated
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.battery_id
     *
     * @return the value of tb_battery.battery_id
     * @mbggenerated
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.battery_id
     *
     * @param deviceId the value for tb_battery.battery_id
     * @mbggenerated
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.battery_name
     *
     * @return the value of tb_battery.battery_name
     * @mbggenerated
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.battery_name
     *
     * @param deviceName the value for tb_battery.battery_name
     * @mbggenerated
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.battery_price
     *
     * @return the value of tb_battery.battery_price
     * @mbggenerated
     */
    public BigDecimal getBatteryPrice() {
        return batteryPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.battery_price
     *
     * @param batteryPrice the value for tb_battery.battery_price
     * @mbggenerated
     */
    public void setBatteryPrice(BigDecimal batteryPrice) {
        this.batteryPrice = batteryPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.address
     *
     * @return the value of tb_battery.address
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.address
     *
     * @param address the value for tb_battery.address
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.status
     *
     * @return the value of tb_battery.status
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.status
     *
     * @param status the value for tb_battery.status
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeviceRepair() {
        return deviceRepair;
    }

    public void setDeviceRepair(String deviceRepair) {
        this.deviceRepair = deviceRepair;
    }

    public String getFaultDetail() {
        return faultDetail;
    }

    public void setFaultDetail(String faultDetail) {
        this.faultDetail = faultDetail;
    }

    public String getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(String rentalTime) {
        this.rentalTime = rentalTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.create_time
     *
     * @return the value of tb_battery.create_time
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public Date getDeleteTime() {
        return deleteTime;
    }
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.login_time
     *
     * @return the value of tb_battery.login_time
     * @mbggenerated
     */
    public Long getLoginTime() {
        return loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.login_time
     *
     * @param loginTime the value for tb_battery.login_time
     * @mbggenerated
     */
    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.longitude
     *
     * @return the value of tb_battery.longitude
     * @mbggenerated
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.longitude
     *
     * @param longitude the value for tb_battery.longitude
     * @mbggenerated
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.latitude
     *
     * @return the value of tb_battery.latitude
     * @mbggenerated
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.latitude
     *
     * @param latitude the value for tb_battery.latitude
     * @mbggenerated
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.isonline
     *
     * @return the value of tb_battery.isOnline
     * @mbggenerated
     */
    public String getIsOnline() {
        return isOnline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.isonline
     *
     * @param isOnline the value for tb_battery.isOnline
     * @mbggenerated
     */
    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline == null ? null : isOnline.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.protocolVersion
     *
     * @return the value of tb_battery.protocolVersion
     * @mbggenerated
     */
    public String getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.protocolVersion
     *
     * @param protocolVersion the value for tb_battery.protocolVersion
     * @mbggenerated
     */
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion == null ? null : protocolVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.batteryVersion
     *
     * @return the value of tb_battery.batteryVersion
     * @mbggenerated
     */
    public String getDeviceVersion() {
        return deviceVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.batteryVersion
     *
     * @param deviceVersion the value for tb_battery.batteryVersion
     * @mbggenerated
     */
    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion == null ? null : deviceVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.verifyMethod
     *
     * @return the value of tb_battery.verifyMethod
     * @mbggenerated
     */
    public String getVerifyMethod() {
        return verifyMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.verifyMethod
     *
     * @param verifyMethod the value for tb_battery.verifyMethod
     * @mbggenerated
     */
    public void setVerifyMethod(String verifyMethod) {
        this.verifyMethod = verifyMethod == null ? null : verifyMethod.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_battery.batteryType
     *
     * @return the value of tb_battery.batteryType
     * @mbggenerated
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_battery.batteryType
     *
     * @param deviceType the value for tb_battery.batteryType
     * @mbggenerated
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @Override
    public String toString() {
        return "Battery{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", statusId='" + statusId + '\'' +
                ", typeId='" + typeId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", batteryPrice=" + batteryPrice +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", deviceRepair='" + deviceRepair + '\'' +
                ", faultDetail='" + faultDetail + '\'' +
                ", rentalTime='" + rentalTime + '\'' +
                ", startTime=" + startTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                ", loginTime=" + loginTime +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", isOnline='" + isOnline + '\'' +
                ", protocolVersion='" + protocolVersion + '\'' +
                ", deviceVersion='" + deviceVersion + '\'' +
                ", verifyMethod='" + verifyMethod + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", batteryType=" + batteryType +
                ", batteryStatus=" + batteryStatus +
                '}';
    }
}