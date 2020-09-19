package com.example.userprofile.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.example.userprofile.databinding.ActivityMainBinding;
import com.example.userprofile.modal.User;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String PHONE_FORMAT = "(%s) %s-%s";
    private int activityID;
    public User user;
    public static final String OBJECT_KEY = "USER_OBJECT";
    public static final String INT_KEY = "ACTIVITY_ID";
    public static final int REQUEST_CODE = 99;
    public static final int RESULT_CODE = 100;
    public static final int IMAGE_QUALITY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        user = new User();
        user.initUser();
        initViews();
        binding.nameLayoutLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityID = 0;
                launchDetailsActivity(activityID);
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

        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityID = 4;
                launchDetailsActivity(activityID);
            }
        });
    }

    private void initViews() {
        binding.nameValueTv.setText(user.getFirstName() + " " + user.getLastName());
        binding.phoneValueTv.setText(toPhoneNumberFormat(user.getPhone()));
        binding.emailValueTv.setText(user.getEmail());
        binding.introValueTv.setText(user.getBio());
        if (EditActivity.bytes != null) {
            Bitmap decodedByte = BitmapFactory.decodeByteArray(EditActivity.bytes, 0, EditActivity.bytes.length);
            binding.profileImage.setImageBitmap(decodedByte);
        }
    }

    public static String toPhoneNumberFormat(String s) {
        return String.format(PHONE_FORMAT, s.substring(0, 3), s.substring(3, 6), s.substring(6));
    }

    public static boolean isValidEmail(final String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void launchDetailsActivity(int id) {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        intent.putExtra(OBJECT_KEY, user);
        intent.putExtra(INT_KEY, id);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE) {
            user = (User) data.getSerializableExtra(MainActivity.OBJECT_KEY);
            initViews();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}