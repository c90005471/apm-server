package com.aaa.entity;

import java.io.Serializable;

/**
 * @Author: 陈建
 * @Date: 2019/6/19 0019 16:16
 * @Version 1.0
 */
public class RunInfo implements Serializable {
    /**
    线程id
     */
    private String id;
    /**
     * 方法名称，包含类名
     */
    private String methodName;
    /**
     * 开始时间的毫秒数
     */
    private Long startTime;
    /**
     * 结束时间的毫秒数
     */
    private Long endTime;
    /**
     * 方法执行花费的毫秒数
     */
    private Long costTime;
    /**
     * 方法执行完时的内存使用率
     */
    private Double memoryRate;
    /**
     * 方法执行完时的cpu使用率
     */
    private Double cpuRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public Double getMemoryRate() {
        return memoryRate;
    }

    public void setMemoryRate(Double memoryRate) {
        this.memoryRate = memoryRate;
    }

    public Double getCpuRate() {
        return cpuRate;
    }

    public void setCpuRate(Double cpuRate) {
        this.cpuRate = cpuRate;
    }
}
