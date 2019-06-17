package com.unab.camerica.models;

import java.util.Map;

public class Prediction {

    Long countryId1;
    Long countryId2;
    int res1;
    int res2;

    // Constructor
    public Prediction(Long countryId1, Long countryId2, int res1, int res2) {
        this.countryId1 = countryId1;
        this.countryId2 = countryId2;
        this.res1 = res1;
        this.res2 = res2;
    }

    public Prediction(Map map) {
        this.countryId1 = (Long)map.get("countryId1");
        this.countryId2 = (Long)map.get("countryId2");
        this.res1 = Integer.parseInt(map.get("res1").toString());
        this.res2 = Integer.parseInt(map.get("res2").toString());
    }

    public Long getCountryId1() {
        return countryId1;
    }

    public void setCountryId1(Long countryId1) {
        this.countryId1 = countryId1;
    }

    public Long getCountryId2() {
        return countryId2;
    }

    public void setCountryId2(Long countryId2) {
        this.countryId2 = countryId2;
    }

    public int getRes1() {
        return res1;
    }

    public void setRes1(int res1) {
        this.res1 = res1;
    }

    public int getRes2() {
        return res2;
    }

    public void setRes2(int res2) {
        this.res2 = res2;
    }
}
