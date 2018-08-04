package com.example.talita.horizonchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.talita.horizonchallenge.Model.Pessoa;
import com.example.talita.horizonchallenge.Repository.PessoaRepository;

public class VisualizarDadosActivity extends AppCompatActivity {

    private LinearLayout linearVisible;
    private TextView nomeView;
    private TextView dataNasView;
    private boolean show=false;
    private TextView cpfView;
    private TextView sexoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_dados);

        linearVisible=(LinearLayout)findViewById(R.id.linearVisible);
        nomeView=(TextView)findViewById(R.id.nomeView);
        dataNasView=(TextView) findViewById(R.id.dataNasView);
        sexoView = (TextView)findViewById(R.id.sexoView);
        cpfView=(TextView)findViewById(R.id.cpfView);

        Intent intent = getIntent();
        nomeView.setText(intent.getStringExtra("nome"));
        dataNasView.setText(intent.getStringExtra("dataNascimento"));
    }

    //EXIBIR OU ESCONDER LINEARLAYOUT
    public void clickBtnExibir(View view) {
        if(show){
            linearVisible.setVisibility(View.INVISIBLE);
            show = false;
        }else{
            linearVisible.setVisibility(View.VISIBLE);
            show = true;
            recuperar_onClick();
        }
    }

    //RECUPERAR OS DADOS NO BANCO DE DADOS
    protected  void recuperar_onClick(){

        Intent intent = getIntent();
        String name =intent.getStringExtra("nome");

        PessoaRepository pessoaRepository =  new PessoaRepository(this);
        Pessoa pessoa = pessoaRepository.consulta(name);

        System.out.println("cpf"+pessoa.getCpf());
        System.out.println("nome"+pessoa.getSexo());
        cpfView.setText(pessoa.getCpf());
        sexoView.setText(pessoa.getSexo());
    }
}
