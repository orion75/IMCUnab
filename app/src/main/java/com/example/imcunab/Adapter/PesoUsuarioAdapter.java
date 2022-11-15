package com.example.imcunab.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imcunab.R;

import java.util.List;

public class PesoUsuarioAdapter extends RecyclerView.Adapter<PesoUsuarioAdapter.ViewHolder> {
    List<PesoUsuario> pesoUsuarioList;

    public PesoUsuarioAdapter(List<PesoUsuario> pesoUsuarioList/*, Context context*/) {
        this.pesoUsuarioList = pesoUsuarioList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_usuario_pesos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PesoUsuario item = pesoUsuarioList.get(position);
        holder.txtFecha.setText(item.getFecha());
        holder.txtDatos.setText("Estatura: " + item.getAltura() +
                " Peso: " + item.getPeso());
        holder.txtimc.setText(item.getImc());
    }

    @Override
    public int getItemCount() {
        return pesoUsuarioList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView txtFecha;
        private TextView txtDatos;
        private TextView txtimc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.tup_imageLabel);
            txtFecha = itemView.findViewById(R.id.tup_fechaLabel);
            txtDatos = itemView.findViewById(R.id.tup_datosLabel);
            txtimc = itemView.findViewById(R.id.tup_imcLabel);
        }
    }
}
