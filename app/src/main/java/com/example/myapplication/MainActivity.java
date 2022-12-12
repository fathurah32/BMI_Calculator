package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
     EditText HeightInput;
     EditText WeightInput;
    private TextView result;
    private static final String FILE_NAME ="example.txt";
    private static final String FILE_NAME1 ="example1.txt";

    private Button buttonInfo;
    private Button buttonGithub1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonInfo = (Button) findViewById(R.id.buttonInfo);
        HeightInput = (EditText) findViewById(R.id.HeightInput);
        WeightInput = (EditText) findViewById(R.id.WeightInput);
        result = (TextView) findViewById(R.id.result);


    }

    public void openInfo(View v){
        Intent intent =  new Intent(this,info.class);
        this.startActivity(intent);}

    public void calculateBMI(View v) {
        String heightStr = HeightInput.getText().toString();
        String weightStr = WeightInput.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";
        String riskLabel = "";

        if (Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
            riskLabel= getString(R.string.riskUnderweight);
        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
            riskLabel= getString(R.string.riskNormal);
        } else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
            riskLabel= getString(R.string.riskOverweight);
        } else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.moderately_obese);
            riskLabel= getString(R.string.riskModerately_obese);
        } else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.severely_obese);
            riskLabel= getString(R.string.riskSeverely_obese);
        } else {
            bmiLabel = getString(R.string.very_severely_obese);
            riskLabel= getString(R.string.riskVery_severely_obese);

        }

        bmiLabel = "BMI= " + bmi + "\n\n" + bmiLabel+ "\n\n"+riskLabel;
        result.setText(bmiLabel);
    }

    public void Save(View v) {
        String weight = WeightInput.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(weight.getBytes());
            WeightInput.getText().clear();
            Toast.makeText(this, "Saved to" + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void Save1(View v) {
        String height = HeightInput.getText().toString();
        FileOutputStream fos1 = null;

        try {
            fos1 = openFileOutput(FILE_NAME1, MODE_PRIVATE);
            fos1.write(height.getBytes());
            HeightInput.getText().clear();
            Toast.makeText(this, "Saved to" + getFilesDir() + "/" + FILE_NAME1,
                    Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos1 != null) {
                try {
                    fos1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void Load(View v)
    {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            WeightInput.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void Load1(View v)
    {
        FileInputStream fis1 = null;
        try {
            fis1 = openFileInput(FILE_NAME1);
            InputStreamReader isr1 = new InputStreamReader(fis1);
            BufferedReader br = new BufferedReader(isr1);
            StringBuilder sb = new StringBuilder();
            String text1;

            while ((text1 = br.readLine()) != null) {
                sb.append(text1).append("\n");
            }
            HeightInput.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis1 != null) {
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




   }