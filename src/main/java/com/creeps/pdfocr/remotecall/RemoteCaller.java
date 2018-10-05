package com.creeps.pdfocr.remotecall;

import com.creeps.pdfocr.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RemoteCaller {

    final static String crlf = "\r\n";
    final static String twoHyphens = "--";
    final static String boundary =  "*****";




    private final static String TAG = "RemoteCaller";
    @Autowired
    private Logger logger;
    /**
     * Places a remote call to the specified url and returns the response as  string
     * @param serverAddress:String -> The server's url
     * @param apiKey:String -> The api key
     * @return The server response
     */
    public String getOCRData(String serverAddress, String apiKey ,MultipartFile file) {
        try {
            HttpURLConnection httpUrlConnection = null;
            String attachmentName = "pdf";
            String attachmentFileName = file.getOriginalFilename();
            URL url = new URL(serverAddress);
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);

            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
            httpUrlConnection.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + boundary);
            httpUrlConnection.setRequestProperty("apikey", apiKey);



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

        return null;

    }

}
