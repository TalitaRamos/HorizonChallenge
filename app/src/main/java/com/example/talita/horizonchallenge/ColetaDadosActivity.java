package com.example.talita.horizonchallenge;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.effect.Effect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talita.horizonchallenge.Model.Pessoa;

import java.util.Calendar;

public class ColetaDadosActivity extends AppCompatActivity  implements TextView.OnClickListener{
    private EditText nome;
    private EditText cpf;
    private TextView dataView;
    private Button buttonColeta;
    private RadioGroup radioGroup;
    private RadioButton radioButton4;
    static final int DATE_DIALOG_ID = 0;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleta_dados);

        nome = (EditText)findViewById(R.id.nome);
        cpf = (EditText)findViewById(R.id.cpf);
        dataView=(TextView)findViewById(R.id.dataView);
        buttonColeta=(Button) findViewById(R.id.buttonColeta);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioButton4=(RadioButton)findViewById(R.id.radioButton4);

        dataView.setOnClickListener(this);

        buttonColeta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            if(validarCampos()!=false){
                pessoa = new Pessoa();
                pessoa.setNome(nome.getText().toString().trim());
                pessoa.setDataNascimento(dataView.getText().toString().trim());

                Intent it = new Intent(ColetaDadosActivity.this, VisualizarDadosActivity.class);
                it.putExtra("nome",pessoa.getNome());
                it.putExtra("dataNascimento",pessoa.getDataNascimento());
                startActivity(it);
            }
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
                return new DatePickerDialog(this, mDateSetListener, ano, mes, dia);
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

    //VERIFICANDO SE OS CAMPOS FORAM PREENCHIDOS
    private boolean validarCampos(){
        boolean resultado=true;

        if (nome.getText().toString().length() == 0) {
            resultado=false;
            nome.setError("Digite seu nome!");
        }
        if(dataView.getText().toString().equals("dd/mm/aaaa")){
            resultado=false;
            dataView.setError("Insira uma data!");
        }else{
            dataView.setError(null);
        }
        if(cpf.getText().toString().length() == 0){
            resultado=false;
            cpf.setError("Digite seu cpf!");
        }

        int selectedItemID =radioGroup.getCheckedRadioButtonId();
        if(selectedItemID<=0){
            resultado=false;
            radioButton4.setError("Selecione uma opção!");
        }else{
            radioButton4.setError(null);
        }

        return resultado;
    }

}
