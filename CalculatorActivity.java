package com.example.wazoople.samplecalculator;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.StringBuilder;

public class CalculatorActivity extends ActionBarActivity {

    String equation = "";
    double root;
    int[] tones = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initNumPad();
        initSettings();
        save();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }
    private void queueNote(char a) {
        StringBuilder stringBuilder = new StringBuilder();
        TextView answer = (TextView) findViewById(R.id.answer);
        stringBuilder.append(equation);
        stringBuilder.append(a);
        equation = stringBuilder.toString();
        answer.setText(equation);
        stringBuilder.setLength(0);
    }
    private void initNumPad() {
        Button zeroButton = (Button) findViewById(R.id.zero);
        Button oneButton = (Button) findViewById(R.id.one);
        Button twoButton = (Button) findViewById(R.id.two);
        Button threeButton = (Button) findViewById(R.id.three);
        Button fourButton = (Button) findViewById(R.id.four);
        Button fiveButton = (Button) findViewById(R.id.five);
        Button sixButton = (Button) findViewById(R.id.six);
        Button sevenButton = (Button) findViewById(R.id.seven);
        Button eightButton = (Button) findViewById(R.id.eight);
        Button nineButton = (Button) findViewById(R.id.nine);
        Button addButton = (Button) findViewById(R.id.addition);
        Button subButton = (Button) findViewById(R.id.subtraction);
        Button multButton = (Button) findViewById(R.id.multiplication);
        Button divButton = (Button) findViewById(R.id.division);
        Button decButton = (Button) findViewById(R.id.decimal);
        Button enterButton = (Button) findViewById(R.id.enter);
        Button openButton = (Button) findViewById(R.id.open);
        Button closeButton = (Button) findViewById(R.id.close);
        Button clearButton = (Button) findViewById(R.id.clear);
        Button saveButton = (Button) findViewById(R.id.save);

        zeroButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('0');
            }
        });
        oneButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('1');
            }
        });
        twoButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('2');

            }
        });
        threeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('3');
            }
        });
        fourButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('4');
            }
        });
        fiveButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('5');
            }
        });
        sixButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('6');
            }
        });
        sevenButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('7');
            }
        });
        eightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('8');
            }
        });
        nineButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('9');
            }
        });
        addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('+');
            }
        });
        subButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('-');
            }
        });
        multButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('*');
            }
        });
        divButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('÷');
            }
        });
        decButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('.');
            }
        });
        openButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote('(');
            }
        });
        closeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                queueNote(')');
            }
        });
        enterButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                sing();
            }
        });
        clearButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                equation = "";
                TextView answer = (TextView) findViewById(R.id.answer);
                answer.setText(equation);
            }
        });
        saveButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                save();
            }
        });
    }
    private void sing() {
        PlaySound playSound = new PlaySound();
        double dot = 1;
        double duration = .5;
        for (int i = 0; i < equation.length(); i++) {
            int in = Character.getNumericValue(equation.charAt(i));

            if (in >= 0 && in < tones.length) {
                playSound.playFreq(tones[in], dot * duration);
                dot = 1;
                duration = .5;
            } else {

                switch (equation.charAt(i)) {

                    case '.':
                        dot = 1.5;
                        break;
                    case '÷':
                        duration = .12;
                        break;
                    case '*':
                        duration = 2;
                        break;
                    case '-':
                        duration = .25;
                        break;
                    case '+':
                        duration = 1;
                        break;
                    case '0':
                        playSound.playFreq(tones[Character.getNumericValue(in)], dot * duration);
                        dot = 1;
                        duration = .5;
                        break;
                    default:
                        playSound.playFreq(200, .1);
                }
            }
        }
    }
    private void initSettings() {

        String rootNote = getSharedPreferences("Scale", Context.MODE_PRIVATE).getString("rootnote", "c");
        String scale = getSharedPreferences("Scale", Context.MODE_PRIVATE).getString("scale", "major");

        RadioButton rbMajor = (RadioButton) findViewById(R.id.radioMajor);
        RadioButton rbMinor = (RadioButton) findViewById(R.id.radioMinor);

        root = rootToHz(rootNote); // takes in note name, returns Hz

        if (scale.equalsIgnoreCase("major")) {
            rbMajor.setChecked(true);
        } else {
            rbMinor.setChecked(true);
        }
    }
    private void save() {

        EditText etRoot = (EditText) findViewById(R.id.editRoot);

        root = rootToHz(etRoot.getText().toString());

        RadioGroup rgScale = (RadioGroup) findViewById(R.id.radioGroup);

        RadioButton rbMajor = (RadioButton) findViewById(R.id.radioMajor);
        RadioButton rbMinor = (RadioButton) findViewById(R.id.radioMinor);

        if(rbMajor.isChecked()) {
            getSharedPreferences("Scale", Context.MODE_PRIVATE).edit().putString("scale", "major").commit();
            setMajor(root);
        }
        else {
            getSharedPreferences("Scale", Context.MODE_PRIVATE).edit().putString("scale", "minor").commit();
            setMinor(root);
        }
    }
    private double rootToHz(String rootNote) {
        double root;
        EditText etRoot = (EditText) findViewById(R.id.editRoot);
        switch (rootNote) {
            case "c":
            case "C":
                root = 523.25;
                etRoot.setText("C", TextView.BufferType.EDITABLE);
                break;
            case "c#":
            case "C#":
                root = 554.37;
                etRoot.setText("C#", TextView.BufferType.EDITABLE);
                break;
            case "d":
            case "D":
                root = 587.33;
                etRoot.setText("D", TextView.BufferType.EDITABLE);
                break;
            case "d#":
            case "D#":
                root = 622.25;
                etRoot.setText("D#", TextView.BufferType.EDITABLE);
                break;
            case "e":
            case "E":
                root = 329.63;
                etRoot.setText("E", TextView.BufferType.EDITABLE);
                break;
            case "f":
            case "F":
                root = 349.23;
                etRoot.setText("F", TextView.BufferType.EDITABLE);
                break;
            case "f#":
            case "F#":
                root = 369.99;
                etRoot.setText("F#", TextView.BufferType.EDITABLE);
                break;
            case "g":
            case "G":
                root = 392.00;
                etRoot.setText("G", TextView.BufferType.EDITABLE);
                break;
            case "g#":
            case "G#":
                root = 415.30;
                etRoot.setText("G#", TextView.BufferType.EDITABLE);
                break;
            case "a":
            case "A":
                root = 440.00;
                etRoot.setText("A", TextView.BufferType.EDITABLE);
                break;
            case "a#":
            case "A#":
                root = 466.16;
                etRoot.setText("A#", TextView.BufferType.EDITABLE);
                break;
            case "b":
            case "B":
                root = 493.88;
                etRoot.setText("B", TextView.BufferType.EDITABLE);
                break;
            default:
                root = 0;
                etRoot.setText("-", TextView.BufferType.EDITABLE);
        }
        return root;
    }
    private void setMajor(double root) {
        double[] major = {0, 1, 1.12245, 1.25993, 1.33484, 1.49829, 1.68179, 1.88775, 2, 2.24493};
        for(int i=0;i<tones.length;i++) {
            tones[i] = (int)(root * major[i]);
        }
    }
    private void setMinor(double root) {
        double[] minor = {0, 1, 1.12245, 1.18920, 1.33484, 1.49829, 1.58741, 1.78180, 2, 2.24493};
        for(int i=0;i<tones.length;i++) {
            tones[i] = (int) (root * minor[i]);
        }
    }
}
