package com.creeps.userdata.userdata;

import com.sun.org.apache.xpath.internal.operations.Mult;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.mock.web.MockMultipartFile;


@RequestMapping("/api/v1/userdata/")
@RestController
public class UserDataController {

    @Autowired
    private UserInfoRepo repo;


    @Autowired
    private RemoteCaller caller;


//    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    @PostMapping("/create/")
    public ResponseEntity storeUserInfo(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName,
                                        @RequestParam(name = "address1") String address1,
                                        @RequestParam(name = "address2") String address2,
                                        @RequestParam(name = "phone") String phone,
                                        @RequestParam(name = "gender") String gender,
//                                        @RequestBody  UserData data,
                                        @RequestParam(name="aadhar") MultipartFile aadhar, @RequestParam("license") MultipartFile license, @RequestParam("pan") MultipartFile pan){


        UserData data = new UserData();
        data .setFirstName(firstName);
        data .setLastName(lastName);
        data .setAddress1(address1);
        data .setAddress2(address2);
        data .setPhone(phone);
        data .setGender(gender);

        File temp = new File("/home/rohan/Documents/creeps/userdata/temp"+System.currentTimeMillis()), temp2=new File("/home/rohan/Documents/creeps/userdata/temp2"+System.currentTimeMillis()), temp3=new File("/home/rohan/Documents/creeps/userdata/temp3"+System.currentTimeMillis());
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
            if((arr=this.parseJson(this.caller.sendToRahulsServer(temp, "http://localhost:8099/api/v1/pdfocr/uploadfile/")))!=null) {
                if(data.getFirstName().equalsIgnoreCase(arr[0]) && data.getLastName().equalsIgnoreCase(arr[1])) {
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

    @RequestMapping(value = "/add-image/{id}/", method = RequestMethod.POST )
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

    @RequestMapping(value="/verify-kairos/", method=RequestMethod.POST)
    public ResponseEntity verifyWithKairos( @RequestParam MultipartFile file){
        File temp;
        try {
            temp = new File("/home/rohan/Documents/creeps/userdata/temp"+System.currentTimeMillis());
            file.transferTo(temp);

        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(this.caller.sendDataToGauravServer(temp, RemoteCaller.KAIROS_SERVER+"/register.php", 0));

    }

    @RequestMapping(value="test", method = RequestMethod.GET)
    public ResponseEntity sendDataToFileServer(){
        String x = caller.generateToken(3, 1,3);
        return ResponseEntity.status(200).body(x);
    }

    @RequestMapping(value="verify/{token}", method=RequestMethod.GET)
    public ResponseEntity<Boolean> verifyToken(@RequestParam ("token") String token){
        return ResponseEntity.status(200).body(this.caller.verifyToken(token));
    }


    @RequestMapping(value="user/verified/{id}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> verifyToken(@RequestParam("id") Long id){
        UserData data= null;
        try {
            data = this.repo.findById(id).orElseThrow(() -> new Exception("Cant obtain resource"));
            return ResponseEntity.status(200).body(data.getDataVerified());
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }




    @RequestMapping(value = "test-r", method = RequestMethod.GET)
    public ResponseEntity sendDataToServer(){
        File f = new File("/home/rohan/Documents/creeps/userdata/catalina.home:temp.pdf");
        try {
            return ResponseEntity.status(200).body(this.caller.sendToRahulsServer(f, RemoteCaller.FILE_SERVER_URL+"/distributed/v1/split/"));
        }catch (Exception ioe){
            ioe.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }


    @PostMapping("/subscribe/")
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



    public String[] parseJson(String json){
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
