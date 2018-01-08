package com.example.asus.iotapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MainActivity extends ListActivity {
    private Button btnSignOut;
    private SessionActivity sessionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] Devices = {"rasberrypi", "ESP12"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(),android.R.layout.simple_list_item_1, Devices);
        getListView().setAdapter(adapter);


        sessionActivity = new SessionActivity(this);
        if(!sessionActivity.signedIn()){
            signout();
        }
        btnSignOut = (Button)findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signout();
            }
        });
    }

    private void signout() {
        sessionActivity.sessionSignedIn(false);
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

    }


}
