package com.bjlx.ErShouFang.model.house;

import com.bjlx.ErShouFang.model.misc.Position;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * 房屋信息
 * Created by xiaozhi on 2017/4/24.
 */
@Entity
public class House {

    /**
     * 主键
     */
    @Id
    private ObjectId id;

    /**
     * 小区名
     */
    private String resident;

    /**
     * 楼号
     */
    private Integer buildingNo;

    /**
     * 单元号
     */
    private Integer unitNo;

    /**
     * 房间号
     */
    private Integer roomNo;

    /**
     * 期望价格
     */
    private Integer price;

    /**
     * 总楼层
     */
    private Integer totalFloor;

    /**
     * 楼层
     */
    private Integer locatedFloor;

    /**
     * 建筑面积
     */
    private double buildingArea;

    /**
     * 套内面积
     */
    private double usableArea;

    /**
     * 户型图
     */
    private String houseModel;

    /**
     * 卧室
     */
    private List<Room> rooms;

    /**
     * 卫生间
     */
    private List<Room> wcs;

    /**
     * 大厅
     */
    private List<Room> halls;

    /**
     * 厨房
     */
    private List<Room> kitchen;

    /**
     * 房屋用途。1 普通住宅，2 商住两用，3 纯商用
     */
    private Integer houseApplication;

    /**
     * 1 精装，2 中等，3 普通
     */
    private Integer decorate;

    /**
     * 有无电梯
     */
    private boolean lift;

    /**
     * 建筑年代
     */
    private Integer buildingYear;

    /**
     * 楼型。1 板楼， 2 塔楼
     */
    private Integer buildingType;

    /**
     * 上次交易
     */
    private Long lastTradeTime;

    /**
     * 是否唯一
     */
    private boolean isUnique;

    /**
     * 交易权属。1 商品房，2 一类经适房，3 二类经适房，4 公房，5 限价房
     */
    private Integer ownership;

    /**
     * 产权年限。houseApplication决定，1 是 70年, 2 是 50年, 3 是 40年
     */
    private Integer rightLimit;

    /**
     * 建筑结构。1 钢混结构，2 砖混结构
     */
    private Integer buildingStruct;

    /**
     * 供暖方式。1 市政供暖，2 燃气供暖，3 煤供暖
     */
    private Integer heatingMode;

    /**
     * 地图位置
     */
    private Position position;

    /**
     * 抵押信息
     */
    private String mortgage;

    /**
     * 学区
     */
    private String schoolDistrict;

    /**
     * 房产证
     */
    private String propertyOwnershipUrl;

    /**
     * 物业费
     */
    private double propertyFee;

    /**
     * 是否承担税费(默认否)
     */
    private boolean isBearTax = false;

    /**
     * ###################税费(自动生成)#####################
     */

    /**
     * 增值税
     */
    private Integer addedValueTax;

    /**
     * 个人所得税
     */
    private Integer personalIncomeTax;

    /**
     * 契税
     */
    private Integer contractTax;

    /**
     * 土地出让金
     */
    private Integer landTransferringFee;
    /**
     * 评估费
     */
    private Integer valuationFee;

    /**
     * 缺陷.1 有遮挡, 2 漏水, 3 凶宅
     */
    private List<Integer> drawbacks;

    /**
     * 备注
     */
    private String memo;

    /**
     * 评估比例
     */
    private double valuationProportion = 1;

    /**
     * 首付
     */
    private Integer downpayment;

    /**
     * 标题
     */
    private String title;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 状态。1 待审核，2 审核通过，3 审核不通过
     */
    private Integer status;

    /**
     * 审核不通过原因
     */
    private String approvalReason;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 调价历史
     */
    private List<PriceHistory> priceHistories;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getResident() {
        return resident;
    }

    public void setResident(String resident) {
        this.resident = resident;
    }

    public Integer getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(Integer buildingNo) {
        this.buildingNo = buildingNo;
    }

