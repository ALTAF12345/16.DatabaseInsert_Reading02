package org.sawhers.altafhussain.databasereading.MODEL;

import java.io.Serializable;

/**
 * Created by ALTAFHUSSAIN on 12/28/2016.
 */

public class Student implements Serializable{

    int id;
    String name;
    int totalFee;
    String course;
    int feePaid;

    public Student() {
    }


    public Student(String name,String course, int totalFee, int feePaid) {
        //This constractor is used when we entry in database but remember we not include the id this time
        this.name = name;
        this.totalFee = totalFee;
        this.feePaid = feePaid;
        this.course = course;
    }

    public Student(int id, String name,String course, int totalFee, int feePaid) {
        this.id = id;
        this.name = name;
        this.totalFee = totalFee;
        this.feePaid = feePaid;
        this.course=course;
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

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(int feePaid) {
        this.feePaid = feePaid;
    }

    //((((((((((((((((((
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.name = course;
    }
    //((((((((((((((((((

    @Override
    public String toString() {
        return name;
    }
}

