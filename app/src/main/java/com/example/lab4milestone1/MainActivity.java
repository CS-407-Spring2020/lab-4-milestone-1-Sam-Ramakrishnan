package com.example.lab4milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.example.lab4milestone1.MainActivity.btn_start;
import static com.example.lab4milestone1.MainActivity.stopThread;

public class MainActivity extends AppCompatActivity {


    class ExampleRunnable implements Runnable{
        int seconds;

        public ExampleRunnable(int i) {
            seconds = i;
        }

        @Override
        public void run() {
            for(int i=0; i<seconds; i++){
                if(stopThread){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btn_start.setText("Start");
                        }
                    });
                    return;
                }
                if(i==5){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btn_start.setText("50%");
                        }
                    });
                }

                Log.d(MainActivity.TAG, "startThread "+i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            stopThread = true;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btn_start.setText("Start");
                }
            });
            return;

        }


    }


    static Button btn_start, btn_stop;
     static boolean stopThread = true;
     //static final String TAG = "MainActivity";
    static final String TAG = "zzzz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "btnp");
                stopThread = false;
                ExampleRunnable runnable = new ExampleRunnable(10);
                new Thread(runnable).start();

            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopThread = true;
            }
        });


    }

    void startThread(){
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable(10);
        new Thread(runnable).start();

    }

    void stopThread(){
        stopThread = true;
    }
}

