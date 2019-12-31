package com.feibi.trade.NetWork.respond;

public class    LoginRes {
    String token;
    UserInfo user_info;

    public UserInfo getUserInfo() {
        return user_info;
    }

    public void setUserInfo(UserInfo user_info) {
        this.user_info = user_info;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserInfo{
        String id;
        String nick_name;
        String phone;
        String email;
        String country;
        String district;
        String country_name;
        String district_name;
        String address;
        String user_head;
        String is_binding;
        String binding_third_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getDistrict_name() {
            return district_name;
        }

        public void setDistrict_name(String district_name) {
            this.district_name = district_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUser_head() {
            return user_head;
        }

        public void setUser_head(String user_head) {
            this.user_head = user_head;
        }

        public String getBinding_third_name() {
            return binding_third_name;
        }

        public void setBinding_third_name(String binding_third_name) {
            this.binding_third_name = binding_third_name;
        }
    }
}