    public Integer getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(Integer unitNo) {
        this.unitNo = unitNo;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    public Integer getLocatedFloor() {
        return locatedFloor;
    }

    public void setLocatedFloor(Integer locatedFloor) {
        this.locatedFloor = locatedFloor;
    }

    public double getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(double buildingArea) {
        this.buildingArea = buildingArea;
    }

    public double getUsableArea() {
        return usableArea;
    }

    public void setUsableArea(double usableArea) {
        this.usableArea = usableArea;
    }

    public String getHouseModel() {
        return houseModel;
    }

    public void setHouseModel(String houseModel) {
        this.houseModel = houseModel;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getWcs() {
        return wcs;
    }

    public void setWcs(List<Room> wcs) {
        this.wcs = wcs;
    }

    public List<Room> getHalls() {
        return halls;
    }

    public void setHalls(List<Room> halls) {
        this.halls = halls;
    }

    public List<Room> getKitchen() {
        return kitchen;
    }

    public void setKitchen(List<Room> kitchen) {
        this.kitchen = kitchen;
    }

    public Integer getHouseApplication() {
        return houseApplication;
    }

    public void setHouseApplication(Integer houseApplication) {
        this.houseApplication = houseApplication;
    }

    public Integer getDecorate() {
        return decorate;
    }

    public void setDecorate(Integer decorate) {
        this.decorate = decorate;
    }

    public boolean isLift() {
        return lift;
    }

    public void setLift(boolean lift) {
        this.lift = lift;
    }

    public Integer getBuildingYear() {
        return buildingYear;
    }

    public void setBuildingYear(Integer buildingYear) {
        this.buildingYear = buildingYear;
    }

    public Integer getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(Integer buildingType) {
        this.buildingType = buildingType;
    }

    public Long getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(Long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setIsUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public Integer getOwnership() {
        return ownership;
    }

    public void setOwnership(Integer ownership) {
        this.ownership = ownership;
    }

    public Integer getRightLimit() {
        return rightLimit;
    }

    public void setRightLimit(Integer rightLimit) {
        this.rightLimit = rightLimit;
    }

    public Integer getBuildingStruct() {
        return buildingStruct;
    }

    public void setBuildingStruct(Integer buildingStruct) {
        this.buildingStruct = buildingStruct;
    }

    public Integer getHeatingMode() {
        return heatingMode;
    }

    public void setHeatingMode(Integer heatingMode) {
        this.heatingMode = heatingMode;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getMortgage() {
        return mortgage;
    }

    public void setMortgage(String mortgage) {
        this.mortgage = mortgage;
    }

    public String getSchoolDistrict() {
        return schoolDistrict;
    }

    public void setSchoolDistrict(String schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    public String getPropertyOwnershipUrl() {
        return propertyOwnershipUrl;
    }

    public void setPropertyOwnershipUrl(String propertyOwnershipUrl) {
        this.propertyOwnershipUrl = propertyOwnershipUrl;
    }

    public double getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(double propertyFee) {
        this.propertyFee = propertyFee;
    }

    public boolean isBearTax() {
        return isBearTax;
    }

    public void setIsBearTax(boolean isBearTax) {
        this.isBearTax = isBearTax;
    }

    public Integer getAddedValueTax() {
        return addedValueTax;
    }

    public void setAddedValueTax(Integer addedValueTax) {
        this.addedValueTax = addedValueTax;
    }

    public Integer getPersonalIncomeTax() {
        return personalIncomeTax;
    }

    public void setPersonalIncomeTax(Integer personalIncomeTax) {
        this.personalIncomeTax = personalIncomeTax;
    }

    public Integer getContractTax() {
        return contractTax;
    }

    public void setContractTax(Integer contractTax) {
        this.contractTax = contractTax;
    }

    public Integer getLandTransferringFee() {
        return landTransferringFee;
    }

    public void setLandTransferringFee(Integer landTransferringFee) {
        this.landTransferringFee = landTransferringFee;
    }

    public Integer getValuationFee() {
        return valuationFee;
    }

    public void setValuationFee(Integer valuationFee) {
        this.valuationFee = valuationFee;
    }

    public List<Integer> getDrawbacks() {
        return drawbacks;
    }

    public void setDrawbacks(List<Integer> drawbacks) {
        this.drawbacks = drawbacks;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public double getValuationProportion() {
        return valuationProportion;
    }

    public void setValuationProportion(double valuationProportion) {
        this.valuationProportion = valuationProportion;
    }

    public Integer getDownpayment() {
        return downpayment;
    }

    public void setDownpayment(Integer downpayment) {
        this.downpayment = downpayment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getApprovalReason() {
        return approvalReason;
    }

    public void setApprovalReason(String approvalReason) {
        this.approvalReason = approvalReason;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public List<PriceHistory> getPriceHistories() {
        return priceHistories;
    }

    public void setPriceHistories(List<PriceHistory> priceHistories) {
        this.priceHistories = priceHistories;
    }

    public House(){}

    public House(ObjectId id, String resident, Integer buildingNo, Integer unitNo, Integer roomNo, Integer price, Integer totalFloor,
                 Integer locatedFloor, double buildingArea, double usableArea, String houseModel, List<Room> rooms, List<Room> wcs,
                 List<Room> halls, List<Room> kitchen, Integer houseApplication, Integer decorate, boolean lift, Integer buildingYear,
                 Integer buildingType, Long lastTradeTime, boolean isUnique, Integer ownership, Integer rightLimit, Integer buildingStruct,
                 Integer heatingMode, Position position, String mortgage, String propertyOwnershipUrl, String title, String unitPrice) {
        this.id = id;
        this.resident = resident;
        this.buildingNo = buildingNo;
        this.unitNo = unitNo;
        this.roomNo = roomNo;
        this.price = price;
        this.totalFloor = totalFloor;
        this.locatedFloor = locatedFloor;
        this.buildingArea = buildingArea;
        this.usableArea = usableArea;
        this.houseModel = houseModel;
        this.rooms = rooms;
        this.wcs = wcs;
        this.halls = halls;
        this.kitchen = kitchen;
        this.houseApplication = houseApplication;
        this.decorate = decorate;
        this.lift = lift;
        this.buildingYear = buildingYear;
        this.buildingType = buildingType;
        this.lastTradeTime = lastTradeTime;
        this.isUnique = isUnique;
        this.ownership = ownership;
        this.rightLimit = rightLimit;
        this.buildingStruct = buildingStruct;
        this.heatingMode = heatingMode;
        this.position = position;
        this.mortgage = mortgage;
        this.propertyOwnershipUrl = propertyOwnershipUrl;
        this.title = title;
        this.unitPrice = unitPrice;
        this.createTime = System.currentTimeMillis();
    }
}
