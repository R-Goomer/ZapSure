package com.rushil.zapsure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.rushil.zapsure.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import android.widget.TextView;


import java.util.ArrayList;

public class Car_profile extends AppCompatActivity {

    ImageView imageview;

    String url = "http://10.12.10.226:8080//images/chiku.png";
    String loading = "loading O";
    ArrayList<String> mylist = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_profile);
        imageview = (ImageView) findViewById(R.id.imageView2);
        loadImageFromUrl(url);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String username_string = extras.getString("EXTRA_USERNAME");
        String password_string = extras.getString("EXTRA_PASSWORD");

        final TextView name = (TextView) findViewById(R.id.name);
        final TextView username = (TextView) findViewById(R.id.username);
        name.setText(loading);
        username.setText(loading);

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("fetch_patient", username_string, password_string);
        while(backgroundWorker.getRushil().equals("nothing")){
            Log.e("myTag","getting data from server");
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String pinku = backgroundWorker.getRushil();
        //Log.e("pinku",pinku);
        String strArray[] = pinku.split(",");
        for(int r = 0 ; r<strArray.length; r++) {
            String lol = strArray[r];
            String lol_arr[] = lol.split(":");
            mylist.add(lol_arr[1]);
        }

        name.setText(mylist.get(1));
        username.setText(mylist.get(2));
    }



    private void loadImageFromUrl(String url){
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageview, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

}
