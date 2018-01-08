package com.example.asus.iotapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button SignUp;
    private EditText tvEmail, tvpassword;
    private TextView bcksignin;
    private DatabaseHelp dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelp = new DatabaseHelp(this);
        SignUp = (Button)findViewById(R.id.btnsignUp);
        tvEmail = (EditText)findViewById(R.id.tvEmail);
        tvpassword= (EditText)findViewById(R.id.tvpassword);
        bcksignin = (TextView)findViewById(R.id.bcksignin);
        SignUp.setOnClickListener(this);
        bcksignin.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsignUp:
                signUp();
                break;

            case R.id.bcksignin:
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
                break;

            default:

        }
    }

    private void signUp()
    {
        String Email = tvEmail.getText().toString();
        String Password = tvpassword.getText().toString();

        if(Email.isEmpty() && Password.isEmpty())
        {
            displayToast("Please Enter Valid Email address and password");
        }else{
            dbHelp.StoreUserDetails(Email,Password);
            displayToast("User Added");
            finish();
        }

    }

    private void displayToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();

    }
}
