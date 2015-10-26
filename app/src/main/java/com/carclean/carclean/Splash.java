package com.carclean.carclean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.carclean.util.Teste;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */

public class Splash extends Activity {

    /**
     *  Essa classe serve basicamente de apresentção no começo do app.
     *  Ela tem apenas um timer de 3 segundos pra chamar a tela principal de login.
     */


    private Timer timerAtual = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //Para Teste
        Teste.preencherDadosIniciaisTESTE();
        //-----

        // Ativa o timer pra enviar pra tela de login.
        ativaTimer();
    }

    // Metodo que ativa o timer.
    private void ativaTimer(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        Intent it = new Intent(Splash.this, Login.class);
                        startActivity(it);
                        finish();
                    }
                });
            }};

        timerAtual.schedule(task, 3000);
    }


}
