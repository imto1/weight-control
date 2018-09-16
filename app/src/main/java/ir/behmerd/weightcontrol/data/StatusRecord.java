package ir.behmerd.weightcontrol.data;

import java.io.Serializable;

/**
 * Structure class to hold a record from STATUS table of Database.
 */
public class StatusRecord implements Serializable {

    private int id, height, body_status;
    private String checkDate, activities;
    private float weight,bmi, difference;

    // Constructors
    public StatusRecord(){}
    public StatusRecord(int id, String checkDate,int height,
                        float weight, float bmi, int body_status,
                        float difference, String activities){
        this.id = id;
        this.checkDate = checkDate;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.body_status = body_status;
        this.difference = difference;
        this.activities = activities;
    }
    public StatusRecord(String checkDate, int height, float weight,float bmi,
                        int body_status, float difference, String activities){
        this.checkDate = checkDate;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.body_status = body_status;
        this.difference = difference;
        this.activities = activities;
    }

    // Commands
    public void setId(int id){
        this.id = id;
    }
    public void setCheckDate(String checkDate){
        this.checkDate = checkDate;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setWeight(float weight){
        this.weight = weight;
    }
    public void setBmi(float bmi){
        this.bmi = bmi;
    }
    public void setBody_status(int body_status){
        this.body_status = body_status;
    }
    public void setDifference(float difference){
        this.difference = difference;
    }
    public void setActivities(String activities){
        this.activities = activities;
    }

    // Queries
    public int getId(){
        return this.id;
    }
    public String getCheckDate(){
        return this.checkDate;
    }
    public int getHeight(){
        return this.height;
    }
    public float getWeight(){
        return this.weight;
    }
    public float getBmi(){
        return this.bmi;
    }
    public int getBody_status(){
        return this.body_status;
    }
    public float getDifference(){
        return this.difference;
    }
    public String getActivities(){
        return this.activities;
    }
}
