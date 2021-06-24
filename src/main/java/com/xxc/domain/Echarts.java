package com.xxc.domain;

/**
 * @author xxc
 * @date 2020/7/27 - 8:54
 */
public class Echarts {

    private String name;
    private int nums;

    public Echarts() {
    }

    public Echarts(String name, int nums) {
        this.name = name;
        this.nums = nums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "Echarts{" +
                "name='" + name + '\'' +
                ", nums=" + nums +
                '}';
    }
}
