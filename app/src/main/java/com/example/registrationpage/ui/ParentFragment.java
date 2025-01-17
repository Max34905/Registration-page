package com.example.registrationpage.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.registrationpage.utils.CheckInputValidity;
import com.example.registrationpage.R;
import com.example.registrationpage.databinding.FragmentParentBinding;


public class ParentFragment extends Fragment {
    private FragmentParentBinding binding;
    private RegistrationViewModel model;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentParentBinding.inflate(inflater, container, false);
        model = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.navHost);
        setupContinueButton();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupContinueButton() {
        binding.parentContinueButton.setOnClickListener(this::onClickContinueParentSignUp);
    }

    private void onClickContinueParentSignUp(View view) {
        String parentInput = binding.parentNameInput.getText().toString();
        if (isParentInputValid(parentInput)) {
            model.setUserParentName(parentInput);
            navController.navigate(R.id.action_parentFragment_to_successFragment);
        } else {
            handleParentInputErrors(parentInput);
        }
    }

    private boolean isParentInputValid(String parentInput) {
        return !CheckInputValidity.isParentFieldEmpty(parentInput) &&
                CheckInputValidity.isParentNameValid(parentInput);
    }

    private void handleParentInputErrors(String parentInput) {
        if (CheckInputValidity.isParentFieldEmpty(parentInput)) {
            binding.parentNameInputLayout.setError(getString(R.string.empty_field_error));
        } else if (!CheckInputValidity.isParentNameValid(parentInput)) {
            binding.parentNameInputLayout.setError(getString(R.string.invalid_name_error));
        }
    }
}
