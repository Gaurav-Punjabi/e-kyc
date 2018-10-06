package com.creeps.userdata.otps;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class OtpService {
    public static final String API_KEY = "a110acaa-c8e5-11e8-a895-0200cd936042";
    public static final String BASE_URL = "https://2factor.in/API/V1/"+API_KEY+"/SMS/+91";
    public static final String OTP_MATCHED = "OTP Matched";
    public static final String VERIFY_URL = "https://2factor.in/API/V1/"+API_KEY+"/SMS/VERIFY/";

    @Autowired
    PerformGet performGet;
    public String sendOtp(String phoneNo) throws Exception{
        final String REQUEST_URL = BASE_URL+phoneNo+"/AUTOGEN";
        String response = performGet.get(REQUEST_URL);
        return response.split(":")[2].replace("\"", "").replace("}", "");
    }
    public boolean verifyOtp(String otp,String sessionId) throws Exception{
        final String REQUEST_URL = VERIFY_URL + sessionId+"/"+otp;
        String response = performGet.get(REQUEST_URL);
        ObjectMapper objectMapper= new ObjectMapper();
        OtpModel otpModel = objectMapper.readValue(response,OtpModel.class);
        return otpModel.getSessionId().equals(OTP_MATCHED);
    }
}
