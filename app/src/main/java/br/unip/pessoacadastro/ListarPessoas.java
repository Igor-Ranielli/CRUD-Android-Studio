package br.unip.pessoacadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ListarPessoas extends AppCompatActivity {

    private ListView listview;
    private PessoaDAO dao;
    private List<Pessoa> pessoas;
    private List<Pessoa> pessoasFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoas);

        listview = findViewById(R.id.lista_pessoas);
        dao = new PessoaDAO(this);
        pessoas = dao.obterTodos();
        pessoasFiltrados.addAll(pessoas);
        ArrayAdapter<Pessoa> adaptador = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoasFiltrados);
        listview.setAdapter(adaptador);
        registerForContextMenu(listview);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraPessoa(s);
                return false;
            }

            public void procuraPessoa(String nome){
                pessoasFiltrados.clear();
                for(Pessoa a : pessoas) {
                    if(a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                        pessoasFiltrados.add(a);
                    }
                }
                listview.invalidateViews();
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
        (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Pessoa pessoaExcluir = pessoasFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o cadastro?")
                .setNegativeButton("Não", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pessoasFiltrados.remove(pessoaExcluir);
                        pessoas.remove(pessoaExcluir);
                        dao.excluir(pessoaExcluir);
                        listview.invalidateViews();
                    }
                }).create();
            dialog.show();

    }

    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Pessoa pessoaAtualizar = pessoasFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("pessoa", pessoaAtualizar);
        startActivity(it);
    }

    @Override
    public void onResume() {
        super.onResume();
        pessoas = dao.obterTodos();
        pessoasFiltrados.clear();
        pessoasFiltrados.addAll(pessoas);
        listview.invalidateViews();
    }

}