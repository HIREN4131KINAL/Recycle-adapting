package com.tranetech.infocity.userbay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity
{

    AppCompatButton sign_up;
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tv=(TextView)findViewById(R.id.link_login);
        sign_up=(AppCompatButton)findViewById(R.id.btn_signup);



        sign_up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

        tv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               finish();

            }
        });

    }
}