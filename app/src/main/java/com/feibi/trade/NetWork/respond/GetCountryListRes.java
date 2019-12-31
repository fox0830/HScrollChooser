package com.feibi.trade.NetWork.respond;

import java.util.ArrayList;

public class GetCountryListRes {
    public ArrayList<Country> country_list;

    public ArrayList<Country> getCountryList() {
        return country_list;
    }

    public  void setCountryList(ArrayList<Country> countryList) {
        this.country_list = countryList;
    }

   public static class Country{
        String id;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
