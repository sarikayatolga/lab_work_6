package com.example.vize_sinav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText musteriAd;
    RadioGroup cinsiyetRadioGroup;
    RadioButton erkekRB, kadinRB;
    CheckBox mzkCB, sprCB, ktpCB;
    Spinner yasSP;
    Button profilBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        musteriAd = findViewById(R.id.etAd);
        cinsiyetRadioGroup = findViewById(R.id.cinsiyetRadioGroup);
        erkekRB = findViewById(R.id.erkekRB);
        kadinRB = findViewById(R.id.kadinRB);
        mzkCB = findViewById(R.id.mzkCB);
        sprCB = findViewById(R.id.sprCB);
        ktpCB = findViewById(R.id.ktpCB);
        yasSP = findViewById(R.id.YasSpinner);
        profilBtn = findViewById(R.id.profilBtn);

        ArrayList<String> yasAraliklari = new ArrayList<>();
        yasAraliklari.add("18-24");
        yasAraliklari.add("25-34");
        yasAraliklari.add("35-44");
        yasAraliklari.add("45-54");
        yasAraliklari.add("55+");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yasAraliklari);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yasSP.setAdapter(spinnerAdapter);

        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfilOzetActivity.class);

                String adSoyad = musteriAd.getText().toString();
                if (adSoyad.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Kullanıcı adını girin!", Toast.LENGTH_SHORT).show();
                }

                String seciliCinsiyet = "";
                int seciliCinsiyetId = cinsiyetRadioGroup.getCheckedRadioButtonId();
                if (seciliCinsiyetId == R.id.erkekRB) {
                    seciliCinsiyet = "Erkek";
                } else if (seciliCinsiyetId == R.id.kadinRB) {
                    seciliCinsiyet = "Kadın";
                }

                if (seciliCinsiyet.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Cinsiyeti seçin!", Toast.LENGTH_SHORT).show();
                }

                StringBuilder hobiler = new StringBuilder();
                if (mzkCB.isChecked()) {
                    hobiler.append(mzkCB.getText().toString()).append(" ");
                }
                if (sprCB.isChecked()) {
                    hobiler.append(sprCB.getText().toString()).append(" ");
                }
                if (ktpCB.isChecked()) {
                    hobiler.append(ktpCB.getText().toString()).append(" ");
                }


                String seciliYas = "";
                if (yasSP.getSelectedItem() != null) {
                    seciliYas = yasSP.getSelectedItem().toString();
                }

                intent.putExtra("adSoyad", adSoyad);
                intent.putExtra("cinsiyet", seciliCinsiyet);
                intent.putExtra("hobi", hobiler.toString().trim());
                intent.putExtra("yas", seciliYas);

                startActivity(intent);
            }
        });
    }
}
