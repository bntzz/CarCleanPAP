package com.carclean.beans;

import com.carclean.util.Info;
import com.carclean.util.Teste;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */

public class Veiculo {

    private int id;
    private String marca, modelo, cor, placa;
    private String status;
    private int codStatus;
    private Usuario proprietario;
    private Date dataAgendamento;

    public Veiculo(String marca, String modelo, String cor, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(int codStatus) {
        switch (codStatus) {
            case 0:
                status = "-";
                break;
            case 1:
                status = "Agendado para "+getDataFormatada();
                break;
            case 2:
                status = "Em processo de lavagem.";
                break;
            case 3:
                status = "Veículo pronto.";
                break;
        }

        this.codStatus = codStatus;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getStatus() {
        return status;
    }

    public String getDataFormatada() {
        SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy HH:mm");
        return format.format(getDataAgendamento());
    }



}
