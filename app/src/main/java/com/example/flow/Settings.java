package com.example.flow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        drawerLayout = findViewById(R.id.drawer_layout);
        TextView userName = (TextView)findViewById(R.id.userName);
        userName.setText(getSharedPreferences("user_data", MODE_PRIVATE).getString("username", "To Do"));
    }

    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickDay(View view){
        MainActivity.redirectActivity(this,MainActivity.class);
    }
    public void ClickNight(View view){
        MainActivity.redirectActivity(this,Night.class);
    }
    public void ClickSettings(View view){
        recreate();
    }

    public void ClickAbout(View view){
        MainActivity.redirectActivity(this,About.class);
    }
    public void ClickExit(View view){
        MainActivity.exit(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }
}