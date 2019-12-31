package com.feibi.trade.NetWork.respond;

import java.util.ArrayList;

public class GetAreaListRes {
    private ArrayList<District> district_list;

    public void setDistrict_list(ArrayList<District> district_list) {
        this.district_list = district_list;
    }

    public ArrayList<District> getDistrict_list() {
        return district_list;
    }

    public class District {
        private String id;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
