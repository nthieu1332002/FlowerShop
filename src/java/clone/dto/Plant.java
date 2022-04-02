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
public class Plant {
    private int id;
    private String name;
    private int price;
    private String imgpath;
    private String description;
    private int status;
    private int cateid;
    
    public Plant() {
        
    }
    
    public Plant(int id, String name, int price, String imgpath, String description, int status, int cateid) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgpath = imgpath;
        this.description = description;
        this.status = status;
        this.cateid = cateid;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }
    
}
