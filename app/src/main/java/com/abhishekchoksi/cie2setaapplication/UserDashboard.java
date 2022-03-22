package com.abhishekchoksi.cie2setaapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UserDashboard extends AppCompatActivity {

    Button buttonLeaveApply;
    TextView textViewFromDate,textViewToDate,textViewLogout;
    EditText editTextReason;
    String leaveType = "";
    String fDate = "";
    String tDate = "";

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
                startActivity(new Intent(UserDashboard.this,LoginActivity.class));
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        editTextReason = findViewById(R.id.editTextReason);
        textViewFromDate = findViewById(R.id.textViewFromDate);
        textViewToDate = findViewById(R.id.textViewToDate);
        textViewLogout = findViewById(R.id.textViewLogout);
        buttonLeaveApply = findViewById(R.id.buttonLeaveApply);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int dayofMonth = calendar.get(calendar.DAY_OF_MONTH);

        textViewFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UserDashboard.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month + 1;
                                fDate =  day + "/" + month + "/" + year;
                                textViewFromDate.setText(fDate);
                            }
                        },year,month,dayofMonth
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        textViewToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UserDashboard.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month + 1;
                                tDate = day + "/" + month + "/" + year;
                                textViewToDate.setText(tDate);
                            }
                        }, year,month,dayofMonth
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("LoginData",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("UserName","Fail");
                editor.commit();
                startActivity(new Intent(UserDashboard.this,LoginActivity.class));
                finish();
            }
        });

        buttonLeaveApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserDashboard.this);
                builder.setTitle("Leave Apply");
                builder.setMessage("Are you confirm Leave Apply");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences pref = getSharedPreferences("EmployeeData",MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("Name", "Abhishek Choksi");
                        editor.putString("Reason", editTextReason.getText().toString());
                        editor.putString("From_Date",textViewFromDate.getText().toString() );
                        editor.putString("To_Date",textViewToDate.getText().toString());
                        editor.putString("Leave_Type",leaveType);
                        editor.putString("Leave_Status","Pending");
                        editor.commit();
                        Toast.makeText(UserDashboard.this, "Leave Apply Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(UserDashboard.this, "Please Try Again!\nClick Leave Button!", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void radioButtonCheck(View view){
        boolean isChecked  = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rbtCasual:
                if(isChecked){
                    leaveType = "Casual Leave";
                }
                break;
            case R.id.rbtAddtional:
            if(isChecked){
                leaveType = "Additional Leave";
            }
        }
    }
}