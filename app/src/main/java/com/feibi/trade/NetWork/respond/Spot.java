package com.feibi.trade.NetWork.respond;

import java.util.List;

public class Spot {

    /**
     * Address : 地址
     * Description : 描述
     * CoverMedia : {"id":"478535b5-fdd7-4f98-b644-a4a8ca4b7a7c","keyid":"478535b5-fdd7-4f98-b644-a4a8ca4b7a7c","preview":"Blob | string;","real":"string;","type":"video/mp4"}
     * Media : {"id":"478535b5-fdd7-4f98-b644-a4a8ca4b7a7c","keyid":"478535b5-fdd7-4f98-b644-a4a8ca4b7a7c","preview":"Blob | string;","real":"string;","type":"video/mp4"}
     * Voice : {"id":"478535b5-fdd7-4f98-b644-a4a8ca4b7a7c","keyid":"478535b5-fdd7-4f98-b644-a4a8ca4b7a7c","preview":"Blob | string;","real":"string;","type":"video/mp4"}
     * Mode : EDITLINE
     * PanoSetId : aGBsYYVVa
     * Pano : adv-968f43d1-e2cb-4144-9e56-365dd0b0bef2
     * Path : {"waypoints":[{"lat":"number","lng":"number"}],"polyline":"string"}
     * Position : {"PictureLat":"number","PictureLng":"number","ViewLat":"number","ViewLng":"number","PicFrameLat":"number","PicFrameLng":"number","ViewZoom":"number: 街景圖的Zoom","ViewHead":"number","ViewPitch":"number"}
     * Size : {"Width":"number","Height":"number"}
     * Time : string;
     * Title : string;
     * id : string;
     * BindingRawResourceId : string
     */

    private String Address = "";
    private String Description;
    private String Mode  = "EDITLINE";
    private String PanoSetId;
    private String Pano = "hQJr6LHBfzwGK4iC5GU7oA";
    private String Time;
    private String Title = "Adventrip";
    private String id = "UUID";
    private String BindingRawResourceId  ="string";

    private PathBean Path;
    private PositionBean Position;
    private SizeBean Size;
    private CoverMediaBean CoverMedia;
    private MediaBean Media;
    private VoiceBean Voice;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public CoverMediaBean getCoverMedia() {
        return CoverMedia;
    }

    public void setCoverMedia(CoverMediaBean CoverMedia) {
        this.CoverMedia = CoverMedia;
    }

    public MediaBean getMedia() {
        return Media;
    }

    public void setMedia(MediaBean Media) {
        this.Media = Media;
    }

    public VoiceBean getVoice() {
        return Voice;
    }

    public void setVoice(VoiceBean Voice) {
        this.Voice = Voice;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String Mode) {
        this.Mode = Mode;
    }

    public String getPanoSetId() {
        return PanoSetId;
    }

    public void setPanoSetId(String PanoSetId) {
        this.PanoSetId = PanoSetId;
    }

    public String getPano() {
        return Pano;
    }

    public void setPano(String Pano) {
        this.Pano = Pano;
    }

    public PathBean getPath() {
        return Path;
    }

    public void setPath(PathBean Path) {
        this.Path = Path;
    }

    public PositionBean getPosition() {
        return Position;
    }

    public void setPosition(PositionBean Position) {
        this.Position = Position;
    }

    public SizeBean getSize() {
        return Size;
    }

