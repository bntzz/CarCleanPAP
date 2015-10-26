package com.carclean.util;

import com.carclean.beans.Usuario;
import com.carclean.beans.Veiculo;

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

        Usuario u1 = new Usuario();
        u1.setNome("Administrador do Sistema");
        u1.setSenha("1234");
        u1.setEmail("admin@carclean.com");
        u1.setAdmin(true);
        u1.setId(1);
        u1.setVeiculos(new ArrayList<Veiculo>());
        usuarios.add(u1);

        Usuario u2 = new Usuario();
        u2.setNome("Fernando Benitez");
        u2.setSenha("1234");
        u2.setEmail("fernandohbenitez@hotmail.com");
        u2.setAdmin(false);
        u2.setId(2);
        u2.setVeiculos(new ArrayList<Veiculo>());
        usuarios.add(u2);

        Veiculo v1 = new Veiculo();
        v1.setDataAgendamento(Calendar.getInstance().getTime());
        v1.setCor("Preto");
        v1.setId(1);
        v1.setMarca("Chevrolet");
        v1.setModelo("Astra");
        v1.setPlaca("GND-4670");
        v1.setProprietario(u2);
        v1.setCodStatus(1);
        u2.getVeiculos().add(v1);
        veiculos.add(v1);

        Veiculo v2 = new Veiculo();
        v2.setCodStatus(2);
        v2.setCor("Vermelho");
        v2.setId(2);
        v2.setMarca("Ford");
        v2.setModelo("Ka");
        v2.setPlaca("CGA-1891");
        v2.setProprietario(u2);
        u2.getVeiculos().add(v2);
        veiculos.add(v2);

        Veiculo v3 = new Veiculo();
        v3.setCodStatus(3);
        v3.setCor("Prata");
        v3.setId(3);
        v3.setMarca("Volkswagen");
        v3.setModelo("Golf");
        v3.setPlaca("YKK-5846");
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
