package com.earlynetworks.modernandroid.room_test_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.earlynetworks.modernandroid.R;

import java.util.List;

public class RoomJavaActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_java);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        findViewById(R.id.add_button).setOnClickListener(v -> {
            viewModel.insert(new Todo(mTodoEditText.getText().toString()));
        });
    }
}