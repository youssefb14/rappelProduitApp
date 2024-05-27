package com.example.tprojet.ui.configuration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class configurationViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public configurationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is configuration fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}