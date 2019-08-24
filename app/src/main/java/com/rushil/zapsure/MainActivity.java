package com.rushil.zapsure;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity {
    EditText Car_noEt, PasswordEt;
    String l_result = "";
    Context context;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Car_noEt = (EditText)findViewById(R.id.Car_no);
        PasswordEt = (EditText)findViewById(R.id.Usr_pass);
    }

    public void OnLogin(View view) throws InterruptedException {
        String username = Car_noEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
        while(backgroundWorker.getRushil() == "nothing"){
            Log.e("myTag","getting data from server");
            Thread.sleep(900);
        }
        l_result = backgroundWorker.getRushil();
        Log.e("OnLogin: ",l_result );

        if(l_result.equalsIgnoreCase("1")){
            Intent i = new Intent(MainActivity.this,Car_profile.class);
            Bundle extras = new Bundle();
            extras.putString("EXTRA_USERNAME",username);
            extras.putString("EXTRA_PASSWORD",password);
            i.putExtras(extras);
            startActivity(i);
        }
        else{
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");
            alertDialog.setMessage("Invalid Credentials,please login again");
            alertDialog.show();
        }

    }

    public void OpenReg(View view) {
        startActivity(new Intent(this,Register_car.class));
    }


}
