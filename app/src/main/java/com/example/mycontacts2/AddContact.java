package com.example.mycontacts2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddContact extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    // declare Intent
    Intent intent;

    // declare EditTexts
    EditText nameEditText;
    EditText emailEditText;
    EditText phoneEditText;

    // declare DBHandler
    DBHandler dbHandler;

    // decleare spinner
    Spinner groupSpinner;

    // declare a sting to store quantiiyt
    String group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize the EditTexts
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        // initialize Spinners
        groupSpinner = (Spinner) findViewById(R.id.groupSpinner);

        // initialize DBHandler
        dbHandler = new DBHandler(this, null);

        // initialize arrayadapter with vaalues in quantities stringarray
        // and stylize it with style defined by simple_spinner_item
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.group, android.R.layout.simple_spinner_item);

        //further stylize the arrayadapter
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // set the arrayadapter on the spinner
        groupSpinner.setAdapter(adapter);

        // register an onitemselectedlistern to Spinner
        groupSpinner.setOnItemSelectedListener(this);
    }



    /**
     * This method further initializes the Action Bar of the activity
     * It gets the code (XML) in the menu resource file and incorporates it into the Action Bar
     * @param menu menu resource file for the activity
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    /**
     * this method gets called when a menu item in the overflow menu is selected and it
     * controls what happens when the menu item is selected.
     * @param item selected menu item in the overflow menu
     * @return true if menu is selected, else false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get the id of menu item selected
        switch (item.getItemId()){
            case R.id.action_home :
                // initialize and Intent for the MainActivity and start it
                intent = new Intent(this, com.example.mycontacts2.MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_add_contact :
                // initialize and Intent for the MainActivity and start it
                intent = new Intent(this, AddContact.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method gets called when the add button in the Action Bar gets clicked
     * @param menuItem add list menu item
     */
    public void addContact(MenuItem menuItem){
        // get data input in EditTexts and store it in Strings
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();


        // trim strings and see if they're equal to empty string
        if (name.trim().equals("") || email.trim().equals("") || phone.trim().equals("") || group.trim().equals("")){
            // display "Please enter a name, store, and date!" toast
            Toast.makeText(this, "Please enter a name, email, phone, and group!", Toast.LENGTH_LONG).show();
        } else {
            // add shopping list into database
            dbHandler.addContact(name, email, phone, group);

            // display Shopping list created toast
            Toast.makeText(this, "Contact Created!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method gets called when an item in the Spinner is selected
     * @param parent Spinner AdapterView
     * @param view AddItem view
     * @param position position of item that was selected in the spinner
     * @param id database id of item that was selected in spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        group = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
