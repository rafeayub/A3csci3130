package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText numField, nameField, typeField, addressField, provinceField;
    Business receivedBusinessInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        numField = (EditText) findViewById(R.id.num);
        nameField = (EditText) findViewById(R.id.name);
        typeField = (EditText) findViewById(R.id.type);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedBusinessInfo != null){
            numField.setText(receivedBusinessInfo.num);
            nameField.setText(receivedBusinessInfo.name);
            typeField.setText(receivedBusinessInfo.type);
            addressField.setText(receivedBusinessInfo.address);
            provinceField.setText(receivedBusinessInfo.province);
        }
    }

    public void updateBusiness(View v){
        String uid= receivedBusinessInfo.uid;
        String num =  numField.getText().toString();
        String name = nameField.getText().toString();
        String type = typeField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();

        Business business = new Business(uid, num, name, type, address, province);

        appState.firebaseReference.child(receivedBusinessInfo.uid).setValue(receivedBusinessInfo);

        finish();
    }

    public void eraseBusiness(View v)
    {
        appState.firebaseReference.child(receivedBusinessInfo.uid).removeValue();

        finish();
    }
}
