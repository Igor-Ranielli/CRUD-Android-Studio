package br.unip.pessoacadastro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pessoa(id integer primary key autoincrement, " +
                "nome varchar(50), cpf varchar(11), logradouro varchar(50), numero varchar(5), cep varchar(8), bairro varchar(50), cidade varchar(50), estado varchar(50), telefone varchar(9), ddd varchar(3), tipotelefone varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
