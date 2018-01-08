package com.example.asus.iotapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewCredentials extends AppCompatActivity implements View.OnClickListener{
    private EditText tvnewusrname, tvnewpass, backtomain;
    private Button Update;
    private DatabaseHelp dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_credentials);

        tvnewpass =(EditText)findViewById(R.id.tvnewpass);
        tvnewusrname=(EditText)findViewById(R.id.tvnewusrname);
        backtomain=(EditText)findViewById(R.id.backtomain);
        Update=(Button)findViewById(R.id.tvupdate);
        Update.setOnClickListener(this);
        backtomain.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvupdate:
                update();
                break;

            case R.id.backtomain:
                startActivity(new Intent(NewCredentials.this, MainActivity.class));
                finish();
                break;

            default:
        }

    }

    private void update() {

        String newusrname = tvnewusrname.getText().toString();
        String newpassword = tvnewpass.getText().toString();

        if(newusrname.isEmpty() && newpassword.isEmpty())
        {
            displayToast("Please Enter Valid Email address and password");
        }else{
            dbHelp.StoreUserDetails(newusrname,newpassword);
            displayToast("User Added");
            finish();
        }
    }

    private void displayToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();

    }
}
