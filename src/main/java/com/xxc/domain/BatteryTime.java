package com.xxc.domain;

import java.io.Serializable;
import java.util.Date;

public class BatteryTime implements Serializable {

    private Integer id;
    private String deviceId;
    private String useDay;
    private String useHour;
    private String useMin;
    private String useSec;
    private String usedDay;
    private String usedHour;
    private String usedMin;
    private String usedSec;
    private String useTotalTime;
    private String usedTotalTime;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getUseDay() {
        return useDay;
    }
    public void setUseDay(String useDay) {
        this.useDay = useDay == null ? null : useDay.trim();
    }

    public String getUseHour() {
        return useHour;
    }
    public void setUseHour(String useHour) {
        this.useHour = useHour == null ? null : useHour.trim();
    }

    public String getUseMin() {
        return useMin;
    }
    public void setUseMin(String useMin) {
        this.useMin = useMin == null ? null : useMin.trim();
    }

    public String getUseSec() {
        return useSec;
    }
    public void setUseSec(String useSec) {
        this.useSec = useSec == null ? null : useSec.trim();
    }

    public String getUsedDay() {
        return usedDay;
    }
    public void setUsedDay(String usedDay) {
        this.usedDay = usedDay == null ? null : usedDay.trim();
    }

    public String getUsedHour() {
        return usedHour;
    }
    public void setUsedHour(String usedHour) {
        this.usedHour = usedHour == null ? null : usedHour.trim();
    }

    public String getUsedMin() {
        return usedMin;
    }
    public void setUsedMin(String usedMin) {
        this.usedMin = usedMin == null ? null : usedMin.trim();
    }

    public String getUsedSec() {
        return usedSec;
    }
    public void setUsedSec(String usedSec) {
        this.usedSec = usedSec == null ? null : usedSec.trim();
    }

    public String getUseTotalTime() {
        return useTotalTime;
    }
    public void setUseTotalTime(String useTotalTime) {
        this.useTotalTime = useTotalTime == null ? null : useTotalTime.trim();
    }

    public String getUsedTotalTime() {
        return usedTotalTime;
    }
    public void setUsedTotalTime(String usedTotalTime) {
        this.usedTotalTime = usedTotalTime == null ? null : usedTotalTime.trim();
    }

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

    @Override
    public String toString() {
        return "BatteryTime{" +
                "id=" + id +
                ", batteryId='" + deviceId + '\'' +
                ", useDay='" + useDay + '\'' +
                ", useHour='" + useHour + '\'' +
                ", useMin='" + useMin + '\'' +
                ", useSec='" + useSec + '\'' +
                ", usedDay='" + usedDay + '\'' +
                ", usedHour='" + usedHour + '\'' +
                ", usedMin='" + usedMin + '\'' +
                ", usedSec='" + usedSec + '\'' +
                ", useTotalTime='" + useTotalTime + '\'' +
                ", usedTotalTime='" + usedTotalTime + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                '}';
    }
}