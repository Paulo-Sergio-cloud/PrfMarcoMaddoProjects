package com.paulosergio.appgaseta.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.paulosergio.appgaseta.R;
import com.paulosergio.appgaseta.database.GasEtaDB;

public class SplashActivity extends AppCompatActivity {

    public static final int TIME_OUT_SPLASH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        comutarTelaSplash();
    }

    private void comutarTelaSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String c = "Splash";
                GasEtaDB db = new GasEtaDB(SplashActivity.this);

                Intent telaPrincipal = new Intent(
                        SplashActivity.this,
                        GasEtaActivity.class);
                startActivity(telaPrincipal);
                finish();

            }
        }, TIME_OUT_SPLASH);

    }
}