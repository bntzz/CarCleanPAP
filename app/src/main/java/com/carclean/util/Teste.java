package com.carclean.util;

import com.carclean.beans.Usuario;
import com.carclean.beans.Veiculo;
import com.carclean.dao.UsuarioService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */

public class Teste {

    /**
     * Essa classe só serve pra gerar dados de teste, simulando a conexão com WS
     */

    public static int ultIdUsuario = 2;

    public static ArrayList<Veiculo> veiculos;
    public static ArrayList<Usuario> usuarios;

    public static void preencherDadosIniciaisTESTE() {
        usuarios = new ArrayList<>();
        veiculos = new ArrayList<>();

        Usuario u1 = new Usuario("Administrador do Sistema","1234","admin@carclean.com");
        u1.setAdmin(true);
        u1.setId(1);
        u1.setVeiculos(new ArrayList<Veiculo>());
        usuarios.add(u1);

        Usuario u2 = new Usuario("Fernando Benitez","1234","fernandohbenitez@hotmail.com");
        u2.setAdmin(false);
        u2.setId(2);
        u2.setVeiculos(new ArrayList<Veiculo>());
        usuarios.add(u2);

        Veiculo v1 = new Veiculo("Chevrolet","Astra","Preto","GND-4670");
        v1.setDataAgendamento(Calendar.getInstance().getTime());
        v1.setId(1);
        v1.setProprietario(u2);
        v1.setCodStatus(1);
        u2.getVeiculos().add(v1);
        veiculos.add(v1);

        Veiculo v2 = new Veiculo("Ford","Ka","Vermelho","CGA-1891");
        v2.setCodStatus(2);
        v2.setId(2);
        v2.setProprietario(u2);
        u2.getVeiculos().add(v2);
        veiculos.add(v2);

        Veiculo v3 = new Veiculo("Volkswagen","Golf","Prata","YKK-5846");
        v3.setCodStatus(3);
        v3.setId(3);
        v3.setProprietario(u2);
        u2.getVeiculos().add(v3);
        veiculos.add(v3);



    }

    public static ArrayList<Veiculo> retornaVeiculosUsuarioTESTE() {
        return Info.getInstancia().getUsuario().getVeiculos();
    }

    public static ArrayList<Veiculo> geraListaVeiculosFilaTESTE() {
        ArrayList<Veiculo> fila = new ArrayList<>();

        for (Veiculo v : veiculos) {
            if (v.getCodStatus() != 0) {
                fila.add(v);
            }
        }

        return fila;
    }

    public static Usuario loginTESTE(Usuario usuario) {
        Usuario uRetorno = null;
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(usuario.getEmail()) && u.getSenha().equals(usuario.getSenha())) {
                uRetorno = u;
                break;
            }
        }
        return uRetorno;
    }

}
