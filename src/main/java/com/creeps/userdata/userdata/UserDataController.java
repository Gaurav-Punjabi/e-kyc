package com.creeps.userdata.userdata;

import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;

@RequestMapping("/api/v1/userdata/")
@RestController
public class UserDataController {

    @Autowired
    private UserInfoRepo repo;


    @Autowired
    private RemoteCaller caller;


    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    public ResponseEntity storeUserInfo(@RequestBody UserData data, @RequestParam(name="aadhar") MultipartFile aadhar, @RequestParam("license") MultipartFile license, @RequestParam("pan") MultipartFile pan){



        long aadharId = Long.parseLong(caller.sendToFileServer( aadhar));
        long panId = Long.parseLong(caller.sendToFileServer( pan));
        long licenseId = Long.parseLong(caller.sendToFileServer(license));
        
        data.setAadharFileId(aadharId);
        data.setLicenseFileId(licenseId);
        data.setPanFileId(panId);
        ;


        this.repo.save(data);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value="test", method = RequestMethod.GET)
    public ResponseEntity sendDataToFileServer(){
        String x = caller.generateToken(3, 1,3);
        return ResponseEntity.status(200).body(x);
    }



/*    @GetMapping("files/{user-id}/{token}")
    public ResponseEntity getUserFiles(@RequestParam(name="user-id") Long userId, @RequestParam(name="token")String token){

    }*/
}
