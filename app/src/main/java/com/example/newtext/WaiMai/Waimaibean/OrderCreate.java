package com.example.newtext.WaiMai.Waimaibean;

import java.util.List;

public class OrderCreate {
    /**
     * addressDetail : 大连理工大学 教学楼 A3-118
     * label : 学校
     * name : 王先生
     * phone : 13800000000
     * amount : 64
     * orderItemList : [{"productId":1,"quantity":2},{"productId":30,"quantity":1}]
     * sellerId : 1
     */

    private String addressDetail;
    private String label;
    private String name;
    private String phone;
    private double amount;
    private int sellerId;
    private List<OrderItemListBean> orderItemList;

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public List<OrderItemListBean> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemListBean> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public static class OrderItemListBean {
        /**
         * productId : 1
         * quantity : 2
         */

        private int productId;
        private int quantity;

        public OrderItemListBean(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
