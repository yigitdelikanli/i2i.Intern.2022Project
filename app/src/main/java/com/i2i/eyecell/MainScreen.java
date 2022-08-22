package com.i2i.eyecell;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class MainScreen extends AppCompatActivity {
    int mobileData = 1000, dataUsage = 326;
    int remainingMobileData = mobileData - dataUsage;
    int sms = 5000, smsUsage = 300;
    int remainingSms = sms - smsUsage;
    int voice = 1000,voiceUsage=300;
    int remainingVoice = voice - voiceUsage;
    private Button buttonQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        PieChart pieChartData = (PieChart) findViewById(R.id.piechartData);
        pieChartData.addPieSlice(new PieModel("Kalan Mobil Veri",remainingMobileData,Color.parseColor("#00ff5f")));
        pieChartData.addPieSlice(new PieModel("Kullanılan Mobil Veri",dataUsage,Color.parseColor("#ff0000")));

        PieChart pieChartVoice = (PieChart) findViewById(R.id.piechartVoice);
        pieChartVoice.addPieSlice(new PieModel("Kalan Dakika",remainingVoice,Color.parseColor("#00ff5f")));
        pieChartVoice.addPieSlice(new PieModel("Kullanılan Dakika",voiceUsage,Color.parseColor("#ff0000")));

        PieChart pieChartSms = (PieChart) findViewById(R.id.piechartSms);
        pieChartSms.addPieSlice(new PieModel("Kalan SMS",remainingSms,Color.parseColor("#00ff5f")));
        pieChartSms.addPieSlice(new PieModel("Kullanılan SMS",smsUsage,Color.parseColor("#ff0000")));

        buttonQuit = (Button) findViewById(R.id.buttonQuit);
        buttonQuit.setOnClickListener(view -> {
            Intent nextPage = new Intent(MainScreen.this, LoginScreen.class);
            startActivity(nextPage);
        });
    }

}
