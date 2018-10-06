package com.creeps.userdata.userdata;

import com.creeps.userdata.otps.OtpService;
import com.sun.org.apache.xpath.internal.operations.Mult;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import jdk.nashorn.internal.parser.Token;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.servlet.view.RedirectView;


@RequestMapping("/api/v1/userdata/")
@RestController
public class UserDataController {

    @Autowired
    private UserInfoRepo repo;


    @Autowired
    private RemoteCaller caller;


    @Autowired
    private OtpService otpService;

    @PostMapping("/create/")
    public ResponseEntity storeUserInfo(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName,
                                        @RequestParam(name = "address1") String address1,
                                        @RequestParam(name = "address2") String address2,
                                        @RequestParam(name = "phone") String phone,
                                        @RequestParam(name = "gender") String gender,
                                         // @RequestBody  UserData data,
                                        @RequestParam(name="aadhar") MultipartFile aadhar, @RequestParam("license") MultipartFile license, @RequestParam("pan") MultipartFile pan){


        UserData data = new UserData();
        data.setFirstName(firstName);
        data.setLastName(lastName);
        data.setAddress1(address1);
        data.setAddress2(address2);
        data.setPhone(phone);
        data.setGender(gender);

        File temp = new File("/home/rohan/Documents/creeps/userdata/temp"+System.currentTimeMillis()+".pdf"), temp2=new File("/home/rohan/Documents/creeps/userdata/temp2"+System.currentTimeMillis()+".pdf"), temp3=new File("/home/rohan/Documents/creeps/userdata/temp3"+System.currentTimeMillis()+".pdf");
        try {
            aadhar.transferTo(temp);
            pan.transferTo(temp2);
            license.transferTo(temp3);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        long aadharId = Long.parseLong(caller.sendToRahulsServer( temp, RemoteCaller.FILE_SERVER_URL+"/distributed/v1/split/"));
        long panId = Long.parseLong(caller.sendToRahulsServer( temp2, RemoteCaller.FILE_SERVER_URL+"/distributed/v1/split/"));
        long licenseId = Long.parseLong(caller.sendToRahulsServer(temp3, RemoteCaller.FILE_SERVER_URL+"/distributed/v1/split/"));

        data.setAadharFileId(aadharId);
        data.setLicenseFileId(licenseId);
        data.setPanFileId(panId);



        //validating first and last name
        try {
            String arr[];
            String resp = this.caller.sendToRahulsServer(temp, "http://localhost:8099/api/v1/pdfocr/uploadfile/");
            System.out.println(resp);
            if((arr=this.parseJson(resp))!=null) {
                if(data.getFirstName().equalsIgnoreCase(arr[0]) && data.getLastName().equalsIgnoreCase(arr[1])) {
                    data.setAadharURL();
                    data.setLicenseURL();
                    data.setPanURL();
                    this.repo.save(data);
                    return ResponseEntity.status(HttpStatus.OK).body(data.getId());
                }

            }


        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(200).body(0);
        }


        return ResponseEntity.status(200).body(0);
    }

    @GetMapping("send-otp/{id}")
    public ResponseEntity generateOtpWithUserId(@PathVariable("id") Long userId){
        try{
            UserData user = this.repo.findById(userId).orElseThrow(() -> new Exception("THIS RESOURCE cant be accessed with "+userId));
            return ResponseEntity.status(200).body(this.otpService.sendOtp(user.getPhone()));

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            return ResponseEntity.status(404).build();
        }
    }


    @GetMapping("verify-otp/{otp}/{session-id}/{user-id}/")
    public ResponseEntity verifyOtp(@Nullable @PathVariable("otp") String otp, @Nullable @PathVariable("session-id") String sessionId,
                                    @PathVariable("user-id") Long userId){
        try {
            //if (this.otpService.verifyOtp(otp, sessionId)) {
                UserData data = this.repo.findById(userId).orElseThrow(() -> new Exception("Cant"));
            data.setAadharURL();
            data.setLicenseURL();
            data.setPanURL();
            this.repo.save(data);
                return ResponseEntity.status(200).body(data);
            //}
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).build();
        }

        //return ResponseEntity.status(403).build();
    }





