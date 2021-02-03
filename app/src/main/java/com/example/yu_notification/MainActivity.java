package com.example.yu_notification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "TAG 메인";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate 호출됨");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();    //툴바 숨기기

        FragmentListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume 호출됨");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy 호출됨");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause 호출됨");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop 호출됨");
    }

    private void FragmentListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_news:
                        NewsFragment frag_news = new NewsFragment();
                        FragmentTransaction trans_news = getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.alpha_in, R.anim.alpha_out)
                                .replace(R.id.frame, frag_news);
                        trans_news.commit();
                        break;
                    case R.id.btn_info:
                        CodeFragment frag_codes = new CodeFragment();
                        FragmentTransaction trans_codes = getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.alpha_in, R.anim.alpha_out)
                                .replace(R.id.frame, frag_codes);
                        trans_codes.commit();
                        break;
                    case R.id.btn_st_council:
                        break;
                    case R.id.btn_college:
                        startActivity(new Intent(getApplicationContext(), CollegeActivity.class));
                        break;

                }//end switch
            }
        }; //end clicked listener

        findViewById(R.id.btn_news).setOnClickListener(listener);
        findViewById(R.id.btn_info).setOnClickListener(listener);
        findViewById(R.id.btn_st_council).setOnClickListener(listener);
        findViewById(R.id.btn_college).setOnClickListener(listener);
    }
}