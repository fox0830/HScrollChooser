package com.feibi.trade.NetWork.respond;

import java.util.ArrayList;

public class GetDeviceClassRes {
    ArrayList<DeviceClass> device_class_list;
    ArrayList<DeviceClass> device_place_list;

    public ArrayList<DeviceClass> getDevice_class_list() {
        return device_class_list;
    }

    public void setDevice_class_list(ArrayList<DeviceClass> device_class_list) {
        this.device_class_list = device_class_list;
    }

    public ArrayList<DeviceClass> getDevice_place_list() {
        return device_place_list;
    }

    public void setDevice_place_list(ArrayList<DeviceClass> device_place_list) {
        this.device_place_list = device_place_list;
    }

    public class DeviceClass{
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
