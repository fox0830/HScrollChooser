package com.feibi.trade.NetWork.request;

import com.feibi.trade.NetWork.basic.BasicReq;

public class GetAreaListReq extends BasicReq {
    String country_id;

    public GetAreaListReq(String countryId) {
        country_id = countryId;
    }
}
