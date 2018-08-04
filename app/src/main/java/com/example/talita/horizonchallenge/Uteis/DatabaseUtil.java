package com.example.talita.horizonchallenge.Uteis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseUtil extends SQLiteOpenHelper{

    //NOME DA BASE DE DADOS
    private static final String NOME_BASE_DE_DADOS = "SISTEMA.db";
    private static int VERSAO_BASE_DE_DADOS= 1;

    public DatabaseUtil(Context context) {
        super(context, NOME_BASE_DE_DADOS , null, VERSAO_BASE_DE_DADOS);
    }

    /*CRIANDO A TABELA NA INICIALIZAÇÃO DA CLASSE*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder stringBuilderCreateTable = new StringBuilder();

        stringBuilderCreateTable.append("CREATE TABLE tb_pessoa (");
        stringBuilderCreateTable.append("cpf TEXT NOT NULL, ");
        stringBuilderCreateTable.append("nome TEXT NOT NULL, ");
        stringBuilderCreateTable.append("nascimento TEXT NOT NULL, ");
        stringBuilderCreateTable.append("sexo TEXT NOT NULL )");

        db.execSQL(stringBuilderCreateTable.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_pessoa");
        onCreate(db);
    }

    public SQLiteDatabase getConexaoDatabase(){
        return  this.getWritableDatabase();
    }
}
