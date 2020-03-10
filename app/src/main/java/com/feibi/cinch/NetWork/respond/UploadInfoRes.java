package com.feibi.cinch.NetWork.respond;

public class UploadInfoRes {

    /**
     * success : true
     * revision : 19
     */

    private boolean success;
    private int revision;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }
}
