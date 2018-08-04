package com.example.talita.horizonchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.talita.horizonchallenge.Model.Pessoa;

public class VisualizarDadosActivity extends AppCompatActivity {

    private LinearLayout linearVisible;
    private Button buttonExibir;
    private TextView nomeView;
    private TextView dataNasView;
    private boolean show=false;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_dados);

        linearVisible=(LinearLayout)findViewById(R.id.linearVisible);
        buttonExibir=(Button)findViewById(R.id.buttonExibir);
        nomeView=(TextView)findViewById(R.id.nomeView);
        dataNasView=(TextView) findViewById(R.id.dataNasView);

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
        }
    }
}
