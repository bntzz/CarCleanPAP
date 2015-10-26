package com.carclean.carclean;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carclean.beans.Usuario;
import com.carclean.beans.Veiculo;
import com.carclean.dao.UsuarioDAO;
import com.carclean.dao.VeiculoDAO;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */
public class CadastrarUsuario extends Activity {

    private EditText etNome, etEmail, etSenha;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_usuario);


        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);

        btSalvar = (Button) findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                if (nome.trim().equals("") || email.trim().equals("") || senha.trim().equals("")) {
                    Toast.makeText(CadastrarUsuario.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                    return;
                }

                Usuario u = new Usuario();
                u.setSenha(etSenha.getEditableText().toString());
                u.setNome(etNome.getEditableText().toString());
                u.setEmail(etEmail.getEditableText().toString());
                u.setAdmin(false);

                UsuarioDAO.cadastrar(u);

                Toast.makeText(CadastrarUsuario.this, "Usuário adicionado com sucesso!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }


}

