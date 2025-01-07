package com.optimus.dto;

import java.time.ZonedDateTime;

public class DealDto {
    private String dealtitle;
    private String dealdescription;
    private String couponCode;
    private ZonedDateTime expirydate;

    public String getDealtitle() {
        return dealtitle;
    }

    public void setDealtitle(String dealtitle) {
        this.dealtitle = dealtitle;
    }

    public String getDealdescription() {
        return dealdescription;
    }

    public void setDealdescription(String dealdescription) {
        this.dealdescription = dealdescription;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public ZonedDateTime getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(ZonedDateTime expirydate) {
        this.expirydate = expirydate;
    }
}
