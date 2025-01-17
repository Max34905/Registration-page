package com.example.registrationpage.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.registrationpage.databinding.FragmentSuccessBinding;

public class SuccessFragment extends Fragment {
    private FragmentSuccessBinding binding;
    private RegistrationViewModel model;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initializeBinding(inflater, container);
        model = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);
        setObserve();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View initializeBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentSuccessBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void setObserve() {
        model.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                String nameSurname = user.getName() + " " + user.getSurname();
                binding.nameSurnameText.setText(nameSurname);
                if (user.getParentName() != null) {
                    binding.parentCard.setVisibility(View.VISIBLE);
                    binding.parentNameText.setText(user.getParentName());
                }
            }
        });
    }
}
