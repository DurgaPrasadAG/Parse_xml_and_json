package com.bliszkot.parsexml;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; import android.view.View; import android.widget.Button; import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bxml,bjson;
    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main); bxml=(Button)findViewById(R.id.button_xml); bxml.setOnClickListener(this);
        bjson=(Button)findViewById(R.id.button_json);
        bjson.setOnClickListener(this); }
    @Override
    public void onClick(View v) {
        if(v.equals(bjson)){
        Intent intent=new Intent(this,ViewActivity.class);
        intent.putExtra("mode",1);
        startActivity(intent);
    }
    else if(v.equals(bxml)){
        Intent intent=new Intent(this,ViewActivity.class);
        intent.putExtra("mode",2);
        startActivity(intent);
    } }
}