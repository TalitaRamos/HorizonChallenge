package com.example.talita.horizonchallenge;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
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
import com.example.talita.horizonchallenge.Repository.PessoaRepository;

import java.util.Calendar;
import java.util.Locale;

public class ColetaDadosActivity extends AppCompatActivity{
    private EditText nome;
    private EditText cpf;
    private TextView dataView;
    private Button buttonColeta;
    private RadioGroup radioGroup;
    private RadioButton radioButtonMasculino;
    private RadioButton radioButtonFeminino;

    //CRIA POPUP COM O CALENDÁRIO
    DatePickerDialog datePickerDialogDataNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleta_dados);

        nome = (EditText)findViewById(R.id.nome);
        cpf = (EditText)findViewById(R.id.cpf);
        dataView=(TextView)findViewById(R.id.dataView);
        buttonColeta=(Button) findViewById(R.id.buttonColeta);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioButtonMasculino=(RadioButton)findViewById(R.id.radioButtonMasculino);
        radioButtonFeminino=(RadioButton)findViewById(R.id.radioButtonFeminino);

        // CHAMA O METODO PARA DIZER QUAL A LOCALIZAÇÃO,
        // USADO PARA TRADUZIR OS TEXTOS DO CALENDÁRIO.
        this.Localizacao();

        //CRIA EVENTO DO SELECIONADOR DE DATA
        this.criarSelecionador();

        buttonColeta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            if(validarCampos()!=false){
                Intent it = new Intent(ColetaDadosActivity.this, VisualizarDadosActivity.class);
                it.putExtra("nome",nome.getText().toString().trim());
                it.putExtra("dataNascimento",dataView.getText().toString().trim());
                salvar_onClick();
                startActivity(it);
            }
            }
        });

    }

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
            radioButtonMasculino.setError("Selecione uma opção!");
        }else{
            radioButtonMasculino.setError(null);
        }

        return resultado;
    }

    //SALVAR OS DADOS NO BANCO DE DADOS
    protected  void salvar_onClick(){
        Pessoa pessoa = new Pessoa();

        pessoa.setCpf(cpf.getText().toString().trim());
        pessoa.setNome(nome.getText().toString().trim());
        pessoa.setDataNascimento(dataView.getText().toString().trim());

        if(radioButtonFeminino.isChecked()){
            pessoa.setSexo("Feminino");
        }else{
            pessoa.setSexo("Masculino");
        }

        new PessoaRepository(this).salvar(pessoa);
        Toast.makeText(getApplicationContext(), "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
    }

    //DIZ QUAL A LOCALIZAÇÃO PARA TRADUZIR OS TEXTOS DO CALENDÁRIO.
    protected  void Localizacao(){

        Locale locale = new Locale("pt", "BR");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
    }

    protected  void criarSelecionador() {
        final Calendar calendarDataAtual = Calendar.getInstance();
        int anoAtual   = calendarDataAtual.get(Calendar.YEAR);
        int mesAtual   = calendarDataAtual.get(Calendar.MONTH);
        int diaAtual   = calendarDataAtual.get(Calendar.DAY_OF_MONTH);

        //MONTANDO O OBJETO DE DATA PARA PREENCHER O CAMPO QUANDO  FOR SELECIONADO
        //FORMATO DD/MM/YYYY

        datePickerDialogDataNascimento = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
                //FORMATANDO O MÊS COM DOIS DÍGITOS
                String mes = (String.valueOf((mesSelecionado + 1)).length() == 1 ? "0" + (mesSelecionado + 1 ): String.valueOf(mesSelecionado+1));
                dataView.setText(diaSelecionado + "/" + mes + "/" + anoSelecionado);
            }

        }, anoAtual, mesAtual, diaAtual);

        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        dataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialogDataNascimento.show();
            }
        });

        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        dataView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                datePickerDialogDataNascimento.show();
            }
        });
    }
}
