package com.timemohk.timemo.model;

/**
 * Created by walter.lam on 28/4/2015.
 */
public class Memo {

    private String name;
    private String image;
    private String slug;
    private int total;
    private String dateTime;
    private boolean isMine;

    public Memo(){}

    public Memo(String name, String image){
        this.name = name;
        this.image = image;
    }

    public Memo(String name, String image, String slug, int total){
        this.name = name;
        this.image = image;
        this.total = total;
        this.slug = slug;
    }

    public Memo(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getImage(){
        return this.image;
    }

    public String getSlug(){
        return this.slug;
    }

    public int getTotal(){
        return this.total;
    }

    public boolean isMine() {return isMine; }

    public String getDateTime() {return dateTime; }

    public void setName(String name){
        this.name = name;
    }

    public void setImage(String image){
        this.image = image;
    }

    public void setSlug(String slug){
        this.slug = slug;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public void setMine(boolean isMine) {this.isMine = isMine; }

    public void setDateTime(String dateTime) {this.dateTime = dateTime; }
}
