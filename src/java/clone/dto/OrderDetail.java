/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clone.dto;

/**
 *
 * @author nthie
 */
public class OrderDetail {
    private int orderdetailid;
    private int orderid;
    private int plantid;
    private String plantname;
    private int price;
    private String imgpath;
    private int quantity;

    public OrderDetail(int orderdetailid, int orderid, int plantid, String plantname, int price, String imgpath, int quantity) {
        this.orderdetailid = orderdetailid;
        this.orderid = orderid;
        this.plantid = plantid;
        this.plantname = plantname;
        this.price = price;
        this.imgpath = imgpath;
        this.quantity = quantity;
    }

    public int getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(int orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getPlantid() {
        return plantid;
    }

    public void setPlantid(int plantid) {
        this.plantid = plantid;
    }

    public String getPlantname() {
        return plantname;
    }

    public void setPlantname(String plantname) {
        this.plantname = plantname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
