package com.example.vize_sinav;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilOzetActivity extends AppCompatActivity {
    TextView tvOzet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilozet);

        tvOzet = findViewById(R.id.tvOzet);

        Intent intent = getIntent();

        String adSoyad = intent.getStringExtra("adSoyad");
        String cinsiyet = intent.getStringExtra("cinsiyet");
        String hobiler = intent.getStringExtra("hobi");
        String yas = intent.getStringExtra("yas");

        if (hobiler == null || hobiler.trim().isEmpty()) {
            hobiler = "Belirtilmedi";
        }

        String ozetMetni = "Ad Soyad: " + adSoyad + "\n" +
                "Cinsiyet: " + cinsiyet + "\n" +
                "Hobiler: " + hobiler + "\n" +
                "Yaş Aralığı: " + yas;

        tvOzet.setText(ozetMetni);
    }
}
