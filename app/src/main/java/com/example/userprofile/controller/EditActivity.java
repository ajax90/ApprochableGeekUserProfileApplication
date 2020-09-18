package com.example.userprofile.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userprofile.R;
import com.example.userprofile.databinding.ActivityEditBinding;
import com.example.userprofile.modal.User;

import java.sql.BatchUpdateException;
import java.time.chrono.MinguoChronology;

public class EditActivity extends AppCompatActivity {

    private com.example.userprofile.databinding.ActivityEditBinding binding;
    private User user;
    private int id;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if(getIntent() != null && getIntent().getExtras() != null) {
            Intent intent = getIntent();
            user = (User) intent.getSerializableExtra(MainActivity.OBJECT_KEY);
            id = intent.getIntExtra(MainActivity.INT_KEY, 99);
            Bundle bundle = new Bundle();
            bundle.putSerializable(MainActivity.OBJECT_KEY, user);

            switch (id) {
                case 0:
                    fragment = new EditNameFragment();
                    fragment.setArguments(bundle);
                    ft.add(R.id.fragmentContainer, fragment, "0");
                    ft.commitAllowingStateLoss();
                    break;
                case 1:
                    fragment = new EditPhoneFragment();
                    fragment.setArguments(bundle);
                    ft.add(R.id.fragmentContainer, fragment, "1");
                    ft.commitAllowingStateLoss();
                    break;
                case 2:
                    fragment = new EditEmailFragment();
                    fragment.setArguments(bundle);
                    ft.add(R.id.fragmentContainer, fragment, "2");
                    ft.commitAllowingStateLoss();
                    break;
                case 3:
                    fragment = new EditBioFragment();
                    fragment.setArguments(bundle);
                    ft.add(R.id.fragmentContainer, fragment, "3");
                    ft.commitAllowingStateLoss();
                    break;
                default:
                    finish();
            }

            binding.updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (fragment.getTag()){
                        case "0":
                            EditText firsNameEditText = fragment.getView().findViewById(R.id.edit_first_name_tv);
                            EditText lastNameEditText = fragment.getView().findViewById(R.id.edit_last_name_tv);
                            user.setFirstName(firsNameEditText.getText().toString());
                            user.setLastName(lastNameEditText.getText().toString());
                            setResult(100, getIntent().putExtra(MainActivity.OBJECT_KEY, user));
                            finish();
                            break;
                        case "1":
                            EditText phoneNumberEditText = fragment.getView().findViewById(R.id.edit_value_tv);
                            user.setPhone(phoneNumberEditText.getText().toString().replaceAll("[^\\d]",""));
                            setResult(100, getIntent().putExtra(MainActivity.OBJECT_KEY, user));
                            finish();
                            break;
                        case "2":
                            EditText emailEditText = fragment.getView().findViewById(R.id.edit_value_tv);
                            String email = emailEditText.getText().toString();
                            if(MainActivity.isValidEmail(email)){
                                user.setEmail(email);
                                setResult(100, getIntent().putExtra(MainActivity.OBJECT_KEY, user));
                                finish();
                            } else{
                                Toast.makeText(EditActivity.this, "Please enter a valid email!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "3":
                            EditText bioEditText = fragment.getView().findViewById(R.id.edit_value_tv);
                            user.setBio(bioEditText.getText().toString());
                            setResult(100, getIntent().putExtra(MainActivity.OBJECT_KEY, user));
                            finish();
                            break;
                    }
                }
            });

        }
    }
}