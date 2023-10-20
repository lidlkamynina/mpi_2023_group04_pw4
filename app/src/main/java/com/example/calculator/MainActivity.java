package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button[] numericButtons = new Button[10];
    private Button[] operatorButtons = new Button[4];
    private Button equal;
    private Button clear;
    private Button MS;
    private Button MR;
    private Button MC;

    private TextView info;
    private TextView result;
    private final char[] OPERATORS = {'+', '-', '*', '/'};
    private double val1 = Double.NaN;
    private double val2;
    private char action;
    private double memoryValue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        for (int i = 0; i < 10; i++) {
            final int num = i;
            numericButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    info.setText(info.getText().toString() + num);
                }
            });
        }

        for (int i = 0; i < 4; i++) {
            final char operator = OPERATORS[i];
            operatorButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    compute();
                    action = operator;
                    result.setText(String.valueOf(val1) + operator);
                    info.setText(null);
                }
            });
        }

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                result.setText(result.getText().toString() + String.valueOf(val2) + "=" + String.valueOf(val1));
                info.setText(null);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getText().length() > 0) {
                    CharSequence name = info.getText().toString();
                    info.setText(name.subSequence(0, name.length() - 1));
                } else {
                    val1 = Double.NaN;
                    val2 = Double.NaN;
                    info.setText(null);
                    result.setText(null);
                }
            }
        });

        MS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memorySave();
            }
        });

        MR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryRead();
            }
        });

        MC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryClear();
            }
        });
    }

    private void setupUI() {
        int[] numericButtonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        int[] operatorButtonIds = {R.id.btnadd, R.id.btnsub, R.id.btnmul, R.id.btndivide};

        for (int i = 0; i < 10; i++) {
            numericButtons[i] = (Button) findViewById(numericButtonIds[i]);
        }

        for (int i = 0; i < 4; i++) {
            operatorButtons[i] = (Button) findViewById(operatorButtonIds[i]);
        }

        equal = (Button) findViewById(R.id.btnequal);
        clear = (Button) findViewById(R.id.btnclear);
        info = (TextView) findViewById(R.id.tvControl);
        result = (TextView) findViewById(R.id.tvResult);
        MS = (Button) findViewById(R.id.btnMemorySave);
        MR = (Button) findViewById(R.id.btnMemoryRead);
        MC = (Button) findViewById(R.id.btnMemoryClear);
    }

    private void memorySave() {
        memoryValue = val1;
    }

    private void memoryRead() {
        val1 = memoryValue;
        info.setText(String.valueOf(val1));
    }

    private void memoryClear() {
        memoryValue = 0.0;
        val1 = memoryValue;
        info.setText(String.valueOf(val1));
    }

    private void compute() {
        if (!Double.isNaN(val1)) {
            val2 = Double.parseDouble(info.getText().toString());
            if (action == '+') {
                val1 = val1 + val2;
            } else if (action == '-') {
                val1 = val1 - val2;
            } else if (action == '*') {
                val1 = val1 * val2;
            } else if (action == '/') {
                val1 = val1 / val2;
            }
        } else {
            val1 = Double.parseDouble(info.getText().toString());
        }
    }
}
