package com.feibi.trade.NetWork.respond;

import java.io.Serializable;
import java.util.ArrayList;

//        "id": "b6cfad49-b8e9-4905-b4fe-0e242d7b4481",
//        "name": "淨水器",
//        "data_name": "Device6",
//        "device_data": "{\"Data2\":0,\"Data3\":1096,\"Data4\":1096,\"Data5\":1096,\"Data6\":0,\"Data7\":0,\"Data8\":0,\"Data9\":0,\"Data10\":0,\"Data11\":0,\"Data12\":0,\"Data13\":0,\"Data14\":0,\"Data15\":0,\"Data16\":0,\"Data17\":0,\"Data18\":0,\"Data19\":0,\"Data20\":0}",
//        "member_host_id": "3f7a2862-287a-4f06-bfcf-16ebe33d02f1",
//        "device_class_id": "ff2a52c5-8b0e-40b6-a32f-53246c212920",
//        "device_place_id": null,
//        "device_place_name": null,
//        "device_id": "ecffffbc-060e-4823-9fa8-4f45cb626fee",
//        "thing": "28e148eb2ced5214edaa79237ea9ee4644388ebd",
//        "device_name": "淨水器",
//        "device_pic": "updata/device/2019-11/20191129122756.jpg",
//        "device_class_name": "淨水",
//        "device_type": "6"

public class DeviceBindRes {
    String is_update;         //數據是否是最新的，yes/no
    ArrayList<HostDevice> host_list;

    public String getIs_update() {
        return is_update;
    }

    public void setIs_update(String is_update) {
        this.is_update = is_update;
    }

    public ArrayList<HostDevice> getHost_list() {
        return host_list;
    }

    public void setHost_list(ArrayList<HostDevice> host_list) {
        this.host_list = host_list;
    }

    public static class HostDevice{
        String id;           //主機Id
        String host_name;    //主機名
        String mac;
        String thing;
        int device_count;    //設備數量
        ArrayList<Device> device_list;

        public String getHost_name() {
            return host_name;
        }

        public void setHost_name(String host_name) {
            this.host_name = host_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getThing() {
            return thing;
        }

        public void setThing(String thing) {
            this.thing = thing;
        }

        public int getDevice_count() {
            return device_count;
        }

        public void setDevice_count(int device_count) {
            this.device_count = device_count;
        }

        public ArrayList<Device> getDevice_list() {
            return device_list;
        }

        public void setDevice_list(ArrayList<Device> device_list) {
            this.device_list = device_list;
        }
    }
    public static class Device implements Serializable {
        String id;                      //這個設備的ID,對應這個唯一的設備
        String name;                    //用戶自定義這個設備的名稱   ex:客廳的清淨器
        String data_name;               //設備在主機下的序號   ex: Device1
        String device_data;             //設備Data數據，json字符串
        String member_host_id;          //設備所屬的主機的ID
        String device_class_id;         //設備分類ID
        String device_class_name;       //設備分類名稱    ex:媒體設備
        String device_place_id;         //設備擺放地址ID
        String device_place_name;       //設備擺放地址    ex:客廳
        String device_id;               //設備種類的id，泛指一種設備，和設備分類不同，此ID對前端沒用  ex:電視機、音響（都屬於「媒體設備」這個分類）
        String device_name;             //設備名稱  ex:電視機
        String device_pic;              //設備圖片
        String thing;                   //thingId
        String device_type;             //設備Type值，目前只有123456六種
        String is_new;                  //新加入的設備 yes/no

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

        public String getData_name() {
            return data_name;
        }

        public void setData_name(String data_name) {
            this.data_name = data_name;
        }

        public String getDevice_data() {
            return device_data;
        }

        public void setDevice_data(String device_data) {
            this.device_data = device_data;
        }

        public String getMember_host_id() {
            return member_host_id;
        }

        public void setMember_host_id(String member_host_id) {
            this.member_host_id = member_host_id;
        }

