package org.example.entity;

import lombok.Data;

@Data
public class Order {
    private String order_id;
    private String order_title;
    private int order_fee;
    private String create_time;
    private String pay_time;
    private int pay_status;
    public Order(String order_id,String order_title,int order_fee,String create_time,String pay_time,int pay_status){
        this.order_id = order_id;
        this.order_title = order_title;
        this.order_fee = order_fee;
        this.create_time = create_time;
        this.pay_time = pay_time;
        this.pay_status = pay_status;
    }
}
