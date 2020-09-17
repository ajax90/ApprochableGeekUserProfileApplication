package com.example.userprofile.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.userprofile.R;
import com.example.userprofile.modal.User;

public class MainActivity extends AppCompatActivity {

    private com.example.userprofile.databinding.ActivityMainBinding binding;
    private static final String OBJECT_KEY = "USER_OBJECT";
    private static final String INT_KEY = "ACTIVITY_ID";
    private int activityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.userprofile.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.nameLayoutLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityID = 0;
                launchDetailsActivity(activityID);
                Log.d("demo", "name");
            }
        });

        binding.phoneLayoutLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityID = 1;
                launchDetailsActivity(activityID);
            }
        });

        binding.emailLayoutLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityID = 2;
                launchDetailsActivity(activityID);
            }
        });

        binding.bioLayoutLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityID = 3;
                launchDetailsActivity(activityID);
            }
        });
    }

    private void launchDetailsActivity(int id) {
        User user = new User();
        user.initUser();
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(OBJECT_KEY, user);
        bundle.putInt(INT_KEY, id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}