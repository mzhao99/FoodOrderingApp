/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.sql.*;

/**
 *
 * @author manlingzhao
 */
@Entity
@Table(name="orders")
public class Order {
    @Id
    private int id;
    private String resId;
    private String itemId;
    private String userId;
    private String orderStatus;    //placed, in progress, done
    private Timestamp orderTime;
    private int orderId;
    private String itemName;
    private double itemPrice;
    private String resName;
    private String userFn;

    public Order() {
    }

    public Order(int id, String resId, String itemId, String userId, String orderStatus, Timestamp orderTime, int orderId, String itemName, double itemPrice, String resName, String userFn) {
        this.id = id;
        this.resId = resId;
        this.itemId = itemId;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
        this.orderId = orderId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.resName = resName;
        this.userFn = userFn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getUserFn() {
        return userFn;
    }

    public void setUserFn(String userFn) {
        this.userFn = userFn;
    }

}
