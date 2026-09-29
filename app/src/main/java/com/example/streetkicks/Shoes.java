package com.example.streetkicks;
import java.io.Serializable;

public class Shoes implements Serializable {
    String sid,brand,type,category,color,imgUrl;
    int size,price,rating,modelnum,quantity;

    public Shoes() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Shoes(String sid, String brand, String type, String category, String color, int size, int price, int rating, String imgUrl, int modelnum) {
        this.sid = sid;
        this.brand = brand;
        this.type = type;
        this.category = category;
        this.color = color;
        this.size = size;
        this.price = price;
        this.rating = rating;
        this.imgUrl = imgUrl;
        this.modelnum = modelnum;
    }

    public Shoes(String sid, String brand, String type, String category, String color, int size, int price, int rating, int modelnum) {
        this.sid = sid;
        this.brand = brand;
        this.type = type;
        this.category = category;
        this.color = color;
        this.size = size;
        this.price = price;
        this.rating = rating;
        this.modelnum = modelnum;
    }

    public String toString()
    {
        return ""+brand+" , "+type+" , "+price+","+category+","+imgUrl+","+modelnum;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }


    public String getBrand() {
        return brand;
    }

    public String getImgUrl() {
        return imgUrl;
    }


    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getModelnum() {
        return modelnum;
    }

    public void setModelnum(int modelnum) {
        this.modelnum = modelnum;
    }
}