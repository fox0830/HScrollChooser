package com.feibi.trade.NetWork.respond;

public class AddTradeRes {

    /**
     * status : ok
     * id : L4I67bNPP
     * token : 2ImJ31lktzSQXDPJtOJR
     * icon : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/icon-website.jpg
     * url : https://r.adventrip.net/Jps
     */

    private String status;
    private String id;
    private String token;
    private String icon;
    private String url;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
