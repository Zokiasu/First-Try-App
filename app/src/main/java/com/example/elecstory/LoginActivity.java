package com.example.elecstory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.elecstory.Database.Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    TextView Pseudo, Age;
    Button Confirm;
    private Database db;

    public String generateUniqueId(String Pseudo, int Age) {
        db = new Database(this);

        int Min = 1, Max = 999999;
        int First;
        Random rand = new Random();

        First = rand.nextInt(Max - Min + 1) + Min;

        char lettre = Pseudo.charAt(0);

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HHmmss");
        String date_hour = sdf.format(new Date());

        Date actuelle = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(actuelle);

        String iD = date + "-" + date_hour + "-" + First + "-" + lettre + Age;
        db.close();
        return iD;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new Database(this);

        Pseudo = findViewById(R.id.Pseudo);
        Age = findViewById(R.id.Age);
        Confirm = findViewById(R.id.Confirm);

        try {
            if(db.infoFirstPlayer() != null) {
                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        } catch (Exception e) {}

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertPlayer(Pseudo.getText().toString(), Integer.parseInt(Age.getText().toString()), 0, 0, generateUniqueId(Pseudo.getText().toString(), Integer.parseInt(Age.getText().toString())));
                db.insertCity("Lampe", "Objet", 1, 1, 5, 1, R.drawable.ampoule);
                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        db.close();
    }
}
