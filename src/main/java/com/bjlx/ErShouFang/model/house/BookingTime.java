package com.bjlx.ErShouFang.model.house;

/**
 * Created by xiaozhi on 2017/4/24.
 */
public class BookingTime {

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

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

    public BookingTime() {}

    public BookingTime(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
