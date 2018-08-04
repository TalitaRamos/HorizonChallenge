package com.example.talita.horizonchallenge.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

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
        contentValues.put("nome", pessoa.getNome());
        contentValues.put("nascimento", pessoa.getDataNascimento());

        //INSERT
        databaseUtil.getConexaoDatabase().insert("tb_pessoa",null,contentValues);
    }

    public Pessoa consulta(String nomeP){
        Cursor cursor = databaseUtil.getConexaoDatabase().rawQuery("SELECT cpf,sexo FROM tb_pessoa WHERE nome=?",new String[]{nomeP});
        cursor.moveToFirst();

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
        pessoa.setSexo(cursor.getString(cursor.getColumnIndex("sexo")));

        return  pessoa;
    }
}
