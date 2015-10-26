package com.carclean.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.carclean.beans.Veiculo;
import com.carclean.carclean.EditarVeiculo;
import com.carclean.carclean.Principal;
import com.carclean.carclean.PrincipalAdmin;
import com.carclean.carclean.R;
import com.carclean.dao.VeiculoDAO;
import com.carclean.util.DialogsUtil;
import com.carclean.util.Info;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Fernando Benitez on 10/10/2015.
 */
public class VeiculosAdminAdapter extends BaseAdapter {

    private ArrayList<Veiculo> veiculos;
    private PrincipalAdmin context;

    private TextView tvMarca, tvModelo, tvCor, tvPlaca, tvStatus, tvProprietario;
    private Button btIniciar, btTerminar, btRetirar;


    public VeiculosAdminAdapter(ArrayList<Veiculo> veiculos, PrincipalAdmin context) {
        this.veiculos = veiculos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return veiculos.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View vi, ViewGroup vig) {
        vi = LayoutInflater.from(context).inflate(R.layout.item_veiculo_admin, vig, false);


        tvCor = (TextView) vi.findViewById(R.id.tv_cor);
        tvMarca = (TextView) vi.findViewById(R.id.tv_marca);
        tvModelo = (TextView) vi.findViewById(R.id.tv_modelo);
        tvPlaca = (TextView) vi.findViewById(R.id.tv_placa);
        tvStatus = (TextView) vi.findViewById(R.id.tv_status);
        tvProprietario = (TextView) vi.findViewById(R.id.tv_proprietario);

        tvCor.setText(context.getString(R.string.tituloCor)+veiculos.get(position).getCor());
        tvMarca.setText(context.getString(R.string.tituloMarca)+veiculos.get(position).getMarca());
        tvModelo.setText(context.getString(R.string.tituloModelo)+veiculos.get(position).getModelo());
        tvPlaca.setText(context.getString(R.string.tituloPlaca)+veiculos.get(position).getPlaca());
        if (veiculos.get(position).getCodStatus() == 1) {
            tvStatus.setText(context.getString(R.string.tituloStatus)+"\n"+veiculos.get(position).getStatus());
        } else {
            tvStatus.setText(context.getString(R.string.tituloStatus)+veiculos.get(position).getStatus());
        }
        tvProprietario.setText(context.getString(R.string.tituloProprietario)+"\n"+veiculos.get(position).getProprietario().getNome());

        btIniciar = (Button) vi.findViewById(R.id.btIniciar);
        btTerminar = (Button) vi.findViewById(R.id.btTerminar);
        btRetirar = (Button) vi.findViewById(R.id.btRetirar);

        btIniciar.setTag(position);
        btTerminar.setTag(position);
        btRetirar.setTag(position);


        switch (veiculos.get(position).getCodStatus()) {
            case 1:
                btIniciar.setVisibility(View.VISIBLE);
                btTerminar.setVisibility(View.GONE);
                btRetirar.setVisibility(View.GONE);
                break;
            case 2:
                btIniciar.setVisibility(View.GONE);
                btTerminar.setVisibility(View.VISIBLE);
                btRetirar.setVisibility(View.GONE);
                break;
            case 3:
                btIniciar.setVisibility(View.GONE);
                btTerminar.setVisibility(View.GONE);
                btRetirar.setVisibility(View.VISIBLE);
                break;
        }


        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicao = Integer.parseInt(v.getTag().toString());
                VeiculoDAO.iniciarLavagem(veiculos.get(posicao));
                context.montaLista();
            }
        });


        btTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicao = Integer.parseInt(v.getTag().toString());
                VeiculoDAO.terminarLavagem(veiculos.get(posicao));
                context.montaLista();
            }
        });

        btRetirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicao = Integer.parseInt(v.getTag().toString());
                VeiculoDAO.retirarDaFila(veiculos.get(posicao));
                context.montaLista();
            }
        });


        return vi;
    }




}
