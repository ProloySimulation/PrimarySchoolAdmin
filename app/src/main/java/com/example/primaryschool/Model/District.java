package com.example.primaryschool.Model;

public class District {

    String districtid, districtname;

    public District(String districtid, String districtname) {
        this.districtid = districtid;
        this.districtname = districtname;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }
}
