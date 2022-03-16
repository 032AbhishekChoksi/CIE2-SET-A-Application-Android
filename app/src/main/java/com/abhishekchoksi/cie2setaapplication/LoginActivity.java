package com.abhishekchoksi.cie2setaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class LoginActivity extends AppCompatActivity {

    EditText editTextPersonName,editTextPassword;
    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        SharedPreferences pref = getSharedPreferences("LoginData",MODE_PRIVATE);
        String UserName = pref.getString("UserName","Fail");

        if(UserName.equals("Admin"))
        {
            startActivity(new Intent(LoginActivity.this,AdminDashboard.class));
            finish();
        }
        else if(UserName.equals("User"))
        {
            startActivity(new Intent(LoginActivity.this,UserDashboard.class));
            finish();
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextPersonName.getText().toString();
                String password = editTextPassword.getText().toString();

                if(name.equals("Admin") && password.equals("123"))
                {
                    if(UserName.equals("Fail")){
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("UserName","Admin");
                        editor.commit();
                    }
                    startActivity(new Intent(LoginActivity.this,AdminDashboard.class));
                    finish();
                }
                else if(name.equals("User") && password.equals("1234"))
                {
                    if(UserName.equals("Fail")){
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("UserName","User");
                        editor.commit();
                    }
                    startActivity(new Intent(LoginActivity.this,UserDashboard.class));
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Invalid Users Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}