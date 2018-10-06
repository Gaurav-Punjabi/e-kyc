package com.creeps.userdata.otps;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OtpModel {
    @JsonAlias(value = "OTP")
    private String otp;
    @JsonAlias(value = "Details")
    private String sessionId;

    @JsonIgnore
    String Status;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
