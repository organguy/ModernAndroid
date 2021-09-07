package com.earlynetworks.modernandroid.room_test_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.earlynetworks.modernandroid.R;
import com.earlynetworks.modernandroid.databinding.ActivityRoomJavaBinding;

import java.util.List;

public class RoomJavaActivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRoomJavaBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_room_java);
        binding.setLifecycleOwner(this);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);
    }
}