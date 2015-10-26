package com.carclean.dao;

import com.carclean.beans.Usuario;
import com.carclean.beans.Veiculo;
import com.carclean.util.Info;
import com.carclean.util.Teste;

import junit.framework.Test;

import java.util.ArrayList;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */
public class UsuarioDAO {

    public static boolean logar(Usuario usuario) {
        /**
         * AQUI DEVE SER IMPLEMENTADA A CONEXÃO COM O WS
         */


        //Código de teste
        Usuario u = Teste.loginTESTE(usuario);
        if (u != null) {
            Info.getInstancia().setUsuario(u);
            return true;
        } else {
            return false;
        }
        //--------
    }

    public static void cadastrar(Usuario usuario) {
        /**
         * AQUI DEVE SER IMPLEMENTADA A CONEXÃO COM O WS
         */


        //Código de teste
        usuario.setId(Teste.ultIdUsuario+1);
        Teste.ultIdUsuario++;
        usuario.setVeiculos(new ArrayList<Veiculo>());
        Teste.usuarios.add(usuario);
        //--------
    }

}
