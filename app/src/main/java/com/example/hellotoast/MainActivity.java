package com.example.hellotoast;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hellotoast.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int count = 0;

    private static ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count", 0);
        }
        updateUi();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }

    public void show_toast(View view) {
        Toast.makeText(this, "Hello Toast", Toast.LENGTH_SHORT).show();
    }

    public void increase_count(View view) {
        count++;
        updateUi();
    }

    public void show_zero(View view) {
        count = 0;
        updateUi();
    }

    private void updateUi() {
        mainBinding.textDisplayCount.setText(String.valueOf(count));

        // زر Count: أخضر إذا العدد فردي، أزرق إذا زوجي
        int countColor = (count % 2 == 1) ? R.color.green : R.color.blue;
        mainBinding.buttonCount.setBackgroundTintList(
                ColorStateList.valueOf(ContextCompat.getColor(this, countColor)));

        // زر Zero: رمادي إذا العدد 0، غير هيك برتقالي
        int zeroColor = (count == 0) ? R.color.gray : R.color.orange;
        mainBinding.buttonZero.setBackgroundTintList(
                ColorStateList.valueOf(ContextCompat.getColor(this, zeroColor)));
    }
}