package com.steeginaldo.proxernews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Janik on 28.06.2016.
 */
class HTMLReciever {

    private StringBuffer data;

    public void recieveData(String urls) {
        HttpURLConnection proxer = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;

        try {
            URL url = new URL(urls);
            proxer = (HttpURLConnection) url.openConnection();
            proxer.connect();

            InputStream data = proxer.getInputStream();

            reader = new BufferedReader(new InputStreamReader(data));

            buffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (proxer != null) {
                proxer.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        data = buffer;
    }


    public StringBuffer getData() {
        return data;
    }
}
