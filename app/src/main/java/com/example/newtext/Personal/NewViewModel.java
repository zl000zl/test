package com.example.newtext.Personal;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public NewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is new fragment");
    }
    private LiveData<String> getText() {
        return mText;
    }
}