package com.gigamole.sample.data;

/**
 * Created by youssef on 07/07/17.
 */

/*
    defines class entry containing all possible attributes
 */
public class Entry {

    private String title;
    private String description;
    private String date;   //--------------> store Date as int, long or String?
    private byte [] image;

    public Entry (){
        title = null;
        description = null;
        date = null;
        image = null;
    }
    public Entry (String title, String description, byte [] image ){

        this.description = description;
        this.title = title;
        this.image = image;
    }
    public Entry (String title, String description ){
        this.description = description;
        this.title = title;
        this.image = null;
        // check what is the default value of Blob data type in SQLite
    }
    public Entry (String title, String description,String date, byte [] image ){

        this.date = date;
        this.description = description;
        this.title = title;
        this.image = image;
    }

    public void setEntryTitle(String title){
        this.title = title;
    }
    public void setEntryDescription(String des){
        this.description = des;
    }
    public void setEntryImage(byte [] img){
        this.image = img;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getEntryTitle(){
        return this.title;
    }
    public String getEntryDescription(){
        return this.description;
    }
    public String getDate(){return this.date;}

    public byte [] getEntryImage(){
        return this.image;
    }





}
