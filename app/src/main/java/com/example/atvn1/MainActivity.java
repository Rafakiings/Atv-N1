package com.example.atvn1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnP = (Button) findViewById(R.id.btnPersonagens);
        Button btnA = (Button) findViewById(R.id.btnAnotacoes);

    }

    public void startPersonagens(View view){
        startActivity(new Intent (getBaseContext(), Personagem.class));
    }

    public void startAnotacoes(View view){
        startActivity(new Intent (getBaseContext(), Anotacoes.class));
    }
}