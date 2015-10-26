package com.carclean.carclean;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
import com.carclean.util.DialogsUtil;
import com.carclean.util.Info;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */

public class Principal extends ActionBarActivity {

    private Button btAdicionar;
    private ListView lvVeiculos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        getSupportActionBar().setTitle(getString(R.string.meusVeiculos));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkgreen)));

        lvVeiculos = (ListView) findViewById(R.id.lvVeiculos);

        btAdicionar = (Button) findViewById(R.id.btAdicionar);

        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Principal.this, AdicionarVeiculos.class);
                startActivity(it);
            }
        });
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
        VeiculosAdapter adapter = new VeiculosAdapter(VeiculoDAO.listar(), this);
        lvVeiculos.setAdapter(adapter);
    }



}
