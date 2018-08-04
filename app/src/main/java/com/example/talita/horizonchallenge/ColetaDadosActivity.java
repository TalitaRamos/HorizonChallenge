package com.example.talita.horizonchallenge;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ColetaDadosActivity extends AppCompatActivity  implements TextView.OnClickListener{
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dataView;
    private int dia,mes,ano;
    private Button buttonColeta;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleta_dados);

        dataView=(TextView)findViewById(R.id.dataView);
        buttonColeta=(Button) findViewById(R.id.buttonColeta);
        calendar= Calendar.getInstance();

        dataView.setOnClickListener(this);

        buttonColeta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(ColetaDadosActivity.this, VisualizarDadosActivity.class);
                startActivity(it);
            }
        });
    }

    //****SELECIONADOR DE DATA
    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, ano, mes,
                        dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
        new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String data = String.valueOf(dayOfMonth) + " /"
                    + String.valueOf(monthOfYear+1) + " /" + String.valueOf(year);
                    dataView.setText(data);
                    /*Toast.makeText(ColetaDadosActivity.this,
                            "DATA = " + data, Toast.LENGTH_SHORT)
                            .show();*/
                }
            };

    @Override
    public void onClick(View v) {
        if (v==dataView)
            showDialog(DATE_DIALOG_ID);
    }
    //**** FIM SELECIONADOR DE DATA
}
