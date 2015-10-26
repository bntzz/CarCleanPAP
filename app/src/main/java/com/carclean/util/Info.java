package com.carclean.util;

import com.carclean.beans.Usuario;
import com.carclean.beans.Veiculo;

import java.util.ArrayList;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */

public class Info {

    /**
     * Essa classe é um Singleton contendo as informações do App em uma única instancia
     */

    private static Info info;

    public static Info getInstancia() {
        if (info == null) {
            info = new Info();
        }
        return info;
    }

    public static void limpar() {
        info = null;
    }


    private ArrayList<Veiculo> veiculos;
    private Usuario usuario;

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
