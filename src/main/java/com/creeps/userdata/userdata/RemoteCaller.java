package com.creeps.userdata.userdata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class RemoteCaller {

    final static String crlf = "\r\n";
    final static String twoHyphens = "--";
    final static String boundary =  "*****";

    private final static String TOKEN_URL = "http://localhost:8080/api/v1/tokens/";
    public final static String FILE_SERVER_URL = "http://192.168.43.9:8081/";
    public final static String KAIROS_SERVER = "http://192.168.43.108/hkt/api/v1/";

    /*

    serverId = 0 distribute file splitter

    1 = enroll kairos

    2 = recognize user


     */
    public String sendToFileServer( MultipartFile file, int serverId, long userId){
        try {
            HttpURLConnection httpUrlConnection = null;
            String attachmentName = "pdf";
            String attachmentFileName = file.getOriginalFilename();
            String u = "", later = "Content-Disposition: form-data; name=\"" +
                    attachmentName + "\";fileName=\"" +
                    attachmentFileName + "\"" + crlf;
            if(serverId == 0){
                u=FILE_SERVER_URL+"distributed/v1/split/";
                later = "Content-Disposition: form-data; id=\"" + userId+"\" ;name=\"" +
                        attachmentName + "\";file=";
            }else if(serverId == 2){
                u = KAIROS_SERVER+"enroll-user.php";
                later = "Content-Disposition: form-data; name=\"" +
                        attachmentName + "\";fileName=\"" +
                        attachmentFileName + "\"" + crlf;
            }else if(serverId == 3){
                u = KAIROS_SERVER+"recognize-user.php";
            }
            URL url = new URL(u);
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
            request.writeBytes(later);
            request.writeBytes(crlf);

            //write file contents here
            File temp = new File("catalina.home"+File.pathSeparator+"temp.pdf");


            request.write(file.getBytes());

            System.out.println("WROTE FILE");
            OutputStream os = new FileOutputStream(temp);
            os.write(file.getBytes());
            os.flush();
            os.close();

            System.out.println("Path "+temp.getAbsolutePath());


            request.writeBytes(crlf);
            request.writeBytes(twoHyphens + boundary +
                    twoHyphens + crlf);

            request.flush();
            //request.close();

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


    public boolean verifyToken(String token ){
        try {
            HttpURLConnection httpUrlConnection = null;

            URL url = new URL(TOKEN_URL+"/validate/"+token+"/");
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);

            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");

            httpUrlConnection.setRequestProperty("Content-Type", "application/JSON");

            httpUrlConnection.connect();

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

            return Boolean.parseBoolean(response);

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");



    public String sendToRahulsServer(File file, String url){
        OkHttpClient client ;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(100000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(100000, TimeUnit.MILLISECONDS);
        client = builder.build();
        RequestBody formBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("text/pdf"), file))
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            return response.body().string();
        }catch (Exception eio){
            eio.printStackTrace();
        }
        return null;
    }

    public String sendDataToGauravServer(File file, String url, long extra){
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", extra+"")
                .addFormDataPart("webcam", file.getName(),
                        RequestBody.create(MediaType.parse("text/plain"), file))
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            return response.body().string();
        }catch (Exception eio){
            eio.printStackTrace();
        }
        return null;
    }





/*    public File getFileFromServer(long userid, long fileid){

    }


    public File getFileFromServiceWithConsumerId(long consumerId, long fileId){

    }*/

}
