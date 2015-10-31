package com.carclean.carclean;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carclean.beans.Usuario;
import com.carclean.beans.Veiculo;
import com.carclean.dao.VeiculoDAO;
import com.carclean.dao.VeiculoService;
import com.carclean.util.Info;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */

public class AdicionarVeiculos extends Activity {

    private String marca, modelo, cor, placa;
    private VeiculoService service = new VeiculoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionarveiculos);
    }

    public void salvar(View v){
        marca = ((EditText) findViewById(R.id.etMarca)).getText().toString();
        modelo = ((EditText) findViewById(R.id.etModelo)).getText().toString();
        cor = ((EditText) findViewById(R.id.etCor)).getText().toString();
        placa = ((EditText) findViewById(R.id.etPlaca)).getText().toString();

        new EnviarMeusProdutosTask().execute();
    }
    private class EnviarMeusProdutosTask extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(AdicionarVeiculos.this);
            dialog.setMessage("Salvando");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ((EditText) findViewById(R.id.etMarca)).setText("");
            ((EditText) findViewById(R.id.etModelo)).setText("");
            ((EditText) findViewById(R.id.etCor)).setText("");
            ((EditText) findViewById(R.id.etPlaca)).setText("");
            Toast.makeText(AdicionarVeiculos.this, "Veículo adicionado com sucesso!", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }

        @Override
        protected Void doInBackground(String... params) {
            service.post(new Veiculo(marca,modelo,cor,placa));
            return null;
        }
    }


}
