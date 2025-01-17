package com.example.registrationpage.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.registrationpage.model.User;

public class RegistrationViewModel extends ViewModel {
    private MutableLiveData<User> userMutableLiveData;

    public RegistrationViewModel() {
        User defaultUser = new User("DefaultName", "DefaultSurname", "DefaultPassword");
        userMutableLiveData = new MutableLiveData<>(defaultUser);
    }

    public MutableLiveData<User> getUser() {
        return userMutableLiveData;
    }

    public void registerUser(String name, String surname, String password) {
        User user = new User(name, surname, password);
        userMutableLiveData.setValue(user);
    }

    public void setUserParentName(String parentName) {
        User user = userMutableLiveData.getValue();
        user.setParentName(parentName);
        userMutableLiveData.setValue(user);
    }
}
