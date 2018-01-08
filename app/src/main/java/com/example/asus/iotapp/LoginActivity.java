package com.example.asus.iotapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button SignIn, register;
    private EditText tvEmail, tvpassword;
    private DatabaseHelp dbHelp;
    private SessionActivity sessionActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelp = new DatabaseHelp(this);
        sessionActivity = new SessionActivity(this);
        SignIn = (Button)findViewById(R.id.btnsignin);
        register = (Button)findViewById(R.id.btnsignUp);
        tvEmail = (EditText)findViewById(R.id.tvEmail);
        tvpassword= (EditText)findViewById(R.id.tvpassword);
        SignIn.setOnClickListener(this);
        register.setOnClickListener(this);

        if(sessionActivity.signedIn()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsignin:
                Login();
                break;
            case R.id.btnsignUp:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
            default:
        }

    }

    private void Login()
    {
        String Email = tvEmail.getText().toString();
        String Password = tvpassword.getText().toString();

        if(dbHelp.getUser(Email,Password))
        {
            sessionActivity.sessionSignedIn(true);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "invalid EmailId/Password", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
