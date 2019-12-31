package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

import java.io.File;

public class FileUploadReq extends BasicReq {
    File file;
    String type;

    public FileUploadReq(File file, String type) {
        this.file = file;
        this.type = type;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
