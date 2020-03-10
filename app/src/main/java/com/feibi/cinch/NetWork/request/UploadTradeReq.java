package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.NetWork.respond.Spot;

import java.util.ArrayList;
import java.util.List;

public class UploadTradeReq extends BasicReq {

    /**
     * Description : 將照片與360環景結合，
     讓旅行的分享，被體驗！

     Adventrip, make your sharing Real!
     * PlanBackUrl : https://www.facebook.com/adventrip.tw/
     * PlanName : 10photo
     * Spots : [{"Address":"Avenue du Casino 10, 1820 Montreux, 瑞士","BindingRawResourceId":"20962b05-ac50-4733-9b73-424be30658d1","Media":{"id":"14e89ef4-a5fe-4ebf-86ce-c07193b6e690","keyid":"bfeb2511-cb64-f984-5029-70964fc0d08f","type":"image/png"},"Mode":"EDITLINE","Pano":"","Position":{"PicFrameLat":"46.430306434016934","PicFrameLng":"6.9152778969751125","PictureLat":"46.43009444444444","PictureLng":"6.915297222222223","ViewHead":"0","ViewLat":"46.43017588070406","ViewLng":"6.9152778969751125","ViewPitch":"0","ViewZoom":"1"},"Size":{"Height":"600","Width":"800"},"Time":"2016/04/09 12:16","Title":"瑞士沃州","id":"fccc10d0-4db2-437d-bc06-9973b895b603"}]
     */

    private String Description;
    private String PlanBackUrl;
    private String PlanName;
    private List<Spot> Spots = new ArrayList<>();

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPlanBackUrl() {
        return PlanBackUrl;
    }

    public void setPlanBackUrl(String PlanBackUrl) {
        this.PlanBackUrl = PlanBackUrl;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String PlanName) {
        this.PlanName = PlanName;
    }

    public List<Spot> getSpots() {
        return Spots;
    }

    public void setSpots(List<Spot> spots) {
        Spots = spots;
    }

    public void addSpot(Spot spot) {
        Spots.add(spot);
    }
    public void addSpots(List<Spot> spots) {
        Spots.addAll(spots);
    }
}
