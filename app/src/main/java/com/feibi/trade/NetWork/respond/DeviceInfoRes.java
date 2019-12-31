package com.feibi.trade.NetWork.respond;

public class DeviceInfoRes {

    /**
     * device_info : {"id":"f118c753-5a4d-4d87-b50c-c808fc802d56","name":"除濕器","data_name":"Device1","device_data":"{\"Data2\":0,\"Data3\":19,\"Data4\":55,\"Data5\":7,\"Data6\":0,\"Data7\":0,\"Data8\":0,\"Data9\":0,\"Data10\":40,\"Data11\":70,\"Data12\":40,\"Data13\":70,\"Data14\":0,\"Data15\":0,\"Data16\":0,\"Data17\":0,\"Data18\":0,\"Data19\":0,\"Data20\":0}","member_host_id":"f6e448f2-3abc-436c-a344-ac34302dade7","device_class_id":"582970c2-46e6-453e-82dd-8313b761896a","device_place_id":null,"device_place_name":null,"device_id":"4a0b4d6b-c32a-4aae-9fec-b6c421fdffbe","thing":"28e148eb2ced5214edaa79237ea9ee4644388ebd","device_name":"除濕器","device_pic":"updata/device/2019-11/20191129122730.jpg","device_class_name":"除濕","device_type":"1","is_new":"no"}
     */

    private DeviceBindRes.Device device_info;

    public DeviceBindRes.Device getDevice_info() {
        return device_info;
    }

    public void setDevice_info(DeviceBindRes.Device device_info) {
        this.device_info = device_info;
    }
}
