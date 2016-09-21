package com.zhi.www.utils;

import android.os.AsyncTask;

import com.zhi.www.info.GetHttpListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/16.
 */
public class HttpHelper extends AsyncTask<String, Void, String> {
    HttpURLConnection mConnection;
    InputStream in;
    GetHttpListener getHttpListener;

    String url;

    public HttpHelper(String url, GetHttpListener getHttpListener){
        this.url = url;
        this.getHttpListener = getHttpListener;
    }

    @Override
    protected String doInBackground(String... params){
        try {
            URL uri = new URL(url);
            mConnection = (HttpURLConnection) uri.openConnection();
            mConnection.connect();
            in = mConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine())!=null){
                sb.append(line);
            }
            br.close();
            mConnection.disconnect();
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        getHttpListener.gethttpData(s);
        super.onPostExecute(s);
    }
}
