package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateBusinessAcitivity extends Activity {

    private Button submitButton;
    private EditText numField, nameField, typeField, addressField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        numField = (EditText) findViewById(R.id.num);
        nameField = (EditText) findViewById(R.id.name);
        typeField = (EditText) findViewById(R.id.type);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String uid = appState.firebaseReference.push().getKey();
        String num = numField.getText().toString();
        String name = nameField.getText().toString();
        String type = typeField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();

        Business business = new Business(uid, num, name, type, address, province);

        appState.firebaseReference.child(uid).setValue(business);

        finish();

    }
}
