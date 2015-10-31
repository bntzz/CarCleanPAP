package com.carclean.carclean;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carclean.beans.Veiculo;
import com.carclean.dao.VeiculoDAO;
import com.carclean.util.Info;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */

public class EditarVeiculo extends Activity {

    private TextView titulo;
    private EditText etMarca, etModelo, etCor, etPlaca;
    private Button btSalvar;

    private int posicao;
    private Veiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionarveiculos);

        posicao = getIntent().getExtras().getInt("posicao");
        veiculo = Info.getInstancia().getVeiculos().get(posicao);

        titulo = (TextView) findViewById(R.id.tituloAddVeiculo);
        titulo.setText(getString(R.string.editarVeiculo));

        etMarca = (EditText) findViewById(R.id.etMarca);
        etModelo = (EditText) findViewById(R.id.etModelo);
        etCor = (EditText) findViewById(R.id.etCor);
        etPlaca = (EditText) findViewById(R.id.etPlaca);

        etPlaca.setEnabled(false);
        etCor.setImeOptions(EditorInfo.IME_ACTION_DONE);

        etMarca.setText(veiculo.getMarca());
        etModelo.setText(veiculo.getModelo());
        etCor.setText(veiculo.getCor());
        etPlaca.setText(veiculo.getPlaca());

        btSalvar = (Button) findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marca = etMarca.getText().toString();
                String modelo = etModelo.getText().toString();
                String cor = etCor.getText().toString();
                String placa = etPlaca.getText().toString();

                if (marca.trim().equals("") || modelo.trim().equals("") || cor.trim().equals("") || placa.trim().equals("")) {
                    Toast.makeText(EditarVeiculo.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(EditarVeiculo.this, "Veículo editado com sucesso!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }


}

