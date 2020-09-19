package com.example.userprofile.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.userprofile.databinding.FragmentEditPhoneBinding;
import com.example.userprofile.databinding.FragmentEditPhotoBinding;
import com.example.userprofile.modal.User;

import static com.example.userprofile.controller.EditActivity.bytes;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditPhotoFagment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class EditPhotoFagment extends Fragment {

    private FragmentEditPhotoBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = MainActivity.OBJECT_KEY;

    // TODO: Rename and change types of parameters
    private User user;

    public EditPhotoFagment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditNameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditPhotoFagment newInstance(String param1, String param2) {
        EditPhotoFagment fragment = new EditPhotoFagment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditPhotoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        if(bytes != null){
            Bitmap decodedByte = BitmapFactory.decodeByteArray(EditActivity.bytes, 0, EditActivity.bytes.length);
            binding.photoImageview.setImageBitmap(decodedByte);
        }
        binding.photoImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, EditActivity.SELECT_PICTURE);
            }
        });
        return view ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}