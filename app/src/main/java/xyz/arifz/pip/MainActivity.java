package xyz.arifz.pip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    MaterialButton btnToPipActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnToPipActivity = findViewById(R.id.mb_to_pip);

        btnToPipActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PIPActivity.class);
            startActivity(intent);
        });
    }
}