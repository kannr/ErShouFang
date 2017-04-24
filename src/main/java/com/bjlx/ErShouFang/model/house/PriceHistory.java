package com.bjlx.ErShouFang.model.house;

import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by xiaozhi on 2017/4/24.
 */
@Embedded
public class PriceHistory {

    /**
     * 调整时间
     */
    private Long adjustTime;

    /**
     * 价格
     */
    private Integer price;

    public Long getAdjustTime() {
        return adjustTime;
    }

    public void setAdjustTime(Long adjustTime) {
        this.adjustTime = adjustTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
