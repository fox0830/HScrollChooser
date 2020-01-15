package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

import java.io.File;

public class FileUploadReq{
    File file;

    public FileUploadReq(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
