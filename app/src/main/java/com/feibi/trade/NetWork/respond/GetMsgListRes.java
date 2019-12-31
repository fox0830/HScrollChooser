package com.feibi.trade.NetWork.respond;

import java.util.ArrayList;

public class GetMsgListRes {

    ArrayList<Message> msg_list;

    public ArrayList<Message> getMsg_list() {
        return msg_list;
    }

    public void setMsg_list(ArrayList<Message> msg_list) {
        this.msg_list = msg_list;
    }

    public static class Message{
       String id;
       String push_time;
       String push_title;
       String push_content;
       String content;
       String push_type;
       String pic;
       String is_reading; //yes/no

       public String getId() {
           return id;
       }

       public void setId(String id) {
           this.id = id;
       }

       public String getPush_time() {
           return push_time;
       }

       public void setPush_time(String push_time) {
           this.push_time = push_time;
       }

       public String getPush_title() {
           return push_title;
       }

       public void setPush_title(String push_title) {
           this.push_title = push_title;
       }

       public String getPush_content() {
           return push_content;
       }

       public void setPush_content(String push_content) {
           this.push_content = push_content;
       }

       public String getContent() {
           return content;
       }

       public void setContent(String content) {
           this.content = content;
       }

       public String getPush_type() {
           return push_type;
       }

       public void setPush_type(String push_type) {
           this.push_type = push_type;
       }

       public String getPic() {
           return pic;
       }

       public void setPic(String pic) {
           this.pic = pic;
       }

       public String getIs_reading() {
           return is_reading;
       }

       public void setIs_reading(String is_reading) {
           this.is_reading = is_reading;
       }
   }
}
