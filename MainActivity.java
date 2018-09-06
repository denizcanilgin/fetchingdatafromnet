package com.example.denizcan.parsingjson;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownLoadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

//            try {
//                JSONObject jsonObject = new JSONObject(result);
//
//                String weatherInfo = jsonObject.getString("weather");
//
//                Toast.makeText(getApplicationContext(),"Weather content: " + weatherInfo, 0).show();
//
//                JSONArray array = new JSONArray(weatherInfo);
//
//                for(int i = 0; i < array.length(); i++){
//
//                    JSONObject jsonPart = array.getJSONObject(i);
//
//                    jsonPart.getString("main");
//
//                    Toast.makeText(getApplicationContext(),"Wheather Info " + jsonPart.getString("main"), 0).show();
//
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            Toast.makeText(getApplicationContext(),"Website content: " + result, 0).show();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownLoadTask task = new DownLoadTask();
        task.execute("http://tamircikapinda.com/FetchData/getdata2.php");

    }
}
