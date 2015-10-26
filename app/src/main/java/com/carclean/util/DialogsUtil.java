package com.carclean.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogsUtil {

	private OnDialogSimNaoClicado onDialogSimNaoClicado;
	private OnDialogOkClicado onDialogOkClicado;
	private Context context;
	
	private ProgressDialog dialog;

	public DialogsUtil(Context context) {
		this.context = context;
	}

	public void mostrarDialogSimNao(String titulo, String mensagem) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titulo);
		builder.setMessage(mensagem);
		builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				onDialogSimNaoClicado.onSim();
			}
		});
		builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				onDialogSimNaoClicado.onNao	();
			}
		});
		AlertDialog alerta = builder.create();
		alerta.show();
	}

	public void mostrarDialogOk(String titulo, String mensagem) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titulo);
		builder.setMessage(mensagem);
		builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				onDialogOkClicado.onOk();
			}
		});
		AlertDialog alerta = builder.create();
		alerta.show();
	}

	public interface OnDialogSimNaoClicado {
		public void onSim();

		public void onNao();
	}

	public interface OnDialogOkClicado {
		public void onOk();
	}

	public void setOnDialogSimNaoClicado(
			OnDialogSimNaoClicado onDialogSimNaoClicado) {
		this.onDialogSimNaoClicado = onDialogSimNaoClicado;
	}

	public void setOnDialogOkClicado(OnDialogOkClicado onDialogOkClicado) {
		this.onDialogOkClicado = onDialogOkClicado;
	}
	
	public void mostrarCarregando() {
		dialog = ProgressDialog.show(context, "", 
                "Carregando...", true);
		dialog.show();
	}
	
	public void fecharCarregando() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

}
