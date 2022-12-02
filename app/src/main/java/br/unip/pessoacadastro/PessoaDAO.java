package br.unip.pessoacadastro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public PessoaDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }
    public long inserir(Pessoa pessoa){
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("cpf", pessoa.getCpf());
        values.put("logradouro", pessoa.getLogradouro());
        values.put("numero", pessoa.getNumero());
        values.put("cep", pessoa.getCep());
        values.put("bairro", pessoa.getBairro());
        values.put("cidade", pessoa.getCidade());
        values.put("estado", pessoa.getEstado());
        values.put("telefone", pessoa.getTelefone());
        values.put("ddd", pessoa.getDdd());
        values.put("tipotelefone", pessoa.getTipotelefone());
        return banco.insert("pessoa", null, values);
    }

    public List<Pessoa> obterTodos() {
        List<Pessoa> pessoas = new ArrayList<>();
        Cursor cursor = banco.query("pessoa", new String[]{"id", "nome", "cpf", "logradouro", "numero", "cep", "bairro", "cidade", "estado", "telefone", "ddd", "tipotelefone"}, null, null, null, null, null);
        while(cursor.moveToNext()){
            Pessoa a = new Pessoa();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getInt(2));
            a.setLogradouro(cursor.getString(3));
            a.setNumero(cursor.getInt(4));
            a.setCep(cursor.getInt(5));
            a.setBairro(cursor.getString(6));
            a.setCidade(cursor.getString(7));
            a.setEstado(cursor.getString(8));
            a.setTelefone(cursor.getInt(9));
            a.setDdd(cursor.getInt(10));
            a.setTipotelefone(cursor.getString(11));
            pessoas.add(a);
        }
        return pessoas;
    }

    public void excluir(Pessoa a) {
        banco.delete("pessoa", "id = ?", new String[]{a.getId().toString()});
    }

    public void atualizar (Pessoa pessoa) {
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("cpf", pessoa.getCpf());
        values.put("logradouro", pessoa.getLogradouro());
        values.put("numero", pessoa.getNumero());
        values.put("cep", pessoa.getCep());
        values.put("bairro", pessoa.getBairro());
        values.put("cidade", pessoa.getCidade());
        values.put("estado", pessoa.getEstado());
        values.put("telefone", pessoa.getTelefone());
        values.put("ddd", pessoa.getDdd());
        values.put("tipotelefone", pessoa.getTipotelefone());
        banco.update("pessoa", values,"id = ?", new String[]{pessoa.getId().toString()});
    }
}
