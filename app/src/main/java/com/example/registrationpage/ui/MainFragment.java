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
import com.example.registrationpage.databinding.FragmentMainBinding;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private RegistrationViewModel model;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        model = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.navHost);
        setupSignUpButton();
        setOnClickListenerForPasswordInput();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupSignUpButton() {
        binding.signUpButton.setOnClickListener(this::onClickContinueSignUp);
    }

    private void setOnClickListenerForPasswordInput() {
        binding.passwordInput.setOnClickListener(view -> binding.passwordInputLayout.setError(null));
    }

    public void onClickContinueSignUp(View view) {
        ArrayList<String> inputArray = collectInputData();

        if (isInputValid(inputArray)) {
            performTransaction(inputArray);
        } else {
            handleInputErrors(inputArray);
        }
    }

    private ArrayList<String> collectInputData() {
        ArrayList<String> inputArray = new ArrayList<>();
        inputArray.add(binding.nameInput.getText().toString());
        inputArray.add(binding.surnameInput.getText().toString());
        inputArray.add(binding.passwordInput.getText().toString());
        return inputArray;
    }

    private boolean isInputValid(ArrayList<String> inputArray) {
        return !CheckInputValidity.areRegistrationFieldsEmpty(inputArray) &&
                CheckInputValidity.isPasswordReliable(inputArray.get(2)) &&
                CheckInputValidity.areUsersNameAndSurnameValid(inputArray);
    }

    private void performTransaction(ArrayList<String> inputArray) {
        int action;

        if (binding.adultSwitch.isChecked()) {
            action = R.id.action_mainFragment_to_successFragment;
        } else {
            action = R.id.action_mainFragment_to_parentFragment;
        }
        String name = inputArray.get(0);
        String surname = inputArray.get(1);
        String password = inputArray.get(2);
        model.registerUser(name, surname, password);

        navController.navigate(action);
    }

    private void handleInputErrors(ArrayList<String> inputArray) {
        if (CheckInputValidity.areRegistrationFieldsEmpty(inputArray)) {
            binding.nameInputLayout.setError(getString(R.string.empty_field_error));
            binding.surnameInputLayout.setError(getString(R.string.empty_field_error));
            binding.passwordInputLayout.setError(getString(R.string.empty_field_error));
        } else if (!CheckInputValidity.areUsersNameAndSurnameValid(inputArray)) {
            if (!CheckInputValidity.isInputAlphabetical(binding.nameInput.getText().toString())) {
                binding.nameInputLayout.setError(getString(R.string.invalid_name_error));
            } else {
                binding.nameInputLayout.setError(null);
            }
            if (!CheckInputValidity.isInputAlphabetical(binding.surnameInput.getText().toString())) {
                binding.surnameInputLayout.setError(getString(R.string.invalid_name_error));
            } else {
                binding.surnameInputLayout.setError(null);
            }
            binding.passwordInputLayout.setError(null);
        } else if (!CheckInputValidity.isPasswordReliable(inputArray.get(2))) {
            binding.nameInputLayout.setError(null);
            binding.surnameInputLayout.setError(null);
            binding.passwordInputLayout.setError(getString(R.string.unreliable_password_error));
        }
    }
}
