package com.nikraina.dummyapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "dummyAPP_TAG";

    Handler handle = new Handler(){             //Always use the handler object to update the UI
        @Override
        public void handleMessage(Message msg) {
            TextView textView = (TextView) findViewById(R.id.ltext);
            textView.setText("LOL we waited for nothing");
        }
    };


    public void handleClickMe(View view){

        Runnable run_obj = new Runnable() {
            @Override
            public void run() {
                long future = System.currentTimeMillis() + 10000;
                while (System.currentTimeMillis() < future){
                    synchronized (this){
                        try{
                            wait(future - System.currentTimeMillis());
                        }catch (Exception e){

                        }
                    }
                }
                handle.sendEmptyMessage(0);
            }
        };

        Thread thread = new Thread(run_obj);
        thread.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Log.i(TAG,"onCreate MainActivity");
    }

    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart MainActivity");
    }

    protected void onResume(){
        super.onResume();
        Log.i(TAG, "onResume MainActivity");
    }

    protected void onPause(){
        super.onPause();
        Log.i(TAG, "onPause MainActivity");
    }

    protected void onStop(){
        super.onStop();
        Log.i(TAG, "onStop MainActivity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