    public void setSize(SizeBean Size) {
        this.Size = Size;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBindingRawResourceId() {
        return BindingRawResourceId;
    }

    public void setBindingRawResourceId(String BindingRawResourceId) {
        this.BindingRawResourceId = BindingRawResourceId;
    }

    public static class CoverMediaBean {
        /**
         * id : 478535b5-fdd7-4f98-b644-a4a8ca4b7a7c
         * keyid : 478535b5-fdd7-4f98-b644-a4a8ca4b7a7c
         * preview : Blob | string;
         * real : string;
         * type : video/mp4
         */

        private String id;
        private String keyid;
        private String preview;
        private String real;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeyid() {
            return keyid;
        }

        public void setKeyid(String keyid) {
            this.keyid = keyid;
        }

        public String getPreview() {
            return preview;
        }

        public void setPreview(String preview) {
            this.preview = preview;
        }

        public String getReal() {
            return real;
        }

        public void setReal(String real) {
            this.real = real;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class MediaBean {
        /**
         * id : 478535b5-fdd7-4f98-b644-a4a8ca4b7a7c
         * keyid : 478535b5-fdd7-4f98-b644-a4a8ca4b7a7c
         * preview : Blob | string;
         * real : string;
         * type : video/mp4
         */

        private String id = "UUID";
        private String keyid;
        private String preview;
        private String real;
        private String type;

        public MediaBean(String id, String keyid, String type) {
            this.id = id;
            this.keyid = keyid;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeyid() {
            return keyid;
        }

        public void setKeyid(String keyid) {
            this.keyid = keyid;
        }

        public String getPreview() {
            return preview;
        }

        public void setPreview(String preview) {
            this.preview = preview;
        }

        public String getReal() {
            return real;
        }

        public void setReal(String real) {
            this.real = real;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class VoiceBean {
        /**
         * id : 478535b5-fdd7-4f98-b644-a4a8ca4b7a7c
         * keyid : 478535b5-fdd7-4f98-b644-a4a8ca4b7a7c
         * preview : Blob | string;
         * real : string;
         * type : video/mp4
         */

        private String id;
        private String keyid;
        private String preview;
        private String real;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeyid() {
            return keyid;
        }

        public void setKeyid(String keyid) {
            this.keyid = keyid;
        }

        public String getPreview() {
            return preview;
        }

        public void setPreview(String preview) {
            this.preview = preview;
        }

        public String getReal() {
            return real;
        }

        public void setReal(String real) {
            this.real = real;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class PathBean {
        /**
         * waypoints : [{"lat":"number","lng":"number"}]
         * polyline : string
         */

        private String polyline;
        private List<WaypointsBean> waypoints;

        public String getPolyline() {
            return polyline;
        }

        public void setPolyline(String polyline) {
            this.polyline = polyline;
        }

        public List<WaypointsBean> getWaypoints() {
            return waypoints;
        }

        public void setWaypoints(List<WaypointsBean> waypoints) {
            this.waypoints = waypoints;
        }

        public static class WaypointsBean {
            /**
             * lat : number
             * lng : number
             */

            private String lat;
            private String lng;

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }
        }
    }

    public static class PositionBean {
        /**
         * PictureLat : number
         * PictureLng : number
         * ViewLat : number
         * ViewLng : number
         * PicFrameLat : number
         * PicFrameLng : number
         * ViewZoom : number: 街景圖的Zoom
         * ViewHead : number
         * ViewPitch : number
         */

        private String PictureLat;
        private String PictureLng;
        private String ViewLat;
        private String ViewLng;
        private String PicFrameLat;
        private String PicFrameLng;
        private String ViewZoom;
        private String ViewHead;
        private String ViewPitch;

        public PositionBean(String pictureLat, String pictureLng, String viewLat, String viewLng, String picFrameLat, String picFrameLng, String viewZoom, String viewHead, String viewPitch) {
            PictureLat = pictureLat;
            PictureLng = pictureLng;
            ViewLat = viewLat;
            ViewLng = viewLng;
            PicFrameLat = picFrameLat;
            PicFrameLng = picFrameLng;
            ViewZoom = viewZoom;
            ViewHead = viewHead;
            ViewPitch = viewPitch;
        }
        public PositionBean(String pictureLat, String pictureLng) {
            PictureLat = pictureLat;
            PictureLng = pictureLng;
            ViewLat = pictureLat;
            ViewLng = pictureLng;
            PicFrameLat = pictureLat;
            PicFrameLng = pictureLng;
            ViewZoom = "1";
            ViewHead = "0";
            ViewPitch = "0";
        }

        public String getPictureLat() {
            return PictureLat;
        }

        public void setPictureLat(String PictureLat) {
            this.PictureLat = PictureLat;
        }

        public String getPictureLng() {
            return PictureLng;
        }

        public void setPictureLng(String PictureLng) {
            this.PictureLng = PictureLng;
        }

        public String getViewLat() {
            return ViewLat;
        }

        public void setViewLat(String ViewLat) {
            this.ViewLat = ViewLat;
        }

        public String getViewLng() {
            return ViewLng;
        }

        public void setViewLng(String ViewLng) {
            this.ViewLng = ViewLng;
        }

        public String getPicFrameLat() {
            return PicFrameLat;
        }

        public void setPicFrameLat(String PicFrameLat) {
            this.PicFrameLat = PicFrameLat;
        }

        public String getPicFrameLng() {
            return PicFrameLng;
        }

        public void setPicFrameLng(String PicFrameLng) {
            this.PicFrameLng = PicFrameLng;
        }

        public String getViewZoom() {
            return ViewZoom;
        }

        public void setViewZoom(String ViewZoom) {
            this.ViewZoom = ViewZoom;
        }

        public String getViewHead() {
            return ViewHead;
        }

        public void setViewHead(String ViewHead) {
            this.ViewHead = ViewHead;
        }

        public String getViewPitch() {
            return ViewPitch;
        }

        public void setViewPitch(String ViewPitch) {
            this.ViewPitch = ViewPitch;
        }
    }

    public static class SizeBean {
        /**
         * Width : number
         * Height : number
         */

        private String Width = "4";
        private String Height = "3";

        public String getWidth() {
            return Width;
        }

        public void setWidth(String Width) {
            this.Width = Width;
        }

        public String getHeight() {
            return Height;
        }

        public void setHeight(String Height) {
            this.Height = Height;
        }
    }
}
