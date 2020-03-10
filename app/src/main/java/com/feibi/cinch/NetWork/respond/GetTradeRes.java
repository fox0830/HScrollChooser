package com.feibi.cinch.NetWork.respond;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetTradeRes extends BasicReq {


    /**
     * PlanId : i_1rm35yX
     * PlanName : Adventrip
     * PlanBackUrl : https://www.facebook.com/adventrip.tw/
     * ThemeId : 2019adventrip
     * PanoSets : []
     * Spots : []
     * Url : https://r.adventrip.net/zv-
     * Description : 將照片與360環景結合，
     讓旅行的分享，被體驗！

     Adventrip, make your sharing Real!
     * Theme : {"_id":"5db066b7497c6b2cbba6ef80","details-icon":"https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/details_icon.png","header-logo":"https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/header_logo.png","color-logo":"https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/color_logo.png","white-logo":"https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/white_logo.png","app-icon":"https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/app_icon.jpg","image-vertical":"https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/image_vertical.png","image-vertical_logo":"https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/image_vertical_logo.png","image-horizon":"https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/image_horizon.png","map-point-10px":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-point-10px.svg","map-point-15px":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-point-15px.svg","map-point-20px":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-point-20px.svg","map-dot-10px":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-dot-10px.svg","map-dot-15px":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-dot-15px.svg","map-dot-20px":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-dot-20px.svg","map-line-selected":"#93170B","map-line":"#FF7D67","prelanding-horizontal":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/horizontal-min.jpg","prelanding-vertical":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/vertical-min.jpg","icon-menu":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/about-logo.svg","icon-website":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/icon-website.jpg","frame-up":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/up_re.svg","frame-down":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/down.png","header-icon":"https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/heager-logo.svg","ThemeId":"2019adventrip","style":{"--color-3":"#E7481A","--color-4":"#E7481A","--color-5":"#8B2C10"},"color-theme":{"3":"light","4":"light","5":"light"},"map-cover":[]}
     * ShareImage : https://dev.adventrip.net/trip/i_1rm35yX/share-image
     */

    private String PlanId;
    private String PlanName;
    private String PlanBackUrl;
    private String ThemeId;
    private String Url;
    private String Description;
    private ThemeBean Theme;
    private String ShareImage;
    private List<String> PanoSets;
    private ArrayList<UrlSpot> Spots = new ArrayList<>();

    public String getPlanId() {
        return PlanId;
    }

    public void setPlanId(String PlanId) {
        this.PlanId = PlanId;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String PlanName) {
        this.PlanName = PlanName;
    }

    public String getPlanBackUrl() {
        return PlanBackUrl;
    }

    public void setPlanBackUrl(String PlanBackUrl) {
        this.PlanBackUrl = PlanBackUrl;
    }

    public String getThemeId() {
        return ThemeId;
    }

    public void setThemeId(String ThemeId) {
        this.ThemeId = ThemeId;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public ThemeBean getTheme() {
        return Theme;
    }

    public void setTheme(ThemeBean Theme) {
        this.Theme = Theme;
    }

    public String getShareImage() {
        return ShareImage;
    }

    public void setShareImage(String ShareImage) {
        this.ShareImage = ShareImage;
    }

    public List<String> getPanoSets() {
        return PanoSets;
    }

    public void setPanoSets(List<String> panoSets) {
        PanoSets = panoSets;
    }

    public List<UrlSpot> getSpots() {
        return Spots;
    }

    public ArrayList<Spot> getReqSpots() {
        ArrayList<Spot> spots = new ArrayList<>();
        for(UrlSpot spot:Spots){
            spots.add(spot.toSpot());
        }
        return spots;
    }

    public void setSpots(ArrayList<UrlSpot> spots) {
        Spots = spots;
    }

    public static class ThemeBean {
        /**
         * _id : 5db066b7497c6b2cbba6ef80
         * details-icon : https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/details_icon.png
         * header-logo : https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/header_logo.png
         * color-logo : https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/color_logo.png
         * white-logo : https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/white_logo.png
         * app-icon : https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/app_icon.jpg
         * image-vertical : https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/image_vertical.png
         * image-vertical_logo : https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/image_vertical_logo.png
         * image-horizon : https://d1khdzv3e8jf08.cloudfront.net/Theme/2019adventrip/image_horizon.png
         * map-point-10px : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-point-10px.svg
         * map-point-15px : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-point-15px.svg
         * map-point-20px : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-point-20px.svg
         * map-dot-10px : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-dot-10px.svg
         * map-dot-15px : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-dot-15px.svg
         * map-dot-20px : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/map-dot-20px.svg
         * map-line-selected : #93170B
         * map-line : #FF7D67
         * prelanding-horizontal : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/horizontal-min.jpg
         * prelanding-vertical : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/vertical-min.jpg
         * icon-menu : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/about-logo.svg
         * icon-website : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/icon-website.jpg
         * frame-up : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/up_re.svg
         * frame-down : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/down.png
         * header-icon : https://d1khdzv3e8jf08.cloudfront.net/Theme/adventrip/heager-logo.svg
         * ThemeId : 2019adventrip
         * style : {"--color-3":"#E7481A","--color-4":"#E7481A","--color-5":"#8B2C10"}
         * color-theme : {"3":"light","4":"light","5":"light"}
         * map-cover : []
         */

        private String _id;
        @SerializedName("details-icon")
        private String detailsicon;
        @SerializedName("header-logo")
        private String headerlogo;
        @SerializedName("color-logo")
        private String colorlogo;
        @SerializedName("white-logo")
        private String whitelogo;
        @SerializedName("app-icon")
        private String appicon;
        @SerializedName("image-vertical")
        private String imagevertical;
        @SerializedName("image-vertical_logo")
        private String imagevertical_logo;
        @SerializedName("image-horizon")
        private String imagehorizon;
        @SerializedName("map-point-10px")
        private String mappoint10px;
        @SerializedName("map-point-15px")
        private String mappoint15px;
        @SerializedName("map-point-20px")
        private String mappoint20px;
        @SerializedName("map-dot-10px")
        private String mapdot10px;
        @SerializedName("map-dot-15px")
        private String mapdot15px;
        @SerializedName("map-dot-20px")
        private String mapdot20px;
        @SerializedName("map-line-selected")
        private String maplineselected;
        @SerializedName("map-line")
        private String mapline;
        @SerializedName("prelanding-horizontal")
        private String prelandinghorizontal;
        @SerializedName("prelanding-vertical")
        private String prelandingvertical;
        @SerializedName("icon-menu")
        private String iconmenu;
        @SerializedName("icon-website")
        private String iconwebsite;
        @SerializedName("frame-up")
        private String frameup;
        @SerializedName("frame-down")
        private String framedown;
        @SerializedName("header-icon")
        private String headericon;
        private String ThemeId;
        private StyleBean style;
        @SerializedName("color-theme")
        private ColorthemeBean colortheme;
        @SerializedName("map-cover")
        private List<?> mapcover;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getDetailsicon() {
            return detailsicon;
        }

        public void setDetailsicon(String detailsicon) {
            this.detailsicon = detailsicon;
        }

        public String getHeaderlogo() {
            return headerlogo;
        }

        public void setHeaderlogo(String headerlogo) {
            this.headerlogo = headerlogo;
        }

        public String getColorlogo() {
            return colorlogo;
        }

        public void setColorlogo(String colorlogo) {
            this.colorlogo = colorlogo;
        }

        public String getWhitelogo() {
            return whitelogo;
        }

        public void setWhitelogo(String whitelogo) {
            this.whitelogo = whitelogo;
        }

        public String getAppicon() {
            return appicon;
        }

        public void setAppicon(String appicon) {
            this.appicon = appicon;
        }

        public String getImagevertical() {
            return imagevertical;
        }

        public void setImagevertical(String imagevertical) {
            this.imagevertical = imagevertical;
        }

        public String getImagevertical_logo() {
            return imagevertical_logo;
        }

        public void setImagevertical_logo(String imagevertical_logo) {
            this.imagevertical_logo = imagevertical_logo;
        }

        public String getImagehorizon() {
            return imagehorizon;
        }

        public void setImagehorizon(String imagehorizon) {
            this.imagehorizon = imagehorizon;
        }

        public String getMappoint10px() {
            return mappoint10px;
        }

        public void setMappoint10px(String mappoint10px) {
            this.mappoint10px = mappoint10px;
        }

        public String getMappoint15px() {
            return mappoint15px;
        }

        public void setMappoint15px(String mappoint15px) {
            this.mappoint15px = mappoint15px;
        }

        public String getMappoint20px() {
            return mappoint20px;
        }

        public void setMappoint20px(String mappoint20px) {
            this.mappoint20px = mappoint20px;
        }

        public String getMapdot10px() {
            return mapdot10px;
        }

        public void setMapdot10px(String mapdot10px) {
            this.mapdot10px = mapdot10px;
        }

        public String getMapdot15px() {
            return mapdot15px;
        }

        public void setMapdot15px(String mapdot15px) {
            this.mapdot15px = mapdot15px;
        }

        public String getMapdot20px() {
            return mapdot20px;
        }

        public void setMapdot20px(String mapdot20px) {
            this.mapdot20px = mapdot20px;
        }

        public String getMaplineselected() {
            return maplineselected;
        }

        public void setMaplineselected(String maplineselected) {
            this.maplineselected = maplineselected;
        }

        public String getMapline() {
            return mapline;
        }

        public void setMapline(String mapline) {
            this.mapline = mapline;
        }

        public String getPrelandinghorizontal() {
            return prelandinghorizontal;
        }

        public void setPrelandinghorizontal(String prelandinghorizontal) {
            this.prelandinghorizontal = prelandinghorizontal;
        }

        public String getPrelandingvertical() {
            return prelandingvertical;
        }

        public void setPrelandingvertical(String prelandingvertical) {
            this.prelandingvertical = prelandingvertical;
        }

        public String getIconmenu() {
            return iconmenu;
        }

        public void setIconmenu(String iconmenu) {
            this.iconmenu = iconmenu;
        }

        public String getIconwebsite() {
            return iconwebsite;
        }

        public void setIconwebsite(String iconwebsite) {
            this.iconwebsite = iconwebsite;
        }

        public String getFrameup() {
            return frameup;
        }

        public void setFrameup(String frameup) {
            this.frameup = frameup;
        }

        public String getFramedown() {
            return framedown;
        }

        public void setFramedown(String framedown) {
            this.framedown = framedown;
        }

        public String getHeadericon() {
            return headericon;
        }

        public void setHeadericon(String headericon) {
            this.headericon = headericon;
        }

        public String getThemeId() {
            return ThemeId;
        }

        public void setThemeId(String ThemeId) {
            this.ThemeId = ThemeId;
        }

        public StyleBean getStyle() {
            return style;
        }

        public void setStyle(StyleBean style) {
            this.style = style;
        }

        public ColorthemeBean getColortheme() {
            return colortheme;
        }

        public void setColortheme(ColorthemeBean colortheme) {
            this.colortheme = colortheme;
        }

        public List<?> getMapcover() {
            return mapcover;
        }

        public void setMapcover(List<?> mapcover) {
            this.mapcover = mapcover;
        }

        public static class StyleBean {
            /**
             * --color-3 : #E7481A
             * --color-4 : #E7481A
             * --color-5 : #8B2C10
             */

            @SerializedName("--color-3")
            private String color3;
            @SerializedName("--color-4")
            private String color4;
            @SerializedName("--color-5")
            private String color5;

            public String getColor3() {
                return color3;
            }

            public void setColor3(String color3) {
                this.color3 = color3;
            }

            public String getColor4() {
                return color4;
            }

            public void setColor4(String color4) {
                this.color4 = color4;
            }

            public String getColor5() {
                return color5;
            }

            public void setColor5(String color5) {
                this.color5 = color5;
            }
        }

        public static class ColorthemeBean {
            /**
             * 3 : light
             * 4 : light
             * 5 : light
             */

            @SerializedName("3")
            private String _$3;
            @SerializedName("4")
            private String _$4;
            @SerializedName("5")
            private String _$5;

            public String get_$3() {
                return _$3;
            }

            public void set_$3(String _$3) {
                this._$3 = _$3;
            }

            public String get_$4() {
                return _$4;
            }

            public void set_$4(String _$4) {
                this._$4 = _$4;
            }

            public String get_$5() {
                return _$5;
            }

            public void set_$5(String _$5) {
                this._$5 = _$5;
            }
        }
    }
}
