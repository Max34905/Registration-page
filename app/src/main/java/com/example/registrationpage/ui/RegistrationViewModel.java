package com.example.registrationpage.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.registrationpage.model.User;

public class RegistrationViewModel extends ViewModel {
    private MutableLiveData<User> userMutableLiveData;

    public RegistrationViewModel() {
        User defaultUser = new User("DefaultName", "DefaultSurname", "DefaultPassword", null);
        userMutableLiveData = new MutableLiveData<>(defaultUser);
    }

    public MutableLiveData<User> getUser() {
        return userMutableLiveData;
    }

    public void registerUser(String name, String surname, String password, String parentName) {
        User user = new User(name, surname, password, parentName);
        userMutableLiveData.setValue(user);
    }
}
