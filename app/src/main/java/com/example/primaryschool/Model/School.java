package com.example.primaryschool.Model;

/**
 * Created by Gowrab Bhowmick on 2/23/2020
 * Project PrimaryAdmin
 */
public class School {
    String schoolid,schoolname;

    public School(String schoolid, String schoolname) {
        this.schoolid = schoolid;
        this.schoolname = schoolname;
    }


    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }
}
