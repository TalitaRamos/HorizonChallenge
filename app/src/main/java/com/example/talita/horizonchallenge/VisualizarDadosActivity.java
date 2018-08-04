package com.example.talita.horizonchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class VisualizarDadosActivity extends AppCompatActivity {

    private LinearLayout linearVisible;
    private Button buttonExibir;
    private boolean show=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_dados);

        linearVisible=(LinearLayout)findViewById(R.id.linearVisible);
        buttonExibir=(Button)findViewById(R.id.buttonExibir);
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
