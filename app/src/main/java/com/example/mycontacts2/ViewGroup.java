package com.example.mycontacts2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class ViewGroup extends AppCompatActivity {

    // decalre a dbHandler
    DBHandler dbHandler;

    // declare Intnent
    Intent intent;

    // declare editTexts
    EditText nameEditText;
    EditText emailEditText;
    EditText phoneEditText;

    // declare a bundle and a long used to get and store the data sent from
    // the main activity
    Bundle bundle;
    long id;
    long listId;

    // declare Strings to store the shopping clist items name price quantity
    String name;
    String email;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
}