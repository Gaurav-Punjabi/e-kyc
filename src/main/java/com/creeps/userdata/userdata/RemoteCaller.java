package com.creeps.userdata.userdata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class RemoteCaller {

    final static String crlf = "\r\n";
    final static String twoHyphens = "--";
    final static String boundary =  "*****";

    private final static String TOKEN_URL = "http://localhost:8080/api/v1/tokens/";
    private final static String FILE_SERVER_URL = "http://192.168.1.1:8081/";


    public String sendToFileServer( MultipartFile file){
        try {
            HttpURLConnection httpUrlConnection = null;
            String attachmentName = "pdf";
            String attachmentFileName = file.getOriginalFilename();
            URL url = new URL(FILE_SERVER_URL+"distributed/v1/split/");
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);

            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
            httpUrlConnection.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + boundary);



            DataOutputStream request = new DataOutputStream(
                    httpUrlConnection.getOutputStream());

            request.writeBytes(twoHyphens + boundary + crlf);
            request.writeBytes("Content-Disposition: form-data; name=\"" +
                    attachmentName + "\";fileName=\"" +
                    attachmentFileName + "\"" + crlf);
            request.writeBytes(crlf);

            //write file contents here
            request.write(file.getBytes());

            request.writeBytes(crlf);
            request.writeBytes(twoHyphens + boundary +
                    twoHyphens + crlf);

            request.flush();
            request.close();

            InputStream responseStream = new
                    BufferedInputStream(httpUrlConnection.getInputStream());

            BufferedReader responseStreamReader =
                    new BufferedReader(new InputStreamReader(responseStream));

            String line = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            responseStreamReader.close();

            String response = stringBuilder.toString();

            responseStream.close();

            httpUrlConnection.disconnect();




            return response;



        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        return "0";

    }


    public String generateToken(int perms, long userId, long consumerId){
        try {
            HttpURLConnection httpUrlConnection = null;

            URL url = new URL(TOKEN_URL+"/create/");
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);

            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");

            httpUrlConnection.setRequestProperty("Content-Type", "application/JSON");


            HashMap<String, String> map = new HashMap<>();
            map.put("perms", perms+"");
            map.put("user", userId+"");
            map.put("consumer", consumerId+"");

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            String json = gson.toJson(map);


            DataOutputStream request = new DataOutputStream(
                    httpUrlConnection.getOutputStream());

            request.write(json.getBytes("UTF-8"));


            request.flush();
            request.close();

            InputStream responseStream = new
                    BufferedInputStream(httpUrlConnection.getInputStream());

            BufferedReader responseStreamReader =
                    new BufferedReader(new InputStreamReader(responseStream));

            String line = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            responseStreamReader.close();

            String response = stringBuilder.toString();

            responseStream.close();

            httpUrlConnection.disconnect();

            return response;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


/*    public File getFileFromServer(long userid, long fileid){

    }


    public File getFileFromServiceWithConsumerId(long consumerId, long fileId){

    }*/

}
