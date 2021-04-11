package com.example.flow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import devlight.io.library.ArcProgressStackView;
import com.example.flow.EventsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ///圆形日历显示不出来
    EventsFragment eventsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignUsername();
        drawerLayout =findViewById(R.id.drawer_layout);
        //eventsFragment.setupDateProgress();///圆形日历
    }



    public void assignUsername() {
        //set Toolbar and toolbar title
        TextView userName = (TextView) this.<View>findViewById(R.id.userName);
        userName.setText(getSharedPreferences("user_data", MODE_PRIVATE).getString("username", "To Do"));
    }

///修改用户名的地方还有错
    public void change_username_popup(){
        //inflating the change username pop out layout
        View viewLocal;
        viewLocal = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popup_change_username,null);
        final EditText etNewUsername = viewLocal.findViewById(R.id.popup_change_username);
        final String currentTitle = getSupportActionBar().getTitle().toString();

        etNewUsername.setText(currentTitle);

        //creating ALERT DIALOG
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
        builder.setView(viewLocal);
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();

        // Cancel button click listener
        viewLocal.findViewById(R.id.popup_change_username_cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });

        // Change button click listener
        viewLocal.findViewById(R.id.popup_change_username_done_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etNewUsername.getText().toString().isEmpty()){
                    // changing the username
                    SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
                    prefs.edit().putString("username", etNewUsername.getText().toString().trim()).apply();
                    getSupportActionBar().setTitle(getSharedPreferences("user_data", MODE_PRIVATE).getString("username", "To Do"));
                    alertDialog.cancel();
                }
            }
        });
        alertDialog.show();
    }

    public void EditUserName(View view){
        change_username_popup();
    }
///以上

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickDay(View view){
        recreate();
    }
    public void ClickNight(View view){
        redirectActivity(this,Night.class);
    }

    public void ClickAbout(View view){
        redirectActivity(this,About.class);
    }

    public void ClickSettings(View view){
        redirectActivity(this,Settings.class);
    }

    public void ClickExit(View view){
        exit(this);
    }

    public static void exit(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit FLOW?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity,Class night) {
        Intent intent = new Intent(activity,night);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }
}