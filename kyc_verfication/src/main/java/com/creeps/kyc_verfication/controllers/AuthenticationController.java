package com.creeps.kyc_verfication.controllers;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {

    @GetMapping("/recognize")
    public String recognizeUser() {

        try {
            byte[] bytes = extractBytes("/Users/gauravpunjabi/Desktop/test.png");
            String base64 = toBase64(bytes);
            System.out.println("base64 = " + base64);
            System.out.println(remoteCall(base64));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return "Hello WOrld";
    }

    public byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }

    private String toBase64(byte[] bytes) {
        String encodedFile = Base64.getEncoder().encodeToString(bytes);
        return encodedFile;
    }

    private String remoteCall(String image) {
        try {
            URL url = new URL("https://api.kairos.com/detect");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            connection.setRequestProperty("app_id", "463f4986");
            connection.setRequestProperty("app_key", "f1c20907256dddc23fe11fd40697cb7b");

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write("{  \"image\": \"" + image + "\",  \"selector\": \"ROLL\"}");
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            return jsonString.toString();

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