        public String getDevice_class_id() {
            return device_class_id;
        }

        public void setDevice_class_id(String device_class_id) {
            this.device_class_id = device_class_id;
        }

        public String getDevice_class_name() {
            return device_class_name;
        }

        public void setDevice_class_name(String device_class_name) {
            this.device_class_name = device_class_name;
        }

        public String getDevice_place_id() {
            return device_place_id;
        }

        public void setDevice_place_id(String device_place_id) {
            this.device_place_id = device_place_id;
        }

        public String getDevice_place_name() {
            return device_place_name;
        }

        public void setDevice_place_name(String device_place_name) {
            this.device_place_name = device_place_name;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getThing() {
            return thing;
        }

        public void setThing(String thing) {
            this.thing = thing;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_pic() {
            return device_pic;
        }

        public void setDevice_pic(String device_pic) {
            this.device_pic = device_pic;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }
    }
//    public static class Info{
//        int Data2;
//        int Data3;
//        int Data4;
//        int Data5;
//        int Data6;
//        int Data7;
//        int Data8;
//        int Data9;
//        int Data10;
//        int Data11;
//        int Data12;
//        int Data13;
//        int Data14;
//        int Data15;
//        int Data16;
//        int Data17;
//        int Data18;
//        int Data19;
//        int Data20;
//
//        public int getData2() {
//            return Data2;
//        }
//
//        public void setData2(int data2) {
//            Data2 = data2;
//        }
//
//        public int getData3() {
//            return Data3;
//        }
//
//        public void setData3(int data3) {
//            Data3 = data3;
//        }
//
//        public int getData4() {
//            return Data4;
//        }
//
//        public void setData4(int data4) {
//            Data4 = data4;
//        }
//
//        public int getData5() {
//            return Data5;
//        }
//
//        public void setData5(int data5) {
//            Data5 = data5;
//        }
//
//        public int getData6() {
//            return Data6;
//        }
//
//        public void setData6(int data6) {
//            Data6 = data6;
//        }
//
//        public int getData7() {
//            return Data7;
//        }
//
//        public void setData7(int data7) {
//            Data7 = data7;
//        }
//
//        public int getData8() {
//            return Data8;
//        }
//
//        public void setData8(int data8) {
//            Data8 = data8;
//        }
//
//        public int getData9() {
//            return Data9;
//        }
//
//        public void setData9(int data9) {
//            Data9 = data9;
//        }
//
//        public int getData10() {
//            return Data10;
//        }
//
//        public void setData10(int data10) {
//            Data10 = data10;
//        }
//
//        public int getData11() {
//            return Data11;
//        }
//
//        public void setData11(int data11) {
//            Data11 = data11;
//        }
//
//        public int getData12() {
//            return Data12;
//        }
//
//        public void setData12(int data12) {
//            Data12 = data12;
//        }
//
//        public int getData13() {
//            return Data13;
//        }
//
//        public void setData13(int data13) {
//            Data13 = data13;
//        }
//
//        public int getData14() {
//            return Data14;
//        }
//
//        public void setData14(int data14) {
//            Data14 = data14;
//        }
//
//        public int getData15() {
//            return Data15;
//        }
//
//        public void setData15(int data15) {
//            Data15 = data15;
//        }
//
//        public int getData16() {
//            return Data16;
//        }
//
//        public void setData16(int data16) {
//            Data16 = data16;
//        }
//
//        public int getData17() {
//            return Data17;
//        }
//
//        public void setData17(int data17) {
//            Data17 = data17;
//        }
//
//        public int getData18() {
//            return Data18;
//        }
//
//        public void setData18(int data18) {
//            Data18 = data18;
//        }
//
//        public int getData19() {
//            return Data19;
//        }
//
//        public void setData19(int data19) {
//            Data19 = data19;
//        }
//
//        public int getData20() {
//            return Data20;
//        }
//
//        public void setData20(int data20) {
//            Data20 = data20;
//        }
//    }

}
