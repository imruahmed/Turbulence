package io.github.imruahmed.turbulence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextAirline;
    EditText editTextFlightNum;
    EditText editTextDestination;
    EditText editTextDeparture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextAirline = (EditText) findViewById(R.id.editText_airline);
        editTextFlightNum = (EditText) findViewById(R.id.editText_flightnum);
        editTextDeparture = (EditText) findViewById(R.id.editText_departure);
        editTextDestination = (EditText) findViewById(R.id.editText_destination);

    }


    public void login(View view) {

        Intent i = new Intent(this, MainActivity.class);

        i.putExtra("NAME", editTextName.getText().toString());
        i.putExtra("AIRLINE", editTextAirline.getText().toString());
        i.putExtra("FLIGHTNUM", editTextFlightNum.getText().toString());
        i.putExtra("DEPARTURE", editTextDeparture.getText().toString());
        i.putExtra("DESTINATION", editTextDestination.getText().toString());

        startActivity(i);

    }
}
