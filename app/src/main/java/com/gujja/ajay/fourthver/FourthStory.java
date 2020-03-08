package com.gujja.ajay.fourthver;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;




public class FourthStory extends AppCompatActivity {


    float speed = 0.8f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_story);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.speedmeter,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case R.id.Slow :
                speed = 0.4f;
                Toast.makeText(this,"Slow is selected",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Normal :
                speed = 0.8f;
                Toast.makeText(this,"Normal is selected",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Fast :
                speed = 1.2f;
                Toast.makeText(this,"Fast is selected",Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
