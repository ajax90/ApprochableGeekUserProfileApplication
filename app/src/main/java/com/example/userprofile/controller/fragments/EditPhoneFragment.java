package com.example.userprofile.controller.fragments;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.userprofile.controller.activities.MainActivity;
import com.example.userprofile.databinding.FragmentEditPhoneBinding;
import com.example.userprofile.modal.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditPhoneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class EditPhoneFragment extends Fragment {

    private FragmentEditPhoneBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = MainActivity.OBJECT_KEY;

    // TODO: Rename and change types of parameters
    private User user;

    public EditPhoneFragment() {
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
    public static EditPhoneFragment newInstance(String param1, String param2) {
        EditPhoneFragment fragment = new EditPhoneFragment();
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
        binding = FragmentEditPhoneBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        binding.includeLayout.editLabelTv.setText("Phone");
        binding.includeLayout.editValueTv.setText(MainActivity.toPhoneNumberFormat(user.getPhone()));
        binding.includeLayout.editValueTv.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}