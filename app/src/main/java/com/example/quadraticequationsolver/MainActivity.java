package com.example.quadraticequationsolver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText inputA, inputB, inputC;
    Button solveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        inputC = findViewById(R.id.inputC);
        solveButton = findViewById(R.id.solveButton);

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v -> {
            inputA.setText("");
            inputB.setText("");
            inputC.setText("");
        });

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strA = inputA.getText().toString().trim();
                String strB = inputB.getText().toString().trim();
                String strC = inputC.getText().toString().trim();

                if (strA.isEmpty() || strB.isEmpty() || strC.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ hệ số a, b, và c!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double a = Double.parseDouble(strA);
                    double b = Double.parseDouble(strB);
                    double c = Double.parseDouble(strC);

                    if (Math.abs(a) < 1E-10) {
                        Toast.makeText(MainActivity.this,
                                "Hệ số a quá nhỏ, phương trình có thể không phải bậc 2!",
                                Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putDouble("a", a);
                    bundle.putDouble("b", b);
                    bundle.putDouble("c", c);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Dữ liệu nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}