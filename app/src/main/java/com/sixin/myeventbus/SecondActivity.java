package com.sixin.myeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sixin.eventbus.EventBus;
import com.sixin.eventbus.Subscribe;
import com.sixin.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EventBus.getInstance().register(this);
        findViewById(R.id.btnSecond).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(tag = "getData",threadMode = ThreadMode.MAIN)
    private void getData2(String data){
        Log.d(TAG, "((((((((((((" + data);
    }
}
