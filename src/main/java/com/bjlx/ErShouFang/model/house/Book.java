package com.bjlx.ErShouFang.model.house;

import com.bjlx.ErShouFang.model.misc.Address;
import com.bjlx.ErShouFang.model.misc.Position;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * Created by xiaozhi on 2017/4/24.
 */
@Entity
public class Book {

    /**
     * 主键
     */
    @Id
    private ObjectId id;

    /**
     * 预约地点
     */
    private Address address;

    /**
     * 位置
     */
    private Position position;

    /**
     * 可利用时间
     */
    private List<BookingTime> bookingTimes;

    /**
     * 时间描述
     */
    private String bookTimeDesc;

    /**
     * 预约状态。
     * 1 预约，未交爽约金；
     * 2 买家已交爽约金；
     * 3 卖家确定时间，并同意预约；
     * 4 卖家支付爽约金；
     * 5 仅买家爽约(买家与卖家的爽约金都退款卖家)；
     * 6 仅卖家爽约(买家与卖家的爽约金都退款卖家)；
     * 7 买家与卖家均爽约(归平台所有)；
     * 8 买家与卖家均未爽约(各自退回爽约金)
     */
    private Integer status;

    /**
     * 预约类型
     */
    private Integer bookingType;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<BookingTime> getBookingTimes() {
        return bookingTimes;
    }

    public void setBookingTimes(List<BookingTime> bookingTimes) {
        this.bookingTimes = bookingTimes;
    }

    public String getBookTimeDesc() {
        return bookTimeDesc;
    }

    public void setBookTimeDesc(String bookTimeDesc) {
        this.bookTimeDesc = bookTimeDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBookingType() {
        return bookingType;
    }

    public void setBookingType(Integer bookingType) {
        this.bookingType = bookingType;
    }

    public Book() {

    }
}
