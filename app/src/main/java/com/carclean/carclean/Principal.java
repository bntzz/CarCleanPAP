package com.carclean.carclean;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.carclean.adapter.VeiculosAdapter;
import com.carclean.beans.Veiculo;
import com.carclean.dao.VeiculoDAO;
import com.carclean.dao.VeiculoService;
import com.carclean.util.DialogsUtil;
import com.carclean.util.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */

public class Principal extends ActionBarActivity {

    private VeiculoService service = new VeiculoService();
    private VeiculosAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        getSupportActionBar().setTitle(getString(R.string.meusVeiculos));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkgreen)));

        montaLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        montaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sair) {
            DialogsUtil du = new DialogsUtil(this);
            du.setOnDialogSimNaoClicado(new DialogsUtil.OnDialogSimNaoClicado() {
                @Override
                public void onSim() {
                    Intent it = new Intent(Principal.this, Login.class);
                    startActivity(it);
                    Info.limpar();
                    finish();
                }

                @Override
                public void onNao() {
                    //Nada a fazer
                }
            });
            du.mostrarDialogSimNao("Atenção!", "Deseja realmente sair?");
            return true;
        } else if (id == R.id.action_perfil) {
            Intent it = new Intent(Principal.this, Perfil.class);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void montaLista() {
        new CarregarMeusProdutosTask().execute();
    }

    private class CarregarMeusProdutosTask extends AsyncTask<String, Void, List<Veiculo>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(Principal.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(List<Veiculo> veiculo) {
            if (veiculo != null) {
                adapter = new VeiculosAdapter((ArrayList<Veiculo>) veiculo, Principal.this);
                ((ListView) findViewById(R.id.lvVeiculos)).setAdapter(adapter);
            }
            dialog.dismiss();
        }

        @Override
        protected List<Veiculo> doInBackground(String... params) {
            return service.getAll();
        }
    }



}
