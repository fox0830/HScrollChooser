package com.feibi.trade.utils;

import com.feibi.trade.NetWork.respond.DeviceBindRes;
import com.feibi.trade.NetWork.respond.GetDeviceClassRes;
import com.feibi.trade.NetWork.respond.LoginRes;

import java.util.ArrayList;

public class Global {
    public static ArrayList<DeviceBindRes.HostDevice> host_list = new ArrayList<>();
    public static ArrayList<DeviceBindRes.Device> all_device_list = new ArrayList<>();
    public static ArrayList<GetDeviceClassRes.DeviceClass> deviceClassList = new ArrayList<>();  //設備分類列表
    public static ArrayList<GetDeviceClassRes.DeviceClass> devicePlaceList = new ArrayList<>();  //地點列表
    public static LoginRes.UserInfo userInfo = new LoginRes.UserInfo();

}
