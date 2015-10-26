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
public class VeiculosAdapter  extends BaseAdapter {

    private ArrayList<Veiculo> veiculos;
    private Principal context;

    private TextView tvMarca, tvModelo, tvCor, tvPlaca, tvStatus;
    private Button btAgendar, btEditar, btExcluir;

    private Date dataAuxiliar;

    public VeiculosAdapter(ArrayList<Veiculo> veiculos, Principal context) {
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
        vi = LayoutInflater.from(context).inflate(R.layout.item_veiculo, vig, false);


        tvCor = (TextView) vi.findViewById(R.id.tv_cor);
        tvMarca = (TextView) vi.findViewById(R.id.tv_marca);
        tvModelo = (TextView) vi.findViewById(R.id.tv_modelo);
        tvPlaca = (TextView) vi.findViewById(R.id.tv_placa);
        tvStatus = (TextView) vi.findViewById(R.id.tv_status);

        tvCor.setText(context.getString(R.string.tituloCor)+veiculos.get(position).getCor());
        tvMarca.setText(context.getString(R.string.tituloMarca)+veiculos.get(position).getMarca());
        tvModelo.setText(context.getString(R.string.tituloModelo)+veiculos.get(position).getModelo());
        tvPlaca.setText(context.getString(R.string.tituloPlaca)+veiculos.get(position).getPlaca());
        if (veiculos.get(position).getCodStatus() == 1) {
            tvStatus.setText(context.getString(R.string.tituloStatus)+"\n"+veiculos.get(position).getStatus());
        } else {
            tvStatus.setText(context.getString(R.string.tituloStatus)+veiculos.get(position).getStatus());
        }


        btAgendar = (Button) vi.findViewById(R.id.btAgendar);
        btEditar = (Button) vi.findViewById(R.id.btEditar);
        btExcluir = (Button) vi.findViewById(R.id.btExcluir);

        btAgendar.setTag(position);
        btEditar.setTag(position);
        btExcluir.setTag(position);

        dataAuxiliar = new Date();

        btAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicao = Integer.parseInt(v.getTag().toString());
                dialogAgendarData(posicao);
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicao = Integer.parseInt(v.getTag().toString());
                Intent it = new Intent(context, EditarVeiculo.class);
                it.putExtra("posicao", posicao);
                context.startActivity(it);
            }
        });

        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int posicao = Integer.parseInt(v.getTag().toString());

                DialogsUtil du = new DialogsUtil(context);
                du.setOnDialogSimNaoClicado(new DialogsUtil.OnDialogSimNaoClicado() {
                    @Override
                    public void onSim() {
                        VeiculoDAO.excluir(veiculos.get(posicao));
                        context.montaLista();
                    }

                    @Override
                    public void onNao() {
                        //Nada a fazer
                    }
                });
                du.mostrarDialogSimNao("Atenção!", "Deseja realmente excluir este veículo?");
            }
        });



        return vi;
    }


    public void dialogAgendarData(int posicao) {

        final View dialogView = View.inflate(context, R.layout.dialog_datepicker,
                null);
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        Button btSetar = (Button) dialogView.findViewById(R.id.date_time_set);
        btSetar.setTag(posicao);

        btSetar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int posicao = Integer.parseInt(v.getTag().toString());

                        DatePicker datePicker = (DatePicker) dialogView
                                .findViewById(R.id.date_picker);

                        Calendar calendar = new GregorianCalendar(datePicker
                                .getYear(), datePicker.getMonth(), datePicker
                                .getDayOfMonth());

                        dataAuxiliar = calendar.getTime();

                        dialogAgendarHora(posicao);
                        alertDialog.dismiss();
                    }
                });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }

    public void dialogAgendarHora(int posicao) {
        final View dialogView = View.inflate(context, R.layout.dialog_time, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        Button btSetar = (Button) dialogView.findViewById(R.id.date_time_set);
        btSetar.setTag(posicao);

        btSetar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int posicao = Integer.parseInt(v.getTag().toString());

                        TimePicker timePicker = (TimePicker) dialogView
                                .findViewById(R.id.time_picker);

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY,
                                timePicker.getCurrentHour());
                        calendar.set(Calendar.MINUTE,
                                timePicker.getCurrentMinute());

                        dataAuxiliar.setHours(calendar.get(Calendar.HOUR_OF_DAY));
                        dataAuxiliar.setMinutes(calendar.get(Calendar.MINUTE));



                        veiculos.get(posicao).setDataAgendamento(dataAuxiliar);
                        veiculos.get(posicao).setCodStatus(1);

                        VeiculoDAO.agendar(veiculos.get(posicao));

                        context.montaLista();

                        alertDialog.dismiss();
                    }
                });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }

}
