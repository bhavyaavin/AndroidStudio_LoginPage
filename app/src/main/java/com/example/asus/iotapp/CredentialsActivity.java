package com.example.asus.iotapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CredentialsActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText tvusrname, tvpass;
    private Button StoreCredentials;
    private TextView ChangeCred;
    private DatabaseHelp dbHelp;
    private SessionActivity sessionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);

        tvusrname = (EditText)findViewById(R.id.tvusrname);
        tvpass = (EditText)findViewById(R.id.tvpass);
        StoreCredentials = (Button)findViewById(R.id.storeCred);
        ChangeCred = (TextView)findViewById(R.id.ChangeCred);
        ChangeCred.setOnClickListener(this);
        StoreCredentials.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.storeCred:
                StoreCredentials();
                break;
            case R.id.ChangeCred:
                startActivity(new Intent(CredentialsActivity.this, NewCredentials.class));
                break;

            default:

        }
    }

    private void StoreCredentials() {

        String Username = tvusrname.getText().toString();
        String Password = tvpass.getText().toString();

        if(dbHelp.getUser(Username,Password))
        {
            sessionActivity.sessionstoreCredentials(true);
            startActivity(new Intent(CredentialsActivity.this, MainActivity.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "invalid Username/Password", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
