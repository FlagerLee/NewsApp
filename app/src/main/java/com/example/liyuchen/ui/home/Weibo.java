package com.example.liyuchen.ui.home;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Weibo {
    private static String AppKey = "4073260475";
    private static String AppSecret = "c23f863f31c44824afcabf764a503e77";

    private static String OAuthURL = "https://api.weibo.com/oauth2/access_token";
    private static String GrantType = "client_credentials";

    private static String AccessToken = null;

    private static String ShareURL = "https://api.weibo.com/2/statuses/share.json";

    public static void getAccessToken(Finish callback) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                String content = null;
                try {
                    URL url = new URL(OAuthURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestProperty("client_id", Weibo.AppKey);
                    connection.setRequestProperty("client_secret", Weibo.AppSecret);
                    connection.setRequestProperty("grant_type ", Weibo.GrantType);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setReadTimeout(10000);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    content = reader.readLine();
                } catch (Exception e) {
                    content = null;
                }

                callback.finish(content);
            }
        };
        thread.start();
    }

    public static void Share(String content, String accessToken, Finish callback) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                String c;
                try {
                    URL url = new URL(ShareURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestProperty("access_token", accessToken);
                    connection.setRequestProperty("status", content);
                    connection.setRequestProperty("rip", "127.0.0.1");

                    connection.setReadTimeout(10000);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    c = reader.readLine();
                } catch (Exception e ) {
                    c = null;
                }

                callback.finish(c);
            }
        };
        thread.start();
    }
}
