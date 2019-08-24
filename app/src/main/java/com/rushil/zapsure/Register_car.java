package com.rushil.zapsure;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Register_car extends AppCompatActivity {

    EditText new_car_name,new_car_usr_name,new_car_gender,new_car_age,new_car_contact,new_car_password,new_car_doctor ;
    String p_result;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_car);
        new_car_name = (EditText)findViewById(R.id.new_usr_name);
        new_car_usr_name = (EditText)findViewById(R.id.new_car_no);
        new_car_age = (EditText)findViewById(R.id.new_usr_age);
        new_car_contact = (EditText)findViewById(R.id.new_usr_phone);
        new_car_password = (EditText)findViewById(R.id.new_usr_pass);
    }

    public void OnReg(View view) throws InterruptedException {
        String name = new_car_name.getText().toString();
        String username = new_car_usr_name.getText().toString();
        String gender = new_car_gender.getText().toString();
        String age = new_car_age.getText().toString();
        String contact = new_car_contact.getText().toString();
        String password = new_car_password.getText().toString();
        String doctor = new_car_doctor.getText().toString();
        String type = "register";
        final BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,name, username,gender,age,contact, password,doctor);
        while(backgroundWorker.getRushil() == "nothing"){
            Log.e("myTag","getting data from server");
            Thread.sleep(900);
        }
        p_result = backgroundWorker.getRushil();
        Log.e("myTag Pinku",p_result);


        if(p_result.equalsIgnoreCase("rushil")){
            Log.e("myTag","-------------------ehyrtg------------------------");
            Intent intent = new Intent(Register_car.this,Upload_Pic_Car.class);
            intent.putExtra("username",username);
            startActivity(intent);
            finish();
        }
        else if(p_result.equalsIgnoreCase("goomer")){
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Registration Status");
            alertDialog.setMessage("Not registered");
            alertDialog.show();
        }
        else{
            Log.e("myTag","12234556677");
        }


    }
}

