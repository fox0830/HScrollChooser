package com.feibi.trade.utils;

import com.feibi.trade.Adapter.Picture;
import com.feibi.trade.NetWork.respond.AddTradeRes;
import com.feibi.trade.NetWork.respond.GetTradeRes;

import java.util.ArrayList;

public class Global {
    public static AddTradeRes TripInfo = new AddTradeRes();
    public static GetTradeRes GetTripInfo = new GetTradeRes();
    public static HasUpload hasUpload = new HasUpload(new ArrayList<>());

    public static class HasUpload {
        ArrayList<Picture> hasUploadPic = new ArrayList<>();

        public HasUpload(ArrayList<Picture> hasUploadPic) {
            this.hasUploadPic = hasUploadPic;
        }

        public ArrayList<Picture> getHasUploadPic() {
            if (hasUploadPic == null) {
                hasUploadPic = new ArrayList<>();
            }
            return hasUploadPic;
        }

        public void remove(int index) {
            if (hasUploadPic == null) {
                hasUploadPic = new ArrayList<>();
            }
            hasUploadPic.remove(index);
        }

        public void add(Picture picture) {
            if (hasUploadPic == null) {
                hasUploadPic = new ArrayList<>();
            }
            hasUploadPic.add(picture);
        }

        public void setHasUploadPic(ArrayList<Picture> hasUploadPic) {
            this.hasUploadPic = hasUploadPic;
        }
    }
}
