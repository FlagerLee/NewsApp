package com.example.liyuchen.Async;

import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public class AsyncFunctions {

    static private ExecutorService es = Executors.newCachedThreadPool();

    static private SSLContext sslContext = null;

    static public void RequestTextFromURL(final String url, final String method, final int timeout, final NetworkRequest callback) {
        Thread thread = new Thread() {
            public void run() {
                String msg = null;
                String errMsg = null;
                boolean statement = true;
                try {

                    if(AsyncFunctions.sslContext == null) {
                        sslContext = SSLContext.getInstance("SSL");
                        TrustManager[] trustManager = {new IX509TrustManager()};
                        sslContext.init(null, trustManager, new SecureRandom());

                        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                            public boolean verify(String s, SSLSession sslsession) {
                                System.out.println("WARNING: Hostname is not matched for cert.");
                                return true;
                            }
                        };
                        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
                        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

                    }

                    URL newURL = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) newURL.openConnection();
                    connection.setRequestMethod(method);
                    connection.setConnectTimeout(timeout);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    msg = reader.readLine();
                }
                catch (Exception e) {
                    statement = false;
                    errMsg = e.toString();
                }
                callback.NetworkRequestTextFinish(statement, msg, errMsg);
            }
        };
        AsyncFunctions.es.execute(thread);
    }

    static public void DownloadFile(final String url, final String method, final int timeout, final String savePath, final DownloadFile callback) {
        Thread thread = new Thread() {
            public void run() {
                String errMsg = null;
                boolean statement = true;
                try {
                    String content = null;
                    URL newURL = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) newURL.openConnection();
                    connection.setRequestMethod(method);
                    connection.setConnectTimeout(timeout);
                    InputStream inputStream = connection.getInputStream();
                    byte[] data = readInputStream(inputStream);
                    FileOutputStream outputStream = new FileOutputStream(savePath);
                    outputStream.write(data);
                    outputStream.close();
                }
                catch (Exception e) {
                    statement = false;
                    errMsg = e.toString();
                }
                callback.DownloadFileFinish(statement, savePath, errMsg);
            }
        };
        AsyncFunctions.es.execute(thread);
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    //static public void DatabaseQuery
    //f**k off, who knows what would be queried
    //Database operation won't be defined here

    public static void DatabaseCURD(BaseModel table, String operation, DatabaseCURD callback) {
        Thread thread = new Thread() {
            public void run() {
                String errMsg = null;
                boolean statement = true;
                try {
                    switch (operation) {
                        case "INSERT":
                            table.insert();
                            break;
                        case "UPDATE":
                            table.update();
                            break;
                        case "DELETE":
                            table.delete();
                            break;
                        case "SAVE":
                            table.save();
                            break;
                        default:
                            statement = false;
                            errMsg = "Argument \"operation\" requires \"INSERT\", \"UPDATE\", \"DELETE\" or \"SAVE\", not " + operation;
                            break;
                    }
                }
                catch (Exception e) {
                    statement = false;
                    errMsg = e.toString();
                }
                callback.DatabaseCURDFinish(statement, errMsg);
            }
        };
        AsyncFunctions.es.execute(thread);
    }
}
