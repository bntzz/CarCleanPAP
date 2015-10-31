package com.carclean.carclean;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.carclean.beans.Usuario;
import com.carclean.dao.UsuarioService;


/**
 * Created by Fernando Benitez on 10/10/2015.
 */
public class CadastrarUsuario extends Activity {

    private String nome, email, senha;
    private UsuarioService service = new UsuarioService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_usuario);
    }
    public void salvar(View v){
        nome = ((EditText) findViewById(R.id.etNome)).getText().toString();
        senha = ((EditText) findViewById(R.id.etSenha)).getText().toString();
        email = ((EditText) findViewById(R.id.etEmail)).getText().toString();

        new EnviarMeusProdutosTask().execute();
    }
    private class EnviarMeusProdutosTask extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(CadastrarUsuario.this);
            dialog.setMessage("Salvando");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ((EditText) findViewById(R.id.etNome)).setText("");
            ((EditText) findViewById(R.id.etSenha)).setText("");
            ((EditText) findViewById(R.id.etEmail)).setText("");
            Toast.makeText(CadastrarUsuario.this, "Usuário adicionado com sucesso!", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }

        @Override
        protected Void doInBackground(String... params) {
            service.post(new Usuario(nome,senha,email));
            return null;
        }
    }


}

