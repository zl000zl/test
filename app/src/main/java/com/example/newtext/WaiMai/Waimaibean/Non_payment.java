package com.example.newtext.WaiMai.Waimaibean;

import java.util.List;

public class Non_payment {

    /**
     * sellerInfo : {"searchValue":null,"createBy":null,"createTime":"2021-04-30 12:01:35","updateBy":null,"updateTime":"2021-05-11 15:40:46","remark":null,"params":{},"id":3,"name":"贡茶","address":"尖山街131号1-2","introduction":"黑石礁奶茶第二名","themeId":2,"score":4.8,"saleQuantity":3737,"deliveryTime":38,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/a42956a6-6ca5-47a1-8778-c93d8deba365.jpg","avgCost":20,"other":null,"recommend":"Y","distance":3700,"saleNum3hour":66}
     * orderInfo : {"searchValue":null,"createBy":null,"createTime":"2021-12-02 22:06:37","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":793,"orderNo":"2021120222063760454","userId":1113094,"sellerId":3,"amount":32,"postage":null,"status":"待支付","paymentType":null,"payTime":null,"sendTime":null,"receiverName":"联系人：大卫","receiverPhone":"手机：12240669812","receiverAddress":"收货地址：大连理工大学教学楼 A3-118","receiverLabel":"学校","houseNumber":null,"orderItemList":[{"searchValue":null,"createBy":null,"createTime":"2021-12-02 22:06:37","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2391,"userId":1113094,"orderNo":"2021120222063760454","productId":54,"productName":"仙草奶茶","productImage":"/prod-api/profile/upload/image/2021/05/08/7e808acf-306f-499e-95dd-3eb9c506a0fb.jpg","productPrice":16,"quantity":1,"totalPrice":16},{"searchValue":null,"createBy":null,"createTime":"2021-12-02 22:06:37","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2392,"userId":1113094,"orderNo":"2021120222063760454","productId":43,"productName":"布丁奶茶","productImage":"/prod-api/profile/upload/image/2021/05/08/37e26f43-e764-4f70-8d31-98b1094c7641.jpg","productPrice":16,"quantity":1,"totalPrice":16}]}
     */

    private SellerInfoBean sellerInfo;
    private OrderInfoBean orderInfo;

