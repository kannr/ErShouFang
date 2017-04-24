package com.bjlx.ErShouFang.model.house;

import org.mongodb.morphia.annotations.Embedded;

/**
 * 房间。
 * 卧室，大厅，厨房，卫生间
 * Created by xiaozhi on 2017/4/24.
 */
@Embedded
public class Room {

    /**
     * 东，西，南，北，无窗
     */
    private String orientation;

    private String image;

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
