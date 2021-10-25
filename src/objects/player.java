
package objects;

import clients.*;
import controller_and_fxml.*;
import objects.*;
import servers.*;
import util.NetworkUtil;


import java.io.Serializable;

public class player implements Serializable
{
    private String name;
    private String country;
    private int age;
    private double height;
    private String club_name;
    private String position;
    private int number;
    private double salary;
    private int selling_price=0;
    private boolean on_sell_list=false;






    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setClub(String club) {
        this.club_name = club;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public void setOn_sell_list(boolean on_sell_list) {
        this.on_sell_list = on_sell_list;
    }










    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public double getSalary() {
        return salary;
    }

    public double getHeight() {
        return height;
    }

    public String getClub() {
        return club_name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getPosition() {
        return position;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public boolean isOn_sell_list() {
        return on_sell_list;
    }






}
