package com.abhishekchoksi.cie2setaapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AdminDashboard extends AppCompatActivity {
    TextView textViewName,textViewReason,textViewFrom_Date,textViewTo_Date,textViewLeave_Type,textViewLeave_Status,textAdminLogout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.MenuLogout:
                SharedPreferences pref = getSharedPreferences("LoginData",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("UserName","Fail");
                editor.commit();
                startActivity(new Intent(AdminDashboard.this,LoginActivity.class));
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        textViewName = findViewById(R.id.textViewName);
        textViewReason = findViewById(R.id.textViewReason);
        textViewFrom_Date = findViewById(R.id.textViewFrom_Date);
        textViewTo_Date = findViewById(R.id.textViewTo_Date);
        textViewLeave_Type = findViewById(R.id.textViewLeave_Type);
        textViewLeave_Status = findViewById(R.id.textViewLeave_Status);
        textAdminLogout = findViewById(R.id.textAdminLogout);

        String Name,Reason,From_Date,To_Date,Leave_Type,Leave_Status;

        SharedPreferences pref = getSharedPreferences("EmployeeData",MODE_PRIVATE);
        Name = pref.getString("Name","Name");
        Reason = pref.getString("Reason","Reason");
        From_Date = pref.getString("From_Date","From Date");
        To_Date = pref.getString("To_Date","To Date");
        Leave_Type = pref.getString("Leave_Type","Leave_Type");
        Leave_Status = pref.getString("Leave_Status","Leave Status");

        textViewName.setText(Name);
        textViewReason.setText(Reason);
        textViewFrom_Date.setText(From_Date);
        textViewTo_Date.setText(To_Date);
        textViewLeave_Type.setText(Leave_Type);
        textViewLeave_Status.setText(Leave_Status);

        textViewLeave_Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Leave_Status.equals("Pending")){
                    textViewLeave_Status.setText("Rejected");
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Leave_Status","Rejected");
                    editor.commit();
                }
            }
        });

        textAdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("LoginData",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("UserName","Fail");
                editor.commit();
                startActivity(new Intent(AdminDashboard.this,LoginActivity.class));
                finish();
            }
        });
    }
}