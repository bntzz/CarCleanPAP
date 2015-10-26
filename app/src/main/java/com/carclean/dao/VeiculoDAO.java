package com.carclean.dao;

import com.carclean.beans.Veiculo;
import com.carclean.util.Info;
import com.carclean.util.Teste;

import java.util.ArrayList;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */

public class VeiculoDAO {

    public static ArrayList<Veiculo> listar() {
        /**
         * AQUI DEVE SER IMPLEMENTADA A CONEXÃO COM O WS
         */

        //Código de teste
            if (Info.getInstancia().getUsuario().isAdmin()) {
                Info.getInstancia().setVeiculos(Teste.geraListaVeiculosFilaTESTE());
            } else {
                Info.getInstancia().setVeiculos(Teste.retornaVeiculosUsuarioTESTE());
            }
        return Info.getInstancia().getVeiculos();
        //-------
    }

    public static void adicionar(Veiculo veiculo) {
        /**
         * AQUI DEVE SER IMPLEMENTADA A CONEXÃO COM O WS
         */

        //Código de teste
        Teste.veiculos.add(veiculo);
        Info.getInstancia().getVeiculos().add(veiculo);
        //-------
    }

    public static void excluir(Veiculo veiculo) {
        /**
         * AQUI DEVE SER IMPLEMENTADA A CONEXÃO COM O WS
         */

        //Código de teste
        for (Veiculo v : Info.getInstancia().getVeiculos()) {
            if (v.getPlaca().equals(veiculo.getPlaca())) {
                Info.getInstancia().getVeiculos().remove(v);
                break;
            }
        }

        for (Veiculo v : Info.getInstancia().getUsuario().getVeiculos()) {
            if (v.getPlaca().equals(veiculo.getPlaca())) {
                Info.getInstancia().getUsuario().getVeiculos().remove(v);
                break;
            }
        }

        for (Veiculo v : Teste.veiculos) {
            if (v.getPlaca().equals(veiculo.getPlaca())) {
                Teste.veiculos.remove(v);
                break;
            }
        }
        //-------
    }

    public static void editar(Veiculo veiculo) {
        /**
         * AQUI DEVE SER IMPLEMENTADA A CONEXÃO COM O WS
         */

        //Código de teste
        for (int ind = 0; ind < Info.getInstancia().getVeiculos().size(); ind++) {
            if (Info.getInstancia().getVeiculos().get(ind).getPlaca().equals(veiculo.getPlaca())) {
                Info.getInstancia().getVeiculos().remove(ind);
                Info.getInstancia().getVeiculos().add(ind, veiculo);
                break;
            }
        }

        for (int ind = 0; ind < Info.getInstancia().getUsuario().getVeiculos().size(); ind++) {
            if (Info.getInstancia().getUsuario().getVeiculos().get(ind).getPlaca().equals(veiculo.getPlaca())) {
                Info.getInstancia().getUsuario().getVeiculos().remove(ind);
                Info.getInstancia().getUsuario().getVeiculos().add(ind, veiculo);
                break;
            }
        }

        for (int ind = 0; ind < Teste.veiculos.size(); ind++) {
            if (Teste.veiculos.get(ind).getPlaca().equals(veiculo.getPlaca())) {
                Teste.veiculos.remove(ind);
                Teste.veiculos.add(ind, veiculo);
                break;
            }
        }
        //-------
    }

    public static void agendar(Veiculo veiculo) {
        /**
         * AQUI DEVE SER IMPLEMENTADA A CONEXÃO COM O WS
         */

        //Código de teste
        for (int ind = 0; ind < Info.getInstancia().getVeiculos().size(); ind++) {
            if (Info.getInstancia().getVeiculos().get(ind).getPlaca().equals(veiculo.getPlaca())) {
                Info.getInstancia().getVeiculos().get(ind).setDataAgendamento(veiculo.getDataAgendamento());
                Info.getInstancia().getVeiculos().get(ind).setCodStatus(1);
                break;
            }
        }
        //-------
    }

    public static void iniciarLavagem(Veiculo veiculo) {
        /**
         * AQUI DEVE SER IMPLEMENTADA A CONEXÃO COM O WS
         */

        //Código de teste
        for (int ind = 0; ind < Info.getInstancia().getVeiculos().size(); ind++) {
            if (Info.getInstancia().getVeiculos().get(ind).getPlaca().equals(veiculo.getPlaca())) {
                Info.getInstancia().getVeiculos().get(ind).setCodStatus(2);
                break;
            }
        }
        //-------
    }

    public static void terminarLavagem(Veiculo veiculo) {
        /**
         * AQUI DEVE SER IMPLEMENTADA A CONEXÃO COM O WS
         */

        //Código de teste
        for (int ind = 0; ind < Info.getInstancia().getVeiculos().size(); ind++) {
            if (Info.getInstancia().getVeiculos().get(ind).getPlaca().equals(veiculo.getPlaca())) {
                Info.getInstancia().getVeiculos().get(ind).setCodStatus(3);
                break;
            }
        }
        //-------
    }

    public static void retirarDaFila(Veiculo veiculo) {
        /**
         * AQUI DEVE SER IMPLEMENTADA A CONEXÃO COM O WS
         */

        //Código de teste
        for (int ind = 0; ind < Info.getInstancia().getVeiculos().size(); ind++) {
            if (Info.getInstancia().getVeiculos().get(ind).getPlaca().equals(veiculo.getPlaca())) {
                Info.getInstancia().getVeiculos().get(ind).setCodStatus(0);
                break;
            }
        }
        //-------
    }

}
