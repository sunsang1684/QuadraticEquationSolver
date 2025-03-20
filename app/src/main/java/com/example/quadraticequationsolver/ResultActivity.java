package com.example.quadraticequationsolver;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    TextView resultText;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.resultText);
        backButton = findViewById(R.id.backButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            double a = bundle.getDouble("a");
            double b = bundle.getDouble("b");
            double c = bundle.getDouble("c");

            String results = solveQuadraticEquation(a, b, c);
            resultText.setText(results);
        }

        backButton.setOnClickListener(v -> finish());
    }

    private String solveQuadraticEquation(double a, double b, double c) {
        // Format để làm tròn kết quả
        DecimalFormat df = new DecimalFormat("#.####");

        if (Math.abs(a) < 1E-10) {
            if (Math.abs(b) < 1E-10) {
                return c == 0 ?
                        "Phương trình có vô số nghiệm" :
                        "Phương trình vô nghiệm";
            } else {
                double root = -c / b;
                return "Phương trình thoái hóa thành bậc nhất:\n" +
                        "x = " + df.format(root);
            }
        }

        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            double realPart = -b / (2 * a);
            double imagPart = Math.sqrt(Math.abs(delta)) / (2 * a);

            return "Phương trình vô nghiệm thực.\n" +
                    "Có hai nghiệm phức:\n" +
                    "x₁ = " + df.format(realPart) + " + " + df.format(imagPart) + "i\n" +
                    "x₂ = " + df.format(realPart) + " - " + df.format(imagPart) + "i";

        } else if (Math.abs(delta) < 1E-10) {
            double root = -b / (2 * a);
            return "Phương trình có nghiệm kép:\n" +
                    "x = " + df.format(root);

        } else {
            double root1 = (-b + Math.sqrt(delta)) / (2 * a);
            double root2 = (-b - Math.sqrt(delta)) / (2 * a);

            return "Phương trình có hai nghiệm phân biệt:\n" +
                    "x₁ = " + df.format(root1) + "\n" +
                    "x₂ = " + df.format(root2);
        }
    }
}