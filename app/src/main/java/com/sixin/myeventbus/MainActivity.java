package com.sixin.myeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sixin.eventbus.EventBus;
import com.sixin.eventbus.Subscribe;
import com.sixin.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getName();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getInstance().register(this);
        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(tag = "getData",threadMode = ThreadMode.MAIN)
    public void getData(String data){
//        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        textView.setText(data);
    }

    @Subscribe(tag = "getData",threadMode = ThreadMode.MAIN)
    public void getData1(String data){
        Log.d(TAG, "**********" + data);
    }



}