    public SellerInfoBean getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(SellerInfoBean sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static class SellerInfoBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2021-04-30 12:01:35
         * updateBy : null
         * updateTime : 2021-05-11 15:40:46
         * remark : null
         * params : {}
         * id : 3
         * name : 贡茶
         * address : 尖山街131号1-2
         * introduction : 黑石礁奶茶第二名
         * themeId : 2
         * score : 4.8
         * saleQuantity : 3737
         * deliveryTime : 38
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/a42956a6-6ca5-47a1-8778-c93d8deba365.jpg
         * avgCost : 20.0
         * other : null
         * recommend : Y
         * distance : 3700.0
         * saleNum3hour : 66
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String name;
        private String address;
        private String introduction;
        private int themeId;
        private double score;
        private int saleQuantity;
        private int deliveryTime;
        private String imgUrl;
        private double avgCost;
        private Object other;
        private String recommend;
        private double distance;
        private int saleNum3hour;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getThemeId() {
            return themeId;
        }

        public void setThemeId(int themeId) {
            this.themeId = themeId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(int saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public int getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(int deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public double getAvgCost() {
            return avgCost;
        }

        public void setAvgCost(double avgCost) {
            this.avgCost = avgCost;
        }

        public Object getOther() {
            return other;
        }

        public void setOther(Object other) {
            this.other = other;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getSaleNum3hour() {
            return saleNum3hour;
        }

        public void setSaleNum3hour(int saleNum3hour) {
            this.saleNum3hour = saleNum3hour;
        }

        public static class ParamsBean {
        }
    }

    public static class OrderInfoBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2021-12-02 22:06:37
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 793
         * orderNo : 2021120222063760454
         * userId : 1113094
         * sellerId : 3
         * amount : 32.0
         * postage : null
         * status : 待支付
         * paymentType : null
         * payTime : null
         * sendTime : null
         * receiverName : 联系人：大卫
         * receiverPhone : 手机：12240669812
         * receiverAddress : 收货地址：大连理工大学教学楼 A3-118
         * receiverLabel : 学校
         * houseNumber : null
         * orderItemList : [{"searchValue":null,"createBy":null,"createTime":"2021-12-02 22:06:37","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2391,"userId":1113094,"orderNo":"2021120222063760454","productId":54,"productName":"仙草奶茶","productImage":"/prod-api/profile/upload/image/2021/05/08/7e808acf-306f-499e-95dd-3eb9c506a0fb.jpg","productPrice":16,"quantity":1,"totalPrice":16},{"searchValue":null,"createBy":null,"createTime":"2021-12-02 22:06:37","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2392,"userId":1113094,"orderNo":"2021120222063760454","productId":43,"productName":"布丁奶茶","productImage":"/prod-api/profile/upload/image/2021/05/08/37e26f43-e764-4f70-8d31-98b1094c7641.jpg","productPrice":16,"quantity":1,"totalPrice":16}]
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBeanX params;
        private int id;
        private String orderNo;
        private int userId;
        private int sellerId;
        private double amount;
        private Object postage;
        private String status;
        private Object paymentType;
        private Object payTime;
        private Object sendTime;
        private String receiverName;
        private String receiverPhone;
        private String receiverAddress;
        private String receiverLabel;
        private Object houseNumber;
        private List<OrderItemListBean> orderItemList;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBeanX getParams() {
            return params;
        }

        public void setParams(ParamsBeanX params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public Object getPostage() {
            return postage;
        }

        public void setPostage(Object postage) {
            this.postage = postage;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(Object paymentType) {
            this.paymentType = paymentType;
        }

        public Object getPayTime() {
            return payTime;
        }

        public void setPayTime(Object payTime) {
            this.payTime = payTime;
        }

        public Object getSendTime() {
            return sendTime;
        }

        public void setSendTime(Object sendTime) {
            this.sendTime = sendTime;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getReceiverPhone() {
            return receiverPhone;
        }

        public void setReceiverPhone(String receiverPhone) {
            this.receiverPhone = receiverPhone;
        }

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public String getReceiverLabel() {
            return receiverLabel;
        }

        public void setReceiverLabel(String receiverLabel) {
            this.receiverLabel = receiverLabel;
        }

        public Object getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(Object houseNumber) {
            this.houseNumber = houseNumber;
        }

        public List<OrderItemListBean> getOrderItemList() {
            return orderItemList;
        }

        public void setOrderItemList(List<OrderItemListBean> orderItemList) {
            this.orderItemList = orderItemList;
        }

        public static class ParamsBeanX {
        }

        public static class OrderItemListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2021-12-02 22:06:37
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * id : 2391
             * userId : 1113094
             * orderNo : 2021120222063760454
             * productId : 54
             * productName : 仙草奶茶
             * productImage : /prod-api/profile/upload/image/2021/05/08/7e808acf-306f-499e-95dd-3eb9c506a0fb.jpg
             * productPrice : 16.0
             * quantity : 1
             * totalPrice : 16.0
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBeanXX params;
            private int id;
            private int userId;
            private String orderNo;
            private int productId;
            private String productName;
            private String productImage;
            private double productPrice;
            private int quantity;
            private double totalPrice;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue = searchValue;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public ParamsBeanXX getParams() {
                return params;
            }

            public void setParams(ParamsBeanXX params) {
                this.params = params;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductImage() {
                return productImage;
            }

            public void setProductImage(String productImage) {
                this.productImage = productImage;
            }

            public double getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(double productPrice) {
                this.productPrice = productPrice;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
            }

            public static class ParamsBeanXX {
            }
        }
    }
}