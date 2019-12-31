package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

import java.io.File;
import java.util.List;

public class S00001Req extends BasicReq {
    private List<File> files;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
