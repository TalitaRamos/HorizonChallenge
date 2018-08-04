package com.example.talita.horizonchallenge.Repository;

import android.content.ContentValues;
import android.content.Context;

import com.example.talita.horizonchallenge.Model.Pessoa;
import com.example.talita.horizonchallenge.Uteis.DatabaseUtil;

public class PessoaRepository {
    DatabaseUtil databaseUtil;

    public PessoaRepository(Context context) {
        databaseUtil = new DatabaseUtil(context);
    }

    //SALVAR UM REISTRO NO BANCO DE DADOS
    public void salvar(Pessoa pessoa){
        ContentValues contentValues = new ContentValues();

        contentValues.put("cpf", pessoa.getCpf());
        contentValues.put("sexo", pessoa.getSexo());

        //INSERT
        databaseUtil.getConexaoDatabase().insert("tb_pessoa",null,contentValues);
    }
}
