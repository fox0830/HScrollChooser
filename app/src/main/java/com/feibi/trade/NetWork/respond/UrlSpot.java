package com.feibi.trade.NetWork.respond;

public class UrlSpot extends Spot {

    private String ImageUrl = "";

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
    public Spot toSpot() {
         Spot s = new Spot();
         s.setId(getId());
         s.setSize(getSize());
         s.setPano(getPano());
         s.setPosition(getPosition());
         s.setMedia(getMedia());
         s.setAddress(getAddress());
         s.setBindingRawResourceId(getBindingRawResourceId());
         s.setCoverMedia(getCoverMedia());
         s.setDescription(getDescription());
         s.setMode(getMode());
         s.setPanoSetId(getPanoSetId());
         s.setPath(getPath());
         s.setTime(getTime());
         s.setTitle(getTitle());
         s.setVoice(getVoice());
         return s;

    }
}
