package com.carclean.carclean;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.carclean.util.Info;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */

public class Perfil extends Activity {

    private TextView tvNome, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvEmail = (TextView) findViewById(R.id.tvEmail);

        tvNome.setText(Info.getInstancia().getUsuario().getNome());
        tvEmail.setText(Info.getInstancia().getUsuario().getEmail());

    }


}
