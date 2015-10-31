package com.carclean.carclean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carclean.beans.Usuario;
import com.carclean.dao.UsuarioDAO;
import com.carclean.util.Info;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */
public class Login extends Activity {

    EditText login;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);
        Button btEntrar = (Button) findViewById(R.id.btEntrar);
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarUsuario();
            }
        });

    }

    public void verificarUsuario(){

        Usuario usuario = new Usuario(login.getText().toString(),senha.getText().toString(),"email");
        usuario.setEmail(login.getText().toString());
        usuario.setSenha(senha.getText().toString());

        if(UsuarioDAO.logar(usuario)){
            trocarTela();
        }else{
            Toast.makeText(Login.this,"Email ou senha incorretos!",Toast.LENGTH_SHORT).show();
        }
    }

    public void trocarTela(){
            if (Info.getInstancia().getUsuario().isAdmin()) {
                Intent novaTela = new Intent(Login.this,PrincipalAdmin.class);
                startActivity(novaTela);
                finish();
            } else {
                Intent novaTela = new Intent(Login.this,Principal.class);
                startActivity(novaTela);
                finish();
            }
    }


}
