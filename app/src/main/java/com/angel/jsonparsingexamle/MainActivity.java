package com.angel.jsonparsingexamle;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
//AppCompatActivity
    ListView listView;
    CityAdapter adapter;
    ArrayList<City> cityArrayList;
    DBHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        /**listView.setOnClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position){
                City city = (City) adapter.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                //intent.putExtras();
            }
        });**/
        handler = new DBHandler(this);
        NetworkUtils utils = new NetworkUtils(MainActivity.this);
        if(handler.getCityCount() == 0 && utils.isConnectingToInternet()){
            new DataFetcherTask().execute();
        }
        else {
            ArrayList<City> cityList = handler.getAllCity();
            adapter = new CityAdapter(MainActivity.this, cityList);
            listView.setAdapter(adapter);
        }
    }

    class DataFetcherTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... Params) {
            String serverData = null; // String object to store fetched data from server
            //http request code start

            try {
                OkHttpClient client = new OkHttpClient();
                URL url  = new URL("http://beta.json-generator.com/api/json/get/GAqnlDN");
                Request request = new Request.Builder().url(url).build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                    responses.headers().get().
                    serverData = responses.body().string();
                }  catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException e){
                e.printStackTrace();
            }


            // Http Request Code end
            // Json Parsing Code Start
            try{
                cityArrayList = new ArrayList<City>();
                JSONObject jsonObject = new JSONObject(serverData);
                JSONArray jsonArray = jsonObject.getJSONArray("cities");

                for (int i = 0; i<jsonArray.length();i++){
                    JSONObject jsonObjectCity = jsonArray.getJSONObject(i);
                    String cityName = jsonObjectCity.getString("name");
                    String cityState = jsonObjectCity.getString("state");
                    String cityDescription = jsonObjectCity.getString("description");
                    City city = new City();
                    city.setName(cityName);
                    city.setState(cityState);
                    city.setDescription(cityDescription);
                    handler.addCity(city);//Inserting into DB
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            ArrayList<City> cityList = handler.getAllCity();
            adapter = new CityAdapter(MainActivity.this, cityList);
            listView.setAdapter(adapter);
        }

    }
}
