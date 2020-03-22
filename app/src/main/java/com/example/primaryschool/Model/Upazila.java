package com.example.primaryschool.Model;

/**
 * Created by Gowrab Bhowmick on 2/23/2020
 * Project PrimaryAdmin
 */
public class Upazila {

    String upazilaid, upazilaname;


    public Upazila(String upazilaid, String upazilaname) {
        this.upazilaid = upazilaid;
        this.upazilaname = upazilaname;
    }

    public String getUpazilaid() {
        return upazilaid;
    }

    public void setUpazilaid(String upazilaid) {
        this.upazilaid = upazilaid;
    }

    public String getUpazilaname() {
        return upazilaname;
    }

    public void setUpazilaname(String upazilaname) {
        this.upazilaname = upazilaname;
    }
}