    /*@RequestMapping(name = "/add-image/{id}/", method = RequestMethod.POST )
    public ResponseEntity registerWithKairos(@RequestParam("id") Long id, @RequestParam MultipartFile file){
        UserData data= null;
        File temp;
        try {
            data = this.repo.findById(id).orElseThrow(() -> new Exception("Cant obtain resource"));
            temp = new File("/home/rohan/Documents/creeps/userdata/temp"+System.currentTimeMillis());
            file.transferTo(temp);

        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(this.caller.sendDataToGauravServer(temp, RemoteCaller.KAIROS_SERVER+"/enroll-user.php", data.getId()));

    }
*/
    /*@RequestMapping(name="/verify-kairos/", method=RequestMethod.POST)
    public ResponseEntity verifyWithKairos( @RequestParam MultipartFile file){
        File temp;
        try {
            temp = new File("/home/rohan/Documents/creeps/userdata/temp"+System.currentTimeMillis());
            file.transferTo(temp);

        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        String res = this.caller.sendDataToGauravServer(temp, RemoteCaller.KAIROS_SERVER+"/register.php", 0);
        UserData ref;
        try{
            System.out.println("RES "+res);
            ref = this.repo.findById(Long.parseLong(res)).orElseThrow(()-> new Exception("CANT access this resouce with id "+res));
            return ResponseEntity.status(200).body(this.otpService.sendOtp(ref.getPhone()));
        }catch (Exception e){
            return ResponseEntity.status(400).build();

        }


    }
*/


    /*@RequestMapping(name="verify/{token}", method=RequestMethod.GET)
    public ResponseEntity<Boolean> verifyToken(@PathVariable ("token") String token){
        return ResponseEntity.status(200).body(this.caller.verifyToken(token));
    }
*/
    @GetMapping(name="user/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("id") Long id){
        UserData data= null;
        try {
            data = this.repo.findById(id).orElseThrow(() -> new Exception("Cant obtain resource"));
            return ResponseEntity.status(200).body(data);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }




    /*@RequestMapping(name = "test-r", method = RequestMethod.GET)
    public ResponseEntity sendDataToServer(){
        File f = new File("/home/rohan/Documents/creeps/userdata/catalina.home:temp.pdf");
        try {
            return ResponseEntity.status(200).body(this.caller.sendToRahulsServer(f, RemoteCaller.FILE_SERVER_URL+"/distributed/v1/split/"));
        }catch (Exception ioe){
            ioe.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }*/


    /*@PostMapping("/subscribe/")
    public ResponseEntity consumerSubscription(@RequestParam("consumer-id") Long consumerId, @RequestParam("permissions") int perms, @RequestParam("user-id") Long userId){
        try {
            UserData consumer = this.repo.findById(consumerId).orElseThrow(() -> new Exception("CANT fetch resource with id " + consumerId));
            UserData user = this.repo.findById(userId).orElseThrow(() -> new Exception("CANT Fetch resource with id " + userId));
            String token = this.caller.generateToken(perms, userId, consumerId);
            System.out.println("TOKEN "+token );
            return ResponseEntity.status(200).body(token);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }
*/
    public final static String UI_LOCATION = "http:://192.168.43.108/hkt/Front-End/pages/register-2.php?id=";



    private String[] parseJson(String json){
        int index = json.indexOf("ParsedText\":");
        System.out.println("index = "+index);
        index +="Parsed Text:\"".length();
        int endOfFirstName = json.indexOf(" ",index);
        String firstName = json.substring(index,endOfFirstName);
        index = json.indexOf(" ",endOfFirstName+1)+1;
        int endOfLastName = json.indexOf(" ",index);
        String lastName = json.substring(index,endOfLastName);
        String[] nameArray = {firstName,lastName};
        return nameArray;
    }

}
