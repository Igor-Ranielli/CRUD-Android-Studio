package br.unip.pessoacadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText nome;
    private EditText cpf;
    private EditText logradouro;
    private EditText numero;
    private EditText cep;
    private EditText bairro;
    private EditText cidade;
    private EditText estado;
    private EditText telefone;
    private EditText ddd;
    private EditText tipotelefone;
    private PessoaDAO dao;
    private Pessoa pessoa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editTextNome);
        cpf = findViewById(R.id.editTextCpf);
        logradouro = findViewById(R.id.editTextLogradouro);
        numero = findViewById(R.id.editTextNumero);
        cep = findViewById(R.id.editTextCep);
        bairro = findViewById(R.id.editTextBairro);
        cidade = findViewById(R.id.editTextCidade);
        estado = findViewById(R.id.editTextEstado);
        telefone = findViewById(R.id.editTextTelefone);
        ddd = findViewById(R.id.editTextDdd);
        tipotelefone = findViewById(R.id.editTextTipoTelefone);
        dao = new PessoaDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("pessoa")) {
            pessoa = (Pessoa) it.getSerializableExtra("pessoa");
            nome.setText(pessoa.getNome());
            cpf.setText(pessoa.getCpf());
            logradouro.setText(pessoa.getLogradouro());
            numero.setText(pessoa.getNumero());
            cep.setText(pessoa.getCep());
            bairro.setText(pessoa.getBairro());
            cidade.setText(pessoa.getCidade());
            estado.setText(pessoa.getEstado());
            telefone.setText(pessoa.getTelefone());
            ddd.setText(pessoa.getDdd());
            tipotelefone.setText(pessoa.getTipotelefone());

        }
    }

    public void salvar(View view) {

        if (pessoa == null) {
            pessoa = new Pessoa();
            pessoa.setNome(nome.getText().toString());
            pessoa.setCpf(Integer.valueOf(cpf.getText().toString()));
            pessoa.setLogradouro(logradouro.getText().toString());
            pessoa.setNumero(Integer.valueOf(numero.getText().toString()));
            pessoa.setCep(Integer.valueOf(cep.getText().toString()));
            pessoa.setBairro(bairro.getText().toString());
            pessoa.setCidade(cidade.getText().toString());
            pessoa.setEstado(estado.getText().toString());
            pessoa.setTelefone(Integer.valueOf(telefone.getText().toString()));
            pessoa.setDdd(Integer.valueOf(ddd.getText().toString()));
            pessoa.setTipotelefone(tipotelefone.getText().toString());
            long id = dao.inserir(pessoa);
            Toast.makeText(this, "Pessoa inserida com id: " + id, Toast.LENGTH_SHORT).show();
        }else {
            pessoa.setNome(nome.getText().toString());
            pessoa.setCpf(Integer.valueOf(cpf.getText().toString()));
            pessoa.setLogradouro(logradouro.getText().toString());
            pessoa.setNumero(Integer.valueOf(numero.getText().toString()));
            pessoa.setCep(Integer.valueOf(cep.getText().toString()));
            pessoa.setBairro(bairro.getText().toString());
            pessoa.setCidade(cidade.getText().toString());
            pessoa.setEstado(estado.getText().toString());
            pessoa.setTelefone(Integer.valueOf(telefone.getText().toString()));
            pessoa.setDdd(Integer.valueOf(ddd.getText().toString()));
            pessoa.setTipotelefone(tipotelefone.getText().toString());
            dao.atualizar(pessoa);
            Toast.makeText(this, "Pessoa foi atualizada", Toast.LENGTH_SHORT).show();
        }
    }
}