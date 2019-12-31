package com.feibi.trade.NetWork.respond;

import java.util.List;

public class getEventRes {

    private List<MemberEventListBean> member_event_list;

    public List<MemberEventListBean> getMember_event_list() {
        return member_event_list;
    }

    public void setMember_event_list(List<MemberEventListBean> member_event_list) {
        this.member_event_list = member_event_list;
    }

    public static class MemberEventListBean {
        /**
         * id : c56d9fae-7794-47e6-bac2-eb3a9db5d8d3
         * member_device_id : f118c753-5a4d-4d87-b50c-c808fc802d56
         * member_host_id : f6e448f2-3abc-436c-a344-ac34302dade7
         * thing : 28e148eb2ced5214edaa79237ea9ee4644388ebd
         * event_name : 定時
         * repeat : 1
         * time_start : 07:00:00
         * time_end : 13:00:00
         * time_status : 1
         * temperature_limit :
         * temperature_limit_value :
         * temperature_value :
         * temperature_status :
         * humidity_limit :
         * humidity_limit_value :
         * humidity_value :
         * humidity_status :
         * air_limit :
         * air_limit_value :
         * air_status : 0
         * status : 1
         * last_implement_time :
         * implement_ok : 0
         * implement_err : 0
         * created_at : 2019-12-19 15:18:21
         * device_name : 除濕器
         * device_pic : updata/device/2019-11/20191129122730.jpg
         * device_class_name : 除濕
         * device_place_name :
         */

        private String id;
        private String member_device_id;
        private String member_host_id;
        private String thing;
        private String event_name;
        private String repeat;
        private String time_start;
        private String time_end;
        private String time_status;
        private String temperature_limit;
        private String temperature_limit_value;
        private String temperature_value;
        private String temperature_status;
        private String humidity_limit;
        private String humidity_limit_value;
        private String humidity_value;
        private String humidity_status;
        private String air_limit;
        private String air_limit_value;
        private String air_status;
        private String status;
        private String last_implement_time;
        private String implement_ok;
        private String implement_err;
        private String created_at;
        private String device_name;
        private String device_pic;
        private String device_class_name;
        private String device_place_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMember_device_id() {
            return member_device_id;
        }

        public void setMember_device_id(String member_device_id) {
            this.member_device_id = member_device_id;
        }

        public String getMember_host_id() {
            return member_host_id;
        }

        public void setMember_host_id(String member_host_id) {
            this.member_host_id = member_host_id;
        }

        public String getThing() {
            return thing;
        }

        public void setThing(String thing) {
            this.thing = thing;
        }

        public String getEvent_name() {
            return event_name;
        }

        public void setEvent_name(String event_name) {
            this.event_name = event_name;
        }

        public String getRepeat() {
            return repeat;
        }

        public void setRepeat(String repeat) {
            this.repeat = repeat;
        }

        public String getTime_start() {
            return time_start;
        }

        public void setTime_start(String time_start) {
            this.time_start = time_start;
        }

        public String getTime_end() {
            return time_end;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }

        public String getTime_status() {
            return time_status;
        }

        public void setTime_status(String time_status) {
            this.time_status = time_status;
        }

        public String getTemperature_limit() {
            return temperature_limit;
        }

        public void setTemperature_limit(String temperature_limit) {
            this.temperature_limit = temperature_limit;
        }

        public String getTemperature_limit_value() {
            return temperature_limit_value;
        }

        public void setTemperature_limit_value(String temperature_limit_value) {
            this.temperature_limit_value = temperature_limit_value;
        }

        public String getTemperature_value() {
            return temperature_value;
        }

        public void setTemperature_value(String temperature_value) {
            this.temperature_value = temperature_value;
        }

        public String getTemperature_status() {
            return temperature_status;
        }

        public void setTemperature_status(String temperature_status) {
            this.temperature_status = temperature_status;
        }

        public String getHumidity_limit() {
            return humidity_limit;
        }

        public void setHumidity_limit(String humidity_limit) {
            this.humidity_limit = humidity_limit;
        }

        public String getHumidity_limit_value() {
            return humidity_limit_value;
        }

        public void setHumidity_limit_value(String humidity_limit_value) {
            this.humidity_limit_value = humidity_limit_value;
        }

        public String getHumidity_value() {
            return humidity_value;
        }

        public void setHumidity_value(String humidity_value) {
            this.humidity_value = humidity_value;
        }

        public String getHumidity_status() {
            return humidity_status;
        }

        public void setHumidity_status(String humidity_status) {
            this.humidity_status = humidity_status;
        }

        public String getAir_limit() {
            return air_limit;
        }

        public void setAir_limit(String air_limit) {
            this.air_limit = air_limit;
        }

        public String getAir_limit_value() {
            return air_limit_value;
        }

        public void setAir_limit_value(String air_limit_value) {
            this.air_limit_value = air_limit_value;
        }

        public String getAir_status() {
            return air_status;
        }

        public void setAir_status(String air_status) {
            this.air_status = air_status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLast_implement_time() {
            return last_implement_time;
        }

        public void setLast_implement_time(String last_implement_time) {
            this.last_implement_time = last_implement_time;
        }

        public String getImplement_ok() {
            return implement_ok;
        }

        public void setImplement_ok(String implement_ok) {
            this.implement_ok = implement_ok;
        }

        public String getImplement_err() {
            return implement_err;
        }

        public void setImplement_err(String implement_err) {
            this.implement_err = implement_err;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
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

        public String getDevice_class_name() {
            return device_class_name;
        }

        public void setDevice_class_name(String device_class_name) {
            this.device_class_name = device_class_name;
        }

        public String getDevice_place_name() {
            return device_place_name;
        }

        public void setDevice_place_name(String device_place_name) {
            this.device_place_name = device_place_name;
        }
    }
}
