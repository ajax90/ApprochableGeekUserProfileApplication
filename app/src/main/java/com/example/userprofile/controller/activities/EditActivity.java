package com.example.userprofile.controller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.userprofile.R;
import com.example.userprofile.controller.fragments.EditBioFragment;
import com.example.userprofile.controller.fragments.EditEmailFragment;
import com.example.userprofile.controller.fragments.EditNameFragment;
import com.example.userprofile.controller.fragments.EditPhoneFragment;
import com.example.userprofile.controller.fragments.EditPhotoFagment;
import com.example.userprofile.databinding.ActivityEditBinding;
import com.example.userprofile.modal.User;

import java.io.ByteArrayOutputStream;


public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;
    private User user;
    private int id;
    private Fragment fragment;
    private Uri imageUri;
    public static byte[] bytes;
    public static final int SELECT_PICTURE = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (getIntent() != null && getIntent().getExtras() != null) {
            Intent intent = getIntent();
            user = (User) intent.getSerializableExtra(MainActivity.OBJECT_KEY);
            id = intent.getIntExtra(MainActivity.INT_KEY, MainActivity.REQUEST_CODE);
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
                case 4:
                    fragment = new EditPhotoFagment();
                    fragment.setArguments(bundle);
                    ft.add(R.id.fragmentContainer, fragment, "4");
                    ft.commitAllowingStateLoss();
                    break;
                default:
                    finish();
            }

            binding.backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            binding.updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (fragment.getTag()) {
                        case "0":
                            EditText firsNameEditText = fragment.getView().findViewById(R.id.edit_first_name_tv);
                            EditText lastNameEditText = fragment.getView().findViewById(R.id.edit_last_name_tv);
                            user.setFirstName(firsNameEditText.getText().toString());
                            user.setLastName(lastNameEditText.getText().toString());
                            setResult(MainActivity.RESULT_CODE, getIntent().putExtra(MainActivity.OBJECT_KEY, user));
                            finish();
                            break;
                        case "1":
                            EditText phoneNumberEditText = fragment.getView().findViewById(R.id.edit_value_tv);
                            user.setPhone(phoneNumberEditText.getText().toString().replaceAll("[^\\d]", ""));
                            setResult(MainActivity.RESULT_CODE, getIntent().putExtra(MainActivity.OBJECT_KEY, user));
                            finish();
                            break;
                        case "2":
                            EditText emailEditText = fragment.getView().findViewById(R.id.edit_value_tv);
                            String email = emailEditText.getText().toString();
                            if (MainActivity.isValidEmail(email)) {
                                user.setEmail(email);
                                setResult(MainActivity.RESULT_CODE, getIntent().putExtra(MainActivity.OBJECT_KEY, user));
                                finish();
                            } else {
                                Toast.makeText(EditActivity.this, "Please enter a valid email!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "3":
                            EditText bioEditText = fragment.getView().findViewById(R.id.bio_textview);
                            user.setBio(bioEditText.getText().toString());
                            setResult(MainActivity.RESULT_CODE, getIntent().putExtra(MainActivity.OBJECT_KEY, user));
                            finish();
                            break;
                        case "4":
                            setResult(MainActivity.RESULT_CODE, getIntent());
                            finish();
                            break;
                    }
                }
            });

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            imageUri = data.getData();
            ImageView imageView = fragment.getView().findViewById(R.id.photo_imageview);
            imageView.setImageURI(imageUri);
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, MainActivity.IMAGE_QUALITY, baos);
            bytes = baos.toByteArray();
        }
    }

}